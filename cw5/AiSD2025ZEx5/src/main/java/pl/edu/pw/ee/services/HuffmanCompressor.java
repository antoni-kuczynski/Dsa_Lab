package pl.edu.pw.ee.services;

import pl.edu.pw.ee.binary.CompressedHeader;
import pl.edu.pw.ee.binary.io.BitWriter;
import pl.edu.pw.ee.struct.huffman.HuffmanKeyLengthMap;
import pl.edu.pw.ee.struct.huffman.HuffmanCodesMap;
import pl.edu.pw.ee.struct.huffman.HuffmanPriorityQueue;
import pl.edu.pw.ee.struct.huffman.HuffmanTree;
import pl.edu.pw.ee.logging.ColorTag;
import pl.edu.pw.ee.logging.LoggingUtils;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

public class HuffmanCompressor implements HuffmanService {
    private HuffmanKeyLengthMap byteOccurenceMap;
    private HuffmanPriorityQueue huffmanNodeQueue;

    private void writeHuffmanTreeString(String tree, BitWriter writer) throws IOException {
        writer.writeBits(tree);
    }

    @Override
    public void apply(File inputFile, File outputFile, int maxBytesPerKey, int bufSize) throws IOException {
        System.out.println("=================================================");
        System.out.println("             STARTING COMPRESSION                ");
        System.out.println("=================================================");
        long fileSize = inputFile.length();
        LoggingUtils.printInfo("File size is " + fileSize + " bytes");
        LoggingUtils.printInfo("Bytes per key: " + maxBytesPerKey);
        LoggingUtils.printInfo("Compression in progress...");
        LoggingUtils.printProgressBar(0);

        long t0 = System.nanoTime();
        byteOccurenceMap = new HuffmanKeyLengthMap(
                inputFile,
                fileSize,
                maxBytesPerKey,
                bufSize
        );
        huffmanNodeQueue = new HuffmanPriorityQueue(byteOccurenceMap);
        HuffmanTree tree = HuffmanTree.buildFromPriorityQueue(huffmanNodeQueue);

        HuffmanCodesMap map = HuffmanCodesMap.getInstanceFromTree(tree);

        long tComp = System.nanoTime() - t0;
        LoggingUtils.printInfo("Compression took " + tComp + " ns");


        FileChannel inputFileChannel = FileChannel.open(
                inputFile.toPath(),
                StandardOpenOption.CREATE,
                StandardOpenOption.WRITE,
                StandardOpenOption.READ
        );

        FileChannel outputFileChannel = FileChannel.open(
                outputFile.toPath(),
                StandardOpenOption.CREATE,
                StandardOpenOption.WRITE,
                StandardOpenOption.READ
        );

        LoggingUtils.printInfo("Writing to output file:");

        BufferedInputStream in = new BufferedInputStream(Channels.newInputStream(inputFileChannel), bufSize);
        BufferedOutputStream out = new BufferedOutputStream(Channels.newOutputStream(outputFileChannel), bufSize);
        BitWriter writer = new BitWriter(out, bufSize);

        //HEADER
        int numberOfBitsRequiredToStoreMaxBytesPerKey;
        if (maxBytesPerKey == 1) {
            numberOfBitsRequiredToStoreMaxBytesPerKey = 0; //no need to store this - all keys are always equal length(1byte)
        } else {
            numberOfBitsRequiredToStoreMaxBytesPerKey = 32 - Integer.numberOfLeadingZeros(maxBytesPerKey);
        }

        CompressedHeader header = new CompressedHeader.Builder().
                setAmountOfAlignBitsAL(0b000).
                setTreeKeyBitCount(numberOfBitsRequiredToStoreMaxBytesPerKey & 0b11111).
        build();
        header.write(writer);

        //TREE
        String inorderTreeString = tree.asPreOrderString(numberOfBitsRequiredToStoreMaxBytesPerKey);
        writeHuffmanTreeString(inorderTreeString, writer);

        //DATA
        long previousTime = System.nanoTime();
        long amountOfReadBytes = 0L;
        int lastProgressWrite = 0;
        int progressWriteThreshold = LoggingUtils.prepareProgressPrintThreshold(fileSize);
        LoggingUtils.printProgressBar(0);

        byte[] chain = new byte[maxBytesPerKey];
        int codeIndex = 0;

        byte[] buf = new byte[bufSize];
        int bytesRead;
        while ((bytesRead = in.read(buf)) > 0) {
            int ptr = 0;
            while (ptr < bytesRead) {
                int readByte = buf[ptr++] & 0xFF;
                amountOfReadBytes++;
                chain[codeIndex++] = (byte) readByte;

                if (codeIndex == maxBytesPerKey) {
                    String value = map.get(chain);
                    if (value == null) {
                        throw new IllegalStateException("No Huffman code for key");
                    }
                    writer.writeBits(value);
                    codeIndex = 0;
                    chain = new byte[maxBytesPerKey];
                }

                if (lastProgressWrite < progressWriteThreshold) {
                    lastProgressWrite++;
                } else {
                    float progressValue = ((float) amountOfReadBytes / fileSize) * 100;
                    LoggingUtils.printProgressBar(progressValue);
                    lastProgressWrite = 0;
                }
            }
        }

        if (codeIndex > 0) {
            String lastValue = map.get(Arrays.copyOf(chain, codeIndex));
            if (lastValue == null) {
                throw new IllegalStateException("No Huffman code for last key");
            }
            writer.writeBits(lastValue);
        }
        writer.flush();

        int amountOfAlignBits = (int) (writer.getBitsConsumed() % 8);
        if (amountOfAlignBits != 0) {
            amountOfAlignBits = 8 - amountOfAlignBits;
        }

        byte remainderOfTheByte =  8 - STORED_ALIGN_BITS_BIT_LENGTH;

        try (FileChannel channelForHeaderWrite = FileChannel.open(
                outputFile.toPath(),
                StandardOpenOption.WRITE)) {

            channelForHeaderWrite.position(HEADER_MEM_POSITION);

            ByteBuffer headerBuf = ByteBuffer.allocate(2);
            headerBuf.put((byte) (header.getBinaryRepresenation() | (amountOfAlignBits << remainderOfTheByte)));
            headerBuf.flip();

            int writtenHeaderBytes = channelForHeaderWrite.write(headerBuf);
            if (writtenHeaderBytes <= 0) {
                throw new IOException("Failed to write header bits!");
            }

        } catch (Exception e) {
            LoggingUtils.printError("Failed to write header!");
            in.close();
            writer.close();
            return;
        }

        in.close();
        writer.close();

        long time = System.nanoTime() - previousTime;
        LoggingUtils.printProgressBar(100, ColorTag.ANSI_GREEN);
        System.out.println();
        LoggingUtils.printInfo("Writing to file took: " + time);
        LoggingUtils.printSuccess("Compression finished");
        System.out.println(ColorTag.ANSI_RESET);
    }
}


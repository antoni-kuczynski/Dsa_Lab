package pl.edu.pw.ee.services;

import pl.edu.pw.ee.binary.CompressedHeader;
import pl.edu.pw.ee.binary.io.BitReader;
import pl.edu.pw.ee.struct.huffman.HuffmanNode;
import pl.edu.pw.ee.struct.huffman.HuffmanTree;
import pl.edu.pw.ee.logging.ColorTag;
import pl.edu.pw.ee.logging.LoggingUtils;

import java.io.*;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.file.StandardOpenOption;

public class HuffmanDecompressor implements HuffmanService {

    @Override
    public void apply(File inputFile, File outputFile, int maxBytesPerKey, int bufSize) throws IOException {
        System.out.println("=================================================");
        System.out.println("            STARTING DECOMPRESSION               ");
        System.out.println("=================================================");

        long fileSize = inputFile.length();
        LoggingUtils.printInfo("Compressed file size is " + fileSize + " bytes");

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

        BufferedInputStream inputStream = new BufferedInputStream(Channels.newInputStream(inputFileChannel), bufSize);
        BufferedOutputStream outputStream = new BufferedOutputStream(Channels.newOutputStream(outputFileChannel), bufSize);
        BitReader reader = new BitReader(inputStream, bufSize);


        CompressedHeader header = CompressedHeader.parseFromBinary(
                reader.readBits(8)
        );

        LoggingUtils.printInfo("Decompression in progress...");
        LoggingUtils.printProgressBar(0);

        HuffmanTree tree = HuffmanTree.buildFromInOrderInFile(reader, header.getKeyLengthValueBitCount());

        HuffmanNode root = tree.getRoot();
        HuffmanNode current = root;

        // --- DATA ---
        long totalFileBits = fileSize << 3;
        long consumedBeforeData = reader.getBitsConsumed();
        long dataBits = totalFileBits - consumedBeforeData - header.getAmountOfAlignBits();
        long bitsRead = 0;

        float progressValue;
        int progressThreshold = LoggingUtils.prepareProgressPrintThreshold(dataBits);
        int lastProgressPrint = 0;

        while (bitsRead < dataBits) {
            int bit = reader.readBit();
            if (bit == -1) break;
            bitsRead++;


            
            if (bit == 0 && !current.isLeaf()) {
                current = current.getLeft();
            } else if (bit == 1 && !current.isLeaf()){
                current = current.getRight();
            }
            
            if (current == null) {
                throw new IOException("Traversal reached null");
            }

            if (current.isLeaf()) {
                byte[] key = current.getKey();
                outputStream.write(key);
                current = root;
            }

            lastProgressPrint++;
            if (lastProgressPrint >= progressThreshold) {
                progressValue = ((float) bitsRead / dataBits) * 100;
                LoggingUtils.printProgressBar(progressValue);
                lastProgressPrint = 0;
            }
        }

        outputStream.flush();

        LoggingUtils.printProgressBar(100, ColorTag.ANSI_GREEN);
        System.out.println();
        LoggingUtils.printSuccess("Decompression finished");
        System.out.println(ColorTag.ANSI_RESET);
    }
}

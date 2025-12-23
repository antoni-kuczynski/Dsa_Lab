package pl.edu.pw.ee.struct.huffman;

import pl.edu.pw.ee.struct.map.RbtMap;
import pl.edu.pw.ee.logging.ColorTag;
import pl.edu.pw.ee.logging.LoggingUtils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

public class HuffmanKeyLengthMap extends RbtMap<Long> {

    public HuffmanKeyLengthMap(File dataFile, long fileSizeBytes, int maxChainLength, int BUF_SIZE) throws IOException {
        int lastProgressPrint = 0;
        int progressPrintThreshold = LoggingUtils.prepareProgressPrintThreshold(fileSizeBytes);
        LoggingUtils.printProgressBar(0);

        BufferedInputStream in = new BufferedInputStream(new FileInputStream(dataFile), BUF_SIZE);
        long amountOfReadBytes = 0L;
        int readByte;
        byte[] key1 = new byte[maxChainLength];
        int keyIndex = 0;
        Long count;
        float progressValue;
        byte[] buf = new byte[BUF_SIZE];
        int bufPtr;
        int readBytes;
        while ((readBytes = in.read(buf)) > 0) {
            bufPtr = 0;
            while (bufPtr < readBytes) {
                readByte = buf[bufPtr++];
                amountOfReadBytes++;
                key1[keyIndex++] = (byte) readByte;

                if (keyIndex < maxChainLength) {
                    continue;
                }
                keyIndex = 0;
                count = this.get(key1);
                if (count != null) {
                    this.put(key1, count + 1);
                } else {
                    this.put(key1, 1L);
                }
                key1 = new byte[maxChainLength];

                if (lastProgressPrint < progressPrintThreshold) {
                    lastProgressPrint++;
                    continue;
                }

                progressValue = ((float) amountOfReadBytes / fileSizeBytes) * 100;
                LoggingUtils.printProgressBar(progressValue);

                lastProgressPrint = 0;
            }
        }
        in.close();

        if (keyIndex > 0) {
            byte[] lastKey = Arrays.copyOf(key1, keyIndex);

            Long countOfLastKey = this.get(lastKey);
            if (countOfLastKey != null) {
                this.put(lastKey, countOfLastKey + 1);
            } else {
                this.put(lastKey, 1L);
            }
        }

        LoggingUtils.printProgressBar(100, ColorTag.ANSI_GREEN);
        System.out.print("\n");
    }
}

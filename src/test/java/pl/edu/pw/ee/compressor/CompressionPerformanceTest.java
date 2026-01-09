package pl.edu.pw.ee.compressor;

import pl.edu.pw.ee.services.HuffmanCompressor;
import pl.edu.pw.ee.services.HuffmanDecompressor;
import org.junit.jupiter.api.*;

import java.io.File;

import static pl.edu.pw.ee.compressor.TestCase.compareMD5;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CompressionPerformanceTest {

    private static final int MAX_CHAIN_LENGTH = 16;
    private static final int BUF_SIZE = 1 << 20;

    private static TestCase baseTest;

    @BeforeAll
    static void setup() {
        baseTest = new TestCase(
                "emoji.txt",
                0,
                "Huffman performance test"
        );

        System.out.printf(
                "%-6s | %-12s | %-14s | %-10s%n",
                "LEN", "COMPRESS(ms)", "DECOMPRESS(ms)", "OK"
        );
        System.out.println("-----------------------------------------------------");
    }

    @Test
    @Order(1)
    void compressionDecompressionPerformanceTest() throws Exception {
        for (int chainLength = 1; chainLength <= MAX_CHAIN_LENGTH; chainLength *= 2) {
            runSingleTest(chainLength);
        }
    }

    private static void runSingleTest(int chainLength) throws Exception {

        TestCase tc = new TestCase(
                baseTest.getInputFileName(),
                chainLength,
                baseTest.toString()
        );

        File input = tc.input();
        File compressed = tc.compressed();
        File decompressed = tc.decompressed();

        compressed.getParentFile().mkdirs();
        decompressed.getParentFile().mkdirs();

        HuffmanCompressor compressor = new HuffmanCompressor();
        HuffmanDecompressor decompressor = new HuffmanDecompressor();

        long t1 = System.nanoTime();
        compressor.apply(input, compressed, chainLength, BUF_SIZE);
        long t2 = System.nanoTime();

        long t3 = System.nanoTime();
        decompressor.apply(compressed, decompressed, chainLength, BUF_SIZE);
        long t4 = System.nanoTime();

        long compressMs = (t2 - t1) / 1_000_000;
        long decompressMs = (t4 - t3) / 1_000_000;

        boolean ok = compareMD5(input, decompressed);

        System.out.printf(
                "%-6d | %-12d | %-14d | %-10s%n",
                chainLength,
                compressMs,
                decompressMs,
                ok ? "OK" : "FAIL"
        );

        assertTrue(
                ok,
                "Decompressed file differs from original for chainLength=" + chainLength
        );
    }
}

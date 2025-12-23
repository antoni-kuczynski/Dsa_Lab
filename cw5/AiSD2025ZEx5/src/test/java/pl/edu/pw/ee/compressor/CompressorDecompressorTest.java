package pl.edu.pw.ee.compressor;

import pl.edu.pw.ee.OptionValidator;
import pl.edu.pw.ee.services.HuffmanService;
import pl.edu.pw.ee.services.HuffmanCompressor;
import pl.edu.pw.ee.services.HuffmanDecompressor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.stream.Stream;

import static pl.edu.pw.ee.compressor.TestCase.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CompressorDecompressorTest {
    private static final int BUF_SIZE = 1 << 20;

    private static Stream<TestCase> testCases() {
        return Stream.of(
                new TestCase("molly.txt", 1, "Odd byte count, 1 byte per key"),
                new TestCase("abcd.txt", 1, "Even byte count, 1 byte per key"),
                new TestCase("molly.txt", 2, "Odd byte count, even(2) bytes per key"),
                new TestCase("abcd.txt", 2, "Even byte count, even(2) bytes per key"),
                new TestCase("emoji.txt",1, "Non 8bit length chars, 1 byte per key"),
                new TestCase("molly.txt", 10, "Byte length larger than byte count"),
                new TestCase("molly.txt", 100_000_000, "Byte length larger than 8bit")
        );
    }

    @BeforeAll
    public static void setUp() {
        deleteAllFilesInDir(COMPRESSED_ROOT);
        deleteAllFilesInDir(DECOMPRESSED_ROOT);
    }

    @ParameterizedTest(name = "Compress & Decompress: {0}")
    @MethodSource("testCases")
    void should_CompressAndDecompressProperly(TestCase tc) {
        compressAndDecompressFile(
                tc.input(),
                tc.compressed(),
                tc.decompressed(),
                tc.getChainLength()
        );

        assertTrue(compareMD5(tc.input(), tc.decompressed()));
    }

    private static void compressAndDecompressFile(
            File input,
            File compressed,
            File decompressed,
            int chainLength
    ) {
        HuffmanService compressor = new HuffmanCompressor();
        HuffmanService decompressor = new HuffmanDecompressor();

        try {
            compressor.apply(input, compressed, chainLength, BUF_SIZE);
            decompressor.apply(compressed, decompressed, chainLength, BUF_SIZE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void deleteAllFilesInDir(String dirPath) {
        File dir = new File(dirPath);
        if (!dir.exists()) return;

        File[] files = dir.listFiles();
        if (files == null) return;

        for (File f : files) {
            f.delete();
        }
    }
}

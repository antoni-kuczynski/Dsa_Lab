package pl.edu.pw.ee.compressor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

class TestCase {

    private final String inputFileName;
    private final int chainLength;
    private final String testName;

    protected static final String ORIGINAL_ROOT = "test_files/original/";
    protected static final String COMPRESSED_ROOT = "test_files/compressed/";
    protected static final String DECOMPRESSED_ROOT = "test_files/decompressed/";

    public TestCase(String inputFileName, int chainLength, String testName) {
        this.inputFileName = inputFileName;
        this.chainLength = chainLength;
        this.testName = testName;
    }

    public String getInputFileName() {
        return inputFileName;
    }

    public int getChainLength() {
        return chainLength;
    }

    public File input() {
        return new File(ORIGINAL_ROOT + inputFileName);
    }

    public File compressed() {
        return new File(COMPRESSED_ROOT + chainLength + "_" + inputFileName);
    }

    public File decompressed() {
        return new File(DECOMPRESSED_ROOT + chainLength + "_" + inputFileName);
    }

    @Override
    public String toString() {
        return testName;
    }

    private static String getMD5(File file) throws IOException, NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] buffer = new byte[8192];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                md.update(buffer, 0, bytesRead);
            }
        }

        byte[] digest = md.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    protected static boolean compareMD5(File file1, File file2) {
        try {
            return getMD5(file1).equals(getMD5(file2));
        } catch (Exception e) {
            throw new RuntimeException("Cannot compare file hashes!", e);
        }
    }
}

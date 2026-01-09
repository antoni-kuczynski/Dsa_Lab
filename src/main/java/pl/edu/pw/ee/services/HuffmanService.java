package pl.edu.pw.ee.services;

import java.io.File;
import java.io.IOException;

public interface HuffmanService {
    int STORED_ALIGN_BITS_BIT_LENGTH = 3;
    long HEADER_MEM_POSITION = 0L;
    int MAX_ARRAY_SIZE = 100_000_000;

    void apply(File inputFile, File outputFile, int maxBytesPerKey, int bufSize) throws IOException;

}

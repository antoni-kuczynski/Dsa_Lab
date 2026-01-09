package pl.edu.pw.ee.binary.io;

import java.io.IOException;
import java.io.InputStream;

public class BitReader extends AbstractBitIO {

    private final InputStream in;
    private int bufferLimit = 0;

    private static final int EOF = -1;

    public BitReader(InputStream in, int bufferSize) {
        super(bufferSize);
        this.in = in;
        bitPos = 8;
    }

    private boolean fillByte() throws IOException {
        if (bufferPos >= bufferLimit) {
            bufferLimit = in.read(buffer);
            bufferPos = 0;

            if (bufferLimit == EOF) {
                return false;
            }
        }

        currentByte = buffer[bufferPos++] & 0xFF;
        bitPos = 0;
        return true;
    }

    public int readBit() throws IOException {
        if (bitPos == 8) {
            if (!fillByte()) {
                return EOF;
            }
        }

        int bit = (currentByte >> (7 - bitPos)) & 1;
        bitPos++;
        bitsConsumed++;

        return bit;
    }

    public int readBits(int n) throws IOException {
        int v = 0;
        for (int i = 0; i < n; i++) {
            v = (v << 1) | readBit();
        }
        return v;
    }

    @Override
    protected void flushBuffer() {}

    @Override
    public void close() throws IOException {
        in.close();
    }
}

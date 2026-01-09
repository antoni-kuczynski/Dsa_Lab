package pl.edu.pw.ee.binary.io;

import java.io.Closeable;
import java.io.IOException;

public abstract class AbstractBitIO implements Closeable {
    protected final byte[] buffer;
    protected int bufferPos = 0;
    protected long bitsConsumed = 0;

    protected int currentByte = 0;
    protected int bitPos = 0;

    protected AbstractBitIO(int bufferSize) {
        this.buffer = new byte[bufferSize];
    }

    protected abstract void flushBuffer() throws IOException;

    public long getBitsConsumed() {
        return bitsConsumed;
    }

    protected final void resetCurrentByte() {
        currentByte = 0;
        bitPos = 0;
    }

    public static int compareByteArrays(byte[] a, byte[] b) {
        int minLen = Math.min(a.length, b.length);

        for (int i = 0; i < minLen; i++) {
            int diff = (a[i] & 0xFF) - (b[i] & 0xFF);
            if (diff != 0) {
                return diff;
            }
        }

        return a.length - b.length;
    }
}

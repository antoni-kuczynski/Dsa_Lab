package pl.edu.pw.ee.binary.io;

import java.io.IOException;
import java.io.OutputStream;

public class BitWriter extends AbstractBitIO {

    private final OutputStream out;

    public BitWriter(OutputStream out, int bufferSize) {
        super(bufferSize);
        this.out = out;
    }

    public void writeBit(int bit) throws IOException {
        currentByte = (currentByte << 1) | (bit & 1);
        bitPos++;
        bitsConsumed++;

        if (bitPos == 8) {
            writeFullByte((byte) currentByte);
            resetCurrentByte();
        }
    }

    public void writeBits(String bits) throws IOException {
        for (int i = 0; i < bits.length(); i++) {
            writeBit(bits.charAt(i) == '1' ? 1 : 0);
        }
    }

    private void writeFullByte(byte value) throws IOException {
        buffer[bufferPos++] = value;

        if (bufferPos == buffer.length) {
            flushBuffer();
        }
    }

    @Override
    protected void flushBuffer() throws IOException {
        out.write(buffer, 0, bufferPos);
        bufferPos = 0;
    }

    public void flush() throws IOException {
        if (bitPos > 0) {
            writeFullByte((byte) (currentByte << (8 - bitPos)));
            resetCurrentByte();
        }

        if (bufferPos > 0) {
            flushBuffer();
        }

        out.flush();
    }

    @Override
    public void close() throws IOException {
        flush();
        out.close();
    }
}

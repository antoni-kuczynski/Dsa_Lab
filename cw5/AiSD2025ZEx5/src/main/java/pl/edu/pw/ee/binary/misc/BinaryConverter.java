package pl.edu.pw.ee.binary.misc;

public final class BinaryConverter {

    public static String intToBinary(int value, int maxBits) {
        if (maxBits <= 0) {
            throw new IllegalArgumentException("maxBits must be greater than 0!");
        }

        StringBuilder sb = new StringBuilder(maxBits);

        for (int i = maxBits - 1; i >= 0; i--) {
            int bit = (value >> i) & 1;
            sb.append(bit);
        }

        return sb.toString();
    }

    public static String byteArrayTo8BitBinary(byte[] key) {
        StringBuilder sb = new StringBuilder(key.length << 3);
        for (byte b : key) {
            sb.append(String.format("%8s",
                    Integer.toBinaryString(b & 0xFF)).replace(' ', '0'));
        }
        return sb.toString();
    }
}

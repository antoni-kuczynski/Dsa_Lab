package pl.edu.pw.ee.binary;

/*
    HEADER:

  0x00
    7   6   5   4   3   2   1   0
    -------------------------------
    [AL AL AL] [BL  BL  BL  BL  BL]
    3bit        5bit



    AL - 3 bit value of how many align 0 bits there should be
    BL - 5 bit value determining how many bits long is the key value in each node of the tree
        if set to 0 there are no such bits
*/


import pl.edu.pw.ee.binary.io.BitWriter;

import java.io.IOException;

import static pl.edu.pw.ee.binary.misc.BinaryConverter.intToBinary;

public class CompressedHeader {
    private int headerVal;
    private int amountOfAlignBitsAL;
    private int bitLengthOfTreeKeyLengthValue;

    public static CompressedHeader parseFromBinary(int headerBits) {
        CompressedHeader ch = new CompressedHeader();

        ch.amountOfAlignBitsAL = (headerBits >> 5) & 0b111;
        ch.bitLengthOfTreeKeyLengthValue = headerBits & 0b11111;

        return ch;
    }


    public int getBinaryRepresenation() {
        int header = 0;

        header |= (amountOfAlignBitsAL & 0b111) << 5;          // AL
        header |= bitLengthOfTreeKeyLengthValue & 0b11111;   // BL
        return header;
    }

    public void write(BitWriter writer) throws IOException {
        int header = getBinaryRepresenation() & 0xFF;
        writer.writeBits(intToBinary(header, 8));
    }



    public int getAmountOfAlignBits() {
        return amountOfAlignBitsAL;
    }

    public int getKeyLengthValueBitCount() {
        return bitLengthOfTreeKeyLengthValue;
    }

    public static class Builder {
        private final CompressedHeader instance;

        public Builder() {
            instance = new CompressedHeader();
        }

        public Builder setAmountOfAlignBitsAL(int al) {
            if (al < 0 || al > 0b111) {
                throw new IllegalArgumentException("AL must be 0-7");
            }
            instance.amountOfAlignBitsAL = al;
            return this;
        }

        public Builder setTreeKeyBitCount(int bl) {
            if (bl < 0 || bl > 0b11111) {
                throw new IllegalArgumentException("BL must be 0-31");
            }
            instance.bitLengthOfTreeKeyLengthValue = bl;
            return this;
        }

        public CompressedHeader build() {
            return instance;
        }
    }
}

package pl.edu.pw.ee.aisd2025zbonus2026.operationcounter;

public class OperationCounter {

    public int countMinSumOfOperations(int k){
        validate(k);

        long num = k;
        int opCount = 0;

        while (num > 1) {
            if ((num & 0x01) == 0) {
                num >>= 1;
            } else if (num == 3 || (num & 0x03) == 1) {
                num--;
            } else {
                num++;
            }
            opCount++;
        }
        return opCount;
    }

    private void validate(int k) {
        if (k <= 0) {
            throw new IllegalArgumentException("k must be >= 1");
        }
    }

}

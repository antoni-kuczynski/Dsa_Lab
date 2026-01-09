package pl.edu.pw.ee.aisd2025zex6.bonus2;

public class MaxRobberySum {

    public static void main(String[] args) {
        int n = 5;
        int[] houseVal = {4,14,18,6,2};
        int[] result = new int[n];

        result[0] = houseVal[0];
        result[1] = Math.max(houseVal[0], houseVal[1]);

        for (int i = 2; i < n; i++) {
            result[i] = Math.max(result[i-1], result[i-2] + houseVal[i]);
        }

        System.out.println(result[n-1]);
    }

}

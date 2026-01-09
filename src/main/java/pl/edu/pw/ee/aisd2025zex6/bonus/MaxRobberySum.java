package pl.edu.pw.ee.aisd2025zex6.bonus;

public class MaxRobberySum {

    public static void main(String[] args) {
        int n = 5;
        int[] houseVal = {400,200,18,123,2};
        int[] stolen = new int[n];

        if (n == 0) {
            System.err.println("n must be greater than 0");
            System.exit(1);
        }

        if (n == 1) {
            System.out.println(houseVal[0]);
            System.exit(1);
        }


        stolen[0] = houseVal[0];
        stolen[1] = Math.max(houseVal[0], houseVal[1]);

        for (int i = 2; i < n; i++) {
            stolen[i] = Math.max(stolen[i-1], stolen[i-2] + houseVal[i]);
        }

        System.out.println(stolen[n-1]);

    }

}

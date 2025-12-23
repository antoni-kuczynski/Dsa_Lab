package pl.edu.pw.ee.aisd2025zex6.bonus2;

public class MinCostSum {

    public static void main(String[] args) {
        int n = 3;
        int[] fieldCost = {10,20,30};
        int[] result = new int[n];

        result[0] = fieldCost[0];
        result[1] = fieldCost[1];

        for (int i = 2; i < n; i++) {
            result[i] = fieldCost[i] + Math.min(result[i-1], result[i-2]);
        }

        System.out.println(Math.min(result[n-1], result[n-2]));

    }

}

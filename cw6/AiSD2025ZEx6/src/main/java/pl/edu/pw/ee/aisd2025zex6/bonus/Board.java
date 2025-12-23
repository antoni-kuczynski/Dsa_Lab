package pl.edu.pw.ee.aisd2025zex6.bonus;

import java.util.Random;

public class Board {

    public static void main(String[] args) {
        Random random = new Random(1234);
        int n = 3;

        int[] fieldCost = new int[n];
        for (int i = 0; i < n; i++) {
            fieldCost[i] = random.nextInt(1, 99_999);
        }
//        int[] fieldCost = {10,20,30};


        int[] moves = new int[n];
        moves[0] = fieldCost[0];
        moves[1] = fieldCost[1];

        for (int i = 2; i < n; i++) {
            moves[i] = fieldCost[i] + Math.min(fieldCost[i-1], fieldCost[i-2]);
        }

        int result = Math.min(moves[n-1], moves[n-2]);
        System.out.println(result);


    }
}

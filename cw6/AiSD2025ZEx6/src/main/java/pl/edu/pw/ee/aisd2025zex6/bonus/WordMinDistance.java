package pl.edu.pw.ee.aisd2025zex6.bonus;

import java.util.Arrays;

public class WordMinDistance {

    public static void main(String[] args) {
        String first = "dom";
        String second = "koty";

        int[] operationCount = new int[Math.max(first.length(), second.length())];

        if (first.charAt(0) != second.charAt(0))
            operationCount[0] = 1;
        else
            operationCount[0] = 0;

        for (int i = 1; i < Math.min(first.length(), second.length()); i++) {
            if (first.charAt(i) != second.charAt(i))
                operationCount[i] = operationCount[i-1] + 1;
            else
                operationCount[i] = operationCount[i-1];
        }

        for (int i = Math.min(first.length(), second.length()); i < Math.max(first.length(), second.length()); i++) {
            operationCount[i] = operationCount[i-1] + 1;
        }

        System.out.println(Arrays.toString(operationCount));

    }
}

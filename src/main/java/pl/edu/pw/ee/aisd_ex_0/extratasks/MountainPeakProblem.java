package pl.edu.pw.ee.aisd_ex_0.extratasks;

/*

Dany jest całkowity ciąg liczb arr o długości n, w którym wartości rosną do elementu szczytowego, a następnie maleją.

Zwróć indeks elementu szczytowego.


Przykład 1:

Wejście: arr = [2, 5, 2]
Wyjście: 1

Przykład 2:

Wejście: arr = [0, 3, 2, 1]
Wyjście: 1

Przykład 3:

Wejście: arr = [0, 12, 7, 4]
Wyjście: 1

*/
public class MountainPeakProblem {

    public int peakIndexInMountainArray(int[] arr) {
    	int left = 0;
        int right = arr.length - 1;

        int maxIndex = (left + right) / 2;
        while (left <= right) {
            int middle = left + (right - left) / 2;

            if (arr[middle] >= arr[maxIndex]) {
                maxIndex = middle;
//                right = middle - 1;
                left = middle + 1;
            } else if (arr[middle] < arr[maxIndex]) {
//                left = middle + 1;
                right = middle - 1;
            }
        }
        return maxIndex;
    }
    
}

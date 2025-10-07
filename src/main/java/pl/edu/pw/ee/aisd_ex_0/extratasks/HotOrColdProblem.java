package pl.edu.pw.ee.aisd_ex_0.extratasks;

/*
Wybieram liczbę od 1 do n. Musisz zgadnąć, którą liczbę wybrałem.
Za każdym razem, gdy zgadniesz błędnie, powiem ci, czy liczba, którą wybrałem, jest większa (zwraca 1) czy mniejsza (zwracam -1) od Twojego zgadnięcia.
Wywołujesz wcześniej zdefiniowane API int guess(int num).
*/

public class HotOrColdProblem {

    private int guess(int num) {
        return num % 3 - 1;
    }

    public int guessNumber(int n) {
        validateN(n);

    	int left = 0;
        int right = n;

        while (left <= right) {
            int middle = left + (right - left) / 2;
            int guess = guess(middle);

            if (guess == 1) {
                right = middle - 1;
            } else if (guess == -1) {
                left = middle + 1;
            } else {
                return middle;
            }
        }
        return -1;
    }

    private void validateN(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n cannot be negative");
        }
    }
}

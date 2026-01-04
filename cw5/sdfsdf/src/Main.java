public class Main {

    public static void main(String[] args) {
        int sum = 0;
        for (int i = 20; i <= 23; i++) {
            sum += (int) Math.pow(2, i);
        }
        System.out.println(sum);
    }
}

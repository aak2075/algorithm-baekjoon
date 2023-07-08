import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        System.out.println(calcFibonacci(n));
    }

    public static int calcFibonacci(int n) {
        return calcFibonacciTail(n, 0, 1);
    }

    private static int calcFibonacciTail(int fn, int fn1, int fn2) {
        if (fn == 0) {
            return fn1;
        } else if (fn == 1) {
            return fn2;
        } else {
            return calcFibonacciTail(fn - 1, fn2, fn1 + fn2);
        }
    }
}
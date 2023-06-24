import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 1; i < n; i++) {
            printLine(n, i);
            System.out.println();
        }
        for (int i = n; i > 0; i--) {
            printLine(n, i);
            System.out.println();
        }
    }

    private static void printLine(int n, int i) {
        for (int j = n - 1; j >= i; j--) {
            System.out.print(" ");
        }
        for (int j = 0; j < i; j++) {
            System.out.print("*");
        }
        for (int j = 1; j < i; j++) {
            System.out.print("*");
        }
    }
}
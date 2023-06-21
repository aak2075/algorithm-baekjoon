import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        int result = input.chars()
                .mapToObj(c -> (char) c)
                .mapToInt(Main::replaceChar)
                .map(i -> i + 1)
                .sum();

        System.out.println(result);
    }

    public static int replaceChar(char c) {
        if ('A' <= c && c <= 'C') {
            return 2;
        }
        if ('D' <= c && c <= 'F') {
            return 3;
        }
        if ('G' <= c && c <= 'I') {
            return 4;
        }
        if ('J' <= c && c <= 'L') {
            return 5;
        }
        if ('M' <= c && c <= 'O') {
            return 6;
        }
        if ('P' <= c && c <= 'S') {
            return 7;
        }
        if ('T' <= c && c <= 'V') {
            return 8;
        }
        if ('W' <= c && c <= 'Z') {
            return 9;
        }
        return 0;
    }
}

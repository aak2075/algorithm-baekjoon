import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split(" ");

        String a = input[0];
        String b = input[1];

        int resultA = Integer.parseInt(reverse(a));
        int resultB = Integer.parseInt(reverse(b));

        System.out.println(Math.max(resultA, resultB));
    }

    private static String reverse(String a) {
        StringBuilder builder = new StringBuilder(a);
        char tmp = a.charAt(0);
        builder.setCharAt(0, a.charAt(2));
        builder.setCharAt(2, tmp);
        return builder.toString();
    }
}

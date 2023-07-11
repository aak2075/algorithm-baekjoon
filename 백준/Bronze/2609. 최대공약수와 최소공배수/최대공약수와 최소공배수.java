import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> input = Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());


        System.out.println(calcGcd(input.get(0), input.get(1)));
        System.out.println(calcLcm(input.get(0), input.get(1)));
    }

    public static int calcGcd(int a, int b) {
        int max = Math.max(a, b);
        int min = Math.min(a, b);
        int r = max % min;
        while (r != 0) {
            max = min;
            min = r;
            r = max % min;
        }

        return min;
    }

    public static int calcLcm(int a, int b) {
        return a * b / calcGcd(a, b);
    }
}
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        List<Integer> list = Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        List<Integer> list1 = countPrime(1000);

        long result = list.stream()
                .filter(list1::contains)
                .count();

        System.out.println(result);
    }

    public static boolean isPrime(int number) {
        if (number == 2 || number == 3 || number == 5 || number == 7) {
            return true;
        }
        if (number % 2 == 0) {
            return false;
        }
        if (number % 3 == 0 || number % 5 == 0 || number % 7 == 0) {
            return false;
        }
        for (int i = 11; i < number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static List<Integer> countPrime(int max) {
        return IntStream.range(2, max)
                .filter(Main::isPrime)
                .boxed()
                .collect(Collectors.toList());
    }
}
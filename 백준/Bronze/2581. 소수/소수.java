import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = Integer.parseInt(scanner.nextLine());
        int n = Integer.parseInt(scanner.nextLine());

        List<Integer> primes = new ArrayList<>();
        for (int i = m; i <= n; i++) {
            if (isPrime(i)) {
                primes.add(i);
            }
        }

        if (primes.size() == 0) {
            System.out.println(-1);
        } else {
            System.out.println(primes.stream()
                    .mapToInt(i -> i)
                    .sum());
            System.out.println(primes.get(0));
        }
    }

    public static boolean isPrime(int n) {
        if (n == 2 || n == 3 || n == 5 || n == 7) {
            return true;
        }

        if (n == 1 || n % 2 == 0 || n % 3 == 0 || n % 5 == 0 || n % 7 == 0) {
            return false;
        }

        for (int i = 11; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }
}
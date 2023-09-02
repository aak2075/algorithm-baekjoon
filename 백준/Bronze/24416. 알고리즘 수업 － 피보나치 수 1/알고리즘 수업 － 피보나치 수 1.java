import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static int FIB_CNT = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        List<Integer> dpArray = new ArrayList<>(Collections.nCopies(41, -1));
        dpArray.set(1, 1);
        dpArray.set(2, 1);

        int cnt = 0;
        for (int i = 3; i <= n; i++) {
            dpArray.set(n, dpArray.get(n - 2) + dpArray.get(n - 1));
            cnt++;
        }

        fib(n);

        System.out.println(FIB_CNT + " " + cnt);
    }

    private static int fib(int n) {
        if (n == 1 || n == 2) {
            FIB_CNT++;
            return 1;
        }
        return fib(n-1) + fib(n-2);
    }
}

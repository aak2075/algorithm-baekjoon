import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split(" ");

        int n = Integer.parseInt(input[0]);
        int k = Integer.parseInt(input[1]);

        int count = 0;
        int result = 0;
        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                count++;
                if (count == k) {
                    result = i;
                    break;
                }
            }
        }
        System.out.println(result);
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] splited = br.readLine().split(" ");
        int n = Integer.parseInt(splited[0]);
        int k = Integer.parseInt(splited[1]);

        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int number = Integer.parseInt(br.readLine());
            numbers.add(number);
        }

        int count = 0;
        int sum = 0;
        for (int i = n - 1; i >= 0; i--) {
            while (sum + numbers.get(i) <= k) {
                sum += numbers.get(i);
                count++;
            }
        }

        System.out.println(count);
    }
}

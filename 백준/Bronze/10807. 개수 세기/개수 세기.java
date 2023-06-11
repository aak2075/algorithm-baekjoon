import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        String s2 = scanner.nextLine();
        String s3 = scanner.nextLine();

        List<Integer> arr = Arrays.stream(s2.split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        int question = Integer.parseInt(s3);

        int count = 0;
        for (Integer i : arr) {
            if (i.equals(question)) {
                count++;
            }
        }

        System.out.println(count);
    }
}

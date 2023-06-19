import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < count; i++) {
            String[] input = scanner.nextLine().split(" ");
            int repeat = Integer.parseInt(input[0]);
            String str = input[1];
            String result = Arrays.stream(str.split(""))
                    .map(s -> s.repeat(repeat))
                    .collect(Collectors.joining());
            System.out.println(result);
        }
    }
}

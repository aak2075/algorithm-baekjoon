import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < count; i++) {
            int n = Integer.parseInt(scanner.nextLine());
            List<Integer> result = new ArrayList<>();
            while (n > 0) {
                int step = n % 2;
                n = n / 2;

                result.add(step);
            }
            StringJoiner stringJoiner = new StringJoiner(" ");
            for (int j = 0; j < result.size(); j++) {
                if (result.get(j) == 1) {
                    stringJoiner.add(Integer.toString(j));
                }
            }
            System.out.println(stringJoiner);
        }
    }
}
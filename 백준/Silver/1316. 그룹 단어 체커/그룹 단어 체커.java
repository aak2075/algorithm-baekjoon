import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        int count = n;
        for (int i = 0; i < n; i++) {
            String input = scanner.nextLine();
            Map<String, Integer> distance = new HashMap<>();
            String[] split = input.split("");
            for (int j = 0; j < split.length; j++) {
                if(distance.containsKey(split[j])) {
                    if (j - distance.get(split[j]) > 1) {
                        count--;
                        break;
                    } else {
                        distance.put(split[j], j);
                    }
                } else {
                    distance.put(split[j], j);
                }
            }
        }

        System.out.println(count);

    }
}
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

class Main {

    static List<Integer> input;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int n = Integer.parseInt(split[0]);
        int m = Integer.parseInt(split[1]);

        Set<String> memo = new HashSet<>();
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            memo.add(line);
        }

        for (int i = 0; i < m; i++) {
            String[] words = br.readLine().split(",");
            for (String word : words) {
                if (memo.contains(word)) {
                    memo.remove(word);
                    n--;
                }
            }

            System.out.println(n);
        }

    }
}

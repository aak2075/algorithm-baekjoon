import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> input = Arrays.stream(br.readLine().split(""))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        List<List<Integer>> total = new ArrayList<>();
        createSpaces(total, input);

        Integer answer = total.stream()
                .filter(line -> line.size() % 2 == 0)
                .filter(Main::isCri)
                .map(List::size)
                .max(Comparator.comparingInt(Integer::intValue))
                .orElse(-1);
        System.out.println(answer);
    }

    private static boolean isCri(List<Integer> line) {
        int left = 0;
        int right = 0;
        for (int i = 0; i < line.size() / 2; i++) {
            left += line.get(i);
        }
        for (int i = line.size() / 2; i < line.size(); i++) {
            right += line.get(i);
        }

        return left == right;
    }

    private static void createSpaces(List<List<Integer>> total, List<Integer> input) {
        for (int i = 0; i < input.size(); i++) {
            for (int j = i + 1; j <= input.size(); j++) {
                List<Integer> tmp = input.subList(i, j);
                total.add(tmp);
            }
        }
    }
}


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        List<List<Integer>> people = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<Integer> person = Arrays.stream(br.readLine().split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());

            people.add(person);
        }

        for (int i = 0; i < people.size(); i++) {
            int cnt = 1;
            for (int j = 0; j < people.size(); j++) {
                if (i == j) {
                    continue;
                }

                if (people.get(i).get(0) < people.get(j).get(0) && people.get(i).get(1) < people.get(j).get(1)) {
                    cnt++;
                }
            }
            people.get(i).add(cnt);
        }

        String result = people.stream()
                .map(person -> String.valueOf(person.get(2)))
                .collect(Collectors.joining(" "));

        System.out.println(result);
    }
}

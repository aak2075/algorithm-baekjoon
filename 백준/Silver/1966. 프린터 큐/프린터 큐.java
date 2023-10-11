import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

class Main {

    static List<Integer> input;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringJoiner sj = new StringJoiner("\n");

        for (int i = 0; i < n; i++) {
            String[] split = br.readLine().split(" ");
            int numberOfDocs = Integer.parseInt(split[0]);
            int wonderPos = Integer.parseInt(split[1]);
            List<Integer> docs = Arrays.stream(br.readLine().split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());

            Deque<List<Integer>> deque = new ArrayDeque<>();
            for (int j = 0; j < docs.size(); j++) {
                deque.offer(List.of(docs.get(j), j));
            }

            int answer = 0;
            while (!deque.isEmpty()) {
                List<Integer> poll = deque.poll();
                int start = deque.size();
                for (List<Integer> peek : deque) {
                    if (poll.get(0) < peek.get(0)) {
                        deque.offer(poll);
                        break;
                    }
                }
                if (deque.size() == start) {
                    answer++;
                    if (poll.get(1).equals(wonderPos)) {
                        sj.add(String.valueOf(answer));
                    }
                }
            }
        }
        System.out.println(sj);

    }
}

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.Collectors;

class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());

        for (int i = 0; i < testCase; i++) {
            int n = Integer.parseInt(br.readLine());

            Queue<Long> chapters = Arrays.stream(br.readLine().split(" "))
                    .map(Long::parseLong)
                    .collect(Collectors.toCollection(PriorityQueue::new));

            Long sum = 0L;
            while (chapters.size() > 1) {
                Long a = chapters.poll();
                Long b = chapters.poll();
                chapters.offer(a + b);
                sum += a + b;
            }

            System.out.println(sum);
        }
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        List<List<Integer>> meetings = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<Integer> meeting = Arrays.stream(br.readLine().split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            meetings.add(meeting);
        }

        meetings.sort(Comparator.comparingInt((List<Integer> meeting) -> meeting.get(1))
                .thenComparingInt(meeting -> meeting.get(0)));

        int count = 0;
        int current = 0;
        for (List<Integer> meeting : meetings) {
            if (meeting.get(0) >= current) {
                count++;
                current = meeting.get(1);
            }
        }

        System.out.println(count);
    }
}

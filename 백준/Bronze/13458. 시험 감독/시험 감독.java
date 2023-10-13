import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<Integer> a = Arrays.stream(br.readLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        String[] split = br.readLine().split(" ");
        int b = Integer.parseInt(split[0]);
        int c = Integer.parseInt(split[1]);

        long answer = 0;

        for (int i = 0; i < n; i++) {
            int ai = a.get(i) - b;
            answer++;
            if (ai <= 0) {
                continue;
            }
            int remainder = ai % c;
            if (remainder != 0) {
                ai += c - remainder;
            }
            int quotient = ai / c;
            answer += quotient;
        }

        System.out.println(answer);
    }
}

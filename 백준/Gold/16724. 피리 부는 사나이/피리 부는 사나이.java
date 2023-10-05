import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

class Main {

    private static List<List<String>> total = new ArrayList<>();
    private static List<List<Integer>> visited = new ArrayList<>();

    private static List<Integer> dy = List.of(0, -1, 0, 1);
    private static List<Integer> dx = List.of(-1, 0, 1, 0);
    private static String DIR = "LURD";
    private static int N;
    private static int M;
    private static int result = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        N = Integer.parseInt(split[0]);
        M = Integer.parseInt(split[1]);

        for (int i = 0; i < N; i++) {
            List<String> line = Arrays.stream(br.readLine().split(""))
                    .collect(Collectors.toList());
            total.add(line);

            ArrayList<Integer> visit = new ArrayList<>(Collections.nCopies(M, -1));
            visited.add(visit);
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visited.get(i).get(j) == -1) {
                    dfs(i, j);
                }
            }
        }
        System.out.println(result);
    }

    private static void dfs(int y, int x) {
        visited.get(y).set(x, 1);
        String current = total.get(y).get(x);
        int nextY = y + dy.get(DIR.indexOf(current));
        int nextX = x + dx.get(DIR.indexOf(current));

        if(visited.get(nextY).get(nextX) == 1) {
            result++;
        }
        if (visited.get(nextY).get(nextX) == -1) {
            dfs(nextY, nextX);
        }
        visited.get(y).set(x, 2);
    }
}

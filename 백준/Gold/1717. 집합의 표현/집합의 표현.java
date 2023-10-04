import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Main {

    private static List<Integer> set = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int n = Integer.parseInt(split[0]);
        int m = Integer.parseInt(split[1]);

        for (int i = 0; i < n + 1; i++) {
            set.add(i);
        }
        List<String> result = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            String[] split1 = br.readLine().split(" ");
            int op = Integer.parseInt(split1[0]);
            int a = Integer.parseInt(split1[1]);
            int b = Integer.parseInt(split1[2]);

            if (op == 0) {
                merge(a, b);
            } else {
                if (findRoot(a) == findRoot(b)) {
                    result.add("YES");
                } else {
                    result.add("NO");
                }
            }
        }

        for (String s : result) {
            System.out.println(s);
        }
    }

    private static int findRoot(int x) {
        while (x != set.get(x)) {
            x = set.get(x);
        }
        return x;
    }

    private static void merge(int x, int y) {
        x = findRoot(x);
        y = findRoot(y);
        if (x == y) {
            return;
        }
        int min = Math.min(x, y);
        int max = Math.max(x, y);
        set.set(min, max);
    }
}


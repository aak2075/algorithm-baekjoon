import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int n = Integer.parseInt(split[0]);
        int m = Integer.parseInt(split[1]);
        int k = Integer.parseInt(split[2]);

        List<List<String>> tiles = new ArrayList<>();
        for (int i1 = 0; i1 < n; i1++) {
            List<String> line = Arrays.stream(br.readLine().split(""))
                    .collect(Collectors.toList());
            tiles.add(line);
        }

        int count = 0;

        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {

                Map<String, Integer> line = new HashMap<>();
                String maxKey = "";
                int maxValue = 0;
                for (int l = i; l < n; l += k) {
                    for (int o = j; o < m; o += k) {
                        String tile = tiles.get(l).get(o);
                        if (line.containsKey(tile)) {
                            int value = line.get(tile) + 1;
                            if (value >= maxValue) {
                                maxKey = tile;
                                maxValue = value;
                            }
                            line.replace(tile, value);
                        } else {
                            if (maxValue < 1) {
                                maxValue = 1;
                                maxKey = tile;
                            }
                            line.put(tile, 1);
                        }
                    }
                }

                for (int l = i; l < n; l += k) {
                    for (int o = j; o < m; o += k) {
                        if (!tiles.get(l).get(o).equals(maxKey)) {
                            tiles.get(l).set(o, maxKey);
                            count++;
                        }
                    }
                }
            }
        }

        System.out.println(count);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sb.append(tiles.get(i).get(j));
            }
            sb.append("\n");
        }
        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb);
    }

}

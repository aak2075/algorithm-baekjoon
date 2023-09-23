import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] split = bf.readLine().split(" ");

        int n = Integer.parseInt(split[0]);
        int m = Integer.parseInt(split[1]);

        List<List<Integer>> coordinate = new ArrayList<>();

        List<Integer> dy = List.of(0, 1, 0, -1);
        List<Integer> dx = List.of(-1, 0, 1, 0);


        for (int i = 0; i < n; i++) {
            List<Integer> line = Arrays.stream(bf.readLine().split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            coordinate.add(line);
        }

        List<List<Integer>> can = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Integer v = coordinate.get(i).get(j);
                if (v == 2) {
                    can.add(List.of(i, j));
                }
            }
        }

        List<List<Integer>> total = new ArrayList<>();
        BT(0, new ArrayList<>(), can.size(), total, m);


        int min = 987654321;
        for (int i = 0; i < total.size(); i++) {
            List<List<Integer>> copyCoordinate = new ArrayList<>();
            for (List<Integer> list : coordinate) {
                copyCoordinate.add(new ArrayList<>(list));
            }
            Deque<List<Integer>> bfs = new ArrayDeque<>();
            List<List<Boolean>> visited = new ArrayList<>();
            List<List<Integer>> depth = new ArrayList<>();

            for (int j = 0; j < n; j++) {
                depth.add(new ArrayList<>(Collections.nCopies(n, 0)));
                visited.add(new ArrayList<>(Collections.nCopies(n, false)));
            }

            for (int j = 0; j < total.get(0).size(); j++) {
                List<Integer> vCoordinate = can.get(j);
                if (total.get(i).get(j) == 1) {
                    visited.get(vCoordinate.get(0)).set(vCoordinate.get(1), true);
                    bfs.offer(vCoordinate);
                } else {
                    copyCoordinate.get(vCoordinate.get(0)).set(vCoordinate.get(1), 0);
                }
            }

            while(!bfs.isEmpty()) {
                List<Integer> poll = bfs.poll();
                int y = poll.get(0);
                int x = poll.get(1);

                for (int j = 0; j < 4; j++) {
                    int nextY = y + dy.get(j);
                    int nextX = x + dx.get(j);

                    if (nextX < 0 || nextX > n - 1 || nextY < 0 || nextY > n - 1) {
                        continue;
                    }

                    if (visited.get(nextY).get(nextX) == true) {
                        if (depth.get(y).get(x) + 1 < depth.get(nextY).get(nextX)) {
                            depth.get(nextY).set(nextX, depth.get(y).get(x) + 1);
                            bfs.offer(List.of(nextY, nextX));
                        }
                        continue;
                    }

                    if (copyCoordinate.get(nextY).get(nextX) == 1) {
                        continue;
                    }

                    visited.get(nextY).set(nextX, true);
                    depth.get(nextY).set(nextX, depth.get(y).get(x) + 1);
                    bfs.offer(List.of(nextY, nextX));
                }
            }


            Integer maxValue = getMax(n, copyCoordinate, depth);
            if (maxValue == -1) {
                continue;
            }

            min = Math.min(min, maxValue);
        }
        if (min == 987654321) {
            min = -1;
        }
        System.out.println(min);
    }

    private static Integer getMax(int n, List<List<Integer>> coordinate, List<List<Integer>> depth) {
        int maxValue = -1;
        for (int j = 0; j < n; j++) {
            for (int k = 0; k < n; k++) {
                int value = depth.get(j).get(k);
                maxValue = Math.max(maxValue, value);
                if (coordinate.get(j).get(k) == 0 && value == 0) {
                    return 987654321;
                }
            }
        }
        return maxValue;
    }

    private static void BT(int index, List<Integer> letter, int size, List<List<Integer>> total, int m) {
        if (size == index) {
            long count = letter.stream()
                    .filter(i -> i == 1)
                    .count();
            if (count == m) {
                total.add(letter);
            }
            return;
        }

        List<Integer> step1 = new ArrayList<>(letter);
        List<Integer> step2 = new ArrayList<>(letter);
        step1.add(0);
        step2.add(1);

        BT(index + 1, step1, size, total, m);
        BT(index + 1, step2, size, total, m);
    }

}

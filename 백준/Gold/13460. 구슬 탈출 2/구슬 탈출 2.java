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
        List<Integer> red = new ArrayList<>();
        List<Integer> blue = new ArrayList<>();

        List<List<String>> total = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<String> line = Arrays.stream(br.readLine().split(""))
                    .collect(Collectors.toList());
            for (int j = 0; j < line.size(); j++) {
                if (line.get(j).equals("R")) {
                    red = List.of(i, j);
                } else if (line.get(j).equals("B")) {
                    blue = List.of(i, j);
                }
            }
            total.add(line);
        }
        int result = bfs(red.get(0), red.get(1), blue.get(0), blue.get(1), total);
        System.out.println(result);
    }

    private static int bfs(int ry, int rx, int by, int bx, List<List<String>> total) {
        List<Integer> dy = List.of(0, -1, 0, 1);
        List<Integer> dx = List.of(-1, 0, 1, 0);

        List<Position> visited = new ArrayList<>();
        Deque<Position> bfs = new ArrayDeque<>();
        Position position = new Position(ry, rx, by, bx, 0);
        visited.add(new Position(position));
        bfs.offer(position);

        while(!bfs.isEmpty()) {
            Position poll = bfs.poll();

            if (poll.count >= 10) {
                return -1;
            }

            for (int i = 0; i < 4; i++) {
                Position tmp = new Position(poll);

                int redDistance = move("r", tmp, i, total);
                int blueDistance = move("b", tmp, i, total);

                if (total.get(tmp.by).get(tmp.bx).equals("O")) {
                    continue;
                }
                if (total.get(tmp.ry).get(tmp.rx).equals("O")) {
                    return tmp.count + 1;
                }

                if (tmp.rx == tmp.bx && tmp.ry == tmp.by) {
                    if (redDistance > blueDistance) {
                        tmp.ry -= dy.get(i);
                        tmp.rx -= dx.get(i);
                    } else {
                        tmp.by -= dy.get(i);
                        tmp.bx -= dx.get(i);
                    }
                }

                boolean visit = visited.stream()
                        .anyMatch(v -> v.equals(tmp));
                if (visit) {
                    continue;
                }

                tmp.count++;
                visited.add(new Position(tmp));
                bfs.offer(tmp);
            }
        }

        return -1;
    }

    private static int move(String color, Position p, int i, List<List<String>> total) {
        List<Integer> dy = List.of(0, -1, 0, 1);
        List<Integer> dx = List.of(-1, 0, 1, 0);

        int distance = 0;
        if (color.equals("r")) {
            while (!total.get(p.ry).get(p.rx).equals("O") &&
                    !total.get(p.ry + dy.get(i)).get(p.rx + dx.get(i)).equals("#")
            ) {
                p.ry += dy.get(i);
                p.rx += dx.get(i);
                distance++;
            }
        } else {
            while (!total.get(p.by).get(p.bx).equals("O") &&
                    !total.get(p.by + dy.get(i)).get(p.bx + dx.get(i)).equals("#")
            ) {
                p.by += dy.get(i);
                p.bx += dx.get(i);
                distance++;
            }
        }
        return distance;
    }

    static class Position {
        int rx;
        int ry;
        int bx;
        int by;
        int count;

        public Position(Position p) {
            this(p.ry, p.rx, p.by, p.bx, p.count);
        }

        public Position(int ry, int rx, int by, int bx, int count) {
            this.rx = rx;
            this.ry = ry;
            this.bx = bx;
            this.by = by;
            this.count = count;
        }

        @Override
        public boolean equals(Object other) {
            Position o = (Position) other;
            return rx == o.rx &&
                    ry == o.ry &&
                    bx == o.bx &&
                    by == o.by;
        }
    }
}

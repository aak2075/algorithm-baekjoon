import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        List<List<Integer>> board = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            List<Integer> line = new ArrayList<>(Collections.nCopies(n + 1, 0));
            board.add(line);
        }

        for (int i = 0; i < k; i++) {
            List<Integer> apple = Arrays.stream(br.readLine().split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            board.get(apple.get(0)).set(apple.get(1), 1);
        }

        int l = Integer.parseInt(br.readLine());
        List<List<String>> moves = new ArrayList<>();
        for (int i = 0; i < l; i++) {
            List<String> move = Arrays.stream(br.readLine().split(" "))
                    .collect(Collectors.toList());
            moves.add(move);
        }

        Deque<List<Integer>> position = new ArrayDeque<>();
        position.offer(List.of(1, 1));

        int answer = move(board, position, moves, 2, 0, 0);
        System.out.println(answer);
    }

    private static int move(List<List<Integer>> board, Deque<List<Integer>> position, List<List<String>> directions, int direction, int dirIndex, int time) {
        time++;
        List<Integer> dy = List.of(0, -1, 0, 1);
        List<Integer> dx = List.of(-1, 0, 1, 0);

        if (dirIndex < directions.size()) {
            int moveTime = Integer.parseInt(directions.get(dirIndex).get(0));
            if (moveTime + 1 == time) {
                String dir = directions.get(dirIndex).get(1);
                direction = getDirection(direction, dir);
                dirIndex++;
            }
        }

        List<Integer> head = position.peekLast();
        int nextY = head.get(0) + dy.get(direction);
        int nextX = head.get(1) + dx.get(direction);

        if (crash(board.size(), position, List.of(nextY, nextX))) {
            return time;
        }

        position.offer(List.of(nextY, nextX));

        if (board.get(nextY).get(nextX) == 0 && position.size() > 1) {
            position.poll();
        }
        board.get(nextY).set(nextX, 0);

        return move(board, position, directions, direction, dirIndex, time);
    }

    private static boolean crash(int size, Deque<List<Integer>> position, List<Integer> target) {
        if (target.get(0) >= size || target.get(1) >= size || target.get(0) < 1 || target.get(1) < 1) {
            return true;
        }
        for (List<Integer> p : position) {
            if (p.get(0).equals(target.get(0)) && p.get(1).equals(target.get(1))) {
                return true;
            }
        }
        return false;
    }

    private static int getDirection(int direction, String dir) {
        if (dir.equals("L")) {
            if (direction == 0) {
                direction = 3;
            } else {
                direction--;
            }
        } else {
            if (direction == 3) {
                direction = 0;
            } else {
                direction++;
            }
        }
        return direction;
    }
}

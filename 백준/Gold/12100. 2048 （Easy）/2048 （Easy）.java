import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

class Main {

    private static int max = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int n = Integer.parseInt(split[0]);

        List<List<Integer>> board = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<Integer> line = Arrays.stream(br.readLine().split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            board.add(line);
        }

        int maxCount = 5;
        BT(board, 0, maxCount);
        System.out.println(max);
    }

    private static void BT(List<List<Integer>> board, int count, int maxCount) {
        max = Math.max(max, maxValue(board));
        if (count == maxCount) {
            return;
        }

        List<Integer> dy = List.of(0, -1, 0, 1);
        List<Integer> dx = List.of(-1, 0, 1, 0);

        for (int i = 0; i < 4; i++) {
            List<List<Integer>> copy = copyBoard(board);

            move(copy, dy.get(i), dx.get(i));
            BT(copy, count + 1, maxCount);
        }
    }

    private static void move(List<List<Integer>> board, int y, int x) {
        if (y == -1) {
            up(board);
        }
        if (y == 1) {
            down(board);
        }
        if (x == -1) {
            left(board);
        }
        if (x == 1) {
            right(board);
        }
        for (int i = 0; i < board.size(); i++) {
            sort(board, y, x);
        }
    }

    private static void left(List<List<Integer>> board) {
        for (int i = 0; i < board.size(); i++) {
            for (int j = 0; j < board.get(0).size(); j++) {
                Integer current = board.get(i).get(j);
                if (current == 0) {
                    continue;
                }
                for (int k = j + 1; k < board.get(0).size(); k++) {
                    Integer next = board.get(i).get(k);
                    if (next == 0) {
                        continue;
                    }
                    if (!next.equals(current)) {
                        break;
                    }
                    board.get(i).set(k, 0);
                    board.get(i).set(j, current + next);
                    break;
                }
            }
        }
    }

    private static void right(List<List<Integer>> board) {
        for (int i = 0; i < board.size(); i++) {
            for (int j = board.get(0).size() - 1; j >= 1; j--) {
                Integer current = board.get(i).get(j);
                if (current == 0) {
                    continue;
                }
                for (int k = j - 1; k >= 0; k--) {
                    Integer next = board.get(i).get(k);
                    if (next == 0) {
                        continue;
                    }
                    if (!next.equals(current)) {
                        break;
                    }
                    board.get(i).set(k, 0);
                    board.get(i).set(j, current + next);
                    break;
                }
            }
        }
    }

    private static void up(List<List<Integer>> board) {
        for (int i = 0; i < board.get(0).size(); i++) {
            for (int j = 0; j < board.size(); j++) {
                Integer current = board.get(j).get(i);
                if (current == 0) {
                    continue;
                }
                for (int k = j + 1; k < board.size(); k++) {
                    Integer next = board.get(k).get(i);
                    if (next == 0) {
                        continue;
                    }
                    if (!next.equals(current)) {
                        break;
                    }
                    board.get(k).set(i, 0);
                    board.get(j).set(i, next + current);
                    break;
                }
            }
        }
    }

    private static void down(List<List<Integer>> board) {
        for (int i = 0; i < board.get(0).size(); i++) {
            for (int j = board.size() - 1; j >= 1; j--) {
                Integer current = board.get(j).get(i);
                if (current == 0) {
                    continue;
                }
                for (int k = j - 1; k >= 0; k--) {
                    Integer next = board.get(k).get(i);
                    if (next == 0) {
                        continue;
                    }
                    if (!next.equals(current)) {
                        break;
                    }
                    board.get(k).set(i, 0);
                    board.get(j).set(i, next + current);
                    break;
                }
            }
        }
    }

    private static void sort(List<List<Integer>> board, int y, int x) {
        if (y == -1) {
            for (int i = board.size() - 1; i > 0; i--) {
                for (int j = 0; j < board.get(0).size(); j++) {
                    Integer current = board.get(i).get(j);
                    Integer next = board.get(i - 1).get(j);
                    if (next == 0) {
                        board.get(i - 1).set(j, current);
                        board.get(i).set(j, 0);
                    }
                }
            }
        }
        if (y == 1) {
            for (int i = 0; i < board.size() - 1; i++) {
                for (int j = 0; j < board.get(0).size(); j++) {
                    Integer current = board.get(i).get(j);
                    Integer next = board.get(i + 1).get(j);
                    if (next == 0) {
                        board.get(i + 1).set(j, current);
                        board.get(i).set(j, 0);
                    }
                }
            }
        }
        if (x == -1) {
            for (int i = 0; i < board.size(); i++) {
                for (int j = board.get(0).size() - 1; j > 0; j--) {
                    if (board.get(i).get(j - 1) == 0) {
                        board.get(i).set(j - 1, board.get(i).get(j));
                        board.get(i).set(j, 0);
                    }
                }
            }
        }
        if (x == 1) {
            for (int i = 0; i < board.size(); i++) {
                for (int j = 0; j < board.get(0).size() - 1; j++) {
                    if (board.get(i).get(j + 1) == 0) {
                        board.get(i).set(j + 1, board.get(i).get(j));
                        board.get(i).set(j, 0);
                    }
                }
            }
        }
    }

    private static int maxValue(List<List<Integer>> board) {
        return board.stream()
                .flatMap(Collection::stream)
                .mapToInt(Integer::intValue)
                .max()
                .orElse(-1);
    }

    private static List<List<Integer>> copyBoard(List<List<Integer>> board) {
        return board.stream()
                .map(ArrayList::new)
                .collect(Collectors.toList());
    }
}

import java.util.*;

class Solution {
    public int solution(int[][] maps) {
        List<Integer> dx = List.of(-1, 0, 1, 0);
        List<Integer> dy = List.of(0, -1, 0, 1);
        
        Deque<List<Integer>> bfs = new ArrayDeque<>();
        List<List<Integer>> depth = new ArrayList<>();
        for (int i = 0; i < maps.length; i++) {
            List<Integer> line = new ArrayList<>(Collections.nCopies(maps[0].length, 0));
            depth.add(line);
        }
        List<List<Boolean>> visited = new ArrayList<>();
        for (int i = 0; i < maps.length; i++) {
            List<Boolean> line = new ArrayList<>(Collections.nCopies(maps[0].length, false));
            visited.add(line);
        }
        depth.get(0).set(0, 1);
        visited.get(0).set(0, true);
        bfs.offer(List.of(0,0));
        while(!bfs.isEmpty()) {
            List<Integer> coordinate = bfs.poll();
            int y = coordinate.get(0);
            int x = coordinate.get(1);
            // 도착
            if(y == maps.length - 1 && x == maps[0].length - 1) {
                return depth.get(y).get(x);
            }
    
            for (int i = 0; i < 4; i++) {
                int nextY = y + dy.get(i);
                int nextX = x + dx.get(i);
                if (nextX < 0 || nextX > maps[0].length -1 || nextY < 0 || nextY > maps.length -1) {
                    continue;
                }
                if (visited.get(nextY).get(nextX)) {
                    continue;
                }
                if (maps[nextY][nextX] == 0) {
                    continue;
                }
                visited.get(nextY).set(nextX, true);
                depth.get(nextY).set(nextX, depth.get(y).get(x) + 1);
                bfs.offer(List.of(nextY, nextX));
            }
        }
        
        return -1;
    }
}
import java.util.*;

class Solution {
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        List<List<Boolean>> coordinate = new ArrayList<>();
        List<List<Boolean>> visited = new ArrayList<>();
        List<List<Integer>> depth = new ArrayList<>();
        for (int i = 0; i < 101; i++) {
            coordinate.add(new ArrayList<>(Collections.nCopies(101, false)));
            visited.add(new ArrayList<>(Collections.nCopies(101, false)));
            depth.add(new ArrayList<>(Collections.nCopies(101, 0)));
        }
        
        List<Integer> dy = List.of(0, 1, 0, -1);
        List<Integer> dx = List.of(-1, 0, 1, 0);
            
        // check
        for (int i = 0; i < rectangle.length; i++) {
            int ldx = rectangle[i][0] * 2;
            int ldy = rectangle[i][1] * 2;
            int rux = rectangle[i][2] * 2;
            int ruy = rectangle[i][3] * 2;
            for (int j = ldy; j <= ruy; j++) {
                for (int k = ldx; k <= rux; k++) {
                    coordinate.get(j).set(k, true);
                }
            }
        }
        
        // uncheck
        for (int i = 0; i < rectangle.length; i++) {
            int ldx = rectangle[i][0] * 2;
            int ldy = rectangle[i][1] * 2;
            int rux = rectangle[i][2] * 2;
            int ruy = rectangle[i][3] * 2;
            for (int j = ldy + 1; j < ruy; j++) {
                for (int k = ldx + 1; k < rux; k++) {
                    coordinate.get(j).set(k, false);
                }
            }
        }
        
        int startY = characterY * 2;
        int startX = characterX * 2;
        Deque<List<Integer>> bfs = new ArrayDeque<>();
        visited.get(startY).set(startX, true);
        depth.get(startY).set(startX, 1);
        bfs.offer(List.of(startY, startX));
        
        int targetY = itemY * 2;
        int targetX = itemX * 2;
        while(!bfs.isEmpty()) {
            List<Integer> poll = bfs.poll();
            
            if (poll.get(0) == targetY && poll.get(1) == targetX) {
                return depth.get(targetY).get(targetX) / 2;
            }
            
            for(int i = 0; i < 4; i++) {
                int nextY = poll.get(0) + dy.get(i);
                int nextX = poll.get(1) + dx.get(i);    
                
                if (nextY < 0 || nextY > 100 || nextX < 0 || nextX > 100) {
                    continue;
                }
                if (visited.get(nextY).get(nextX)) {
                    continue;
                }
                if (!coordinate.get(nextY).get(nextX)) {
                    continue;
                }
                visited.get(nextY).set(nextX, true);
                depth.get(nextY).set(nextX, depth.get(poll.get(0)).get(poll.get(1)) + 1);
                bfs.offer(List.of(nextY, nextX));
            }
        }
        
        return -1;
    }
}

// 모든 좌표를 x2한다
// 좌표 평면에 사각형을 모두 check
// 사각형 별로 사각형 내부만 uncheck
// 시작점부터 bfs로 사방 탐색
import java.util.*;

class Solution {
    public int solution(int n, int[][] computers) {
        Deque<Integer> bfs = new ArrayDeque<>();
        List<Boolean> visited = new ArrayList<>(Collections.nCopies(n, false));
        
        int answer = 0;
        for (int i = 0; i < computers.length; i++) {
            if (!visited.get(i)) {
                answer++;
                bfs.offer(i);
                while (!bfs.isEmpty()) {
                    int poll = bfs.poll();
                    visited.set(poll, true);
                    
                    for (int j = 0; j < computers[0].length; j++) {
                        if (poll == j) {
                            continue;
                        }
                        if (computers[poll][j] == 1 && !visited.get(j)) {
                            bfs.offer(j);
                        }
                    }
                }
            }
        }
        
        return answer;
    }
}

// 컴포넌트의 개수
// 모든 컴퓨터마다 방문하지 않았으면 탐색을 시작한다.
// 탐색을 시작할 때 cnt++

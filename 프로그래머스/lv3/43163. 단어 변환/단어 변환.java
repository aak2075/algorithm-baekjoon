import java.util.*;
import java.util.stream.*;

class Solution {
    public int solution(String begin, String target, String[] words) {
        // 인접 리스트로 알파벳 차이가 하나인 것끼리 간선을 추가한다.
        // begin부터 인접 리스트를 탐색해서 target이 되는 최솟값을 찾으면 됨.
        // 단어 자체가 정점
        
        List<String> vertexes = Arrays.stream(words)
            .collect(Collectors.toList());
        vertexes.add(begin);
        vertexes.add(target);
        
        Map<String, List<String>> adj = new HashMap<>();
        for (int i = 0; i < vertexes.size(); i++) {
            List<String> edge = new ArrayList<>();
            for (int j = 0; j < vertexes.size(); j++) {
                if (i == j) {
                    continue;
                }
                String that = vertexes.get(j);
                if (canTrans(vertexes.get(i), that)) {
                    edge.add(that);
                }
            }
            adj.put(vertexes.get(i), edge);
        }
        
        Deque<String> bfs = new ArrayDeque<>();
        Map<String, Boolean> visited = new HashMap<>();
        Map<String, Integer> depth = new HashMap<>();
        for (int i = 0; i < vertexes.size(); i++) {
            visited.put(vertexes.get(i), false);
            depth.put(vertexes.get(i), 0);
        }
        bfs.offer(begin);
        visited.replace(begin, true);
        while(!bfs.isEmpty()) {
            String poll = bfs.poll();
            if (poll.equals(target)) {
                return depth.get(poll);
            }
            
            List<String> adjList = adj.get(poll);
            for (String v : adjList) {
                if (visited.get(v)) {
                    continue;
                }
                depth.replace(v, depth.get(poll) + 1);
                visited.replace(v, true);
                bfs.offer(v);
            }
        }
        
        return 0;
    }
    
    private boolean canTrans(String s1, String s2) {
        String[] s1s = s1.split("");
        String[] s2s = s2.split("");
        
        int diff = 0;
        for (int i = 0; i < s1s.length; i++) {
            if (!s1s[i].equals(s2s[i])) {
                diff++;
            }
        }
        
        if (diff == 1) {
            return true;
        }
        return false;
    }
}

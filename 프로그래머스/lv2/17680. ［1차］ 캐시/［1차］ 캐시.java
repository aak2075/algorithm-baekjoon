import java.util.*;
import java.util.Map.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        
        LinkedList<String> cache = new LinkedList<>();
        
        for (int i = 0; i < cities.length; i++) {
            if (cacheSize == 0) {
                answer += 5;
                continue;
            }
            // 히트일 때
            if (cache.contains(cities[i].toLowerCase())) {
                answer += 1;
                cache.remove(cities[i].toLowerCase());    
                cache.offer(cities[i].toLowerCase());
                continue;
            }
            
            // miss일 때
            answer += 5;
            if (cache.size() == cacheSize) {
                cache.poll();
            }
            cache.offer(cities[i].toLowerCase());
        }
        
        return answer;
    }
}
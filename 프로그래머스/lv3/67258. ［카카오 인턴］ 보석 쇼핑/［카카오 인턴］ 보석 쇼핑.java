import java.util.*;
import java.util.stream.*;

class Solution {
    public int[] solution(String[] gems) {
        Set<String> set = Arrays.stream(gems)
            .collect(Collectors.toSet());
        
        List<String> list = Arrays.stream(gems)
            .collect(Collectors.toList());
        
        Map<String, Integer> map = new HashMap<>();
        
        int left = 0;
        int right = 0;
        int nextLeft = 0;
        int nextRight = 0;
        int minLeft = 0;
        int minRight = list.size();
        int minDistance = minRight - minLeft;
        
        int total = set.size();
        
        while (true) {
            if (map.size() < total) {
                right = nextRight;
                if (right == list.size()) {
                    break;
                }
                String current = list.get(right);
                if (map.containsKey(current)) {
                    map.compute(current, (k, v) -> v + 1);
                } else {
                    map.put(current, 1);
                }
                if (map.size() == total) {
                    if (right - left < minDistance) {
                        minDistance = right - left;
                        minLeft = left;
                        minRight = right;
                    }
                }
                nextRight++;
            } else {
                left = nextLeft;
                if (left == list.size()) {
                    break;
                }
                String current = list.get(left);
                if (map.get(current) == 1) {
                    map.remove(current);
                    if (right - left < minDistance) {
                        minDistance = right - left;
                        minLeft = left;
                        minRight = right;
                    }
                } else {
                    map.compute(current, (k, v) -> v - 1);
                }
                nextLeft++;
            }
        }
        
        int[] answer = {minLeft + 1, minRight + 1};
        return answer;
    }
}
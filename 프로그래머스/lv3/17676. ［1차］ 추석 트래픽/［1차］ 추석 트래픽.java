import java.util.*;
import java.util.stream.*;
import java.time.*;
import java.time.temporal.*;
import java.math.*;

class Solution {
    public int solution(String[] lines) {
        
        List<Integer> starts = new ArrayList<>();
        List<Integer> ends = new ArrayList<>();
        
        for(String line : lines) {
            int hour = Integer.parseInt(line.substring(11, 13));
            int minute = Integer.parseInt(line.substring(14, 16));
            double sec = Double.parseDouble(line.substring(17, 23));
            double dur = Double.parseDouble(line.substring(24, line.length() - 1));
            
            int end = hour * 3600 * 1000 + minute * 60 * 1000 + (int) (sec * 1000);
            int start = end - (int) (dur * 1000) + 1;
            
            starts.add(start);
            ends.add(end);
        }
    
        int max = 0;
        for (int i = 0; i < starts.size(); i++) {
            int count = 0;
            for (int j = i; j < starts.size(); j++) {
                if (ends.get(i) + 999 >= starts.get(j)) {
                    count++;
                }
            }
            if (count > max) {
                max = count;
            }
        }
        
        return max;
    }
}

// 구간에 제일 많이 걸치는 1초를 찾아라!

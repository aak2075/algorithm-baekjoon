import java.util.regex.*;
import java.util.*;
import java.util.stream.*;

class Solution {
    public int solution(String str1, String str2) {
        int answer = 0;
        
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();
        
        List<String> sp1 = parse(str1);
        List<String> sp2 = parse(str2);
        
        Map<String, Integer> sm1 = toMap(sp1);
        Map<String, Integer> sm2 = toMap(sp2);
        
        double hap = 0;
        double gyo = 0;
        for(String s : sm1.keySet()) {
            if (sm2.containsKey(s)) {
                hap += Math.max(sm1.get(s), sm2.get(s));
                gyo += Math.min(sm1.get(s), sm2.get(s));
            } else {
                hap += sm1.get(s);
            }
        }
        for(String s : sm2.keySet()) {
            if(!sm1.containsKey(s)) {
                hap += sm2.get(s);
            }
        }
        
        if(hap == 0) {
            return 65536;
        }
        answer = (int) (gyo / hap * 65536);
        
        return answer;
    }
    
    private List<String> parse(String s) {
        String[] split = s.split("");
        List<String> result = new ArrayList<>();
        for(int i = 0; i < split.length - 1; i++) {
            String add = split[i] + split[i + 1];
            if (add.matches("([a-z][a-z])")) {
                result.add(add);
            }
        }
        return result;
    }
    
    private Map<String, Integer> toMap(List<String> list) {
        Map<String, Integer> map = new HashMap<>();
        
        for(String s : list) {
            if(map.containsKey(s)) {
                map.put(s, map.get(s) + 1);
            } else {
                map.put(s, 1);
            }
        }
        
        return map;
    }
}
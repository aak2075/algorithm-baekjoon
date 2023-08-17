import java.util.*;
import java.util.regex.*;

class Solution {
    public int solution(String dartResult) {
        Pattern p = Pattern.compile("([0-9]*[A-Z][?#*])|[0-9]*[A-Z]");
        Matcher m = p.matcher(dartResult);
        
        List<String> list = new ArrayList<>();
        
        while(m.find()) {
            list.add(m.group());
        }
        
        List<Integer> result = new ArrayList<>();
        for(int i = 0; i < list.size(); i++) {
            if (list.get(i).contains("*")) {
                if (i > 0) {
                    result.set(i - 1, result.get(i - 1) * 2);
                    result.add(calc(list.get(i)) * 2);
                } else {
                    result.add(calc(list.get(i)) * 2);
                }
            } else if (list.get(i).contains("#")) {
                result.add(-calc(list.get(i)));
            } else {
                result.add(calc(list.get(i)));
            }
        }
        
        int answer = result.stream()
                            .mapToInt(i -> i)
                            .sum();
        
        return answer;
    }
    
    public int calc(String s) {
        Pattern number = Pattern.compile("[0-9]*");
        Pattern bonus = Pattern.compile("[A-Z]");
        
        Matcher nm = number.matcher(s);
        Matcher bm = bonus.matcher(s);
        
        int n1 = 0;
        String n2;
        if (nm.find()) {
            n1 = Integer.parseInt(nm.group());
        }
        bm.find();
        return (int) Math.pow(n1, range(bm.group()));
    }
    
    public int range(String s) {
        if (s.equals("S")) {
            return 1;
        }
        if (s.equals("D")) {
            return 2;
        }
        if (s.equals("T")) {
            return 3;
        }
        
        return 0;
    }
}
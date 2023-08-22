import java.util.*;
import java.util.stream.*;

class Solution {
    public String solution(int n, int t, int m, int p) {
        List<String> numbers = new ArrayList<>();
        int number = 0;
        
        for(int i = 0; i < t * m; i++) {
            // n^jisu + number
            String s = jisuToString(number, n);
            List<String> add = Arrays.stream(s.split(""))
                .collect(Collectors.toList());
            
            numbers.addAll(add);
            number++;
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < numbers.size(); i++) {
            if (sb.length() == t) {
                break;
            }
            if (i % m == p - 1) {
                sb.append(numbers.get(i));
            }
        }
        
        String answer = sb.toString();
        return answer;
    }
    
    private String jisuToString(int number, int n) {
        // n^jisu
        StringBuilder sb = new StringBuilder();
        while(number >= n) {
            sb.append(intToString(number % n));
            number = number / n;
        }
        sb.append(intToString(number % n));
        
        sb.reverse();
        return sb.toString();
    }
    
    private String intToString(int n) {
        switch(n) {
            case 10 : return "A";
            case 11 : return "B";
            case 12 : return "C";
            case 13 : return "D";
            case 14 : return "E";
            case 15 : return "F";
        }
        return Integer.toString(n);
    }
}

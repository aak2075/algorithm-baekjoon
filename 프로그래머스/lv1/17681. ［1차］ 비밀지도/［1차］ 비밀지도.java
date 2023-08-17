import java.util.*;
import java.util.stream.*;

class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        for(int i = 0; i < n; i++) {
            int calcOr = arr1[i] | arr2[i];
            answer[i] = toBinaryStr(calcOr, n);
        }
        
        return answer;
    }
    
    public String toBinaryStr(int n, int size) {
        List<Integer> list = new ArrayList<>();
        while (n > 0) {
            list.add(n % 2);
            n = n / 2;
        }
        
        while(list.size() < size) {
            list.add(0);
        }
        
        Collections.reverse(list);
        
        StringBuilder sb = new StringBuilder();
        for(Integer i : list) {
            if (i == 0) {
                sb.append(" ");
            } else {
                sb.append("#");
            }
        }
        return sb.toString();
    }
    
    // 15...0 7...1 3...1 1...1 0...1
    //"######", "###  #", "##  ##", " #### ", " #####", "### # "
    //"######", "###  #", "##  ##", "#### ", "#####", "### # "
    
}
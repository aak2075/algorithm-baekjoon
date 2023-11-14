import java.util.*;

class Solution {
    public String solution(String p) {
        String answer = recursive(p);
        return answer;
    }
    
    private String recursive(String s) {
        if (s.isEmpty()) {
            return s;
        }
        
        char[] chars = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        int openCount = 0;
        int closeCount = 0;
        boolean split = false;
        String u = "";
        String v = "";
        for (char character : chars) {
            if (!split) {
                if (character == '(') {
                    openCount++;
                    sb.append('(');
                } else {
                    closeCount++;
                    sb.append(')');
                }    
                
                if (openCount == closeCount) {
                    split = true;
                    u = sb.toString();
                    sb = new StringBuilder();
                }
                
                continue;
            }
            
            if (split) {
                sb.append(character);
            }
        }
        v = sb.toString();
        
        if (isRightBracket(u)) {
            return u + recursive(v);
        } else {
            String removePrefix = u.substring(1);
            String removePostfix = removePrefix.substring(0, removePrefix.length() - 1);
            
            sb = new StringBuilder();
            char[] uchars = removePostfix.toCharArray();
            for (char character : uchars) {
                if (character == '(') {
                    sb.append(')');
                } else {
                    sb.append('(');
                }
            }
            return "(" + recursive(v) + ")" + sb.toString();
        }
    }
    
    private boolean isRightBracket(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        char[] chars = s.toCharArray();
        for (char character : chars) {
            if (character == '(') {
                stack.push(character);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                stack.pop();
            }
        }
        
        if (stack.isEmpty()) {
            return true;
        }
        
        return false;
    }
}
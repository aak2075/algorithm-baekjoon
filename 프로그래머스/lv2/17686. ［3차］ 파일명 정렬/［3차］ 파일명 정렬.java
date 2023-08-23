import java.util.*;

class Solution {
    public String[] solution(String[] files) {
        List<Element> result = new ArrayList<>();     
        
        for (String file : files) {
            char[] charArray = file.toCharArray();

            int i = 0;
            List<Character> cHead = new ArrayList<>();
            List<Character> cNumber = new ArrayList<>();
            List<Character> cTail = new ArrayList<>();
            for (char c : charArray) {
                if (i == 0 && c >= '0' && c <= '9') {
                    i++;
                }
                if (i == 1 && ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') || c == '.' || c == ' ' || c == '-')) {
                    i++;
                }
                if (i == 0) {
                    cHead.add(c);
                } else if (i == 1) {
                    cNumber.add(c);
                } else {
                    cTail.add(c);
                }
            }
            
            String head = charsToString(cHead);
            String number = charsToString(cNumber);
            String tail = charsToString(cTail);
            
            result.add(new Element(head, number, tail));
        }
        
        Collections.sort(result);
        
        String[] answer = new String[result.size()];
        
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i).toFile();
        }
        
        return answer;
    }
    
    private String charsToString(List<Character> chars) {
        StringBuilder sb = new StringBuilder();
        for(char c : chars) {
            sb.append(c);
        }
        return sb.toString();
    }
    
    static class Element implements Comparable<Element> {
        String head;
        String number;
        String tail;
        
        public Element (String head, String number, String tail) {
            this.head = head;
            this.number = number;
            this.tail = tail;
        }
        
        @Override
        public int compareTo(Element o) {
            if (head.compareToIgnoreCase(o.head) == 0) {
                return Integer.compare(Integer.parseInt(number), (Integer.parseInt(o.number)));
            }
            return head.compareToIgnoreCase(o.head);
        }
        
        public String toFile() {
            return head + number + tail;
        }
    }
}
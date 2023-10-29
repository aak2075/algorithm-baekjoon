import java.util.*;

class Solution {
    public String solution(String[] survey, int[] choices) {
        Map<String, Integer> scores = new LinkedHashMap<>();
        scores.put("R", 0);
        scores.put("T", 0);
        scores.put("C", 0);
        scores.put("F", 0);
        scores.put("J", 0);
        scores.put("M", 0);
        scores.put("A", 0);
        scores.put("N", 0);
        
        for (int i = 0; i < survey.length; i++) {
            String[] split = survey[i].split("");
            String leftType = split[0];
            String rightType = split[1];
            
            calc(leftType, rightType, choices[i], scores);
        }
        StringBuilder answer = new StringBuilder();
        
        if (scores.get("R") >= scores.get("T")) {
            answer.append("R");
        } else {
            answer.append("T");
        }
        
        if (scores.get("C") >= scores.get("F")) {
            answer.append("C");
        } else {
            answer.append("F");
        }
        
        if (scores.get("J") >= scores.get("M")) {
            answer.append("J");
        } else {
            answer.append("M");
        }
        
        if (scores.get("A") >= scores.get("N")) {
            answer.append("A");
        } else {
            answer.append("N");
        }
        
        return answer.toString();
    }
    
    private void calc(String leftType, String rightType, int choice, Map<String, Integer> scores) {
        switch (choice) {
            case 1 :
                scores.compute(leftType, (k, v) -> v + 3);
                break;
            case 2 :
                scores.compute(leftType, (k, v) -> v + 2);
                break;
            case 3 :
                scores.compute(leftType, (k, v) -> v + 1);
                break;
            case 5 :
                scores.compute(rightType, (k, v) -> v + 1);
                break;
            case 6 :
                scores.compute(rightType, (k, v) -> v + 2);
                break;
            case 7 :
                scores.compute(rightType, (k, v) -> v + 3);
                break;
        }
    }
}
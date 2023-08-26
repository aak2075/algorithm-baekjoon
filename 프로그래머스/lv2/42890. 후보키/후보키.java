import java.util.*;

class Solution {
    public int solution(String[][] relation) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        
        int columnLength = relation[0].length;
        List<Integer> columns = new ArrayList<>();
        for (int i = 0; i < columnLength; i++) {
            columns.add(i);
        }
        
        List<Integer> tmp = new ArrayList<>(columns);
        for (int i = 0; i < columnLength; i++) {
            tmp.remove(0);
            map.put(i, tmp);
            tmp = new ArrayList<>(tmp);
        }
        
        List<String> result = new ArrayList<>();
        
        for (int i = 0; i < columnLength; i++) {
            BT(0, List.of(i), map, result, relation, columnLength);    
        }
        
        List<String> minimalityRemoved = new ArrayList<>(result);
        
        for (String s : result) {
            for (String a : result) {
                if (s.equals(a)) {
                    continue;
                }
                
                String[] split = a.split("");
                int count = 0;
                for (String b : split) {
                    if (s.contains(b)) {
                        count++;
                    }
                }
                if (count == split.length) {
                    minimalityRemoved.remove(s);
                }
            }
        }
        
        int answer = minimalityRemoved.size();
        return answer;
    }
    
    private void BT(int index, List<Integer> letter, Map<Integer, List<Integer>> map, List<String> result, String[][] relation, int columnLength) {
        if (index > columnLength - 1) {
            return;
        }
                
        List<List<String>> rows = new ArrayList<>();
        for (int i = 0; i < relation.length; i++) {
            List<String> row = new ArrayList<>();

            for (Integer column : letter) {
                row.add(relation[i][column]);
            }
            
            // 유일성이 깨지거나 순서있는 최소성이 깨지는 경우 (순서가 맞지 않은 최소성은 처리되지 않음)
            if (rows.contains(row)) {
                int lastLetter = letter.get(letter.size() - 1);
                List<Integer> decision = map.get(lastLetter);
                for (Integer s : decision) {
                    List<Integer> nextLetter = new ArrayList<>(letter);
                    nextLetter.add(s);

                    BT(index + 1, nextLetter, map, result, relation, columnLength);
                }
                return;
            }
            rows.add(row);
        }
        result.add(letterToString(letter));
    }
    
    private String letterToString(List<Integer> letter) {
        StringBuilder sb = new StringBuilder();
        for (int i : letter) {
            sb.append(i);
        }
        return sb.toString();
    }
}

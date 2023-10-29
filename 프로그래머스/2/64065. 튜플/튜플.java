import java.util.*;
import java.util.stream.*;

class Solution {
    public int[] solution(String s) {
        s = s.substring(1, s.length());
        s = s.substring(0, s.length() - 1);
        String[] split = s.split("},\\{");
    
        List<List<Integer>> tuples = Arrays.stream(split)
                .map(it -> it.replace("{", ""))
                .map(it -> it.replace("}", ""))
                .map(it -> Arrays.stream(it.split(","))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList()))
                .sorted(Comparator.comparingInt(List::size))
                .collect(Collectors.toList());
        
        List<Integer> result = new ArrayList<>();
        
        for (List<Integer> tuple : tuples) {
            List<Integer> tmp = new ArrayList<>(tuple);
            tmp.removeAll(result);
            result.add(tmp.get(0));
        }
    
        return result.stream()
            .mapToInt(Integer::intValue)
            .toArray();
    }
}
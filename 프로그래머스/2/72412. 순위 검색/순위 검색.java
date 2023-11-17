import java.util.*;
import java.util.stream.*;

class Solution {
    public int[] solution(String[] info, String[] query) {
        List<Integer> answer = new ArrayList<>();
        Map<String, List<Integer>> infos = new HashMap<>();
        
        for (String line : info) {
            String[] splited = line.split(" ");
            String key = Stream.of(splited[0], splited[1], splited[2], splited[3])
                .collect(Collectors.joining(" "));
            
            List<Integer> value = infos.computeIfAbsent(key, k -> new ArrayList<>());
            value.add(Integer.parseInt(splited[4]));
        }
        
        for (List<Integer> value : infos.values()) {
            value.sort(Comparator.reverseOrder());
        }
        
        for (String line : query) {
            String[] splited = line.split(" and ");
            String language = splited[0];
            String stack = splited[1];
            String carrer = splited[2];
            String[] foodAndScore = splited[3].split(" ");
            String food = foodAndScore[0];
            int score = Integer.parseInt(foodAndScore[1]);
            
            List<String> languages = new ArrayList<>();
            List<String> stacks = new ArrayList<>();
            List<String> carrers = new ArrayList<>();
            List<String> foods = new ArrayList<>();
            
            if (language.equals("-")) {
                languages.add("cpp");
                languages.add("java");
                languages.add("python");
            } else {
                languages.add(language);
            }
            
            if (stack.equals("-")) {
                stacks.add("backend");
                stacks.add("frontend");
            } else {
                stacks.add(stack);
            }
            
            if (carrer.equals("-")) {
                carrers.add("junior");
                carrers.add("senior");
            } else {
                carrers.add(carrer);
            }
            
            if (food.equals("-")) {
                foods.add("chicken");
                foods.add("pizza");
            } else {
                foods.add(food);
            }
            
            List<String> targets = link(languages, stacks, carrers, foods);
            
            int result = 0;
            for (String target : targets) {
                if (!infos.containsKey(target)) {
                    continue;
                }
                
                List<Integer> values = infos.get(target);
                
                result += upperBound(values, score);
            }
            
            answer.add(result);
        }
        
        return answer.stream()
            .mapToInt(Integer::intValue)
            .toArray();
    }
    
    private boolean compare(String query, String info) {
        if (query.equals("-")) {
            return true;
        }
        
        return query.equals(info);
    }
    
    private int parseScore(String s) {
        String[] splited = s.split(" ");
        return Integer.parseInt(splited[4]);
    }
    
    private List<String> link(List<String> languages, List<String> stacks, List<String> carrers, List<String> foods) {
        List<String> result = new ArrayList<>();
        
        for (String language : languages) {
            for (String stack : stacks) {
                for (String carrer : carrers) {
                    for (String food : foods) {
                        String line = Stream.of(language, stack, carrer, food)
                            .collect(Collectors.joining(" "));
                        result.add(line);
                    }
                }
            }
        }
        
        return result;
    }
    
    private int upperBound(List<Integer> list, int target) {
        int left = 0;
        int right = list.size();
        int mid;
        while (left < right) {
            mid = (left + right) / 2;
            if (list.get(mid) >= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        
        return right;
    }
}
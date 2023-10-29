import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

class Solution {
    public String[] solution(String[] orders, int[] course) {
        Set<String> combinationSet = new HashSet<>();
        for (String order : orders) {
            getCombination(0, "", order, combinationSet);
        }



        Map<String, Integer> combinations = combinationSet.stream()
                .collect(Collectors.toMap(Function.identity(), s -> 0));

        for (String order : orders) {
            // 각 order에 모든 key 조합이 포함되어 있는지.
            for (Map.Entry<String, Integer> combination : combinations.entrySet()) {
                List<String> keys = Arrays.stream(combination.getKey().split(""))
                        .collect(Collectors.toList());
                boolean orderContainsAll = keys.stream()
                        .allMatch(order::contains);
                if (orderContainsAll) {
                    combinations.compute(combination.getKey(), (k, v) -> v + 1);
                }
            }
        }

        final List<Integer> courses = Arrays.stream(course)
                .boxed()
                .collect(Collectors.toList());

        List<String> result = new ArrayList<>();

        for (Integer courseSize : courses) {
            Integer max = combinations.entrySet().stream()
                    .filter(e -> e.getKey().length() == courseSize)
                    .map(Map.Entry::getValue)
                    .max(Comparator.comparingInt(Integer::intValue))
                    .orElse(-1);
            if (max < 2) {
                continue;
            }

            List<String> courseList = combinations.entrySet().stream()
                    .filter(e -> e.getKey().length() == courseSize)
                    .filter(e -> e.getValue().equals(max))
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());

            result.addAll(courseList);
        }

        return result.stream()
                .sorted()
                .toArray(String[]::new);
    }

    private void getCombination(int index, String letter, String target, Set<String> bt) {
        if (index == target.length()) {
            if (letter.length() < 2) {
                return;
            }
            String result = Arrays.stream(letter.split(""))
                    .sorted()
                    .collect(Collectors.joining());
            bt.add(result);
            return;
        }
        // 선택하거나 선택하지 않거나

        getCombination(index + 1, letter, target, bt);
        getCombination(index + 1, letter + target.charAt(index), target, bt);
    }
}

// 가장 많이 함께 주문한 단품메뉴를 코스로
// 코스는 최소 2가지 이상 단품메뉴로 구성
// 조합은 최소 2명 이상의 손님으로부터 주문을 받았어야함.
// 횟수가 같은것들은 다 결과에 반영
// course 에 해당하는 메뉴들을 만듬(중복 가능)
// 오름차순 정렬

// 작은 요소부터 다음 요소에 포함되어 있는지 계산.
// 사전순으로 오름차순해서 key를 반환

// 모든 문자열에 대해 2개 이상의 모든 조합을 Set에 담음.
// 각 문자열마다 set에서 조합이 포함된 개수를 <문자열, 포함 개수>로 센다.

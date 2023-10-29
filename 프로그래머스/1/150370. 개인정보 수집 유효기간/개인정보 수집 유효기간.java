import java.util.*;
import java.util.stream.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        Map<String, Integer> termsInfo = Arrays.stream(terms)
                .collect(Collectors.toMap(term -> term.split(" ")[0], term -> Integer.parseInt(term.split(" ")[1])));

        List<Integer> answer = new ArrayList<>();

        for (int i = 0; i < privacies.length; i++) {
            String privacy = privacies[i];
            String[] split = privacy.split(" ");
            int collectDate = calcTime(split[0]);
            int expire = termsInfo.get(split[1]) * 28;
            
            if (expire + collectDate <= calcTime(today)) {
                answer.add(i + 1);
            }
        }
        
        return answer.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }

    private int calcTime(String day) {
        String[] split = day.split("\\.");
        return Integer.parseInt(split[0]) * 12 * 28 + Integer.parseInt(split[1]) * 28 + Integer.parseInt(split[2]);
    }
}

// 12달이 딱 되면 폐기
// 모든 달은 28일

// 유효기간 + 수집일자 <= 오늘 날짜이면 파기
// privacies를 돌면서 계산해서 파기대상이면 insert

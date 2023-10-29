import java.util.*;
import java.util.stream.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        List<String> reports = Arrays.stream(report)
            .distinct()
            .collect(Collectors.toList());
        
        Map<String, List<String>> fromTo = new HashMap<>();
        Map<String, Integer> reportCounts = new HashMap<>();
        
        for (String id : id_list) {
            reportCounts.put(id, 0);
            fromTo.put(id, new ArrayList<>());
        }
        
        for (String reportInfo : reports) {
            String[] split = reportInfo.split(" ");
            String from = split[0];
            String to = split[1];
            
            fromTo.compute(from, (key, v) -> {v.add(to); return v;});
            reportCounts.compute(to, (key, v) -> v + 1);
        }
        
        Map<String, Integer> results = new LinkedHashMap<>();
        for (String id : id_list) {
            results.put(id, 0);
        }
        for (Map.Entry<String, Integer> reportCount : reportCounts.entrySet()) {
            String reported = reportCount.getKey();
            int count = reportCount.getValue();
            
            if (count >= k) {
                for(Map.Entry<String, List<String>> fromToEntry : fromTo.entrySet()) {
                    String from = fromToEntry.getKey();
                    List<String> to = fromToEntry.getValue();
                    
                    if (to.contains(reported)) {
                        results.compute(from, (key, v) -> v + 1);
                    }
                }
            }
        }
        
        return results.values().stream()
            .mapToInt(Integer::intValue)
            .toArray();
    }
}

// 한 유저가 여러번 신고할 수 있음
// 여러 번 신고해도 동일 유저는 1회로 취급
// k번 이상 신고시 신고한 모든 유저에게 메일 발송

// report를 중복 제거
// map에 신고자, 신고당한사람 put
// 신고당한사람, 횟수 map에 put
// 사람 별 신고 횟수가 k 이상이면 
// 각 id_list마다 몇명 정지됐는지

// 횟수 맵을 돌면서 k이상이면 
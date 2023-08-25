import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        
        Map<String, String> user = new HashMap<>();
        
        for (String line : record) {
            String[] info = line.split(" ");
            String action = info[0];
            String id = info[1];
            String nickname = "";
            if (info.length > 2) {
                nickname = info[2];
            }
            
            if (action.equals("Enter") || action.equals("Change")) {
                user.put(id, nickname);
            }
        }
        
        List<String> result = new ArrayList<>();
        for (int i = 0; i < record.length; i++) {
            String[] info = record[i].split(" ");
            String action = info[0];
            String id = info[1];
            
            if (action.equals("Change")) {
                continue;
            }
            
            String name = user.get(id) + "님이 ";
            if (action.equals("Enter")) {
                result.add(name + "들어왔습니다.");
            } else if (action.equals("Leave")) {
                result.add(name + "나갔습니다.");
            }
        }
        String[] answer = new String[result.size()];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }
        
        return answer;
    }
}
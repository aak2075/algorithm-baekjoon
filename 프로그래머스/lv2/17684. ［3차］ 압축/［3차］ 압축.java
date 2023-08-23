import java.util.*;


class Solution {
    public int[] solution(String msg) {
        Map<String, Integer> dictionary = new HashMap<>();
        
        for (int i = 1; i < 27; i++) {
            dictionary.put(Character.toString('A' + i - 1), i);
        }
        int max = 26;
        
        String[] msgs = msg.split("");
        
        List<Integer> result = new ArrayList<>();
        for(int i = 0; i < msgs.length; i++) {
            String contains = "";
            int last = 0;
            for(int j = i; j < msgs.length; j++) {
                contains = contains + msgs[j];
                if (!dictionary.containsKey(contains)) {
                    dictionary.put(contains, max + 1);
                    max++;
                    break;
                } else if (contains.length() > 1){
                    last = dictionary.get(contains);
                    i++;
                } else {
                    last = dictionary.get(contains);
                }
            }
            result.add(last);
        }
        
        int[] answer = new int[result.size()];
        
        for(int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }
        
        return answer;
    }
}

// 사전에 단어를 검색해서 있으면 출력, 앞의 자리까지만 있으면 앞의 자리 + c로 등록.
// 없을 때 까지 사진 탐색. 없으면 보충하고 넘어감.
// 인덱스는 마지막 있는것의 다음부터.
// K
// KA
// AK
// KA
// KAO
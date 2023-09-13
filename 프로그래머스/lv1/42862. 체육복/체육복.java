import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        List<Integer> clothes = new ArrayList<>(Collections.nCopies(31, 1));
        for (Integer i : reserve) {
            clothes.set(i, 2);
        }
        for (Integer i : lost) {
            clothes.set(i, clothes.get(i) - 1);
        }
        
        for (int i = 1; i < clothes.size(); i++) {
            if (clothes.get(i) > 1) {
                if (clothes.get(i - 1) == 0) {
                    clothes.set(i - 1, 1);
                    clothes.set(i, 1);
                } else if (clothes.get(i + 1) == 0) {
                    clothes.set(i + 1, 1);
                    clothes.set(i, 1);
                }
            }
        }
        
        int answer = 0;
        for (int i = 1; i < 31; i++) {
            if (clothes.get(i) == 1) {
                answer++;
            }
        }
        return answer;
    }
    
}

// 여벌이 있으면 앞이나 뒤로 빌려줄 수 있다.
// 도난은 -1
// 앞이 없으면 앞에 빌려주고 있으면 뒤로 빌려준다
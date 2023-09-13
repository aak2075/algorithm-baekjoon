import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        List<Integer> clothes = new ArrayList<>(Collections.nCopies(n + 1, 1));
        
        Arrays.sort(reserve);
        
        for (Integer i : reserve) {
            clothes.set(i, 2);
        }
        for (Integer i : lost) {
            clothes.set(i, clothes.get(i) - 1);
        }
        
        for (int i = 0; i < reserve.length; i++) {
            if (clothes.get(reserve[i]) != 2) {
                continue;
            }
            
            if (reserve[i] > 1 && clothes.get(reserve[i] - 1) == 0) {
                clothes.set(reserve[i] - 1, 1);
                clothes.set(reserve[i], 1);
                continue;
            }
            if (reserve[i] < n && clothes.get(reserve[i] + 1) == 0) {
                clothes.set(reserve[i] + 1, 1);
                clothes.set(reserve[i], 1);
            }
        }
        
        int answer = 0;
        for (int i = 1; i < n + 1; i++) {
            if (clothes.get(i) > 0) {
                answer++;
            }
        }
        return answer;
    }
}

// 여벌이 있으면 앞이나 뒤로 빌려줄 수 있다.
// 도난은 -1
// 앞이 없으면 앞에 빌려주고 있으면 뒤로 빌려준다
import java.lang.*;

class Solution {
    public int solution(int[][] sizes) {
        int maxW = 0;
        int maxH = 0;
        for (int i = 0; i < sizes.length; i++) {
            if (sizes[i][0] < sizes[i][1]) {
                maxW = Math.max(maxW, sizes[i][0]);
                maxH = Math.max(maxH, sizes[i][1]);
            } else {
                maxW = Math.max(maxW, sizes[i][1]);
                maxH = Math.max(maxH, sizes[i][0]);
            }
        }
        int answer = maxW * maxH;
        return answer;
    }
}

// 둘 중 하나는 만족하는 경우

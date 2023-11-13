import java.util.*;

class Solution {
    public int solution(int[][] board, int[] moves) {
        Deque<Integer> stack = new ArrayDeque<>();
        
        int answer = 0;
        for (Integer move : moves) {
            int moveIndex = move - 1;
            
            for (int i = 0; i < board.length; i++) {
                int scan = board[i][moveIndex];
                if (scan != 0) {
                    board[i][moveIndex] = 0;
                    if (stack.isEmpty()) {
                        stack.push(scan);
                    }
                    else if (stack.peekFirst() == scan) {
                        int pop = stack.pop();
                        answer += 2;
                    } else {
                        stack.push(scan);
                    }
                    break;
                }
            }
        }
        
        return answer;
    }
}
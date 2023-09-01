import java.util.*;
import java.util.stream.*;

class Solution {
    public int solution(int[] numbers, int target) {
        return search(0, numbers, new ArrayList<>(), target);
    }
    
    public int search(int index, int[] numbers, List<Integer> operations, int target) {
        if (index == numbers.length) {
            int sum = 0;
            for (int i = 0; i < numbers.length; i++) {
                sum += operations.get(i) * numbers[i];
            }
            if (sum == target) {
                return 1;
            }
            return 0;
        }
        List<Integer> plus = new ArrayList<>(operations);
        plus.add(1);
        List<Integer> minus = new ArrayList<>(operations);
        minus.add(-1);
        
        int result = search(index + 1, numbers, plus, target);
        return result + search(index + 1, numbers, minus, target);
    }
}

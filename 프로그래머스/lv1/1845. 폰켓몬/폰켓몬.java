import java.util.*;
import java.util.function.*;
import java.util.stream.*;

class Solution {
    public int solution(int[] nums) {
        int poketmon = nums.length / 2;
        Map<Integer, Long> map = Arrays.stream(nums)
            .boxed()
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        
        int answer = Math.min(map.size(), poketmon);
        return answer;
    }
}
import java.util.*;
import java.util.stream.*;

class Solution {
    public int[] solution(int N, int[] stages) {        
        List<Stage> failures = new ArrayList<>();
        for (int i = 1; i < N + 1; i++) {
            
            double challenged = 0;
            double failed = 0;
            for (int stage : stages) {
                if (stage == i) {
                    failed++;
                }
                if (stage >= i) {
                    challenged++;
                }
            }
            double failure = failed / challenged;
            if (challenged == 0) {
                failure = 0;
            }
            
            failures.add(new Stage(failure, i));
        }
        
        failures.sort(Collections.reverseOrder());
        
        int[] answer = new int[N];
        
        for (int i = 0; i < N; i++) {
            answer[i] = failures.get(i).index;
        }
            
        return answer;
    }
    
    static class Stage implements Comparable<Stage> {
        double failure;
        int index;
        
        public Stage(double failure, int index) {
            this.failure = failure;
            this.index = index;
        }
        
        @Override
        public int compareTo(Stage o) {
            if (this.failure - o.failure == 0) {
                return o.index - this.index;
            }
            if (this.failure - o.failure < 0) {
                return -1;
            }
            return 1;
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(index);
        }
    }
}
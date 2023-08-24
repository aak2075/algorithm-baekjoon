import java.util.*;
import java.util.stream.*;
import java.util.Map.*;
import java.util.Set.*;

class Solution {
    public int solution(String[] words) {
        int answer = 0;
        TreeSet<String> tree = new TreeSet<>();
        
        for (String word : words) {
            tree.add(word);
        }
        
        for (String word : words) {
            int length = 0;
            // 가장 작은 경우
            if (tree.lower(word) == null) {
                // 현재가 다음것에 포함되는 경우
                if (tree.higher(word).startsWith(word.substring(0, 1))) {
                    length = 1;
                    for (int i = 2; i <= word.length(); i++) {
                        if (!tree.higher(word).startsWith(word.substring(0, i))) {
                            length = i;
                            break;
                        }
                        length = i;
                    }
                // 현재가 다음것에 포함되지 않는 경우
                } else {
                    length = 1;
                }
                answer += length;
                continue;
            }
            
            // 가장 큰 경우
            if (tree.higher(word) == null) {
                // 현재가 이전것을 포함하는 경우
                if (word.startsWith(tree.lower(word).substring(0, 1))) {
                    length = 1;
                    for (int i = 2; i <= tree.lower(word).length(); i++) {
                        if (!word.startsWith(tree.lower(word).substring(0, i))) {
                            length = i;
                            break;
                        }
                        length = i + 1;
                    }
                // 현재가 이전것을 포함하지 않는 경우
                } else {
                    length = 1;
                }
                answer += length;
                continue;
            }
            
            int lower = 0;
            int higher = 0;
            // 중간인 경우 이전것의 substring과 다음것의 substring중 큰것.
            if (word.startsWith(tree.lower(word).substring(0, 1))) {
                lower = 1;
                for (int i = 2; i <= tree.lower(word).length(); i++) {
                    if (!word.startsWith(tree.lower(word).substring(0, i))) {
                        lower = i;
                        break;
                    }
                    lower = i + 1;
                }
            // 현재가 이전것을 포함하지 않는 경우
            } else {
                lower = 1;
            }
        
            if (tree.higher(word).startsWith(word.substring(0, 1))) {
                higher = 1;
                for (int i = 2; i <= word.length(); i++) {
                    if (!tree.higher(word).startsWith(word.substring(0, i))) {
                        higher = i;
                        break;
                    }
                    higher = i;
                }
            // 현재가 다음것에 포함되지 않는 경우
            } else {
                higher = 1;
            }
            
            length = Math.max(lower, higher);
            answer += length;     
        }

        return answer;
    }

}

// go를 찾으려면 모든 문자열을 찾아가면서 문자열의 길이보다 작을 동안 일치하는 최대의 개수 + 1을 센다.
// 모든 문자를 한 번 탐색해서 알아내야 함.
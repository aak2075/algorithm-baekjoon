import java.util.*;

class Solution {
    public String solution(int[] numbers, String hand) {
        List<List<Integer>> key = new ArrayList<>();
        List<Integer> line = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            line.add(i);
            if (i % 3 == 0) {
                key.add(line);
                line = new ArrayList<>();
            }
        }
        key.get(3).set(0, 10);
        key.get(3).set(1, 0);
        key.get(3).set(2, 11);
        
        int leftX = 0;
        int leftY = 3;
        int rightX = 2;
        int rightY = 3;
        
        
        StringBuilder sb = new StringBuilder();
        for (int n : numbers) {
            List<Integer> position = positionAt(key, n);
            if (n == 1 || n == 4 || n == 7) {
                leftY = position.get(0);
                leftX = position.get(1);
                sb.append("L");
            } else if (n == 3 || n == 6 || n == 9) {
                rightY = position.get(0);
                rightX = position.get(1);
                sb.append("R");
            } else {
                int lDistance = Math.abs(position.get(0) - leftY) + Math.abs(position.get(1) - leftX);
                int rDistance = Math.abs(position.get(0) - rightY) + Math.abs(position.get(1) - rightX);
                if (lDistance > rDistance) {
                    rightY = position.get(0);
                    rightX = position.get(1);
                    sb.append("R");
                } else if (rDistance > lDistance) {
                    leftY = position.get(0);
                    leftX = position.get(1);
                    sb.append("L");
                } else {
                    if (hand.equals("right")) {
                        rightY = position.get(0);
                        rightX = position.get(1);
                        sb.append("R");
                    } else {
                        leftY = position.get(0);
                        leftX = position.get(1);
                        sb.append("L");
                    }
                }
            }
        }
        return sb.toString();
    }
    
    private List<Integer> positionAt(List<List<Integer>> key, int n) {
        for (int i = 0; i < key.size(); i++) {
            for (int j = 0; j < key.get(0).size(); j++) {
                if (key.get(i).get(j) == n) {
                    return List.of(i, j);
                }
            }
        }
        
        return null;
    }
}

// 키패드 리스트를 만듬
// 각 손의 좌표를 
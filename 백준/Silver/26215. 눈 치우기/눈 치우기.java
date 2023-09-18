import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        List<Integer> a = Arrays.stream(sc.nextLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        int answer = 0;
        for (int i = 0; i < 2000; i++) {
            if (answer > 1440) {
                break;
            }

            int max1Count = 0;
            int max1j = -1;
            int max2j = -1;
            int max1 = 0;
            int max2 = 0;
            for (int j = 0; j < a.size(); j++) {
                if (a.get(j) > max1) {
                    max1 = a.get(j);
                    max1j = j;
                }
            }

            for (int j = 0; j < a.size(); j++) {
                if (a.get(j) == max1) {
                    max1Count++;
                }
            }

            for (int j = 0; j < a.size(); j++) {
                if (a.get(j) > max2 && a.get(j) < max1) {
                    max2 = a.get(j);
                    max2j = j;
                }
            }

            if (max1Count == 0) {
                break;
            }
            if (max1 == 0) {
                break;
            }
            if (max1Count == 1 && max2 != 0) {
                a.set(max1j, max1 - max2);
                a.set(max2j, 0);
                answer += max2;
                continue;
            }
            if (max1Count == 1 && max2 == 0) {
                a.set(max1j, 0);
                answer += max1;
                break;
            }
            if (max1Count > 1) {
                int cnt = 0;
                for (int j = 0; j < a.size(); j++) {
                    if (cnt == 2) {
                        break;
                    }
                    if (a.get(j) == max1) {
                        a.set(j, 0);
                        cnt++;
                    }
                }
                answer += max1;
                continue;
            }
        }
        if (answer > 1440) {
            answer = -1;
        }

        System.out.println(answer);
    }
}

// 가장 큰 수 max1와 그 다음 큰 수 max2를 찾는다.
// 모든 수가 0 이하인 경우 종료
// max2가 없는 경우 max1을 빼고 answer += max1
// max1과 max2가 존재하는 경우
// max2만큼 max1과 max2에서 뺀다.
// answer에 max2를 더한다.
// answer > 1440 이면 -1 리턴

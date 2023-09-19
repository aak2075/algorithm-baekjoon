import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCase = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < testCase; i++) {
            List<List<Integer>> applicants = new ArrayList<>();
            int applicantsCount = Integer.parseInt(sc.nextLine());
            for (int j = 0; j < applicantsCount; j++) {
                List<Integer> score = Arrays.stream(sc.nextLine().split(" "))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());

                applicants.add(score);
            }

            List<List<Integer>> sortedApplicants = applicants.stream()
                    .sorted(Comparator.comparingInt(score -> score.get(0)))
                    .collect(Collectors.toList());

            int cnt = 1;
            int max = sortedApplicants.get(0).get(1);
            for (int j = 1; j < sortedApplicants.size(); j++) {
                if (sortedApplicants.get(j).get(1) <= max) {
                    max = sortedApplicants.get(j).get(1);
                    cnt++;
                }
            }

            System.out.println(cnt);
        }
    }
}

// 서류 등수를 기준으로 오름차순 정렬한다.
// 서류 등수가 낮으면 면접 점수라도 높아야 뽑힌다.
// 서류 등수를 하나씩 내려가면서 면접 점수가 높아지는 애들만 뽑는다.

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Double> gradePoint = Map.of(
                "A+", 4.5,
                "A0", 4.0,
                "B+", 3.5,
                "B0", 3.0,
                "C+", 2.5,
                "C0", 2.0,
                "D+", 1.5,
                "D0", 1.0,
                "F", 0.0
        );

        double majorScore = 0;
        double totalCredit = 0;
        for (int i = 0; i < 20; i++) {
            String[] input = scanner.nextLine().split(" ");
            if(input[2].equals("P")) {
                continue;
            }
            double credit = Double.parseDouble(input[1]);
            double gradeAverage = gradePoint.get(input[2]);
            totalCredit += credit;
            majorScore += credit * gradeAverage;
        }
        double result = majorScore / totalCredit;
        System.out.printf("%.6f", result);
    }
}
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Solution {
    public long solution(String expression) {
        List<Long> numbers = Arrays.stream(expression.split("[-*+]"))
                .map(Long::parseLong)
                .collect(Collectors.toList());
        List<String> operations = Arrays.stream(expression.split("[0-9]+"))
                .collect(Collectors.toList());
        operations.remove(0);

        List<List<String>> priorities = new ArrayList<>();
        priorities.add(List.of("*", "+", "-"));
        priorities.add(List.of("*", "-", "-"));
        priorities.add(List.of("+", "*", "-"));
        priorities.add(List.of("+", "-", "*"));
        priorities.add(List.of("-", "*", "+"));
        priorities.add(List.of("-", "+", "*"));

        long max = 0;

        for (int i = 0; i < priorities.size(); i++) {

            List<Long> tmpNumbers = new ArrayList<>(numbers);
            List<String> tmpOp = new ArrayList<>(operations);
            for (int j = 0; j < priorities.get(0).size(); j++) {
                String oper = priorities.get(i).get(j);

                for (int k = 0; k < tmpOp.size(); k++) {
                    if (tmpOp.get(k).equals(oper)) {
                        long result = calc(tmpOp.get(k), tmpNumbers.get(k), tmpNumbers.get(k + 1));
                        tmpOp.remove(k);
                        tmpNumbers.set(k, result);
                        tmpNumbers.remove(k + 1);
                        k = -1;
                    }
                }

            }
            max = Math.max(max, Math.abs(tmpNumbers.get(0)));

        }
        return max;
    }

    private long calc(String oper, long a, long b) {
        switch (oper) {
            case "*":
                return a * b;
            case "+":
                return a + b;
            case "-":
                return a - b;
        }

        return -1;
    }
}


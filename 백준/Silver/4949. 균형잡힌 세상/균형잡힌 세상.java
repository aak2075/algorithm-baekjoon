import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            Deque<String> stack = new ArrayDeque<>();
            String line = br.readLine();
            if (line.equals(".")) {
                break;
            }
            String[] split = line.split("");
            for (String s : split) {
                if (stack.isEmpty() && (s.equals("(") || s.equals(")") || s.equals("[") || s.equals("]"))) {
                    stack.push(s);
                    continue;
                }
                if (s.equals("(") || s.equals("[")) {
                    stack.push(s);
                    continue;
                }
                if (stack.isEmpty()) {
                    continue;
                }
                if (s.equals(")") && stack.peekFirst().equals("(")) {
                    stack.pop();
                    continue;
                }
                if (s.equals("]") && stack.peekFirst().equals("[")) {
                    stack.pop();
                    continue;
                }
                if (s.equals("]") || s.equals(")")) {
                    stack.push(s);
                }
            }

            if (stack.isEmpty()) {
                System.out.println("yes");
            } else {
                System.out.println("no");
            }
        }
    }
}


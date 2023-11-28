import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        String[] splited = input.split("-");

        int result = 0;
        for (int i = 0; i < splited.length; i++) {
            String[] split = splited[i].split("\\+");

            int sum = 0;

            for (String s : split) {
                int number = Integer.parseInt(s);
                sum += number;
            }

            if (i == 0) {
                result += sum;
            } else {
                result -= sum;
            }
        }

        System.out.println(result);
    }
}

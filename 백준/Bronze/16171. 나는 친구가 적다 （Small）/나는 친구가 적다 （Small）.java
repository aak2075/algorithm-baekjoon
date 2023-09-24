import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        String expected = br.readLine();

        String result = input.replaceAll("[0-9]", "");

        if (result.contains(expected)) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }
}

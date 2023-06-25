import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        boolean result = true;
        for (int i = 0; i < input.length() / 2 + 1; i++) {
            if (input.charAt(i) != input.charAt(input.length() - i - 1)) {
                result = false;
            }
        }
        if(result) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }
}
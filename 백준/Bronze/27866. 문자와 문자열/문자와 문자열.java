import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        int at = Integer.parseInt(scanner.nextLine());

        System.out.println(str.charAt(at - 1));
    }
}

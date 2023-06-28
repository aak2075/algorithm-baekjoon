import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String word = scanner.nextLine();

        List<String> croatiaAlphabets = List.of("c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z=");

        for (String croatiaAlphabet : croatiaAlphabets) {
            if (word.contains(croatiaAlphabet)) {
                word = word.replace(croatiaAlphabet, "*");
            }
        }
        System.out.println(word.length());
    }
}
import java.util.Scanner;

public class Main {

    private static final int KING = 1;
    private static final int QUEEN = 1;
    private static final int ROOK = 2;
    private static final int BISHOP = 2;
    private static final int KNIGHT = 2;
    private static final int PAWN = 8;

    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split(" ");
        int king = Integer.parseInt(input[0]);
        int queen = Integer.parseInt(input[1]);
        int rook = Integer.parseInt(input[2]);
        int bishop = Integer.parseInt(input[3]);
        int knight = Integer.parseInt(input[4]);
        int pawn = Integer.parseInt(input[5]);

        String kingResult = (KING - king) + " ";
        String queenResult = (QUEEN - queen) + " ";
        String rookResult = (ROOK - rook) + " ";
        String bishopResult = (BISHOP - bishop) + " ";
        String knightResult = (KNIGHT - knight) + " ";
        String pawnResult = (PAWN - pawn) + "";

        System.out.println(kingResult + queenResult + rookResult + bishopResult + knightResult + pawnResult);
    }
}
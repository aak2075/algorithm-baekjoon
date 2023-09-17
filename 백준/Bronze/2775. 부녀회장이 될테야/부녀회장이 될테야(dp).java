import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCase = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < testCase; i++) {
            int k = Integer.parseInt(sc.nextLine());
            int n = Integer.parseInt(sc.nextLine());

            System.out.println(dp(k, n));
        }
    }

    private static int dp(int floor, int room) {
        if (floor == 0) {
            return room;
        }
        if (room == 1) {
            return 1;
        }

        return dp(floor, room - 1) + dp(floor - 1, room);
    }
}

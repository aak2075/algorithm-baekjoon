import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] split = bf.readLine().split(" ");

        int start = toMinute(split[0]);
        int end = toMinute(split[1]);
        int realEnd = toMinute(split[2]);

        Map<String, Integer> map = new HashMap<>();
        Map<String, Boolean> visited = new HashMap<>();

        while (bf.ready()) {
            String[] line = bf.readLine().split(" ");
            int chatTime = toMinute(line[0]);
            String user = line[1];
            if (chatTime <= start) {
                map.put(user, chatTime);
            } else if(chatTime > start && chatTime >= end && chatTime <= realEnd) {
                if (map.containsKey(user)) {
                    visited.computeIfAbsent(user, i -> true);
                }
            }
        }

        System.out.println(visited.size());
    }

    private static int toMinute(String time) {
        String[] split = time.split(":");
        int hour = Integer.parseInt(split[0]);
        int minute = Integer.parseInt(split[1]);
        return hour * 60 + minute;
    }
}

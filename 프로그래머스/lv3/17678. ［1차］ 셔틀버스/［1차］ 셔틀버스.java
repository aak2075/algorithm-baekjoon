import java.util.*;
import java.util.stream.*;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        Comparator<String> timeComparator = (time1, time2)  -> {
            if (isLessThan(time1, time2)) {
                return -1;
            } else {
                return 1;
            }
        };
        
        String suttleTime = "09:00";
        
        Map<String, List<String>> suttles = new LinkedHashMap<>();
        
        for (int i = 0; i < n; i++) {
            suttles.put(suttleTime, new ArrayList<>());
            suttleTime = plusTime(suttleTime, t);
            if (!isLessThan(suttleTime, "23:59")) {
                break;
            }
        }
        
        List<String> crews = Arrays.stream(timetable)
            .sorted(timeComparator)
            .collect(Collectors.toList());
        
        System.out.println(crews);

        for(String crew : crews) {
            for(String suttle : suttles.keySet()) {
                if (suttles.get(suttle).size() < m && isLessThan(crew, suttle)) {
                    suttles.get(suttle).add(crew);
                    break;
                }
            }
        }
           
        String answer = "";
        
        List<String> reverse = suttles.keySet().stream()
            .sorted(Collections.reverseOrder())
            .collect(Collectors.toList());
        
        for(String suttle : reverse) {
            // 막차가 꽉찬 경우 막차의 꼴찌보다 1분 일찍 온다.
            if (suttles.get(suttle).size() == m) {
                List<String> boarded = suttles.get(suttle);
                String max = "00:00";
                for(String crew : boarded) {
                    if (isLessThan(max, crew)) {
                        max = crew;
                    }
                }
                
                answer = minusTime(max, 1);
                break;
            }
            
            // 막차에 자리가 있는 경우
            if (suttles.get(suttle).size() < m) {
                answer = suttle;
                break;
            }
        }
        
        return answer;
    }
    
    private String plusTime(String time, int other) {
        String[] splitied = time.split(":");
        int hour = Integer.parseInt(splitied[0]);
        int minute = Integer.parseInt(splitied[1]);
        
        if(minute + other > 59) {
            hour++;
            minute = minute + other - 60;
            return toTimeFormat(hour) + ":" + toTimeFormat(minute);
        }
        
        return toTimeFormat(hour) + ":" + toTimeFormat(minute + other);
    }
    
    private String minusTime(String time, int other) {
        String[] splitied = time.split(":");
        int hour = Integer.parseInt(splitied[0]);
        int minute = Integer.parseInt(splitied[1]);
        
        if(minute - other < 0) {
            hour--;
            minute = 60 - (other - minute) ;
            return toTimeFormat(hour) + ":" + toTimeFormat(minute);
        }
        
        return toTimeFormat(hour) + ":" + toTimeFormat(minute - other);
    }
    
    private String toTimeFormat(int time) {
        if (time < 10) {
            return "0" + time;
        }
        return "" + time;
    }
    
    private boolean isLessThan(String a, String b) {
        String[] aTime = a.split(":");
        String[] bTime = b.split(":");
        
        if(Integer.parseInt(aTime[0]) < Integer.parseInt(bTime[0])) {
            return true;
        } else if(aTime[0].equals(bTime[0]) && Integer.parseInt(aTime[1]) <= Integer.parseInt(bTime[1])) {
            return true;
        }
        
        return false;
    }
}

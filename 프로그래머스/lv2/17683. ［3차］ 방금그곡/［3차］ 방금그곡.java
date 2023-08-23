import java.util.*;
import java.util.stream.*;

class Solution {
    public String solution(String m, String[] musicinfos) {
        String answer = "(None)";
        
        Map<String, String> shop = new HashMap<>();
        shop.put("C#", "Q");
        shop.put("D#", "W");
        shop.put("F#", "Y");
        shop.put("G#", "R");
        shop.put("A#", "T");
        
        List<String> titles = new ArrayList<>();
        List<Integer> durs = new ArrayList<>();
        
        
        m = changeShop(m, shop);
        
        for (String musicinfo : musicinfos) {
            String[] info = musicinfo.split(",");
            String[] start = info[0].split(":");
            String[] end = info[1].split(":");
            String title = info[2];
            String sheet = info[3];
        
            sheet = changeShop(sheet, shop);
            
            int startMinute = Integer.parseInt(start[0]) * 60 + Integer.parseInt(start[1]);
            int endMinute = Integer.parseInt(end[0]) * 60 + Integer.parseInt(end[1]);
            
            int dur = endMinute - startMinute;
            
            String fullSheet = sheet;
            
            if (sheet.length() >= dur) {
                fullSheet = sheet;
            } else {
                while (fullSheet.length() < dur) {
                    fullSheet = fullSheet + sheet;
                }
            }
            
            fullSheet = fullSheet.substring(0, dur);
            if (fullSheet.contains(m)) {
                titles.add(title);
                durs.add(dur);
            }
        }
        
        int max = 0;
        for (int i = 0; i < titles.size(); i++) {
            if (durs.get(i) > max) {
                max = durs.get(i);
                answer = titles.get(i);
            }
        }
        
        return answer;
    }
    
    private String changeShop(String target, Map<String, String> shop) {
        for (String s : shop.keySet()) {
            target = target.replace(s, shop.get(s));
        }
        
        return target;
    }
}
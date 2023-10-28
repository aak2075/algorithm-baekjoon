import java.util.*;
import java.util.stream.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        Map<String, Car> usingNow = new HashMap<>();
        Map<String, Integer> accTimes = new HashMap<>();
        
        for (String record : records) {
            String[] split = record.split(" ");
            String time = split[0];
            String number = split[1];
            
            if (!usingNow.containsKey(number)) {
                usingNow.put(number, new Car(number, time));
            } else {
                Car car = usingNow.get(number);
                car.setOutTime(time);
                int useTime = car.useTime();
                
                if (accTimes.containsKey(number)) {
                    int accTime = accTimes.get(number);
                    accTimes.replace(number, accTime + useTime);
                } else {
                    accTimes.put(number, useTime);
                }
                usingNow.remove(number);
            }
        }
        
        for (Map.Entry<String, Car> entry : usingNow.entrySet()) {
            String number = entry.getKey();
            Car car = entry.getValue();
            car.setOutTime("23:59");
            int useTime = car.useTime();
            
            if (accTimes.containsKey(number)) {
                int accTime = accTimes.get(number);
                accTimes.replace(number, accTime + useTime);
            } else {
                accTimes.put(number, useTime);
            }
        }
        
        Map<String, Integer> resultPrices = new HashMap<>();
        for (Map.Entry<String, Integer> accTime : accTimes.entrySet()) {
            String number = accTime.getKey();
            int time = accTime.getValue();
            
            if (time <= fees[0]) {
                resultPrices.put(number, fees[1]);
            } else {
                int overTime = time - fees[0];
                
                double unitTmp = (double) overTime / fees[2];
                int unit = (int) Math.ceil(unitTmp);
                int fee = fees[3] * unit + fees[1];
                
                resultPrices.put(number, fee);
            }
        }
        
        return resultPrices.entrySet().stream()
            .sorted(Comparator.comparing(Map.Entry::getKey))
            .mapToInt(e -> e.getValue())
            .toArray();
    }
    
    static class Car {
        int number;
        int inTime;
        int outTime;
        
        public Car(String number, String inTime) {
            this.number = Integer.parseInt(number);
            this.inTime = toTime(inTime);
        }
        
        private int toTime(String time) {
            String[] split = time.split(":");
            return Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);
        }
        
        public void setOutTime(String outTime) {
            this.outTime = toTime(outTime);
        }
        
        public int useTime() {
            return outTime - inTime;
        }
    }
}

// 입차 후 출차 안하면 23:59 출차


// 차량 번호가 작은 자동차부터 

// 입차면 넣고
// 출차이면 누적 시간 계산 
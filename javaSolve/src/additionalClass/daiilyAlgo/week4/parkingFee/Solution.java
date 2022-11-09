package additionalClass.daiilyAlgo.week4.parkingFee;

import java.util.HashMap;
import java.util.Set;


public class Solution {
    static HashMap<String, Float> timeAcc = new HashMap<>();

    public int[] solution(int[] fees, String[] records) {
        HashMap<String, ParkingInfo> restParKingList = parseRecode(records);
        if (restParKingList.size() != 0) {
            processRest(restParKingList);
        }
        System.out.println(timeAcc);
        // 기본 단위보다 작다
        // 기본 요금만 낸다

        // 기본 단위 보다 크다
        // 기본단위 만큼 뺀다
        // 뺀거에서 단위 시간 으로 나눈다 나눈 몪은 올림한다
        // 몪만큼 요금을 곱한다
        // 그리고 기본요금을 더한다.
        return new int[]{};
    }

    private void processRest(HashMap<String, ParkingInfo> restParKingList) {
        //있는데 안나간거 처리
        // 23:59 - 남아 있는 시간
        float maxMin = 23 * 60 + 59;
        for (String key : restParKingList.keySet()) {
            // 만약에 timeAcc 에 있다면 이전에 온게 있으니 누산해야하고
            // timeAcc 에 없다면 온적 없으니 put 한다
            ParkingInfo curInfo = restParKingList.get(key);
            if (!timeAcc.containsKey(key)) {
                timeAcc.put(key, maxMin - curInfo.min);
            } else {
                timeAcc.put(key, timeAcc.get(key) + maxMin - curInfo.min);
            }

        }

    }

    private HashMap<String, ParkingInfo> parseRecode(String[] records) {
        HashMap<String, ParkingInfo> res = new HashMap<>();
        for (String infoString : records) {
            String[] splitInfo = infoString.split(" ");
            String Time = splitInfo[0];
            String[] splitTime = Time.split(":");
            int hour = Integer.parseInt(splitTime[0]);
            int min = Integer.parseInt(splitTime[1]);
            min += hour * 60;
            String number = splitInfo[1];
            if (!res.containsKey(number)) {
                // 없으면 넣고
                res.put(number, new ParkingInfo(min, number));
            } else {
                System.out.println(number);
                // 있다면 빼버려라// 이렇게 하면 out 만 빼넬수 있다.
                calculateTimeDiff(res.get(number), new ParkingInfo(min, number));

                res.remove(number);
            }
        }

        return res;
    }

    // 자동차별 총 누적시간당 요금을 구해야한다.
    private void calculateTimeDiff(ParkingInfo parkingInfo, ParkingInfo parkingInfoTarget) {
        System.out.println(parkingInfo + "vs" + parkingInfoTarget + " = " + (parkingInfoTarget.min - parkingInfo.min));
        float diff = parkingInfoTarget.min - parkingInfo.min;
        if (!timeAcc.containsKey(parkingInfo.number)) {

            timeAcc.put(parkingInfo.number, diff);
        } else {
            timeAcc.put(parkingInfo.number, timeAcc.get(parkingInfo.number) + diff);
        }
    }

    static class ParkingInfo {
        float min;
        String number;
        public ParkingInfo(float min, String number) {
            this.min = min;
            this.number = number;
        }
        @Override
        public String toString() {
            return "ParkingInfo{" +
                    "min=" + min +
                    ", number=" + number +
                    '}';
        }
    }
}

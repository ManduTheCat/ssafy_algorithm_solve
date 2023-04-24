package programers.runner;

import java.util.*;

public class Solution {

    public String[] solution(String[] players, String[] callings) {
        String[] answer = new String[players.length];
        Map<String, Integer> rankMap = new HashMap<>();
        for (int i = 0; i < players.length; i++) {
            rankMap.put(players[i], i);
        }
        for (String calling : callings) {
            Integer curRank = rankMap.get(calling);
            String beforeName = null;
            for (String s : rankMap.keySet()) {
                if(rankMap.get(s) == curRank - 1){
                    beforeName = s;
                }
            }

            rankMap.put(calling, rankMap.get(calling) - 1);
            rankMap.put(beforeName, rankMap.get(beforeName) +1);
        }
        for (String s : rankMap.keySet()) {
            answer[rankMap.get(s)] = s;
        }

        return answer;
    }
}
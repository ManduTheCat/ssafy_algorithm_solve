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
            String beforeName = players[curRank-1];

            // swap
            players[curRank-1] = calling;
            players[curRank] = beforeName;
            rankMap.put(calling, curRank-1); // 랭크를 앞으로 땡기고
            rankMap.put(beforeName, rankMap.get(beforeName) +1);
            // System.out.println(rankMap);
            // System.out.println(Arrays.toString(players));
        }
        for (String s : rankMap.keySet()) {
            answer[rankMap.get(s)] = s;
        }

        return answer;
    }
}
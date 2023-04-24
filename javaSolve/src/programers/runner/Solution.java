package programers.runner;

import java.util.*;

class Solution {
    public String[] solution(String[] players, String[] callings) {
        String[] answer = {};

        HashMap<Integer, String> rank = new HashMap<>();

        for(int i = 0; i < players.length; ++i) {
            rank.put(i+1, players[i]);
        }


        return answer;
    }
}
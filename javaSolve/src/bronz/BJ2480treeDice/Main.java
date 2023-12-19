package bronz.BJ2480treeDice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args)  throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        Map<Integer, Integer> countDice = new HashMap<>();
        for(int i = 0; i <3; i++){
            int input = Integer.parseInt(st.nextToken());
            if(countDice.containsKey(input)){
                countDice.put(input, countDice.get(input) + 1);
            }else {
                countDice.put(input, 1);
            }
        }
        int maxCount = 0;
        int maxDice = 0;
        for(int key:countDice.keySet()){
            if(maxCount < countDice.get(key)){
                maxCount = countDice.get(key);
                maxDice = key;
            }

        }

        if(maxCount == 3){
            System.out.println(10000 + (maxDice * 1000));

        }else if(maxCount == 2){
            System.out.println(1000 + (maxDice * 100));
        } else {
            int max = 0;
            for(int key:countDice.keySet()){
                max = Math.max(key, max);
            }
            System.out.println(max * 100);
        }
    }
}

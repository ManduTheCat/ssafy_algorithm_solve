package silver.BJ1475;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        Map<Integer, Integer> counts = new HashMap<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split("");
        for (String s : input) {
            int num = Integer.parseInt(s);
            if (counts.containsKey(num)) {
                counts.put(num, counts.get(num) + 1);
            } else {
                counts.put(num, 1);
            }
        }
        int maxCount = 0;

        for (int key : counts.keySet()) {
            if (key != 9 && key != 6 && maxCount < counts.get(key)) {
                maxCount = counts.get(key);
            }
        }
        int res = maxCount;
        if (counts.containsKey(6) && counts.containsKey(9)) {
            res = Math.max(maxCount,
                    (counts.get(6) + counts.get(9)) % 2 != 0 ? ((counts.get(6) + counts.get(9)) / 2) + 1 : (counts.get(6) + counts.get(9)) / 2);
        } else if (!counts.containsKey(9) && counts.containsKey(6)) {
            res = Math.max(maxCount, counts.get(6) %2 !=0 ?(counts.get(6) / 2) + 1: (counts.get(6) / 2));
        } else if (!counts.containsKey(6) && counts.containsKey(9)) {
            res = Math.max(maxCount, counts.get(9) %2 !=0 ?(counts.get(9) / 2) + 1: (counts.get(9) / 2));
        }
        System.out.println(res);

    }
}

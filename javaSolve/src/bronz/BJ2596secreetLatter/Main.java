package bronz.BJ2596secreetLatter;


import java.io.*;
import java.util.*;

/*
A 000000
B 001111
C 010011
D 011100
E 100110
F 101001
G 110101
H 111010
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int stringCount = Integer.parseInt(br.readLine());
        String input = br.readLine();
        StringBuilder res = new StringBuilder();
        Map<String, String> dict = new HashMap<>();
        dict.put("000000", "A");
        dict.put("001111", "B");
        dict.put("010011", "C");
        dict.put("011100", "D");
        dict.put("100110", "E");
        dict.put("101001", "F");
        dict.put("110101", "G");
        dict.put("111010", "H");

        int idx = 0;
        for (int n = 0; n < stringCount; n++) {
            StringBuilder chunk = new StringBuilder();
            for (int m = 0; m < 6; m++) {
                chunk.append(input.charAt(idx));
                idx++;
            }
            if (dict.containsKey(chunk.toString())) { // n(1) 으로 찾는다
                res.append(dict.get(chunk.toString()));
            } else { // 같은게 없다면
                String minErr = findMinErrKey(dict, chunk.toString()); // 에러가 2개이상만 있는지 판단해야한다.
                if (minErr.equals("-1")) {// 가능한게 없다면 틀린 문자열 출력
                    System.out.println(n+1);
                    return;
                }else {
                    res.append(dict.get(minErr));
                }
            }
        }
        System.out.println(res);
    }

    private static String findMinErrKey(Map<String, String> dict, String chunk) {
        // 최소의 오류를 가지는 key를 구하는 함수 만약 2개 이상의 에러면  -1 을 리턴해 가능한것이 없다 판단.
        Map<String, Integer> countMap = new HashMap<>();
        for (String key : dict.keySet()) {
            int count = 0;
            for (int i = 0; i < key.length(); i++) {
                if (chunk.charAt(i) != key.charAt(i)) {
                    count++;
                }
            }
            countMap.put(key, count);
        }
        int min = 999;
        String minKey = "-1";
        for (String key : countMap.keySet()) {
            if (min > countMap.get(key)) {
                min = countMap.get(key);
                minKey = key;
            }
        }
        if (min < 2) {
            return minKey;
        } else {
            return "-1";
        }
    }


}

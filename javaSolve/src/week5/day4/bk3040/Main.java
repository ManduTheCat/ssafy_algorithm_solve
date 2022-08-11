package week5.day4.bk3040;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author 김명진
 * 조합을 활용한 풀이
 * 11540KB	76ms
 */
public class Main {
    static int[] hatNums = new int[9];
    static int[] resultIndexArray = new int[7];

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/week5/day4/bk3040/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int tc = 0; tc < 9; tc++) {
            hatNums[tc] = Integer.parseInt(br.readLine());
        }
        combi(0, 0);
    }

    public static void combi(int start, int depth) {
        if (depth == 7) {
            int sum = 0;
            for (int idx : resultIndexArray) {
                sum += hatNums[idx];
            }
            if (sum == 100) {
                for (int idx : resultIndexArray) System.out.println(hatNums[idx]);
            }
            return;
        }
        for (int i = start; i < 9; i++) {
            resultIndexArray[depth] = i;
            combi(i + 1, depth + 1);
        }
    }
}

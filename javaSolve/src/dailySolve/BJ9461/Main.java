package dailySolve.BJ9461;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;

public class Main {
    static int Tc;
    static int inputNum;
    static long[] dp;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/daily/BJ9461/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Tc = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < Tc; tc++) {
            inputNum = Integer.parseInt(br.readLine());
            dp = new long[100+1];
            dp[0] = 1;
            dp[1] = 1;
            dp[2] = 1;
            dp[3] = 2;
            dp[4] = 2;
            dp[5] = 3;
            dp[6] = 4;
            dp[7] = 5;
            dp[8] = 7;
            dp[9] = 9;
            for (int i = 10; i <= inputNum; i++) {
                dp[i] = dp[i - 2] + dp[i - 3];
            }
            System.out.println(dp[inputNum-1]);
        }

    }

}

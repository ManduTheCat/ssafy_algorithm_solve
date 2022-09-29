package week12.day1.makeOne;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        dp = new int[n + 1];
        Arrays.fill(dp, 999999999);
        dp[1] = 1;
        /// dp 정의: i 번째 숫자일때 1로 만드는 최소 연산횟수
        for (int i = 2; i <= n; i++) {
            // 나누어 떨어진다면 이전에 연산 했으니 이전에 최소 연산횟수를 구한 dp에서 가져와 비교하한다
            if (i % 2 == 0) dp[i] = Math.min(dp[i], dp[i / 2] + 1);
            if (i % 3 == 0) dp[i] = Math.min(dp[i], dp[i / 3] + 1);
            dp[i] = Math.min(dp[i], dp[i - 1] + 1);
        }
        System.out.println(dp[n]);
        //System.out.println(Arrays.toString(dp));
    }

}

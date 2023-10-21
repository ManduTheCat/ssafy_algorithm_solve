package SSAFY.week13.day3.WSLIS;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int Tc;
    static int N;
    static int[] arr;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/SSAFY.week13/day3/swea3307LIS/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Tc = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < Tc; tc++) {
            int max = 0;
            N = Integer.parseInt(br.readLine());
            arr = new int[N];
            dp = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int n = 0; n < N; n++) {
                arr[n] = Integer.parseInt(st.nextToken());
            }
            for (int i = 0; i < N; i++) {
                dp[i] = 1;
                for (int j = 0; j < i; j++) {
                    //i  전순서 숫자  && 뒤에 붙였을때 이전보다 커야한다 == 최대값유지
                    if (arr[j] < arr[i] && dp[i] < dp[j] + 1) {
                        dp[i] = dp[j] + 1;
                    }
                }
                max = Math.max(dp[i], max);

            }
            //System.out.println(Arrays.toString(dp));
            System.out.printf("#%d %d\n", tc + 1, max);


        }

    }
}

package week12.day1.stick;

public class Solution {
    static int N;
    static int[] dp;
    public static void main(String[] args) {
        N = 5;
        dp = new int[N+1];
        dp[1] = 2;
        dp[2] = 5;
        for (int i = 3; i <= N; i++) {
            // 이전경우에 붙이는경우수만 더해가는 형태
            // 현재길이 의 경우의 수 = 이전경우에 노랑 붙이는 경우 or 이전 경우에 파랑을 붙이는경우(-1cm) or 이전전 경우 (-2cm) 에 빨같을 붙이는경우
            dp[i] = dp[i -1] * 2 + dp[i-2];
        }
        System.out.println(dp[5]);
    }
}

package dp.pinartNumberBJ2193;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		long[][] dp = new long[N + 1][2];// i 번째가  0 또는 1이 되는 이친수 경우의수
		dp[1][0] = 0;
		dp[1][1] = 1;

		for (int i = 2; i <= N; i++) {
			dp[i][0] += dp[i - 1][0];
			dp[i][0] += dp[i - 1][1];
			dp[i][1] += dp[i - 1][0];
		}

		System.out.println(dp[N][0] + dp[N][1] );
	}
}

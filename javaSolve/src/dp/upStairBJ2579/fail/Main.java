package dp.upStairBJ2579.fail;

import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] input;
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./resources/daily/Dp/BJ2579/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		input = new int[N + 1];
		dp = new int[N + 1][3]; // [][0] 은 한번도 밟지 않는 경우 [][1] 은 한번 밟고 오는 경우

		for (int n = 1; n <= N; n++) {
			int value = Integer.parseInt(br.readLine());
			input[n] = value;
		}


		if (N == 1) {
			System.out.println(input[1]);
			return;
		}

		dp[1][1] = input[1];
		dp[2][1] = input[1] + input[2];
		dp[2][2] = input[2];
		for (int i = 3; i <= N; i++) {
			dp[i][1] = Math.max(dp[i - 1][0] + input[i], dp[i][1]); // 1칸 밟고 오는 경우

			dp[i][2] = Math.max(dp[i - 1][1] + input[i], dp[i][2]); // 2칸 밟고 오는 경우

			dp[i][0] = Math.max(Math.max(dp[i - 2][1], dp[i - 2][2]), dp[i - 1][1]); // 밟지 않고 오는 경우

		}
		int res = Integer.MIN_VALUE;
		for (int num : dp[N]) {
			res = Math.max(res, num);
		}
		System.out.println(res);
	}
}

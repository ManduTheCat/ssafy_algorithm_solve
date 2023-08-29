package dp.upStairBJ2579.suc;

import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] input;
	static int[] dp;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./resources/daily/Dp/BJ2579/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		input = new int[N + 1];
		dp = new int[N + 1];
		// 연속 해서 3번 으로 넘어오는게 불가능 하기 떄문에 경우의 수는 두가지다
		// 두번뛰고 한번뛰기 해서 넘어오기
		// 두번뛰어서 넘어오기

		for (int n = 1; n <= N; n++) {
			int value = Integer.parseInt(br.readLine());
			input[n] = value;
		}
		if (N >= 1) dp[1] = input[1];
		if (N >= 2) dp[2] = input[1] + input[2];
		if (N >= 3) dp[3] = Math.max(input[1], input[2]) + input[3];

		for (int n = 4; n <= N; n++) {
			dp[n] = Math.max(dp[n - 3] + input[n - 1], dp[n - 2]) + input[n];
		}
		System.out.println(dp[N]);

	}
}


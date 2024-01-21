package silver.BJ2302.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {
	static int N, M;
	static long count = 1;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		Set<Integer> vipSet = new HashSet<>();
		int[] dp = new int[N + 1];
		for (int m = 0; m < M; m++) {
			int vip = Integer.parseInt(br.readLine());
			vipSet.add(vip);
		}
		dp[0] = 1;
		dp[1] = 1;
		for (int idx = 2; idx <= N; idx++) {
			if (vipSet.contains(idx)) {
				dp[idx] = 1;
				count *= dp[idx - 1];
			} else if (vipSet.contains(idx - 1)) {
				dp[idx] = 1;
			} else {
				dp[idx] = dp[idx - 1] + dp[idx - 2];
			}
		}
		count*= dp[N];
		// 3 2 2
		//System.out.println(Arrays.toString(dp));
		System.out.println(count);
	}
}

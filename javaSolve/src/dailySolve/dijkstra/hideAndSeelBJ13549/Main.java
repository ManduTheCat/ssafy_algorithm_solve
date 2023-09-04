package dailySolve.dijkstra.hideAndSeelBJ13549;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int S;
	static int T;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./resources/daily/dijkstra/BJ13549/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		int[] dp = new int[100_0001];
		Arrays.fill(dp, Integer.MAX_VALUE);
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		dp[S] = 0;
		pq.offer(S);
		while (!pq.isEmpty()) {
			int curr = pq.poll();
			if (curr * 2 <= 100_000 && dp[curr * 2] > dp[curr]) {
				dp[curr * 2] = dp[curr];
				pq.add(curr * 2);
			}
			if (curr + 1 <= 100_000  && dp[curr + 1] > dp[curr] + 1) {
				dp[curr + 1] = dp[curr] + 1;
				pq.add(curr + 1);
			}
			if (curr - 1 >= 0 && dp[curr - 1] > dp[curr] + 1) {
				dp[curr - 1] = dp[curr] + 1;
				pq.add(curr - 1);
			}

		}
		System.out.println(dp[T]);
	}
}

package dp.hotel;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Dev implements Comparable<Dev> {
	int cost;
	int customer;

	public Dev(int cost, int customer) {
		this.cost = cost;
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Dev{" +
			"cost=" + cost +
			", customer=" + customer +
			'}';
	}

	@Override
	public int compareTo(Dev o) {
		return Integer.compare(this.cost, o.cost);
	}
}

public class Main {
	static int N;
	static int C;
	static int[] dp; // 해당 값으로 올수 있는 최소비용
	static List<Dev> devList;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./resources/daily/DP/BJ1106/inputC.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		dp = new int[C + 101];
		Arrays.fill(dp, 1000);
		dp[0] = 0;
		devList = new ArrayList<>();
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			int cost = Integer.parseInt(st.nextToken());
			int customer = Integer.parseInt(st.nextToken());
			devList.add(new Dev(cost, customer));
		}
		for(int c = 0; c < C + 101; c++){
			for(Dev dev : devList){
				int nextNode = dev.customer + c;
				if(nextNode <= C + 100) {

					dp[nextNode] = Math.min(dp[nextNode], dp[c] + dev.cost);
				}
			}
		}
		// System.out.println(Arrays.toString(dp));
		int result = Integer.MAX_VALUE;
		for (int i = C; i <= C + 100; i++) {
			result = Math.min(result, dp[i]);
		}
		System.out.println(result);

	}



}

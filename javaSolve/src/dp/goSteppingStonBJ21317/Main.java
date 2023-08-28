package dp.goSteppingStonBJ21317;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int [][] input;
	static int powerStep;
	static int [][] dp;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./resources/daily/Dp/BJ21317/input.txt"));
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dp = new int[N][2];
		for(int i = 0; i < N ; i++){
			dp[i][0] = 100000;
			dp[i][1] = 100000;
		}
		dp[0][0] = 0;
		dp[0][1] = 0;
		input = new int[N][2];
		for(int n = 0;  n < N -1 ; n++){
			StringTokenizer st = new StringTokenizer(br.readLine());
			input[n][0] = Integer.parseInt(st.nextToken());
			input[n][1] = Integer.parseInt(st.nextToken());
		}
		powerStep = Integer.parseInt(br.readLine());
		// 다익스트라랑 비슷 dp[i][1] 은 큰점프 하고 도착하는 최소의 값
		// 다익스트라랑 비슷 dp[i][0] 은 큰점프 안하고 도착하는 최소의 값
		for(int idx = 0; idx < N-1; idx ++){
			// 다음 값으로 가는건 작은 입력 더해줘야한다.
			dp[idx + 1][0] = Math.min(dp[idx + 1][0], dp[idx][0] + input[idx][0]);
			dp[idx + 1][1] = Math.min(dp[idx + 1][1], dp[idx][1] + input[idx][0]);
			if(idx < N-2){
				// 다다음 값은 입력 2번 더해야한다.
				dp[idx + 2][0] = Math.min(dp[idx + 2][0], dp[idx][0] + input[idx][1]);
				dp[idx + 2][1] = Math.min(dp[idx + 2][1], dp[idx][1] + input[idx][1]);
			}
			if(idx < N -3){
				// 다음 + 3 경우의 수중 작은것  지금여기서 점프하냐 이전에 도착한 친구들중 더 작은게 있냐
				dp[idx + 3][1] = Math.min(dp[idx+3][1], dp[idx][0] + powerStep);

			}
		}

		System.out.println(Math.min(dp[N-1][0], dp[N-1][1]));

	}
}

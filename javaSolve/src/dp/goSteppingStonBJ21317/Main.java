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
		for(int idx = 0; idx < N-1; idx ++){
			// 다음것 은 현제 애서 한칸 가거나 , 슈퍼 점프후 한칸 가는거다
			dp[idx + 1][0] = Math.min(dp[idx + 1][0], dp[idx][0] + input[idx][0]);
			dp[idx + 1][1] = Math.min(dp[idx + 1][1], dp[idx][1] + input[idx][0]);
			// 다다음 칸은
			if(idx < N-2){
				dp[idx + 2][0] = Math.min(dp[idx + 2][0], dp[idx][0] + input[idx][1]);
				dp[idx + 2][1] = Math.min(dp[idx + 2][1], dp[idx][1] + input[idx][1]);
			}
			if(idx < N -3){
				dp[idx + 3][1] = Math.min(dp[idx+3][1], dp[idx][0] + powerStep);

			}
		}

		System.out.println(Math.min(dp[N-1][0], dp[N-1][1]));

	}
}

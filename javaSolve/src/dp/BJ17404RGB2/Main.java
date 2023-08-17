package dp.BJ17404RGB2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 고민흔적 안봐도 됩니다.
 * 모든 선택이 최소여야한다.
 * 1 번부터 N 번까지 선택하는데 2번부터 N 번까지 색이 같지 않아야한다
 * N 번을 선택할때는 N-1 1번과 색이 같지 않아야한다.
 * N 번과 1번과 2번과 같으면 안된다
 * N 은  1 번과 2 번이 색 같으면 안된다, N 은 N-1 과 1이 색 같으면 안된다.
 * N 은 rgb 중에 하나를 선택할때 1번과 2번이 선택한거 중아닌것 r g -> b , b가 r+b 보다 존나 큰값이면.. 최소를 보장할수 없다.
 * 그냥 진행하는게 답일까? 1 r 선택하고 N r 선택할수 있다 1 r 2 b 라면 N 은 r b 가 될수 없다. N 의 g 비용이 매우 크다면 g를 선택하지 않는 경우가
 * 이득이다.
 * 먼저 개들을 선택하고 나머지를 채우는건? 1,2,N-1,N 으로 구성된 int[][] dp를 채운다 최소보장
 * 나머지 최소보장하며 구한다.
 * dp의 N-1	을 결정할수 없다..
 * 조건에 맞는 것만 선택한걸 기억한다.
 *
 * 비교하는 값이 같아서 두가지다 선택이 가능한경우 1벅 의 r 과 b 값이 최소로 같다면 2번은 r 과 b 둘다 선택이 가능하다 중복선택을 고려해하는 문제 발생
 * 흐..
 *
 * 풀이
 * 결국 첫집과 마지막 집만 다르면되는데
 * 그냥 첫번째 색을 고정하고  모든경우를 구하기 3 * 10000 *3
 * 마지막에 처음 색과 마지막 색이 다른 경우만 구한다.
 */
public class Main {
	static int N;
	static int [][] dp;
	static int [][] input;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./resources/daily/Dp/BJ17404/input.txt"));
		BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		input = new int[N+ 1][3];
		dp = new int[N + 1][3];
		for(int n = 1; n <= N; n++){
			st = new StringTokenizer(br.readLine());
			int rv = Integer.parseInt(st.nextToken());
			int gb = Integer.parseInt(st.nextToken());
			int bv = Integer.parseInt(st.nextToken());
			input[n][0]= rv;
			input[n][1]= gb;
			input[n][2] = bv;

		}
		// 일단 작은 친구들부터1,2,N-1, N 을 구해보자
		// 2번째 라인 결정
		int ans = Integer.MAX_VALUE;
		for(int turn = 0; turn < 3; turn++){
			for(int fix = 0; fix < 3; fix ++){
				if(fix != turn){ // 턴이 아니라면 무한
					dp[1][fix] = 1000 * 1000 * 3;
				}else {// 고정할 차례가 오면 같다고한다.
					dp[1][fix] = input[1][fix];
				}
			}
			// 고정한체로 마지막까지 구하되 마지막에 같이 않은것중 최소를 구한다.
			for(int n = 2; n <= N; n++ ){
				dp[n][0] = Math.min(dp[n-1][1] + input[n][0], dp[n-1][2]+ input[n][0]);
				dp[n][1] = Math.min(dp[n-1][0] + input[n][1], dp[n-1][2]+ input[n][1]);
				dp[n][2] = Math.min(dp[n-1][0] + input[n][2], dp[n-1][1]+ input[n][2]);
			}
			for(int fix = 0; fix < 3; fix ++){ // 첫번째와 같은면안된다.
				if(fix != turn){
					ans = Math.min(ans, dp[N][fix]);
				}
			}
		}
		// for(int[] dpRow: dp){
		// 	System.out.println(Arrays.toString(dpRow));
		// }
		System.out.println(ans);


	}
}

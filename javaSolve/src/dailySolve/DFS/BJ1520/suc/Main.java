package dailySolve.DFS.BJ1520.suc;

import java.io.*;
import java.util.*;

/**
 * 탑다운 방식
 * dp[][] 해당칸에서 목적지 도착하는 경우
 * 1. 기록된게 없다면 재귀타고 찾아온다 끝 닿을때까지
 * 2.  처음 방문하면 0으로 바꿔줘라
 * 방문처리를 배열로 하면 메모리 초과 (계속 넘겨줘야한다.)구할때 -1 이면 찾으러 가라 하자 like 피보나치 + 메모이제이션
 * 내려막이기때문에 역주행 막을수 있다
 */
public class Main {
	static int N;
	static int M;
	static int[][] dp;
	static int[][] input;
	static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("resources/daily/dfs/BJ1520/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		dp = new int[M][N];
		input = new int[M][N];
		for (int[] d : dp) {
			Arrays.fill(d, -1);
		}
		for (int row = 0; row < M; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < N; col++) {
				input[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		// printArr(input);
		System.out.println(dfs(0, 0));
		// printArr(dp);

	}

	private static int dfs(int row, int col) {
		if (row == M - 1 && col == N - 1) {
			return 1; // 닿으면 한개 리턴
		}

		if (dp[row][col] != -1) {
			return dp[row][col]; // 값이 있다면 돌려줘라
		}
		dp[row][col] = 0; //일단 초기화
		for (int[] d : dir) {
			int nextRow = row + d[0];
			int nextCol = col + d[1];

			if (isIn(nextRow, nextCol) && input[row][col] > input[nextRow][nextCol]) {

				dp[row][col] += dfs(nextRow, nextCol); // 값더해주기

			}
		}
		return dp[row][col];
	}


	private static boolean isIn(int row, int col) {
		return row < M && row >= 0 && col < N && col >= 0;
	}
}

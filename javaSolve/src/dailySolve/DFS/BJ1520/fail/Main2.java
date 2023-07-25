package dailySolve.DFS.BJ1520.fail;

import java.io.*;
import java.util.*;

/**
 * 메모리 초과
 * 메모리를 안쓰는 방법..
 * 2초 시간이 오래 걸려도 하나의 배열에 담는 방법 없을까..
 * 많이 돌아도 여러번 돌아도
 * 점별로 역방향 dfs 를 돌고 몇개의 가능성이 있는
 * 각점별로 가능성을 보는건 어떤데 없으면 가
 * 1. 시작점 주변 2개 가능한지 보고 가능하면 1넣는다
 * 3. 기록된게 없다면 재귀타고 찾아온다 {0,1} {1,0} 닿을때까지 가능한 디피칸을 더한다
 * 4. 갈곳이 없다고 판단되면 0을 리턴한다.
 * 만약에 20같은곳을 처음 방문하냐 나중에 방문하냐 다르지 않을까? -> 구할때 -1 이면 찾으러 가라 하자 like 피보나치
 * 내려막이기때문에 역주행 막을수 있다
 */
public class Main2 {
	static int N;
	static int M;
	static int[][] dp;
	static int[][] input;
	static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static int count = 0;
	// 한개 짜리는?

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("resources/daily/dfs/BJ1520/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		dp = new int[M][N];
		for (int[] d : dp) {
			Arrays.fill(d, -1);
		}
		input = new int[M][N];
		for (int row = 0; row < M; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < N; col++) {
				input[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		dp[0][0] = 1;
		bottomUpDfs(0, 0);
		// System.out.println();
		printArr(dp);

		int res = dp[M-1][N-1];
		int rowOnce = M -1;
		int colOnce = N -1;
		if(rowOnce -1  >=0){
			res += dp[rowOnce][N-1];
		}
		if(colOnce -1 >= 0){
			res+= dp[M-1][colOnce];
		}
		System.out.println(res);
	}

	private static void bottomUpDfs(int row, int col) {
		for(int[] d : dir){
			int nextRow = row + d[0];
			int nextCol = col + d[1];
			if(isIn(nextRow, nextCol) && input[row][col] > input[nextRow][nextCol]){

				if(dp[nextRow][nextCol]!= 0){
					dp[nextRow][nextCol] +=1;
					return;
				}
				else if(nextRow == M - 1 && nextCol == N -1){
					count++;
					dp[nextRow][nextCol] +=dp[row][col];
					return;
				}

				else {
					dp[nextRow][nextCol] += dp[row][col];
					bottomUpDfs(nextRow,nextCol);
				}

			}
		}
	}

	static void printArr(int[][] arr) {
		for (int ar[] : arr) {
			for (int val : ar) {
				System.out.print(val == -1 ? "  " : val + " ");
			}
			System.out.println();
		}
	}


	private static boolean isIn(int row, int col) {
		return row < M && row >= 0 && col < N && col >= 0;
	}
}

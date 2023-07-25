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
 * 3. 기록된게 없다면 재귀타고 찾아온다 {0,1} {1,0} 닿을때까지
 *
 */

public class Main {
	static int N;
	static int M;
	static int[][] input;
	static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static int count = 0;
	static int testCount = 0;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("resources/daily/dfs/BJ1520/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		input = new int[N][M];
		for (int row = 0; row < N; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < M; col++) {
				input[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		boolean[][] check = new boolean[N][M];
		check[0][0] = true;
		dfs(0, 0);
		System.out.println(count);
	}

	private static void dfs(int row, int col) {
		testCount++;
		if (row == N - 1 && col == M - 1) {
			count++;
			return;
		}
		for (int d = 0; d < 4; d++) {
			int nextRow = row + dir[d][0];
			int nextCol = col + dir[d][1];
			if (isIn(nextRow, nextCol) && input[nextRow][nextCol] < input[row][col]) {
				for (int brow = 0; brow < N; brow++) {
					for (int bcol = 0; bcol < M; bcol++) {
					}
				}
				dfs(nextRow, nextCol);
			}
		}

	}

	private static void printArr(boolean[][] arr) {
		System.out.println();
		for (boolean[] br : arr) {
			for (boolean b : br) {
				System.out.print(b ? 1 : 0);
			}
			System.out.println();
		}
	}

	private static boolean isIn(int row, int col) {
		return row < N && row >= 0 && col < M && col >= 0;
	}
}

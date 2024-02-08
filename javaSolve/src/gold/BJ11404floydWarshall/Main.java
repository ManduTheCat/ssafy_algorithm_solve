package gold.BJ11404floydWarshall;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	final static int MAX = 999_999_999;

	// 입력이 같은게 들어올 수 있다.
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		int[][] floyd = new int[N + 1][N + 1];
		for (int row = 0; row <= N; row++) {
			for (int col = 0; col <= N; col++) {
				if (col == row) {
					floyd[row][col] = 0;
				}else {
					floyd[row][col] = MAX;
				}
			}
		}
		for (int m = 0; m < M; m++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			floyd[start][end] = floyd[start][end] != 0 ? Math.min(floyd[start][end], cost) : 0;
		}

		for (int k = 1; k <= N; k++) {
			for (int start = 1; start <= N; start++) {
				for (int end = 1; end <= N; end++) {
						floyd[start][end] = Math.min(floyd[start][end], floyd[start][k] + floyd[k][end]);

				}
			}
		}
		printArr(floyd);
	}

	private static void printArr(int[][] arr) {
		for (int row = 1; row <= N; row++) {
			for (int col = 1; col <= N; col++) {
				int val = arr[row][col];
				if (val == MAX) {
					System.out.print("0" + " ");
				} else {
					System.out.print(val + " ");
				}
			}
			System.out.println();
		}
	}

}

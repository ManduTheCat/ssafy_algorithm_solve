package dp;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("resources/daily/dp/BJ2096/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int[][] maxDp = new int[N][3];
		int[][] minDp = new int[N][3];
		for (int row = 0; row < N; row++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int col = 0; col < 3; col++) {
				int value = Integer.parseInt(st.nextToken());
				if (row == 0) {
					maxDp[row][col] = value;
					minDp[row][col] = value;
				} else {
					if (col == 0) {
						maxDp[row][col] = Math.max(maxDp[row - 1][col], maxDp[row - 1][col + 1])
							+ value;
						minDp[row][col] = Math.min(minDp[row - 1][col], minDp[row - 1][col + 1])
							+ value;
					} else if (col == 2) {
						maxDp[row][col] = Math.max(maxDp[row - 1][col], maxDp[row - 1][col - 1])
							+ value;
						minDp[row][col] = Math.min(minDp[row - 1][col], minDp[row - 1][col - 1])
							+ value;
					} else {
						maxDp[row][col] =
							Math.max(maxDp[row - 1][col + 1], Math.max(maxDp[row - 1][col], maxDp[row - 1][col - 1]))
								+ value;
						minDp[row][col] =
							Math.min(minDp[row - 1][col + 1], Math.min(minDp[row - 1][col], minDp[row - 1][col - 1]))
								+ value;
					}
				}
			}
		}
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		for (int value : maxDp[N - 1]) {
			max = Math.max(value, max);
		}
		for (int value : minDp[N - 1]) {
			min = Math.min(value, min);
		}
		System.out.println(max + " " + min);
	}
}

package silver.BJ14940;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Cordi {
	int row;
	int col;

	public Cordi(int row, int col) {
		this.row = row;
		this.col = col;
	}

	@Override
	public String toString() {
		return "Cordi{" +
			"row=" + row +
			", col=" + col +
			'}';
	}
}

public class Main {
	static int N;
	static int M;
	static int[][] map;
	static Cordi start;
	static boolean[][] check;
	static int[][] res;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		check = new boolean[N][M];
		res = new int[N][M];
		for (int row = 0; row < N; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < M; col++) {
				int value = Integer.parseInt(st.nextToken());
				map[row][col] = value;
				if (value == 0) {
					check[row][col] = true;
				} else if (value == 2) {
					start = new Cordi(row, col);
				}
			}
		}
		bfs(start);
		for (int n = 0; n < N; n++) {
			for (int m = 0; m < M; m++) {
				if (!check[n][m]) {
					res[n][m] = -1;
				}
			}
		}
		for (int n = 0; n < N; n++) {
			for (int m = 0; m < M; m++) {
				System.out.print(res[n][m] + " ");
			}
			System.out.println();
		}
	}

	public static void bfs(Cordi start) {
		Queue<Cordi> q = new ArrayDeque<>();
		q.add(start);
		check[start.row][start.col] = true;
		int dist = 0;
		res[start.row][start.col] = dist;
		int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
		while (!q.isEmpty()) {
			int size = q.size();
			dist++;
			while (size-- > 0) {
				Cordi curr = q.poll();
				for (int d = 0; d < 4; d++) {
					int nextRow = dir[d][0] + curr.row;
					int nextCol = dir[d][1] + curr.col;
					if (isIn(nextRow, nextCol) && !check[nextRow][nextCol]) {
						check[nextRow][nextCol] = true;
						q.add(new Cordi(nextRow, nextCol));
						res[nextRow][nextCol] = dist;
					}
				}
			}

		}
	}

	public static boolean isIn(int row, int col) {
		return row >= 0 && row < N && col >= 0 && col < M;
	}
}

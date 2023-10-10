package dailySolve.bfs.BJ14503;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Cordi {
	int r, c;

	public Cordi(int r, int c) {
		this.r = r;
		this.c = c;
	}
}

public class Main {
	static int N;
	static int M;
	static Cordi start;
	static int D;
	static int[][] map;
	static boolean[][] check;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int sr, sc;

		sr = Integer.parseInt(st.nextToken());
		sc = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		int tem  = 0;
		start = new Cordi(sr, sc);
		map = new int[N][M];
		check = new boolean[N][M];
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			for (int m = 0; m < M; m++) {
				int val = Integer.parseInt(st.nextToken());
				map[n][m] = val;
				if(val == 1){
					check[n][m] = true;
				} else {
					tem++;
				}
			}
		}
		System.out.println(bfs(start));
		System.out.println(tem);

	}

	public static int bfs(Cordi start) {
		int count = 1;
		Queue<Cordi> q = new ArrayDeque<>();
		check[start.r][start.c] = true;
		q.offer(start);
		int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
		while (!q.isEmpty()) {
			Cordi curr = q.poll();
			for(int d = 0; d < 4; d++){
				int nextR = curr.r + dir[d][0];
				int nextC = curr.c + dir[d][1];
				if(isIn(nextR,nextC) && !check[nextR][nextC]){
					q.offer(new Cordi(nextR, nextC));
					check[nextR][nextC] = true;
					count++;
				}

			}
		}
		return count;
	}

	public static boolean isIn(int row, int col){
		return row >= 0 && row < N && col >= 0 && col < M;
	}
}

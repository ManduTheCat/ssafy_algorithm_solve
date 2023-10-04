package beak.simul.sangbumBuildingBJ6593;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Cordi {
	int flo;
	int row;
	int col;
	int count;

	public Cordi(int flo, int row, int col, int count) {
		this.flo = flo;
		this.row = row;
		this.col = col;
		this.count = count;
	}
}

public class Main {
	static int L = -1;
	static int R = -1;
	static int C = -1;
	static String[][][] map;
	static boolean[][][] check;

	public static void main(String[] args) throws IOException {
		//	입력의 끝은 L, R, C가 모두 0으로 표현된다.
		System.setIn(new FileInputStream("resources/daily/sim/sangbumBuildeing6593/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			L = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			Cordi start = null;
			Cordi end = null;
			if (L == 0 && R == 0 && C == 0) {
				break;
			}
			map = new String[L][R][C];
			check = new boolean[L][R][C];
			for (int l = 0; l < L; l++) {
				for (int r = 0; r < R; r++) {
					String[] line = br.readLine().split("");
					for (int c = 0; c < C; c++) {
						String val = line[c];
						map[l][r][c] = val;
						if (val.equals("S")) {
							start = new Cordi(l, r, c, 0);
						}
						if (val.equals("E")) {
							end = new Cordi(l, r, c, 0);
						}
						if (val.equals("#")) {
							check[l][r][c] = true;
						}
					}
				}
				br.readLine();
			}
			int res = bfs(start, end);
			if (res > 0) {
				StringBuilder sb = new StringBuilder();
				sb.append("Escaped");
				sb.append(" ");
				sb.append("in");
				sb.append(" ");
				sb.append(res);
				sb.append(" ");
				sb.append("minute(s).");
				System.out.println(sb);
			} else {
				System.out.println("Trapped!");
			}

		}
	}

	public static int bfs(Cordi start, Cordi end) {
		Queue<Cordi> q = new ArrayDeque<>();
		q.add(start);
		int count = 0;
		check[start.flo][start.row][start.col] = true;
		int[][] dir = {{-1, 0, 0,}, {1, 0, 0}, {0, -1, 0}, {0, 0, 1}, {0, 1, 0}, {0, 0, -1}}; // 상 하 앞  뒤 좌
		while (!q.isEmpty()) {
			Cordi curr = q.poll();
			if (curr.flo == end.flo && curr.row == end.row && curr.col == end.col) {
				count = curr.count;
				break;
			}
			for (int d = 0; d < dir.length; d++) {
				int nextL = curr.flo + dir[d][0];
				int nextR = curr.row + dir[d][1];
				int nextC = curr.col + dir[d][2];
				if (isin(nextL, nextR, nextC) && !check[nextL][nextR][nextC]) {
					check[nextL][nextR][nextC] = true;
					Cordi next = new Cordi(nextL, nextR, nextC, curr.count + 1);
					q.add(next);
				}

			}

		}
		return count;
	}

	public static boolean isin(int l, int row, int col) {
		return l >= 0 && l < L && row >= 0 && row < R && col >= 0 && col < C;
	}
}

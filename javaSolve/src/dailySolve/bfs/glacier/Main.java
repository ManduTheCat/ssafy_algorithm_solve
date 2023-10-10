package dailySolve.bfs.glacier;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Cordi {
	int row;
	int col;
	int val;

	public Cordi(int row, int col, int val) {
		this.row = row;
		this.col = col;
		this.val = val;
	}
}

public class Main {
	static int N;
	static int M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		List<Cordi> iceList = new ArrayList<>();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		boolean[][] check = new boolean[N][M];
		for (int row = 0; row < N; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < M; col++) {
				int val = Integer.parseInt(st.nextToken());
				if (val > 0) {
					iceList.add(new Cordi(row, col, val));
				} else {
					check[row][col] = true;
				}

			}
		}
		int count = 0;
		// 최악 20만건
		while (!isTwoGroup(iceList, check)) { // 10000
			check = makeNewCheck(iceList); // 90000
			count++;
			iceList = fallDown(iceList, check); // 10000
			if (iceList.size() == 0) {
				break;
			}
			check = makeNewCheck(iceList); // 90000
		}
		if (iceList.size() > 0) {
			System.out.println(count);
		} else {
			System.out.println(0);
		}
	}

	public static boolean[][] makeNewCheck(List<Cordi> iceList) {
		boolean[][] res = new boolean[N][M];
		for (Cordi ice : iceList) {
			res[ice.row][ice.col] = true;
		}
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < M; col++) {
				res[row][col] = !res[row][col];
			}
		}
		return res;
	}

	public static List<Cordi> fallDown(List<Cordi> iceList, boolean[][]cordiCHeck) {
		// 인접한 친구들 갯수만큼 줄어든다.
		int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0,-1}};
		List<Cordi> res = new ArrayList<>();
		for (Cordi ice : iceList) {
			int adjCount = 0;
			for(int d = 0; d < 4; d++){
				int nextR = ice.row + dir[d][0];
				int nextC = ice.col + dir[d][1];
				// false 인걸 세면 점인 친구들만 세게 된다.
				if(isIn(nextR, nextC) && cordiCHeck[nextR][nextC]){
					adjCount++;
				}
			}
			if (ice.val - adjCount > 0) {
				res.add(new Cordi(ice.row, ice.col, ice.val - adjCount));
			}
		}
		return res;
	}

	public static boolean isTwoGroup(List<Cordi> iceList, boolean[][] check) {
		int groupCount = 0;
		for (Cordi start : iceList) {
			if (!check[start.row][start.col]) {
				groupCount++;
				Queue<Cordi> q = new ArrayDeque<>();
				q.add(start);
				int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
				check[start.row][start.col] = true;
				while (!q.isEmpty()) {
					Cordi curr = q.poll();
					for (int d = 0; d < 4; d++) {
						int nextR = curr.row + dir[d][0];
						int nextC = curr.col + dir[d][1];
						if (isIn(nextR, nextC) && !check[nextR][nextC]) {
							q.add(new Cordi(nextR, nextC, 0));
							// val 은 상실
							check[nextR][nextC] = true;
						}

					}
				}
			}

		}
		if (groupCount >= 2) {
			return true;
		}
		return false;
	}

	public static boolean isIn(int row, int col) {
		return row >= 0 && row < N && col >= 0 && col < M;
	}

	public static void print(boolean[][] check) {
		for (int n = 0; n < N; n++) {
			System.out.println();
			for (int m = 0; m < N; m++) {
				System.out.print(check[n][m] ? 1 + " " : 0 + " ");
			}
		}
	}
	public static void print (List<Cordi> iceList){
		int [][] printMap = new int[N][M];
		for(Cordi ice : iceList){
			printMap[ice.row][ice.col] = ice.val;
		}
		for(int n = 0; n < N; n++){
			System.out.println();
			for(int m = 0; m < M; m++){
				System.out.print(printMap[n][m] + " ");
			}
		}
	}
}

package dailySolve.bfs.BJ2583;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int M;
	static int N;
	static int K;
	static int[][] map;
	static boolean[][] check;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./resources/daily/dfs/BJ2583/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// (0, 0 ) 에서 (N,M)까지
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		check = new boolean[N][M];
		for (int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			for (int row = y1; row < y2; row++) {
				for (int col = x1; col < x2; col++) {
					// System.out.println(row + " ," + col);
					check[row][col] = true;
				}
			}
		}
		int areaCount = 0;
		List<Integer> res = new ArrayList<>();
		for(int row = 0; row < N; row++){
			for (int col = 0; col < M ; col++){
				if(!check[row][col]){
					areaCount ++;
					res.add(bfs(row, col));
				}
			}
		}
		Collections.sort(res);
		System.out.println(areaCount);
		for(int re:res){
			System.out.print(re + " ");
		}
	}

	public static int bfs(int row, int col) {
		int count = 1;
		Queue<Cordi> pq = new ArrayDeque<>();
		pq.add(new Cordi(row, col));
		check[row][col] = true;
		int[][] dir = {{-1,0} ,{0,1} , {1,0}, {0,-1}};
		while (!pq.isEmpty()){
			Cordi curr = pq.poll();
			for(int d = 0; d < 4; d++){
				int nextRow = curr.row + dir[d][0];
				int nextCol = curr.col + dir[d][1];
				if(isIn(nextRow, nextCol) && !check[nextRow][nextCol]){
					count++;
					check[nextRow][nextCol] = true;
					pq.add(new Cordi(nextRow, nextCol));
				}

			}

		}

		return count;
	}
	public static boolean isIn(int row, int col){
		return row >= 0 && row < N && col >=0 && col < M;
	}

	static class Cordi {
		int row, col;

		Cordi(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
}

package dailySolve.bfs.BJ1926;

import java.io.*;
import java.util.*;

//첫째 줄에는 그림의 개수, 둘째 줄에는 그 중 가장 넓은 그림의 넓이를 출력하여라. 단, 그림이 하나도 없는 경우에는 가장 넓은 그림의 넓이는 0이다.
//세로 크기 n(1 ≤ n ≤ 500)과 가로 크기 m(1 ≤ m ≤ 500)

public class Main {
	static int N;
	static int M;

	static int [][] map;
	static boolean[][] check;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./resources/daily/bfs/BJ1926/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		check = new boolean[N][M];
		for(int row = 0; row < N; row++){
			st = new StringTokenizer(br.readLine());
			for(int col = 0; col < M; col++){
				int num = Integer.parseInt(st.nextToken());
				if(num == 0){
					check[row][col] = true;
				}
				map[row][col] = num;
			}
		}
		int maxArea = 0;
		int groupCount = 0;
		for(int row = 0; row < N; row++){
			for(int col = 0; col < M; col++){
				if(!check[row][col]){
					groupCount++;
					maxArea = Math.max(bfs(row, col), maxArea);
				}
			}
		}
		if(groupCount != 0){
			System.out.println(groupCount);
			System.out.println(maxArea);
		}else {
			System.out.println(0);
			System.out.println(0);
		}
	}

	private static int bfs(int row, int col) {
		int area = 1;
		Queue<Cordi> q = new ArrayDeque<>();
		q.add(new Cordi(row, col));
		check[row][col] = true;
		int [][] dir = {{-1,0},{0,1},{1,0},{0,-1}};
		while (!q.isEmpty()){
			Cordi curr = q.poll();
			for(int[] d  :dir){
				int nextRow = curr.row + d[0];
				int nextCol = curr.col + d[1];
				if(isIn(nextRow, nextCol) && !check[nextRow][nextCol] && map[nextRow][nextCol] == 1){
					check[nextRow][nextCol] = true;
					area++;
					q.offer(new Cordi(nextRow, nextCol));
				}
			}
		}
		return area;
	}

	public static boolean isIn(int row, int col){
		return row < N && row >= 0 && col < M  && col >= 0;
	}
	static class Cordi{
		int row, col;

		public Cordi(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
}

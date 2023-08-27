package dailySolve.bfs.foodWasteBJ1743;

import java.io.*;
import java.util.*;

class Cordi {
	int row;
	int col;
	Cordi(int row, int col){
		this.row = row;
		this.col = col;
	}
	@Override
	public String toString(){
		StringBuilder  sb = new StringBuilder();
		sb.append("[");
		sb.append(this.row);
		sb.append(",");
		sb.append(this.col);
		sb.append("]");
		return sb.toString();
	}
}
public class Main {
	static int N;
	static int M;
	static int foodCount;
	static int[][] map;
	static boolean[][] check;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./resources/daily/bfs/foodWasteBJ1743/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		foodCount = Integer.parseInt(st.nextToken());
		map = new int[N + 1][M + 1];
		check = new boolean[N + 1][M + 1];
		List<Cordi> inputs = new ArrayList<>();
		for(int i = 0; i < foodCount; i++){
			st = new StringTokenizer(br.readLine());
			int row = Integer.parseInt(st.nextToken());
			int col = Integer.parseInt(st.nextToken());
			map[row][col] = 1;
			check[row][col] = true;
			inputs.add(new Cordi(row, col));
		}
		int res = Integer.MIN_VALUE;
		for(Cordi input : inputs){
			if(check[input.row][input.col]){ // true 가 방문 안한거 false 가 방문한거
				int count = bfs(input);
				res = Math.max(res, count);
			}
		}
		System.out.println(res);
	}
	public static int bfs(Cordi start){
		Queue<Cordi> q = new ArrayDeque<>();
		q.add(start);
		check[start.row][start.col] = false;
		int count = 1;
		int [][] dir = {{-1,0}, {0,1}, {1,0} ,{0,-1}};
		while (!q.isEmpty()){
			Cordi curr = q.poll();
			for(int d = 0; d < 4; d++){
				int nextRow = curr.row + dir[d][0];
				int nextCol = curr.col + dir[d][1];
				if(isIn(nextRow, nextCol) && check[nextRow][nextCol]){
					count++;
					check[nextRow][nextCol] = false;
					q.add(new Cordi(nextRow, nextCol));
				}
			}
		}
		return count;
	}
	public static boolean isIn(int row, int col){
		return row >= 1 && row <=N && col >=1 && col <=M;
	}
}

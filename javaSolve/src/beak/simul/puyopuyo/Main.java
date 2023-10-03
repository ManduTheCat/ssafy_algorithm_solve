package beak.simul.puyopuyo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Cordi{
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
	static int R = 12;
	static int C = 6;
	static boolean [][] check;
	static String [][] map;
	// 1 같은거 4개 처리
	// 2 중력 작용
	// 3 터질게 있나 검사
	public static void main(String[] args) throws IOException {

		System.setIn(new FileInputStream("resources/daily/sim/puyoPyuo/input.txt"));
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		map = new String[R][C];
		check = new boolean[R][C];
		for(int row = 0; row < R; row++){
			String[] line = br.readLine().split("");
			for(int col = 0; col < C; col++){
				map[row][col] = line[col];
				if(line[col].equals(".")){
					check[row][col] = true;
				}
			}
		}
		delete(); // 와일 델리트
		check = makeNewCheck();


	}
	public static boolean[][] makeNewCheck(){
		boolean[][] res = new boolean[R][C];
		for(int row = 0; row < R; row++){
			for(int col = 0; col < C; col++){
				String val = map[row][col];
				if(val.equals(".")){
					res[row][col] = true;
				}

			}
		}
		return res;
	}
	public static boolean delete(){
		boolean res = false;
		for(int row = 0; row < R; row++){
			for(int col = 0; col < C; col++){
				if(!check[row][col]){
					List<Cordi> deleteCandi = findGroup(new Cordi(row, col));
					System.out.println(deleteCandi);
					if(deleteCandi.size() >= 4){
						res = true;
						for(Cordi target: deleteCandi){
							map[target.row][target.col] = ".";
						}
					}
				}
			}
		}
		return res;
	}

	public static List<Cordi> findGroup(Cordi start){
		int count = 1;
		List<Cordi> res = new ArrayList<>();
		res.add(start);
		Queue<Cordi> q = new ArrayDeque<>();
		q.add(start);
		String startSt = map[start.row][start.col];
		check[start.row][start.col] = true;
		int[][] dir = {{0,-1},{-1,0},{0,1},{1,0}};
		while (!q.isEmpty()){
			Cordi curr = q.poll();
			for(int [] d : dir){
				int nextRow = curr.row + d[0];
				int nextCol = curr.col +d[1];
				if(isIn(nextRow, nextCol) && !check[nextRow][nextCol] && map[nextRow][nextCol].equals(startSt)){
					Cordi next = new Cordi(nextRow, nextCol);
					q.add(next);
					check[nextRow][nextCol] = true;
					count++;
					res.add(next);
				}
			}

		}
		if(count >=4) {
			return res;
		}
		return new ArrayList<>();

	}
	public static boolean isIn(int row, int col){
		return row >= 0 && row < R && col >= 0 && col < C;
	}

}

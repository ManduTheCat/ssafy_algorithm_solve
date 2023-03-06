package beak.simul.puyopuyo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
public class Main {
	// 터트리고 내려야한다.
	// 터트리는걸 어떻게 할건가
	// 1초
	// 터진 친구들의 위치을 기록했다가
	public static String [][] board;
	public static boolean[][] check;
	public static final int ROW = 12;
	public static final int COL = 6;
	public static void main(String[] args) throws IOException {
		FileInputStream fileInputStream = new FileInputStream("resources/additionalClass/beak/sim/pyuo/input.txt");
		System.setIn(fileInputStream);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		board = new String[ROW][COL];
		check = new boolean[ROW][COL];
		for (int row = 0; row < ROW; row++) {
			String readRow = br.readLine();
			for (int col = 0; col < COL; col++) {
				String data = String.valueOf(readRow.charAt(col));
				System.out.println(data);
				board[row][col] = data;
				if(data.equals(".")) check[row][col] = true;
			}
		}
		for (int row = 0; row < ROW; row++) {
			for (int col = 0; col < COL; col++) {
				if(!check[row][col]){
					String startColor = board[row][col];
					breakThings(new Cordi(row, col), startColor);
					printMap(board);
				}
			}
		}

	}
	public static  void printMap(String[][] map){
		for (String[] row : map) {
			System.out.println(Arrays.toString(row));
		}
	}
	public static  void printMap(boolean[][] map){
		for (boolean[] row : map) {
			System.out.println(Arrays.toString(row));
		}
	}


	static class Cordi {
		int row;
		int col;

		public Cordi(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	public static void breakThings(Cordi startPoint, String startColor){
		Queue<Cordi> q = new ArrayDeque<>();
		// 4방 탐색
		int [][] dir  = {{0, -1},{-1, 0},{0,1},{1,0}};
		// 위에서 부터 아래로 간단 // 제거된건 V 로 처리
		q.add(new Cordi(startPoint.row,startPoint.col));
		int SameCount = 0;
		while (!q.isEmpty()){
			// 같은 친구면 제거

			Cordi cur = q.poll();
			for (int d = 0; d < 4; d++) {
				int nextRow = cur.row + dir[d][0];
				int nextCol = cur.col + dir[d][1];
				if(isIn(nextRow, nextCol) && !check[nextRow][nextCol]
					&& board[nextRow][nextCol].equals(startColor)){
					check[nextRow][nextCol] = true;
					q.add(new Cordi(nextRow, nextCol));
					SameCount ++;
				}
			}
		}
		// 카운트를 기반으로 4개 이상이면 처리한다.

		System.out.println(SameCount);
	}

	private static boolean isIn(int nextRow, int nextCol) {
		return nextRow >= 0 && nextRow < ROW && nextCol >= 0 && nextCol < COL;
	}
}

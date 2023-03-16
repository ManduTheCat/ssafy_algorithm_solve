package beak.simul.puyopuyo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
				board[row][col] = data;
				if(data.equals(".")) check[row][col] = true;
			}
		}
		// 돌면서 부슨다. 리턴을 불리언으로 ?
		for (int row = 0; row < ROW; row++) {
			for (int col = 0; col < COL; col++) {
				if(!check[row][col]){
					String startColor = board[row][col];
					breakThings(new Cordi(row, col), startColor);
				}
			}
		}
		// 땡긴다
		// 검사하기 false 면 계속 부슨다.

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

		@Override
		public String toString() {
			return "Cordi{" +
				"row=" + row +
				", col=" + col +
				'}';
		}
	}
	public static void breakThings(Cordi startPoint, String startColor){
		Queue<Cordi> q = new ArrayDeque<>();
		// 4방 탐색
		int [][] dir  = {{0, -1},{-1, 0},{0,1},{1,0}};
		List<Cordi> boomCandidate = new ArrayList<>();
		// 위에서 부터 아래로 간단 // 제거된건 V 로 처리
		q.add(new Cordi(startPoint.row,startPoint.col));
		int sameCount = 0;
		while (!q.isEmpty()){
			// 같은 친구면 제거

			Cordi cur = q.poll();
			for (int d = 0; d < 4; d++) {
				int nextRow = cur.row + dir[d][0];
				int nextCol = cur.col + dir[d][1];
				if(isIn(nextRow, nextCol) && !check[nextRow][nextCol]
					&& board[nextRow][nextCol].equals(startColor)){
					check[nextRow][nextCol] = true;
					Cordi nextCordi = new Cordi(nextRow, nextCol);
					q.add(nextCordi);
					boomCandidate.add(nextCordi);
					sameCount ++;
				}
			}
		}
		// 카운트를 기반으로 4개 이상이면 처리한다.
		if(sameCount >= 4){
			for(Cordi boomTarget : boomCandidate){
				board[boomTarget.row][boomTarget.col] = "V";
			}
			printMap(board);
		}
	}

	private static boolean isIn(int nextRow, int nextCol) {
		return nextRow >= 0 && nextRow < ROW && nextCol >= 0 && nextCol < COL;
	}
}

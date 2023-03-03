package beak.simul.puyopuyo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

import jdk.internal.dynalink.beans.StaticClass;

public class Main {
	// 터트리고 내려야한다.
	// 터트리는걸 어떻게 할건가
	// 1초
	// 터진 친구들의 위치을 기록했다가
	public static String [][] board;
	public static Boolean[][] check;
	public static final int ROW = 12;
	public static final int COL = 6;
	public static void main(String[] args) throws IOException {
		FileInputStream fileInputStream = new FileInputStream("resources/additionalClass/beak/sim/pyuo/input.txt");
		System.setIn(fileInputStream);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		board = new String[ROW][COL];
		for (int row = 0; row < ROW; row++) {
			String readRow = br.readLine();
			for (int col = 0; col < COL; col++) {
				board[row][col] = String.valueOf(readRow.charAt(col));
			}
		}
		printMap(board);

	}
	public static  void printMap(String[][] map){
		for (String[] row : map) {
			System.out.println(Arrays.toString(row));
		}
	}

	static class Cordi {
		int row;
		int col;
	}
	public static void breakThings(){
		Queue<Cordi> q = new ArrayDeque<>();
		while (!q.isEmpty()){

		}
	}
}

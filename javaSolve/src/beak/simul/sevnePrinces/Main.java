package beak.simul.sevnePrinces;

import java.io.*;
import java.util.*;

public class Main {
	static Map<Integer, int[]> groupMap;
	static int[] combiSelect;
	static int count = 0;
	static String [][]input = new String[5][5];

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("resources/daily/sim/sevenPrinces/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		groupMap = new HashMap<>();
		combiSelect = new int[7];
		int idx = 0;
		for (int row = 0; row < 5; row++) {
			String rowLine = br.readLine();
			for (int col = 0; col < 5; col++) {
				int[] cordi = {row, col};
				input[row][col] = String.valueOf(rowLine.charAt(col));
				groupMap.put(idx++, cordi);
			}
		}
		// 7 명으로 구성
		// 7명은 가로 새로로 인접 해야한다.
		// s 가 최소 4명이상은 되야한다.
		combination(0, 0);
		System.out.println(count);
	}

	private static void combination(int depth, int start) {
		if (depth == 7) {
			// 검사 해야한다.
			if (check()) {
				count++;
			}
			return;
		}
		for (int i = start; i < 25; i++) {
			combiSelect[depth] = i;
			combination(depth + 1, i + 1);
		}
	}

	private static boolean check() {
		if (bfs() != 7) {
			return false;
		}
		boolean[][] temp = new boolean[5][5];
		for(int idx:combiSelect){
			temp[groupMap.get(idx)[0]][groupMap.get(idx)[1]] = true;
		}
		// System.out.println("");
		// for(boolean[] trow : temp){
		// 	for (boolean t: trow){
		// 		System.out.print(t ? 1: 0);
		// 	}
		// 	System.out.println();
		// }
		if (sCount() < 4) {
			return false;
		}
		return true;
	}

	private static int sCount() {
		int res = 0;
		for(int idx :combiSelect){
			int [] cordi = groupMap.get(idx);
			if(input[cordi[0]][cordi[1]].equals("S")){
				res++;
			}
		}
		return res;
	}

	private static int bfs() {
		int startIdx = combiSelect[0];
		int resCount = 0;
		Queue<int[]> q = new ArrayDeque<>();
		q.add(groupMap.get(startIdx));
		boolean[][] check = new boolean[5][5];
		for(int idx :combiSelect){
			int [] cordi = groupMap.get(idx);
			check[cordi[0]][cordi[1]] = true;
		}
		int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
		while (!q.isEmpty()) {
			int[] currCordi = q.poll();
			for (int d = 0; d < 4; d++) {
				int nextRow = currCordi[0] + dir[d][0];
				int nextCol = currCordi[1] + dir[d][1];
				// 트루일때만 갈수 있다
				if(isIn(nextRow, nextCol) && check[nextRow][nextCol]){
					resCount++;
					int[] nextCordi = {nextRow, nextCol};
					q.offer(nextCordi);
					check[nextRow][nextCol] = false;
				}
			}
		}

		return resCount;
	}

	private static boolean isIn(int row, int col) {
		return row < 5 && row >= 0 && col < 5 && col >= 0;
	}
}

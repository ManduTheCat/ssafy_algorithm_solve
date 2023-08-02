package softeer.robotPath;

import java.util.*;
import java.io.*;

public class Main {
	static int W;
	static int H;
	static boolean[][] map; // 시작 지점용
	static boolean[][] OriginCheck; // 초기 입력 check
	static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};// 위 오 아 왼 오른쪽 회전 기준
	static String result = ""; // 결과 로 전달할 문자열
	static int minSize = Integer.MAX_VALUE; // 명령어 길이 최소정보
	static int[] resultCordi = new int[3]; // 결과 좌표 + 방향 담을 배열

	public static void main(String args[]) throws IOException {
		// 입력 받기, 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		map = new boolean[H][W];
		OriginCheck = new boolean[H][W];
		for (int row = 0; row < H; row++) {
			String[] rowLine = br.readLine().split("");
			for (int col = 0; col < W; col++) {
				String value = rowLine[col];
				if (value.equals("#")) {
					map[row][col] = false;
					OriginCheck[row][col] = false;
				} else {
					map[row][col] = true;
					OriginCheck[row][col] = true;
				}
			}
		}
		// dfs
		for (int row = 0; row < H; row++) {
			for (int col = 0; col < W; col++) {
				if (!map[row][col]) {//모든 점을 확인하다 시작이 가능한 map 이면 시작해라
					for (int way = 0; way < 4; way++) {
						int[] resCordi = new int[3]; // 결과 출력을위해 재귀 매개변수로 넘길 좌표 + 방향
						resCordi[0] = row;
						resCordi[1] = col;
						resCordi[2] = way;
						boolean[][] nextCheck = checkCopy(OriginCheck); // 복제
						nextCheck[row][col] = true;
						dfs(row, col, way, nextCheck, "", resCordi);
					}
				}
			}
		}
		// 결과 출력 부분
		System.out.println((resultCordi[0] + 1) + " " + (resultCordi[1] + 1));
		if (resultCordi[2] == 0) { // 결과 매핑
			System.out.println("^");
		} else if (resultCordi[2] == 1) {
			System.out.println(">");
		} else if (resultCordi[2] == 2) {
			System.out.println("v");
		} else if (resultCordi[2] == 3) {
			System.out.println("<");
		}

		System.out.println(result);
	}

	public static void dfs(int row, int col, int way, boolean[][] beforeCheck, String res, int[] resCordi) {
		if (isAllTrue(beforeCheck) && res.length() < minSize) {
			// 길이가 최소이고 전부 방문하면 종료하고 결과 전용 전역 변수에 넣는다.
			minSize = res.length();
			result = res;
			resultCordi = resCordi.clone();
			return;
		}

		// 전방이동
		boolean[][] nextCheck = checkCopy(beforeCheck);
		int[] d = dir[way];
		int forwardR = row + d[0];//전방으로 이동
		int forwardC = col + d[1];
		if (isIn(forwardR, forwardC) && !nextCheck[forwardR][forwardC]) {// 한칸앞이 가능하면 앞으로 2칸 앞으로 시도
			// A 명령어는 두번 이동한다 한번더 이동 + 체크
			nextCheck[forwardR][forwardC] = true;
			forwardR += d[0];
			forwardC += d[1];
			if (isIn(forwardR, forwardC) && !nextCheck[forwardR][forwardC]) {
				nextCheck[forwardR][forwardC] = true;// 한번더 가능하면 이동
			} else {// 불가능한 경우 다시 뒤로이동
				forwardR -= d[0];
				forwardC -= d[1];
			}
			dfs(forwardR, forwardC, way, nextCheck, res + "A", resCordi);
		}
		// 오른쪽 이동
		nextCheck = checkCopy(beforeCheck);
		int nextR = (way + 1) % 4; // 오른쪽 방향 회전
		int[] rWay = dir[nextR];
		int nextRrow = row + rWay[0];
		int nextRcol = col + rWay[1];
		if (isIn(nextRrow, nextRcol) && !nextCheck[nextRrow][nextRcol]) {
			nextCheck[nextRrow][nextRcol] = true;
			dfs(nextRrow, nextRcol, nextR, nextCheck, res + "R", resCordi);// 가능하면 재귀로 넘겨라
		}

		// 왼쪽이동
		nextCheck = checkCopy(beforeCheck);
		int nextL;
		if (way == 0) { // 왼쪽 으로 회전 하드코딩말고 좀더 쉬운 방법이 있을텐데
			nextL = 3;
		} else if (way == 1) {
			nextL = 0;
		} else if (way == 2) {
			nextL = 1;
		} else {
			nextL = 2;
		}
		int[] lWay = dir[nextL];
		int nextLRow = row + lWay[0];// 왼쪽 방향으로 이동
		int nextLCol = col + lWay[1];
		if (isIn(nextLRow, nextLCol) && !nextCheck[nextLRow][nextLCol]) {
			nextCheck[nextLRow][nextLCol] = true;
			dfs(nextLRow, nextLCol, nextL, nextCheck, res + "L", resCordi);// 가능하면 재귀로 넘겨라
		}
	}

	// 결과 확인할때 방문다해야 결과를 보여줄수 있다 모두 방분 했는지 체크하는 함수
	public static boolean isAllTrue(boolean[][] checkTarget) {

		for (boolean[] rowTarget : checkTarget) {
			for (boolean b : rowTarget) {
				if (!b) {
					return false;
				}
			}
		}
		return true;

	}

	public static boolean[][] checkCopy(boolean[][] inCheck) {
		boolean[][] re = new boolean[H][W];
		for (int row = 0; row < H; row++) {
			for (int col = 0; col < W; col++) {
				re[row][col] = inCheck[row][col];
			}
		}
		return re;
	}

	public static boolean isIn(int row, int col) {
		return row >= 0 && row < H && col >= 0 && col < W;
	}
}
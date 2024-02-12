package gold.BJ5427fire;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * bfs 에 사용할 맵객체
 */
class BuildingMap {

	private int h;
	private int w;
	private char[][] map;
	private boolean[][] check;

	/**
	 * 생성자 방문처리 배열 check 동시에 생성
	 * @param h 맵 초기화 높이
	 * @param w 맵 초기화 넓이
	 *
	 */
	public BuildingMap(int h, int w) {
		this.h = h;
		this.w = w;
		map = new char[this.h][this.w];
		check = new boolean[this.h][this.w];
	}

	/**
	 * map 안에 있는지 체크하는 함수 일종의 유틸이지만 static  이 아닌 이유는 맵을 먼저 만들어야 하기 때문에
	 * @param row 넘어갔는지 확인하는 col
	 * @param col 넘거갔는지 확인할 col
	 * @return map 안에 있다면 true 아니면 fale
	 */
	public boolean isIn(int row, int col) {
		return row >= 0 && row < h && col >= 0 && col < w;
	}

	/**
	 * map 을 생성한 이후에  에 값을 입력하는 메소드
	 * map 이 할당 되어 있지 않는다면 아무것도 하지 않는다.
	 * map 이 널이면 예외 발생
	 * @param row 변경할 check row
	 * @param col 변경할 check col
	 * @param val 변경할 값
	 */
	public void setValMap(int row, int col, char val) {
		try {
			map[row][col] = val;
			if (val == '#') {
				check[row][col] = true;
			}

		} catch(NullPointerException e) {
			e.printStackTrace();
		}
	}

	/**
	 * map getter
	 * @return map 배열
	 */
	public char[][] getMap() {
		return map;
	}

	/**
	 *
	 * @param row
	 * @param col
	 * @return
	 */
	public boolean getCheckVal(int row, int col) {
		return check[row][col];
	}

	/**
	 * cechk 값 변경
	 * bfs 진행중 check 값 변경에 쓰임
	 * @param row row 위치
	 * @param col col 위치
	 * @param val 변경할 값
	 */
	public void setCheckVal(int row, int col, boolean val) {
		check[row][col] = val;
	}

	/**
	 * 디버깅을 위한 map 출력함수
	 */
	public void printMap() {
		StringBuilder sb = new StringBuilder();
		for (int row = 0; row < this.h; row++) {
			for (int col = 0; col < this.w; col++) {
				sb.append(this.map[row][col]);
				sb.append(" ");
			}
			sb.append("/n");
		}
		System.out.println(sb);
	}

	/**
	 * cechk 함수 출력 함수
	 */
	public void printCheck() {
		StringBuilder sb = new StringBuilder();
		for (int row = 0; row < this.h; row++) {
			for (int col = 0; col < this.w; col++) {
				sb.append(this.check[row][col] ? "1" : "0");
				sb.append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

}

class Cordi {
	private int row;
	private int col;

	public Cordi(int row, int col) {
		this.row = row;
		this.col = col;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
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
	private static int T;

	public static void main(String[] args) throws IOException {
		int W;
		int H;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			BuildingMap buildingMap = new BuildingMap(H, W);
			Queue<Cordi> manQ = new ArrayDeque<>();
			Queue<Cordi> fireQ = new ArrayDeque<>();
			for (int h = 0; h < H; h++) {
				String row = br.readLine();
				for (int w = 0; w < W; w++) {
					char val = row.charAt(w);
					buildingMap.setValMap(h, w, val);
					if (val == '*') {
						fireQ.add(new Cordi(h, w));
						buildingMap.setCheckVal(h, w, true);
					} else if (val == '@') {
						manQ.add(new Cordi(h, w));
						buildingMap.setCheckVal(h, w, true);
					}
				}
			}

			int res = bfs(manQ, fireQ, buildingMap);
			System.out.println(res > -1 ? res : "IMPOSSIBLE");

		}

	}

	private static int bfs(Queue<Cordi> manQ, Queue<Cordi> fireQ, BuildingMap map) {
		int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
		int count = 0;
		while (!manQ.isEmpty()) {
			int fireSize = fireQ.size();
			int manSize = manQ.size();
			while (fireSize-- > 0) {
				Cordi currFire = fireQ.poll();
				for (int d = 0; d < dir.length; d++) {
					int nextRow = currFire.getRow() + dir[d][0];
					int nextCol = currFire.getCol() + dir[d][1];
					if (map.isIn(nextRow, nextCol) && !map.getCheckVal(nextRow, nextCol)) {
						map.setCheckVal(nextRow, nextCol, true);
						fireQ.add(new Cordi(nextRow, nextCol));
					}
				}
			}
			count++;
			while (manSize-- > 0) {
				Cordi currMan = manQ.poll();
				for (int d = 0; d < dir.length; d++) {
					int nextRow = currMan.getRow() + dir[d][0];
					int nextCol = currMan.getCol() + dir[d][1];
					if (!map.isIn(nextRow, nextCol)) {
						return count;
					}
					if (map.isIn(nextRow, nextCol) && !map.getCheckVal(nextRow, nextCol)) {
						map.setCheckVal(nextRow, nextCol, true);
						manQ.add(new Cordi(nextRow, nextCol));
					}
				}

			}
			//System.out.println();
			//map.printCheck();

		}

		return -1;
	}
}

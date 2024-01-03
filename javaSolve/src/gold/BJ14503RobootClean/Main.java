package gold.BJ14503RobootClean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 설계 미스 검사하는것과 이동하는건 분리해야한다.

class Roboot {
	int row;
	int col;
	int dir;
	static int N;
	static int M;
	static int[][] dirMap = {{-1, 0}, {0, 1}, {-1, 0}, {0, -1}};

	public Roboot(int row, int col, int dir, int N, int M) {
		this.row = row;
		this.col = col;
		this.dir = dir;
		Roboot.N = N;
		Roboot.M = M;

	}

	public boolean isIn(int row, int col) {
		return row >= 0 && row < N && col >= 0 && col < M;
	}

	public boolean goFowared() {// 바라 보는 방향으로 전진한다.// 이동을 성공했다 실패 했다 결과를 보여준다 중단 지점으로 활용
		if (isIn(row + dirMap[dir][0], col + dirMap[dir][1])) {
			row += dirMap[dir][0];
			col += dirMap[dir][1];
			return true;
		}
		return false;

	}

	public int[] peekForward() {
		if (isIn(row + dirMap[dir][0], col + dirMap[dir][1])) {
			return new int[] {row + dirMap[dir][0], col + dirMap[dir][1]};
		} else {
			return new int[] {-1, -1};
		}
	}

	public int[] peekBackward() { // 바라보는 방향을 유지한체로 후진한다.

		if (isIn(row - dirMap[dir][0], col - dirMap[dir][1])) {
			return new int[] {row - dirMap[dir][0], col - dirMap[dir][1]};
		} else {
			return new int[] {-1, -1};
		}

	}

	public boolean goBackward() { // 바라보는 방향을 유지한체로 후진한다.
		if (isIn(row - dirMap[dir][0], col - dirMap[dir][1])) {
			row -= dirMap[dir][0];
			col -= dirMap[dir][1];
			return true;
		}
		return false;
	}

	public void turnRight() {
		dir = (dir + 1) % 4;
	}

	@Override
	public String toString() {
		return "Roboot{" +
			"row=" + row +
			", col=" + col +
			", dir=" + dir +
			'}';
	}
}

public class Main {
	static int N;
	static int M;
	static boolean[][] map; // 벽 확인용
	static boolean[][] check; // 청소구영 확인용 // 청소를 했던곳이라도 벽만 아니면 이동가능

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new boolean[N][M];
		check = new boolean[N][M];
		st = new StringTokenizer(br.readLine());
		int startRow = Integer.parseInt(st.nextToken());
		int startCol = Integer.parseInt(st.nextToken());
		int startdir = Integer.parseInt(st.nextToken());
		Roboot roboot = new Roboot(startRow, startCol, startdir, N, M);
		for (int row = 0; row < N; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < M; col++) {
				int val = Integer.parseInt(st.nextToken());
				map[row][col] = val != 1;
				check[row][col] = val != 1;
			}
		}
		int res = 0;
		while (true) {
			System.out.println(roboot);
			if (!check[roboot.row][roboot.col]) {
				check[roboot.row][roboot.col] = true;
				res++;
			} else if (checkFourWay(roboot)) {
				// 4방향중 청소되지 않은 빈칸이 있다면
				roboot.turnRight();
				int[] peek = roboot.peekForward();
				if (peek[0] != -1 && peek[1] != -1 && !map[peek[0]][peek[1]] && !check[peek[0]][peek[1]]) {
					// 앞 이동가능 은 체크 하는데 벽인지 모른다 && 청소가능한지 알아야한다.
					// 여길 안들어가네
					System.out.println("true in");
					roboot.goFowared(); //전진한다.
				}
			} else { // 청소 구역도 아니고 4방향중 갈곳도 없다
				//청소되지 않은 빈칸이 없다면
				System.out.println("false in");
				int[] peek = roboot.peekBackward();
				System.out.println("back :" + Arrays.toString(peek));
				if ((peek[0] != -1 ) && map[peek[0]][peek[1]]) {
					// 뒤 이동 가능하고, 벽이 없다면 불가능하면 -1 을 보넨다
					roboot.goBackward();
				}else {
					break;
				}

			}
		}
		System.out.println(res);

	}

	public static boolean checkFourWay(Roboot roboot) {
		boolean up = false, down = false, right = false, left = false;
		if (isIn(roboot.row - 1, roboot.col) && !check[roboot.row + 1][roboot.col]) {
			up = true;
		}
		if (isIn(roboot.row, roboot.col + 1) && !check[roboot.row][roboot.col + 1]) {
			right = true;
		}
		if (isIn(roboot.row - 1, roboot.col) && !check[roboot.row - 1][roboot.col]) {
			down = true;
		}
		if (isIn(roboot.row, roboot.col - 1) && !check[roboot.row][roboot.col - 1]) {
			left = true;
		}

		System.out.println(
			"roboot: " + roboot + " up: " + up + " down: " + down + " left: " + left + " right: " + right);
		return up || right || down || left;
	}

	public static boolean isIn(int row, int col) {
		return row >= 0 && row < N && col >= 0 && col < M;
	}
}

package gold.BJ14503RobootClean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 설계 미스 검사하는것과 이동하는건 분리해야한다.

class Cordi {
	int row;
	int col;

	public Cordi(int row, int col) {
		this.row = row;
		this.col = col;
	}

	public Cordi() {
	}

	@Override
	public String toString() {
		return "Cordi{" +
			"row=" + row +
			", col=" + col +
			'}';
	}
}

class Robot {
	Cordi cordi;
	int dir;
	static int N;
	static int M;
	static int[][] dirMap = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // 디폴트는 시계방향

	public Robot(Cordi curr, int dir, int N, int M) {
		this.cordi = new Cordi();
		this.cordi.row = curr.row;
		this.cordi.col = curr.col;
		this.dir = dir;
		Robot.N = N;
		Robot.M = M;

	}

	public Cordi getForward() {
		// 바라보는 방향 앞을 돌려준다. // 검사 안하고 돌려준다 불가능하면 해야하는 일이 있어서
		int nextRow = dirMap[dir][0] + this.cordi.row;
		int nextCol = dirMap[dir][1] + this.cordi.col;

		return new Cordi(nextRow, nextCol);
	}

	public Cordi getBackward() {
		// 바라보는 뒤 방향 앞을 돌려준다. // 검사 안하고 돌려준다 불가능하면 해야하는 일이 있어서
		int nextRow = this.cordi.row - dirMap[dir][0];
		int nextCol = this.cordi.col - dirMap[dir][1];
		return new Cordi(nextRow, nextCol);
	}

	public List<Cordi> getFourDir() {
		// 바라보는 방향 앞을 돌려준다. // 범위 검사하고 돌려준다. 범위 나가면 볼때 체크를 안하게 하기 위해서
		List<Cordi> fourForward = new ArrayList<>();
		for (int[] d : dirMap) {
			int nextRow = d[0] + this.cordi.row;
			int nextCol = d[1] + this.cordi.col;
			if (isIn(nextRow, nextCol)) {
				fourForward.add(new Cordi(nextRow, nextCol));
			}

		}
		return fourForward;

	}

	public static boolean isIn(int row, int col) {
		return row >= 0 && row < N && col >= 0 && col < M;
	}

	public void goForward() { // 이동만한다 외부에서 체크가 필요
		this.cordi.row = dirMap[dir][0] + this.cordi.row;
		this.cordi.col = dirMap[dir][1] + this.cordi.col;
	}

	public void goBackward() {
		// 바라보는 뒤 방향 앞을 돌려준다. // 검사 안하고 돌려준다 불가능하면 해야하는 일이 있어서
		this.cordi.row -= dirMap[dir][0];
		this.cordi.col -= dirMap[dir][1];
		;
	}

	public void turnRight() {
		dir = ((dir - 1) +4) % 4; // 방향을  반시계로 돌린다.
	}

	@Override
	public String toString() {
		return "Robot{" +
			"cordi=" + cordi +
			", dir=" + dir +
			'}';
	}
}

public class Main {
	static int N;
	static int M;
	static boolean[][] map; // 벽 확인용
	static boolean[][] cleanCheck; // 청소구영 확인용 // 청소를 했던곳이라도 벽만 아니면 이동가능

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new boolean[N][M];
		cleanCheck = new boolean[N][M];
		st = new StringTokenizer(br.readLine());
		int startRow = Integer.parseInt(st.nextToken());
		int startCol = Integer.parseInt(st.nextToken());
		int startDir = Integer.parseInt(st.nextToken());
		Cordi startCordi = new Cordi(startRow, startCol);
		Robot robot = new Robot(startCordi, startDir, N, M);
		for (int row = 0; row < N; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < M; col++) {
				int val = Integer.parseInt(st.nextToken());
				map[row][col] = val == 1; // 벽 또는 빈칸
				cleanCheck[row][col] = val == 1; // 빈칸이면 false 벽이면 true  벽이면 청소한걸로 취급
			}
		}
		int res = 0;
		while (true) {
			//print(robot);
			if (!map[robot.cordi.row][robot.cordi.col] && !cleanCheck[robot.cordi.row][robot.cordi.col]) {
				//System.out.println("in");
				cleanCheck[robot.cordi.row][robot.cordi.col] = true;
				res++;
			}
			if (!isAnyFourWayCanCleaning(robot)) { // 모두 청소가 불가능하면
				Cordi backCordi = robot.getBackward();
				if (Robot.isIn(backCordi.row, backCordi.col) && map[backCordi.row][backCordi.col]) {
					// 뒤가 벽이면
					break;

				} else if(Robot.isIn(backCordi.row, backCordi.col)&&! map[backCordi.row][backCordi.col]){
					robot.goBackward();
				}

			} else {
				//하나라도 청소가 가능하면
				robot.turnRight();
				Cordi forwardCordi = robot.getForward();
				if (Robot.isIn(forwardCordi.row, forwardCordi.col) && !map[forwardCordi.row][forwardCordi.col]
					&& !cleanCheck[forwardCordi.row][forwardCordi.col]) {
					// map 안에 있고, 이동 가능하고, 청소가 되있지 않다면
					robot.goForward();
				}//바라보는 방향을 기준으로 앞쪽 칸이 청소되지 않은 빈 칸인 경우 한 칸 전진한다.
			}

		}
		System.out.println(res);

	}

	public static boolean isAnyFourWayCanCleaning(Robot robot) { // 한곳이라도 청소가 안되면 true; 청소가 다되었으면
		//int canCount = 0;
		for (Cordi cordi : robot.getFourDir()) { // 범위 검사하고 나온다.
			if (!cleanCheck[cordi.row][cordi.col] && !map[cordi.row][cordi.col]) { // 하나라동 청소가능하면
				return true;
			}
		}
		return false;
	}
	public static void print(Robot robot){ // 출력용 함수
		System.out.println();
		for(int row = 0; row < N; row++ ){
			for(int col = 0; col < M; col++){
				if(row ==robot.cordi.row &&col == robot.cordi.col){
					if(robot.dir == 0){
						System.out.print("u");
					} else if (robot.dir == 1 ) {
						System.out.print(">");
					} else if (robot.dir == 2 ) {
						System.out.print("d");
					} else if (robot.dir == 3 ) {
						System.out.print("<");
					}
				}else {
					System.out.print(cleanCheck[row][col] ? 1:0);
				}
				System.out.print(" ");
			}
			System.out.println();
		}

	}
}



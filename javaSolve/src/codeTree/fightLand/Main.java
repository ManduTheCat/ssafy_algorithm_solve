package codeTree.fightLand;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Player {
	int row;
	int col;

	int d;
	private int s;
	int w;
	int idx;

	public int getS() {
		return s;
	}

	public Player(int row, int col, int d, int s, int w, int idx) {
		this.row = row;
		this.col = col;
		this.d = d;
		this.s = s;
		this.w = w;
		this.idx = idx;
	}

	@Override
	public String toString() {
		return "Player{" +
			"row=" + row +
			", col=" + col +
			", d=" + d +
			", s=" + s +
			", w=" + w +
			", idx=" + idx +
			'}';
	}
}

public class Main {
	static int N;
	static int K;
	static int M;
	static int[][] map;
	static Player[] players;
	static int[] point;
	static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken()); // round 횟수
		map = new int[N][N];
		point = new int[M];
		players = new Player[M];
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			for (int m = 0; m < N; m++) {
				int val = Integer.parseInt(st.nextToken());
				map[n][m] = val;
			}
		}
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken()); // 0~3
			int s = Integer.parseInt(st.nextToken());
			players[m] = new Player(x - 1, y - 1, d, s, 0, m);
		}
		// K+=1;
		while (K-- > 0) {
			// round1
			System.out.println("round " + K);
			for (Player p : players) {
				System.out.println(p + " 가 이동합니다.");
				// 앞으로 가기
				if (!forward(p)) {// 가능하면 이동한다.
					// 벽에 부딪친다면 반대 방향으로 돌고 앞으로 한칸 == 두번 dir 돌리고 앞으로 한칸.
					int nextDirIdx = (p.d + 2) % 4;
					int nextRow = p.row + dir[nextDirIdx][0];
					int nextCol = p.col + dir[nextDirIdx][1];
					p.row = nextRow;
					p.col = nextCol;
					p.d = nextDirIdx;
				}
				for (Player enemy : players) {// 자기자신을 제외해야한다.........
					if (p.row == enemy.row && p.col == enemy.col && p.idx != enemy.idx) {
						//싸운다. 총은 줍지 않는다.
						System.out.println(p + " 차례에 " + enemy + " 와 싸웁니다.");
						// printMap();
						fight(p, enemy);
						// printMap();
					} else {// 부딪친 사람이 없다면
						// 총을 줍는다.// 이미 이동했다.
						getGun(p);
					}
				}

			}
			System.out.println("====싸움 이후 결과 들 start====");
			printMap();
			printPLayer();
			for (Player p : players) {
				System.out.println(p);
			}
			System.out.println("====싸움 이후 결과 들 end ====");
		}

		System.out.println("=====res=======");
		printMap();
		printPLayer();
		System.out.println(Arrays.toString(point));
	}

	public static void fight(Player p, Player enemy) {
		// 총이 있는곳에서 싸우는처리 확인 필요
		int pPower = p.getS() + p.w;
		int ePower = enemy.getS() + enemy.w;
		if (pPower > ePower) {
			// 왼쪽이 이긴경우
			point[p.idx] += Math.abs(pPower - ePower);
			int dropWp = enemy.w; // 진사람 총을 땅에 떨군다
			enemy.w = 0; // 진사람은 무기가 없다.
			if (p.w < dropWp) { // 가지고 있는 총보다 떨군총이 더 크다면
				map[p.row][p.col] = p.w; // 낮은 걸땅에 놓고
				p.w = dropWp; // 떨군걸 가져간다.
			}// 진사람 이동해야한다.
			loseMove(enemy);
			getGun(enemy);
		} else if (pPower < ePower) {
			// 오른쪽이 이긴경우
			point[enemy.idx] += Math.abs(ePower - pPower);
			int dropWp = p.w;
			p.w = 0;
			if (enemy.w < dropWp) {
				map[enemy.row][enemy.col] = enemy.w;
				enemy.w = dropWp;
			}// 진사람 이동해야한다
			loseMove(p);
			getGun(p);
		} else {
			// 비긴경우 초기 능력치를 비교한다.
			// 각 플레이어의 초기 능력치와 가지고 있는 총의 공격력의 합의 차이만큼을 포인트로 획득하게 됩니다. = 0
			if (p.getS() > enemy.getS()) {
				// 포인트 증가는 없다
				int dropW = enemy.w;
				enemy.w = 0;
				if (p.w < dropW) {
					map[p.row][p.col] = p.w;
					p.w = dropW;
				}
				loseMove(enemy);
				getGun(enemy);

			} else {
				//초기 능력치는 모두 다릅니다 -> enemy 승
				// 포인트 증가는 없다.
				System.out.println("비겼다");
				int dropW = p.w;
				p.w = 0;
				if (enemy.w < dropW) {
					map[enemy.row][enemy.col] = enemy.w;
					enemy.w = dropW;
				}
				loseMove(p);
				getGun(p);

			}
		}

		System.out.println(Arrays.toString(point));
	}

	public static void loseMove(Player lose) {
		System.out.println("진사람 " + lose + " 이동합니다.");
		// 90 도씩 돌려보며 가능하면 이동한다. 그리고 총줍느다.
		if (!forward(lose)) {
			for (int add = 1; add < 4; add++) { // 1~3 나다음 3방향 까지만 보면된다
				int nextDir = (lose.d + add) % 4;
				int nextRow = lose.row + dir[nextDir][0];
				int nextCol = lose.col + dir[nextDir][1];
				if (isIn(nextRow, nextCol)) {
					lose.row = nextRow;
					lose.col = nextCol;
					lose.d = nextDir;
					break;
				}
			}
		}
	}

	public static void printMap() {
		System.out.println();
		for (int row = 0; row < N; row++) {
			System.out.println();
			for (int col = 0; col < N; col++) {
				System.out.print(map[row][col] + " ");
			}
		}
		System.out.println();
	}

	public static void printPLayer() {
		System.out.println();
		for (int row = 0; row < N; row++) {
			System.out.println();
			for (int col = 0; col < N; col++) {
				boolean flag = false;
				for (Player p : players) {
					if (p.row == row && p.col == col) {
						System.out.print((p.idx)  +" ");
						flag = true;
						break;
					}
				}
				if (!flag) {
					System.out.print("x ");
				}
			}
		}
		System.out.println();

	}

	public static void getGun(Player p) {
		// 작은 값을 줍고, 지도의 값을 바꾼다.
		int mapGun = map[p.row][p.col];
		if (p.w < mapGun) {
			System.out.println(p.idx +" : " + p.w+ " 가 " + mapGun + " 을 주웠습니다");
			map[p.row][p.col] = p.w;
			p.w = mapGun;
		}
	}

	public static boolean forward(Player p) {
		int nextRow = p.row + dir[p.d][0];
		int nextCol = p.col + dir[p.d][1];
		if (isIn(nextRow, nextCol)) {
			p.row = nextRow;
			p.col = nextCol;
			return true;
		}
		return false;

	}

	public static boolean isIn(int row, int col) {
		return row < N && row >= 0 && col < N && col >= 0;
	}
}

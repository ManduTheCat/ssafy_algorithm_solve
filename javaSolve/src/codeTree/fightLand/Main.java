package codeTree.fightLand;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
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
	static PriorityQueue<Integer> [][] map;
	static Player[] players;
	static int[] point;
	static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken()); // round 횟수
		point = new int[M];
		map = new PriorityQueue[N][N];
		players = new Player[M];
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			for (int m = 0; m < N; m++) {
				int val = Integer.parseInt(st.nextToken());
				map[n][m] = new PriorityQueue(Comparator.reverseOrder());
				if(val != 0){
					map[n][m].offer(val);
				}
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
			System.out.println("round " + K);
			printMap();
			for (Player p : players) {
				// System.out.println(p + " 가 이동을 시도합니다.");
				// 앞으로 가기
				if (!forward(p)) {// 가능하면 이동한다.
					// System.out.println("이동 성공!!");
					// 벽에 부딪친다면 반대 방향으로 돌고 앞으로 한칸 == 두번 dir 돌리고 앞으로 한칸.
					int nextDirIdx = (p.d + 2) % 4;
					int nextRow = p.row + dir[nextDirIdx][0];
					int nextCol = p.col + dir[nextDirIdx][1];
					p.row = nextRow;
					p.col = nextCol;
					p.d = nextDirIdx;
				}// 이동은 한상태

				/**
				 * 싸움 연속 가능하다 싸움이 안날떄까지 돌려야한다.
				 */
				for (Player enemy : players) {// 자기자신을 제외해야한다.........
					if (p.row == enemy.row && p.col == enemy.col && p.idx != enemy.idx) {
						System.out.println( p + " 는 적 " + enemy +  " 를 발견했다");
						fight(p, enemy); // 승자는 그자리에서 총을 줍는다.패자는 이동하고 총줍는다.
						// 그냥 가서 줍는거랑, 승자가 총을 줍는건 다르다.
					}
				}
				getGun(p);


			}
			System.out.println("====싸움 이후 결과 들 start====");
			printPLayer();
			for (Player p : players) {
				System.out.println(p);
			}
			System.out.println("====싸움 이후 결과 들 end ====");
		}

		// System.out.println("=====res=======");
		// printPLayer();
		// for (Player p : players) {
		// 	System.out.println(p);
		// }
		for (int p : point) {
			System.out.print(p + " ");
		}
	}

	public static void fight(Player p, Player e) {// 슬자는 총을 줍고 패자는 총을 버린다.
		// 싸운다.
		int pPower = p.w + p.getS();
		int ePower = e.w + e.getS();
		int mapW = 0;
		if(!map[p.row][p.col].isEmpty()){
			mapW = map[p.row][p.col].poll();
		}
		if(pPower > ePower){
			// 만약 p 가이기면 p 무기와 바닥에 있는 무기, 적의 무기중 가장 강한 무기를 골라서 교체한다.
			// 원래 무기는 바닥에 놓는다.
			point[p.idx] = Math.abs(pPower-ePower);
			if(e.w != 0){
				map[p.row][p.col].offer(e.w);
			}
			e.w = 0;// 졌으니 무기를 떨군다.
			int big = maxW(mapW, p.w);
			// System.out.println("승자 " + p  +  " " + big + "겟또");
			p.w = big;// 승자는 가장쏀걸 고른다.
			loseMove(e);
			getGun(e);// 이동하고 총줍는다.

		}else if(pPower < ePower){
			point[e.idx] = Math.abs(pPower - ePower);
			if(p.w != 0){
				map[p.row][p.col].offer(p.w);
			}
			p.w = 0;
			int big = maxW(mapW, e.w);
			e.w = big;
			// System.out.println("승자 " + e  +  " " + big + "겟또");
			loseMove(p);
			getGun(p);

		}else {
			//같은 값을 가지고 있다면 아무도 포인트를 얻지 못한다.
			// 하지만 s 값을 가지고 승패는 결정된다.
			// 하지만 진사람은 총을 버린다. 그리고 이긴사람은 총을 골라간다.
			// 진사람은 이동도하고 맵에 총을 줍느다.
			if(p.getS() > e.getS()){
				//p 가 승리했다.
				if(e.w != 0){
					map[p.row][p.col].offer(e.w);// 패패자의 총기 버리기
				}
				e.w = 0;
				int big = maxW(mapW, p.w);
				p.w = big;
				loseMove(e);
				getGun(e);
			}else {// s 값은 고유 값이기에 항상 승아니면 패 이다. 그래서 else처리
				// e 가 승리한다.
				if(p.w != 0){
					map[e.row][e.col].offer(p.w);
				}
				p.w = 0;
				int big = maxW(mapW, e.w); // 승자의 무기와 맵에 가장 강렬한 무기중 큰거 선정
				e.w = big;
				loseMove(p);
				getGun(p);
			}

		}
	}

	public static int maxW(int a, int b){
		int[] temp = new int[2];
		temp[0] = a;
		temp[1] = b;
		Arrays.sort(temp);
		return temp[1];
	}

	public static void loseMove(Player lose) {
		// System.out.println("진사람 " + lose + " 이동합니다.");
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

		int mapGun = 0;
		if(!map[p.row][p.col].isEmpty()){
			mapGun = map[p.row][p.col].peek(); // 가장 큰값을꺼낸다.
		}
		if (p.w < mapGun) {
			map[p.row][p.col].poll();
			if(p.w != 0){
				map[p.row][p.col].offer( p.w);
			}
			p.w = mapGun;
			System.out.println(p.idx +" : " +p.getS() +" + w:"+ p.w+ " 가 " + mapGun + " 을 주웠습니다");
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

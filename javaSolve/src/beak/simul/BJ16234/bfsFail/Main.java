package beak.simul.BJ16234.bfsFail;

import java.io.*;
import java.util.*;
class Cordi {
	int row, col;

	public Cordi(int row, int col) {
		this.row = row;
		this.col = col;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Cordi cordi = (Cordi)o;
		return row == cordi.row && col == cordi.col;
	}

	@Override
	public int hashCode() {
		return Objects.hash(row, col);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[").append(row).append(",").append(col).append("]");
		return sb.toString();
	}
}

public class Main {
	static int N;
	static int L;
	static int R;
	static int[][] input;
	static Map<Integer, Set<Cordi>> groupMap;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("resources/daily/bfs/movepeople/input1.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		groupMap = new HashMap<>();
		int day = 0;
		input = new int[N][N];
		for (int row = 0; row < N; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < N; col++) {
				input[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		// while () { //
			findGroupBfs();
			groupNomalization();
			day++;
			printArr(input);
		System.out.println(groupMap);
		// }


	}

	private static void groupNomalization() {

		for(int key:groupMap.keySet()){
			Set<Cordi> group = groupMap.get(key);
			double sum = 0;
			for(Cordi cordi :group){
				sum += input[cordi.row][cordi.col];
			}
			for(Cordi cordi : group){
				input[cordi.row][cordi.col] = (int)(sum/group.size());
			}
			System.out.println(sum);
			System.out.println(group.size());
		}
	}

	// 그룹 가능한 갯수를 완탐으로 묶는 함수 // 틀림 테케 1의 1,1 을 가져가지 않는다 왜냐 미리 탐사를 했기 때문에
	private static boolean findGroupBfs() {
		boolean flag = false;

		int groupCount = 1;
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				boolean[][] check = new boolean[N][N];
				if (!check[row][col]) {// 있다면 인접한 국가를 찾는다.
					bfs(row, col, check, groupCount);
					groupCount++;
					flag = true;
				}
			}
		}
		return flag;
	}

	private static void printArr(boolean[][] check) {
		System.out.println();
		for(boolean[] bl: check){
			for (boolean b : bl){
				System.out.print(b ? 1:0);
			}
			System.out.println();
		}
	}
	private static void printArr(int[][] arr) {
		System.out.println();
		for(int[] arrRow: arr){
			for (int val : arrRow){
				System.out.print(val);
			}
			System.out.println();
		}
	}

	private static void bfs(int row, int col, boolean[][] check, int groupCount) {
		Queue<Cordi> q = new ArrayDeque<>();
		q.add(new Cordi(row, col));
		check[row][col] = true;
		int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
		while (!q.isEmpty()) {
			Cordi cur = q.poll();
			for (int d = 0; d < 4; d++) {
				int nextRow = dir[d][0] + cur.row;
				int nextCol = dir[d][1] + cur.col;
				if (isIn(nextRow, nextCol) && !check[nextRow][nextCol]&& isAbleOpen(cur, new int[] {nextRow, nextCol})) {
					check[nextRow][nextCol] = true;
					q.add(new Cordi(nextRow, nextCol));
					if (!groupMap.containsKey(groupCount)) {
						groupMap.put(groupCount, new HashSet<>());
						Set<Cordi> temp = groupMap.get(groupCount);// 다음것만 넣으면 안된다.지금 값도 넣어야한다.
						temp.add(new Cordi(cur.row, cur.col));
					}else {
						Set<Cordi> temp = groupMap.get(groupCount);
						temp.add(new Cordi(nextRow, nextCol));
						temp.add(new Cordi(cur.row, cur.col));
						groupMap.put(groupCount,temp);
					}
				}
			}
		}
	}

	private static boolean isAbleOpen(Cordi cur, int[] next) {
		int currVal = input[cur.row][cur.col];
		int nextVal = input[next[0]][next[1]];
		int diff = Math.abs(currVal - nextVal);
		if (diff >= L && diff <= R) {
			//가능하다면
			System.out.println("가능  : " +cur+ "->" + next[0]  +" " + next[1] + " diff:" + diff);
			return true;
		}
		return false;
	}

	private static boolean isIn(int nextRow, int nextCol) {
		return nextRow < N && nextRow >= 0 && nextCol < N && nextCol >= 0;
	}

}

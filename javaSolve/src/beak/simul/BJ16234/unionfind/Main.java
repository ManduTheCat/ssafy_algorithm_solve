package beak.simul.BJ16234.unionfind;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.StringTokenizer;

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

/**
 * 로직
 *  국격개방이 가능한 나라가 없을때까지 반복 -> 유니온파인드로 국경 개방 그룹을 찾는다 -> 평균으로 갈아 끼운다 -> day++
 */
public class Main {
	static int N;
	static int L;
	static int R;
	static int[][] input;
	static int[] parents; // 유니온 파인드에 사용될 parents
	static Map<Integer, Cordi> pMap; // index 와 좌표 를 보관하는맵 {0 :  0,0}, {1 : 0,1}
	static Map<Integer, ArrayList<Integer>> groupMap; // 누가 어디에 속해있는지 만든 map

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("resources/daily/bfs/movepeople/input5.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		parents = new int[N * N];
		pMap = new HashMap<>();
		int day = 0;
		groupMap = new HashMap<>();
		input = new int[N][N];
		int p = 0;
		for (int row = 0; row < N; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < N; col++) {
				input[row][col] = Integer.parseInt(st.nextToken());
				parents[p] = p;
				pMap.put(p, new Cordi(row, col));
				p++;

			}
		}
		int[] originParents = parents.clone();
		while (isAbleDay()){ //N *N *2000// 가능한게 하나라도 있으면 진행한다.
			findGroup();// 유니온 파인드로 그룹찾고  parents 에 기록하는함수 // N * N * 4
			makeGropMap(); // 유니온 파인드 한 parents 를 groupMap 으로 바꾸는 함수
			groupNomalization(groupMap); // N * N // groupMap 을 순회하면서  평균으로 바꿔주는 함수
			// printArr(input);
			day++;
			// 초기화
			groupMap = new HashMap<>();
			parents = originParents.clone();
			printArr(input);
		}
		System.out.println(day);

	}

	private static void makeGropMap() {
		for (int idx = 0; idx < N * N; idx++) {
			// 몇번그룹에 뭐가 속해 있는지 groupMap 에 넣는다.
			if (groupMap.containsKey(parents[idx])) {
				ArrayList<Integer> getList = groupMap.get(parents[idx]);
				getList.add(idx);
				groupMap.put(parents[idx], getList);
			} else {
				ArrayList<Integer> add = new ArrayList<>();
				add.add(idx);
				groupMap.put(parents[idx], add);
			}

		}
	}

	private static boolean isAbleDay() { // N* N; // 이웃된것중에 국경을 열수 있는지 확인하는 함수 while 에 사용
		int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				Cordi cur = new Cordi(row, col);
				for (int d = 0; d < 4; d++) {
					// 인덱스를 좌표로 바꿀수 있다.
					int nextRow = cur.row + dir[d][0];
					int nextCol = cur.col + dir[d][1];
					if (isIn(nextRow, nextCol) && isAbleOpen(cur, new int[] {nextRow, nextCol})) {
						return true;
					}
				}
			}
		}
		return false;
	}

	private static void findGroup() {
		int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
		for (int idx : pMap.keySet()) {
			Cordi cur = pMap.get(idx);
			for (int d = 0; d < 4; d++) {
				// 인덱스를 좌표로 바꿀수 있다.
				int nextRow = cur.row + dir[d][0];
				int nextCol = cur.col + dir[d][1];
				if (isIn(nextRow, nextCol) && isAbleOpen(cur, new int[] {nextRow, nextCol})) {
					int nextIdx = nextRow * N + nextCol;
					union(idx, nextIdx);
				}
			}
		}
	}

	private static void groupNomalization(Map<Integer, ArrayList<Integer>> groupMap) {
		for (int group : groupMap.keySet()) {
			int sum = 0;
			ArrayList<Integer> groupMembers = groupMap.get(group);
			for (int member : groupMembers) {
				Cordi cordi = pMap.get(member);
				sum += input[cordi.row][cordi.col];
			}
			int res = sum / groupMembers.size();
			for (int member : groupMembers) {
				Cordi cordi = pMap.get(member);
				input[cordi.row][cordi.col] = res;
			}
			input[pMap.get(group).row][pMap.get(group).col] = res;
		}

	}

	private static int find(int target) {
		if (parents[target] == target) {
			return target;
		}
		return parents[target] = find(parents[target]);
	}

	private static boolean union(int x, int y) {
		x = find(x);
		y = find(y);
		if (x == y)
			return false;
		if (x <= y)
			parents[y] = x;
		else
			parents[x] = y;
		return true;
	}

	private static void printArr(boolean[][] check) {
		System.out.println();
		for (boolean[] bl : check) {
			for (boolean b : bl) {
				System.out.print(b ? 1 : 0);
			}
			System.out.println();
		}
	}

	private static void printArr(int[][] arr) {
		System.out.println();
		for (int[] arrRow : arr) {
			for (int val : arrRow) {
				System.out.print(val + " ");
			}
			System.out.println();
		}
	}

	private static boolean isAbleOpen(Cordi cur, int[] next) {
		int currVal = input[cur.row][cur.col];
		int nextVal = input[next[0]][next[1]];
		int diff = Math.abs(currVal - nextVal);
		if (diff >= L && diff <= R) {
			//가능하다면
			// System.out.println("가능  : " + cur + "->" + next[0] + " " + next[1] + " diff:" + diff);
			return true;
		}
		return false;
	}

	private static boolean isIn(int nextRow, int nextCol) {
		return nextRow < N && nextRow >= 0 && nextCol < N && nextCol >= 0;
	}

}

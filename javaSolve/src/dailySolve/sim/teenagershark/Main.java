package dailySolve.sim.teenagershark;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int[][] directions = {{-1, 0}, {-1, -1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}};

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./resources/daily/sim/shark/test.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Fish[][] initFish = new Fish[4][4];
		List<Fish> inputOrder = new ArrayList<>();
		for (int row = 0; row < 4; row++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int col = 0; col < 4; col++) {
				int num = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				initFish[row][col] = new Fish(row, col, dir - 1, num, false);
				inputOrder.add(new Fish(row, col, dir - 1, num, false));
			}
		}
		Collections.sort(inputOrder);
		// 상어 진입 +  물고기 처형
		Shark shark = new Shark(0, 0, initFish[0][0].dir, new ArrayList<>());
		initFish[0][0].isDeath = true;

		// 여길 dfs 로 잡아야한다.

		// System.out.println(inputOrder.get(0).row + " : " + inputOrder.get(0).col);
		Fish[][] nextFishState = moveFish(initFish, inputOrder, shark);
		// 룰고기들이 이동하는 함수
		// 상어가 먹을수 있는 후보를 주는 함수 -> 받은 후보는 를 돌면서 dfs
		// System.out.println(inputOrder.get(0).row + " : " + inputOrder.get(0).col);
		for (Fish[] fish : nextFishState) {
			System.out.println(Arrays.toString(fish));
		}

		// 이제 상어 이동 할차레
		// List<Fish> targetFish = moveShark(shark, nextFishState);
		//타겟중 하나를 선텍하는걸로 dfs 를 돌린다.
		// System.out.println(targetFish);
		// dfs(initFish, shark, inputOrder);

	}

	private static void dfs(Fish[][] fishState, Shark shark, List<Fish> orderFish) {
		// 상어가 먹으면 물고기는 death 상태가 되야한다.
		int nextRow = shark.row + directions[shark.dir][0];
		int nextCol = shark.col + directions[shark.dir][1];
		if (!isIn(nextRow, nextCol) || fishState[nextRow][nextCol].isDeath) {
			return;
		}
		// 딥카피 부분
		List<Fish> newOrderFish = new ArrayList<>(orderFish);
		Fish[][] nextFishState = moveFish(fishState,newOrderFish,shark);
		System.out.println(Arrays.deepToString(nextFishState));
		List<Fish> eatFishList = moveShark(shark, nextFishState);
		for (Fish eatFish : eatFishList) {
			// 각각 dfs 돌려본다.
			// 상어가 잡아먹으면 죽여야한다.
			List<Fish> nextEatList = new ArrayList<>(shark.eatList);
			nextEatList.add(eatFish);
			Shark nextShark = new Shark(eatFish.row, eatFish.col, eatFish.dir, nextEatList);
			dfs(nextFishState, nextShark, newOrderFish);
		}
	}

	private static List<Fish> moveShark(Shark shark, Fish[][] nextFishState) {
		// 상어의 직진방향을 바라보고 잡아 먹을수 있는 물고기를 반환한다.
		List<Fish> targetFishList = new ArrayList<>();
		int checkRow = shark.row + directions[shark.dir][0];
		int checkCol = shark.col + directions[shark.dir][1];
		while (isIn(checkRow, checkCol)) {//
			if (!nextFishState[checkRow][checkCol].isDeath) {
				targetFishList.add(nextFishState[checkRow][checkCol]);
			}
			checkRow = checkRow + directions[shark.dir][0];
			checkCol = checkCol + directions[shark.dir][1];
		}
		return targetFishList;
	}

	private static Fish[][] moveFish(Fish[][] fishesSate, List<Fish> orderFish, Shark curShark) {
		// 물고기가 갈수 없는 상황 상어가 있거나, 벽으로 막혀 있거나,
		// 갈수 있는 상황 물고기가 있거나, 빈칸이거나
		Fish[][] res = new Fish[4][4];
		for (int order = 0; order < orderFish.size(); order++) {// 물고기 순서 대로 돈다.
			if (orderFish.get(order).isDeath)
				continue;// 죽은건 취급 안한다.
			Fish curOrderFish = fishesSate[orderFish.get(order).row][orderFish.get(order).col];
			int nextRow = curOrderFish.row + directions[curOrderFish.dir][0];
			int nextCol = curOrderFish.col + directions[curOrderFish.dir][1];
			// 만약 밖으로 가거나, 상어 가 있는곳이라면  물고기 방향은 계속  돈다.
			while (!isIn(nextRow, nextCol) || (nextRow == curShark.row && nextCol == curShark.col)) {
				// 현제 방향에서 돌린다.
				// System.out.println(curOrderFish.dir + 1);
				curOrderFish.dir = (curOrderFish.dir + 1) % 8;
				// System.out.println(curOrderFish.dir);
				nextRow = curOrderFish.row + directions[curOrderFish.dir][0];
				nextCol = curOrderFish.col + directions[curOrderFish.dir][1];
			}
			// swap 할때 오더랑 res , fishState 에 반영되야한다.
			// 가는곳에 원래 있언것
			Fish originFish = fishesSate[nextRow][nextCol];
			// System.out.println("origin fish: " + originFish);
			Fish swapTarget = new Fish(orderFish.get(order).row, orderFish.get(order).col, originFish.dir,
				originFish.num, false);
			Fish moveFish = new Fish(nextRow, nextCol, curOrderFish.dir, curOrderFish.num, false);
			// System.out.println("swap " + swapTarget + " " + swapTarget.row + " " + swapTarget.col + " -> "+"move "+ moveFish + " " + moveFish.row  +" " + moveFish.col);
			System.out.println("");
			System.out.println("나는 몇번째 순서다 :"  + (order +1)); // 해쉬로 해보자
			for (Fish[] re : res) {
				System.out.println(Arrays.toString(re));
			}
			System.out.println("");
			// orderFish.set(order, moveFish);
			// orderFish.set(swapTarget.num - 1, swapTarget);
			fishesSate[nextRow][nextCol] = moveFish;
			fishesSate[curOrderFish.row][curOrderFish.col] = swapTarget;
			res[swapTarget.row][swapTarget.col] = swapTarget;
			res[moveFish.row][moveFish.col] = moveFish;
		}
		return res;
	}

	private static boolean isIn(int nextRow, int nextCol) {
		return nextRow >= 0 && nextRow < 4 && nextCol >= 0 && nextCol < 4;
	}
}

class Shark {
	int row, col;
	int dir;
	List<Fish> eatList = new ArrayList<>();

	public Shark(int row, int col, int dir, List<Fish> eatList) {
		this.row = row;
		this.col = col;
		this.dir = dir;
		this.eatList = eatList;
	}

	@Override
	public String toString() {
		return "Shark{" +
			"row=" + row +
			", col=" + col +
			", dir=" + dir +
			", eatList=" + eatList +
			'}';
	}
}

class Fish implements Comparable<Fish> {
	int row, col;
	int dir;
	int num;
	boolean isDeath = false;

	public Fish(int row, int col, int dir, int num, boolean isDeath) {
		this.row = row;
		this.col = col;
		this.dir = dir;
		this.num = num;
		this.isDeath = isDeath;
	}

	@Override
	public int compareTo(Fish o) {
		return this.num - o.num;
	}

	@Override
	public String toString() {
		return "" + num;
	}
}

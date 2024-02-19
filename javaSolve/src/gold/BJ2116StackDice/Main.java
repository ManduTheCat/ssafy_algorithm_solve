package gold.BJ2116StackDice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

class Dice {
	int diceIdx;
	int top;
	int bottom;
	int max = -1;

	public Dice(int diceIdx, int top, int bottom) {
		this.diceIdx = diceIdx;
		this.top = top;
		this.bottom = bottom;
	}

	public void getMaxNum() {
		boolean[] check = new boolean[7]; // 해당 값은 제외한다.
		//01233456
		// top  bottom 제외하고 최대값을 구한다.
		check[this.top] = true;
		check[this.bottom] = true;
		int maxres = -1;
		//123456
		for (int i = 1; i < 7; i++) {
			if (!check[i]) {
				maxres = Math.max(i, maxres);
			}
		}
		this.max = maxres;
	}

	@Override
	public String toString() {
		return "Dice{" +
			"diceIdx=" + diceIdx +
			", top=" + top +
			", bottom=" + bottom +
			", max=" + max +
			'}';
	}
}

public class Main {
	static int N;
	static int[][] posNum;
	static int[][] numPos;
	static int[] maxDiceValue;

	static boolean[] pickCheck;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		posNum = new int[N][6]; // 위치의 값을 기록한 공간 위치로 값을 구할수 있다.
		numPos = new int[N][6 + 1]; // 값의 위치를 기록한 공간 0: A 1:B 2:C 3:D 4:E 5:F 값으로 위치를 알수 있다. 0~5
		maxDiceValue = new int[N ]; // depth 별 최대 값을 기록할 공간
		pickCheck = new boolean[N];
		for (int dice = 0; dice < N; dice++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int pos = 0; pos < 6; pos++) {
				posNum[dice][pos] = Integer.parseInt(st.nextToken()); // 1~6
			}
			for (int pos = 0; pos < 6; pos++) {
				int num = posNum[dice][pos]; //1~6
				numPos[dice][num] = pos;
			}
		}
		for (int diceNum = 0; diceNum < N; diceNum++) {
			pickCheck = new boolean[N];
			//pickCheck[diceNum] = true;
			int[] currDiceValues = posNum[diceNum];
			// 0 -> 5
			List<Dice> diceCase = new ArrayList<>();
			Dice currDice = new Dice(diceNum, currDiceValues[0], currDiceValues[5]);
			currDice.getMaxNum();
			diceCase.add(currDice);
			// 5 - > 0
			currDice = new Dice(diceNum, currDiceValues[5], currDiceValues[0]);
			currDice.getMaxNum();
			// 1 -> 3
			currDice = new Dice(diceNum, currDiceValues[1], currDiceValues[3]);
			currDice.getMaxNum();
			// 3 - > 1선택
			currDice = new Dice(diceNum, currDiceValues[3], currDiceValues[1]);
			currDice.getMaxNum();
			// 2- >4선택
			currDice = new Dice(diceNum, currDiceValues[2], currDiceValues[4]);
			currDice.getMaxNum();
			// 4 -> 2 선택
			currDice = new Dice(diceNum, currDiceValues[4], currDiceValues[2]);
			currDice.getMaxNum();
			int maxIdx = -1;
			int idx = 0;
			int maxValue = -1;
			for (Dice dice : diceCase) {
				if (maxValue < dice.max) {
					maxValue = dice.max;
					maxIdx = idx;
				}
				idx++;
			}
			pickCheck[diceCase.get(maxIdx).diceIdx] = true;
			findMaxValue(diceCase.get(maxIdx), 0);
			//pickCheck[diceCase.get(maxIdx).diceIdx] = false;

		}
		System.out.println(Arrays.stream(maxDiceValue).sum());

	}

	// permutation
	private static void findMaxValue(Dice dice, int depth) {
		// 주사위가 넘어오면 주사위 의 눔금을 확인하고 최대값을 구한다
		// 만약 기록된 최대값보다 작다면 return
		// 깊이가 N 이면 return;
		if (depth == N) {
			System.out.println(Arrays.toString(maxDiceValue));
			// 여길 들어오기전에 다 가지가 쳐지네?
			return;
		}
		if (dice.max > maxDiceValue[depth]) {
			maxDiceValue[depth] = dice.max;
		}
		for (int nextDiceNum = 0; nextDiceNum < N; nextDiceNum++) {
			if (!pickCheck[nextDiceNum]) {
				// 값이 현제 주사위 탑과 같은게 있다면 == 무조건 있다. 인덱스별로 다음 재귀로 넘긴다.
				// nextDiceNum 주사위가 bottom 이 5 이고 현제 dice 탑과 값이 같다면
				if (numPos[nextDiceNum][dice.top] == 5) {
					//int top, int bottom
					Dice nextDice = new Dice(nextDiceNum, posNum[nextDiceNum][0], posNum[nextDiceNum][5]);
					nextDice.getMaxNum();
					pickCheck[nextDiceNum] = true;
					findMaxValue(nextDice, depth + 1);
				}
			}
			// 0 -> 5
			// 고른 주사위 바텀이 0 이라면?
			else if (numPos[nextDiceNum][dice.top] == 0) {
				Dice nextDice = new Dice(nextDiceNum, posNum[nextDiceNum][5], posNum[nextDiceNum][0]);
				nextDice.getMaxNum();
				pickCheck[nextDiceNum] = true;
				findMaxValue(nextDice, depth + 1);

			}
			// 1 -> 3
			// 고른 주사위가 바텀이 1 이라면
			else if (numPos[nextDiceNum][dice.top] == 1) {
				Dice nextDice = new Dice(nextDiceNum, posNum[nextDiceNum][3], posNum[nextDiceNum][1]);
				nextDice.getMaxNum();
				pickCheck[nextDiceNum] = true;
				findMaxValue(nextDice, depth + 1);

			}
			// 3 - > 1선택
			else if (numPos[nextDiceNum][dice.top] == 3) {
				Dice nextDice = new Dice(nextDiceNum, posNum[nextDiceNum][1], posNum[nextDiceNum][3]);
				nextDice.getMaxNum();
				pickCheck[nextDiceNum] = true;
				findMaxValue(nextDice, depth + 1);
			}
			// 2- >4선택
			else if (numPos[nextDiceNum][dice.top] == 2) {
				Dice nextDice = new Dice(nextDiceNum, posNum[nextDiceNum][4], posNum[nextDiceNum][2]);
				nextDice.getMaxNum();
				pickCheck[nextDiceNum] = true;
				findMaxValue(nextDice, depth + 1);

			}
			// 42 선택
			else if (numPos[nextDiceNum][dice.top] == 4) {
				Dice nextDice = new Dice(nextDiceNum, posNum[nextDiceNum][2], posNum[nextDiceNum][4]);
				nextDice.getMaxNum();
				pickCheck[nextDiceNum] = true;
				findMaxValue(nextDice, depth + 1);
			}
		}

	}

}



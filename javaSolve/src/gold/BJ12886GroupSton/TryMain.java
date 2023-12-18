package gold.BJ12886GroupSton;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class TryMain {
	static class State {
		int a;
		int b;
		int c;

		public State(int a, int b, int c) {
			this.a = a;
			this.b = b;
			this.c = c;
		}

		public boolean isSame() {
			return a == b && b == c;
		}
	}

	static boolean[][][] check; // X < Y 일때  X + X  < Y성립? yes

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		// 조합의 중복을 체크 해야한다. 총합은 같으니 마지막하나는 몰라도 된다 2차원으로 가능
		// 최악의 경우 500, 500, 500 이면 3218MB
		check = new boolean[A + B + C + 1][A + B + C + 1][A + B + C + 1];
		if ((A + B + C) % 3 != 0) {
			System.out.println(0);
			return;
		}
		System.out.println(bfs(A, B, C));

	}

	public static int bfs(int a, int b, int c) {

		Queue<State> q = new ArrayDeque<>();
		q.add(new State(a, b, c));
		while (!q.isEmpty()) {
			State curState = q.poll();
			if (curState.isSame()) {
				return 1;
			}

			if (curState.a != curState.b) {
				int[] ab = {curState.a, curState.b};
				int[] calAB = cal(ab);
				State nextState = new State(calAB[0], calAB[1], curState.c);
				if (calAB[0] >= 0 && calAB[1] >= 0 && !check[calAB[0]][calAB[1]][curState.c]) {
					check[calAB[0]][calAB[1]][curState.c] = true;
					q.add(nextState);

				}
			}
			if (curState.b != curState.c) {
				int[] bc = {curState.b, curState.c};
				int[] calBC = cal(bc);
				State nextState = new State(curState.a, calBC[0], calBC[1]);
				if (calBC[0] > 0 && calBC[1] > 0 && !check[curState.a][calBC[0]][calBC[1]]) {
					check[curState.a][calBC[0]][calBC[1]] = true;
					q.add(nextState);
				}
			}
			if (curState.a != curState.c) {// 앞에서 체크를 해버리면?
				int[] ac = {curState.a, curState.c};
				int[] calAC = cal(ac);
				State nextState = new State(calAC[0], curState.b, calAC[1]);
				if (calAC[0] > 0 && calAC[1] > 0 && !check[calAC[0]][curState.b][calAC[1]]) {
					check[calAC[0]][curState.b][calAC[1]] = true;
					q.add(nextState);
				}
			}

		}

		return 0;
	}

	public static int[] cal(int[] input) {
		int[] res = {-1, -1};
		int x;
		int y;
		// 둘다 양수여야하는데..
		if (input[0] < input[1]) {// 첫번쨰가 작을때
			x = input[0];
			y = input[1];
			//, 돌의 개수가 작은 쪽을 X, 큰 쪽을 Y라고 정한다. 그 다음, X에 있는 돌의 개수를 X+X개로, Y에 있는 돌의 개수를 Y-X개로 만든다.
			x += input[0];
			y -= input[0];
			res[0] = x;
			res[1] = y;
		} else { // 첫번째가 클때
			x = input[1];
			y = input[0];
			x += input[1];
			y -= input[1];
			res[1] = x;
			res[0] = y;

		}
		return res;

	}
}

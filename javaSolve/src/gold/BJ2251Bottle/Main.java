package gold.BJ2251Bottle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

class State {
	int a;
	int b;
	int c;

	public State(int a, int b, int c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}

	public boolean isEnd() {
		return this.a == 0;
	}

	@Override
	public String toString() {
		return "State{" +
			"a=" + a +
			", b=" + b +
			", c=" + c +
			'}';
	}
}

public class Main {
	// 한 물통이 비거나, 다른 하 물통이 가득 찰때까지 부을수 있다.
	// 초기에 AB 는 비어 있고 세번쨰는 가득차있다.
	// A 가 비어 있을때 C 에 담겨 있을수 있는 물의 양을 모두 구해네는 프로그램을 작성하시오
	// 포인트는 두개 코드 단순화 , 계산으로 하나를 구할 수 있다.
	static int aMax;
	static int bMax;
	static int cMax;
	static Set<Integer> res;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		aMax = Integer.parseInt(st.nextToken());
		bMax = Integer.parseInt(st.nextToken());
		cMax = Integer.parseInt(st.nextToken());
		boolean[][][] check = new boolean[aMax + 1][bMax + 1][cMax + 1]; // 물의 전체 량은 C 의 크기를 넘을 수 없다.
		State start = new State(0, 0, cMax);
		res = new HashSet<>();
		check[start.a][start.b][start.c] = true;
		dfs(start, check);

		List<Integer> resList = new ArrayList<>(res);

		Collections.sort(resList);
		resList.forEach(v -> System.out.print(v + " "));

	}

	static void dfs(State curr, boolean[][][] check) {
		if(curr.a == 0){
			res.add(curr.c);
		}
		if(curr.c > 0){
			int toGive = Math.min(curr.c, bMax - curr.b);
			int nextB = curr.b + toGive;
			int nextC = curr.c - toGive;
			int nextA = cMax - (nextB + nextC);
			if(!check[nextA][nextB][nextC]){
				check[nextA][nextB][nextC] = true;
				dfs(new State(nextA, nextB, nextC), check);
			}
		}

		// c->a
		if(curr.c > 0){
			int toGive = Math.min(curr.c, aMax - curr.a);
			int nextA = curr.a + toGive;
			int nextC = curr.c - toGive;
			int nextB = cMax - (nextA + nextC);
			if(!check[nextA][nextB][nextC]){
				check[nextA][nextB][nextC] = true;
				dfs(new State(nextA, nextB, nextC), check);
			}
		}
		//b->a
		if(curr.b > 0){
			int toGive = Math.min(curr.b, aMax- curr.a);
			int nextA = curr.a + toGive;
			int nextB = curr.b - toGive;
			int nextC = cMax - (nextB + nextA);
			if(!check[nextA][nextB][nextC]){
				check[nextA][nextB][nextC] = true;
				dfs(new State(nextA, nextB, nextC), check);
			}
		}

		//b ->C
		if(curr.b > 0){
			int toGive = Math.min(curr.b, cMax - curr.c);
			int nextC = curr.c + toGive;
			int nextB = curr.b - toGive;
			int nextA = cMax - (nextC + nextB);
			if(!check[nextA][nextB][nextC]){
				check[nextA][nextB][nextC] = true;
				dfs(new State(nextA, nextB, nextC), check);
			}
		}

		//a -> b
		if(curr.a > 0){
			int toGive = Math.min(curr.a, bMax - curr.b);
			int nextB = curr.b + toGive;
			int nextA = curr.a - toGive;
			int nextC = cMax - (nextA + nextB);
			if(!check[nextA][nextB][nextC]){
				check[nextA][nextB][nextC] = true;
				dfs(new State(nextA, nextB, nextC), check);
			}
		}

		// a ->c
		if(curr.a > 0){
			int toGive = Math.min(curr.a, cMax - curr.c);
			int nextC = curr.c + toGive;
			int nextA = curr.a - toGive;
			int nextB = cMax - (nextA + nextC);
			if(!check[nextA][nextB][nextC]){
				check[nextA][nextB][nextC] = true;
				dfs(new State(nextA, nextB, nextC), check);
			}
		}

	}

	public static boolean[][] deepCopy(boolean[][] input) {
		boolean[][] res = new boolean[aMax+1][cMax+1];
		for (int row = 0; row < input.length; row++) {
			for (int col = 0; col < input[row].length; col++) {
				res[row][col] = input[row][col];
			}
		}
		return res;
	}

}

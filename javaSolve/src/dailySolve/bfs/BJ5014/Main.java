package dailySolve.bfs.BJ5014;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int F, S, G, U, D;
	static boolean[] check;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./resources/daily/bfs/BJ5014/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		F = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		U = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		check = new boolean[F+1];
		int bfs = bfs(S);
		System.out.println(bfs != -1 ?  bfs : "use the stairs" );

	}

	private static int bfs(int start) {
		Queue<Integer> q = new ArrayDeque<>();
		q.add(start);
		check[start] = true;
		int count = 0;
		while (!q.isEmpty()) {
			int size = q.size();
			while (size -- > 0){
				int cur = q.poll();
				if(cur == G) return count;
				if (isIn(cur + U) && !check[cur + U]) {
					check[cur + U] = true;
					q.add(cur + U);
				}
				if(isIn(cur - D) && !check[cur - D]){
					check[cur - D] = true;
					q.add(cur - D);
				}
			}
			count++;

		}
		return -1;

	}

	private static boolean isIn(int num) {
		return num > 0 && num <= F;
	}
}

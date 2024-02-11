package gold.BJ2660;


import java.io.*;
import java.util.*;
// 양방향 노드
public class TEST {

	static int N;
	static int[][] dist;
	static Vector<Integer>[] v;
	static final int INF = Integer.MAX_VALUE / 2;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());

		v = new Vector[N + 1];
		for (int i = 0; i <= N; i++) {
			v[i] = new Vector<>();
		}
		dist = new int[N + 1][N + 1];
		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= N; j++) {
				if (i == j)
					dist[i][j] = 0;
				else
					dist[i][j] = INF;
			}
		}

		while (true) {
			String[] sarr = br.readLine().split(" ");
			int a = Integer.parseInt(sarr[0]);
			int b = Integer.parseInt(sarr[1]);

			if (a == -1 && b == -1)
				break;

			v[a].add(b);
			v[b].add(a);
			dist[a][b] = dist[b][a] = 1;

		}

		floydWarshall();
		printArr(dist);
		int res = Integer.MAX_VALUE;
		int[] score = new int[N + 1];

		for (int n = 1; n <= N; n++) {
			int max = 0;
			for (int i = 1; i <= N; i++) {
				if (dist[n][i] < INF)
					max = Math.max(max, dist[n][i]);
			}
			score[n] = max;
			res = Math.min(res, score[n]);
		}
		System.out.println(Arrays.toString(score));
		List<Integer> list = new ArrayList<>();
		for (int n = 1; n <= N; n++) {
			if (score[n] == res)
				list.add(n);
		}

		bw.write(res + " " + list.size() + "\n");
		for (int n : list)
			bw.write(n + " ");
		bw.flush();

	}

	public static void floydWarshall() {

		for (int t = 1; t <= N; t++) {
			for (int s = 1; s <= N; s++) {
				for (int e = 1; e <= N; e++) {
					dist[s][e] = Math.min(dist[s][e], dist[s][t] + dist[t][e]);
				}
			}
		}
	}

	private static void printArr(int[][] input) {
		for (int[] row : input) {
			System.out.println();
			for (int val : row) {
				if (val == INF) {
					System.out.print("n" + " ");
				} else
					System.out.print(val + " ");
			}
		}
		System.out.println();
	}
}
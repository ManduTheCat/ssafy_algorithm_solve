package silver.BJ15666NM12;

import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int M;
	static int[] input;
	static int[] dfsRes;
	static boolean[] check;
	static Set<String> resSet;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		check = new boolean[N];
		st = new StringTokenizer(br.readLine());
		input = new int[N];
		dfsRes = new int[M];
		resSet = new LinkedHashSet<>();
		for (int n = 0; n < N; n++) {
			input[n] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(input);
		dfs(0, 0);
		StringBuilder sb = new StringBuilder();
		for (String chunk : resSet) {
			sb.append(chunk);
			sb.append("\n");
		}
		System.out.println(sb);

	}

	static public void dfs(int depth, int start) {
		if (depth == M) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < M; i++) {
				if (i == M - 1) {
					sb.append(dfsRes[i]);
				} else {
					sb.append(dfsRes[i]);
					sb.append(" ");
				}
			}
			resSet.add(sb.toString());

			return;
		}
		for (int i = start; i < N; i++) {
			dfsRes[depth] = input[i];
			dfs(depth + 1, i);

		}
	}
}

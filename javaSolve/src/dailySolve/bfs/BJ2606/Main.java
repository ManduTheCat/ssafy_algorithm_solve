package dailySolve.bfs.BJ2606;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	// 1 번 컴퓨터 점염되면?
	static int N;
	static int M;
	static List<Integer>[] adjList;
	static boolean[] check;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./resources/daily/bfs/BJ2606/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		adjList = new List[N + 1];
		check = new boolean[N + 1];
		for (int n = 0; n <= N; n++) {
			adjList[n] = new ArrayList<>();
		}

		for (int m = 0; m < M; m++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			adjList[start].add(end);
			adjList[end].add(start);
		}
		System.out.println(bfs(1));
	}

	public static int bfs(int start) {
		check[start] = true;
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(start);
		int count = 0;
		while (!q.isEmpty()) {
			int curr = q.poll();
			List<Integer> nextList = adjList[curr];
			for (int nextNode : nextList) {
				if (!check[nextNode]) {
					check[nextNode] = true;
					q.add(nextNode);
					count++;
				}
			}
		}
		return count;
	}
}

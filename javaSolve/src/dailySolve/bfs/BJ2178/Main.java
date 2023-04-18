package dailySolve.bfs.BJ2178;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

class Node {
	int row;
	int col;
	int depth;

	public Node(int row, int col, int depth) {
		this.row = row;
		this.col = col;
		this.depth = depth;
	}
}

public class Main {
	static Integer N;
	static Integer M;
	static int[][] map;
	static boolean[][] check;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("resources/daily/bfs/BJ2178/input4.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		check = new boolean[N][M];
		for (int n = 0; n < N; n++) {
			String row = br.readLine();
			for (int m = 0; m < M; m++) {
				map[n][m] = row.charAt(m) - '0';
			}
		}

		Node first = new Node(0, 0, 1);
		Queue<Node> q = new ArrayDeque<>();
		q.offer(first);
		int[][] delta = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
		while (!q.isEmpty()) {

			Node curr = q.poll();
			if (curr.row == N - 1 && curr.col == M - 1) {
				System.out.println(curr.depth);
				break;
			}
			for (int[] d : delta) {
				int dr = d[0];
				int dc = d[1];
				Node next = new Node(curr.row + dr, curr.col + dc, curr.depth + 1);
				if (isIn(next) && map[next.row][next.col] == 1 && !check[next.row][next.col]) {
					check[next.row][next.col] = true;
					q.add(next);
				}
			}

		}

		// printMap(map);

	}

	private static boolean isIn(Node next) {
		return next.row < N && next.row >= 0 && next.col < M && next.col >= 0;
	}

	public static void printMap(int[][] map) {
		for (int[] row : map) {
			System.out.println(Arrays.toString(row));
		}
	}
}

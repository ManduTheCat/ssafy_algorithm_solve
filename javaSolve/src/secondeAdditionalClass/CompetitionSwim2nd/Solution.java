package secondeAdditionalClass.CompetitionSwim2nd;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Node {
	int r;
	int c;
	int time;

	public Node(int r, int c, int time) {
		super();
		this.r = r;
		this.c = c;
		this.time = time;
	}

	@Override
	public String toString() {
		return "Node [r=" + r + ", c=" + c + ", time=" + time + "]";
	}
	
}

public class Solution {
	static int Tc;
	static int N;
	static int[][] map;
	static Node start;
	static Node target;
	static boolean[][] check;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("resources/secondClass/sweaSwim/input1.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Tc = Integer.parseInt(br.readLine());
		// 2 는 소용돌이
		for (int tc = 0; tc < Tc; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			check = new boolean[N][N];
			for (int row = 0; row < N; row++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int col = 0; col < N; col++) {
					map[row][col] = Integer.parseInt(st.nextToken());
				}
			}
			StringTokenizer st = new StringTokenizer(br.readLine());
			int startR = Integer.parseInt(st.nextToken());
			int startC = Integer.parseInt(st.nextToken());
			start = new Node(startR, startC, 0);
			st = new StringTokenizer(br.readLine());
			int endR = Integer.parseInt(st.nextToken());
			int endC = Integer.parseInt(st.nextToken());
			target = new Node(endR, endC, 0);
			bfs(start);

		}

	}

	private static void bfs(Node startNode) {
		// 탐색하다다 3의 배수인 지점에서 이동이 가능하기에
		// 계속 해서 큐에 넣다가 3에배수일때 통과하게 한다
		int[][] arrd = { { 0, -1 }, { -1, 0 }, { 0, 1 }, { 1, 0 } };
		Queue<Node> que = new LinkedList<>();
		que.offer(startNode);
		check[startNode.r][startNode.c] = true;
		while (!que.isEmpty()) {
			Node curr = que.poll();
			//System.out.println(curr);
			if(curr.r == target.r && curr.c == target.c) {
				//System.out.println("touch");
				System.out.println(curr.time);
				return;
			}
			for (int d = 0; d < 4; d++) {
				int newR = curr.r + arrd[d][0];
				int newC = curr.c + arrd[d][1];
				if (isOut(newR, newC) && !check[newR][newC] && map[newR][newC] != 1) {
					if(map[newR][newC] == 2) {
						if((curr.time-2) % 3 == 0) {
							que.offer(new Node(newR, newC, curr.time+ 1));
							check[newR][newC] = true;
						}
						else {
							que.offer(new Node(curr.r, curr.c, curr.time+ 1));
						}
					}
					else if(map[newR][newC] == 0) {
						que.offer(new Node(newR, newC, curr.time+ 1));
						check[newR][newC] = true;
						
					}

				}
			}

		}

	}

	private static boolean isOut(int newR, int newC) {
		return newR < N && newR >= 0 && newC < N && newC >= 0;
	}

}

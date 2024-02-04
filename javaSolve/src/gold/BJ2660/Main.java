package gold.BJ2660;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements Comparable<Node>{
	int idx;
	int count;
	Node(int idx, int count){
		this.idx = idx;
		this.count = count;
	}
	@Override
	public int compareTo(Node o){
		if(o.count == this.count){
			return this.idx - o.idx;
		}else return o.count - this.count;
	}

	@Override
	public String toString() {
		return "Node{" +
			"idx=" + idx +
			", count=" + count +
			'}';
	}
}
public class Main {
	static int N;
	final static int MAX = 100_000_000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int[][] adjArr = new int[N + 1][N + 1];
		for (int i = 0; i <= N; i++) {
			Arrays.fill(adjArr[i], MAX);
		}
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (i == j) {
					adjArr[i][j] = 0;
				}
			}
		}
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			if (start == -1 && end == -1) {
				break;
			} else
				adjArr[start][end] = 1;
		}
		for (int k = 1; k <= N; k++) {
			for (int start = 1; start <= N; start++) {
				for (int end = 1; end <= N; end++) {
					adjArr[start][end] = Math.min(adjArr[start][end], adjArr[start][k] + adjArr[k][end]);

				}
			}
		}
		printArr(adjArr);
		PriorityQueue<Node> pq = new PriorityQueue<>();
		int [] maxArr = new int[N + 1];
		for (int row = 1; row <= N; row++) {
			int max = maxArr[row];
			for (int col = 1; col <= N; col++) {
				if(adjArr[row][col]!=MAX && adjArr[row][col] !=0){
					max = Math.max(adjArr[row][col], max);
				}
			}
			maxArr[row] = max;
		}
		for (int col = 1; col <= N; col++) {
			int max = maxArr[col];
			for (int row = 1; row <= N; row++) {
				if(adjArr[col][row]!=MAX && adjArr[col][row] !=0){
					max = Math.max(adjArr[col][row], max);
				}
			}
			if(maxArr[col] < max){
				maxArr[col] = max;
			}
		}
		for(int n = 1; n <= N; n++){
			pq.offer(new Node(n, maxArr[n]));
		}
		System.out.println(Arrays.toString(maxArr));
		StringBuilder sb = new StringBuilder();
		sb.append(pq.peek().idx + " " +  pq.peek().count);
		System.out.println(sb);

	}

	private static void printArr(int[][] input) {
		for (int[] row : input) {
			System.out.println();
			for (int val : row) {
				if (val == MAX) {
					System.out.print("n" + " ");
				} else
					System.out.print(val + " ");
			}
		}
		System.out.println();
	}
}

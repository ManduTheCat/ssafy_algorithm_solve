package dailySolve.MST.mstBJ1197;

import java.io.*;
import java.util.*;

class Edge implements Comparable<Edge> {
	int start;
	int cost;
	int end;

	Edge(int start, int cost, int end) {
		this.start = start;
		this.cost = cost;
		this.end = end;
	}

	@Override
	public int compareTo(Edge o) {
		return this.cost - o.cost;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		sb.append(this.start);
		sb.append(",");
		sb.append(this.cost);
		sb.append(",");
		sb.append(this.end);
		sb.append("]");
		return sb.toString();
	}

}

public class Main {
	static int V;
	static int E;
	static int[] parent;
	static List<Edge> edges;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./resources/daily/mst/BJ1197/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		parent = new int[V + 1];
		for (int i = 1; i <= V; i++) {
			parent[i] = i;
		}
		edges = new ArrayList<>();
		for (int e = 0; e < E; e++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			edges.add(new Edge(start, cost, end));
		}
		Collections.sort(edges);
		// System.out.println(edges);
		int cost = 0;
		for (Edge currEdge : edges) {
			if (union(currEdge.start, currEdge.end)) {
				cost += currEdge.cost;
			}
		}
		System.out.println(cost);

	}

	public static int find(int a) {
		if (parent[a] == a)
			return a;
		return parent[a] = find(parent[a]);
	}

	public static boolean union(int x, int y) {
		x = find(x);
		y = find(y);
		if (x == y) {
			return false;
		}
		if (x > y) { // 작은값이 부모가되게 설정
			parent[x] = y;
		} else
			parent[y] = x;
		return true;
	}
}

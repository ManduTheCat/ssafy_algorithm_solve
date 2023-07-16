package dailySolve.kruskal;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge> {
	int start;
	int end;
	float dist;

	public Edge(int start, int end, float dist) {
		this.start = start;
		this.end = end;
		this.dist = dist;
	}

	@Override
	public int compareTo(Edge o) {
		return (int)((this.dist * 100) - (o.dist * 100));
	}

	@Override
	public String toString() {
		return "Edge{" +
			"start=" + start +
			", end=" + end +
			", dist=" + dist +
			'}';
	}
}

public class Main {
	static int N;
	static PriorityQueue<Edge> edges;
	static int[] parents;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("resources/daily/kruskal/BJ4386/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		edges = new PriorityQueue<>();
		float[][] cordiList = new float[N][2];
		parents = new int[N];
		for (int n = 0; n < N; n++) {
			// 모든 별간 거리를 구해야한다.
			StringTokenizer st = new StringTokenizer(br.readLine());
			float[] tmp = new float[2];
			tmp[0] = Float.parseFloat(st.nextToken());
			tmp[1] = Float.parseFloat(st.nextToken());
			cordiList[n] = tmp;
			parents[n] = n;
		}
		// 유니온 할때 엣지를 둘다 검사하자.
		for (int stand = 0; stand < N; stand++) {
			float[] st = cordiList[stand];
			for (int target = stand + 1; target < N; target++) {
				float[] t = cordiList[target];
				double x = Math.pow(st[0]-t[0],2);
				double y = Math.pow(st[1]-t[1],2);
				float dist = (float)(Math.sqrt(x + y));
				edges.offer(new Edge(stand, target, dist));
			}

		}
		float res = 0f;
		while (!edges.isEmpty()) {
			Edge curr = edges.poll();
			if(union(curr.start, curr.end)){
				res += curr.dist;
			}
		}
		System.out.println(res);
	}

	public static int find(int target) {
		if(parents[target] == target){
			return target;
		}
		return find(parents[target]);
	}
	public static boolean union(int x, int y){
		x = find(x);
		y = find(y);
		if(x == y ) return false;
		if(x <= y) parents[y] = x;// 큰게 부모
		else parents[x] = y;
		return true;
	}
}

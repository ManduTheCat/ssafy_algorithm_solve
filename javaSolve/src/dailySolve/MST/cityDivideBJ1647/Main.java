package dailySolve.MST.cityDivideBJ1647;

import java.util.*;
import java.io.*;


class Edge implements Comparable<Edge>{
	int start, cost, end;
	Edge(int start, int cost, int end){
		this.start = start;
		this.cost = cost;
		this.end = end;
	}
	@Override
	public String toString(){
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
	@Override
	public int compareTo(Edge o){
		return this.cost - o.cost;
	}
}
public class Main {
	static int N;
	static int M;
	static int [] parents;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./resources/daily/mst/BJ1647/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		parents = new int[N + 1];
		for(int i = 1; i  <= N; i++){
			parents[i] = i;
		}
		List<Edge> edges = new ArrayList<>();
		for(int m = 0; m < M; m++){
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			edges.add(new Edge(start, cost, end));
		}
		Collections.sort(edges);
		int maxCost = Integer.MIN_VALUE;
		int res = 0;
		for(Edge edge :edges){
			if(union(edge.start, edge.end)){
				maxCost = Math.max(maxCost, edge.cost); // mst 중 최대값을 한번 뺴주면 두그룹이 된다.
				res += edge.cost;
			}
		}

		System.out.println(res - maxCost);

	}
	public static int find(int a){
		if(parents[a] == a ) return a;
		return parents[a] = find(parents[a]);
	}
	public static boolean union(int a, int b){
		a = find(a);
		b = find(b);
		if(a == b) return false; // 안되면 false
		if(a > b) parents[a] = b; // 작은게 부모가 되게 일관성 부여
		else parents[b] = a;
		return true;
	}
}

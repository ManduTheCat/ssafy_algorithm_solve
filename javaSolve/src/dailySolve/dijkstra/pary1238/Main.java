package dailySolve.dijkstra.pary1238;

import java.io.*;
import java.util.*;


class Cordi implements Comparable<Cordi>{
	int cur;
	int cost;
	Cordi(int cur, int cost){
		this.cur = cur;
		this.cost = cost;
	}
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		sb.append(this.cur);
		sb.append(",");
		sb.append(this.cost);
		sb.append("]");
		return sb.toString();
	}

	@Override
	public int compareTo(Cordi o){
		return this.cost - o.cost;
	}
}
public class Main {
	static int N;
	static int M;
	static int X;
	static List<Integer>[] adjList;
	static int [][] costArr;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./resources/daily/dijkstra/BJ1238/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		adjList = new List[N + 1];
		costArr = new int[N + 1][N + 1];
		int[] res = new int[N+1];
		for(int node = 1; node <=N; node++){
			adjList[node] = new ArrayList<>();
		}

		// node 1 부터
		for(int m = 1; m <= M; m++){
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			costArr[start][end]= cost;
			adjList[start].add(end);
		}
		for(int start = 1; start <= N; start++){
			int [] dp = new int[N + 1];
			Arrays.fill(dp, Integer.MAX_VALUE);
			dp[start] = 0;
			dijkstra(start,  dp);
			// System.out.println(Arrays.toString(dp));
			res[start] = dp[X];

		}
		int [] dp = new int[N + 1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[X] = 0;
		dijkstra(X,  dp);
		int maxCost = Integer.MIN_VALUE;
		for(int i = 1; i <= N; i++){
			maxCost = Math.max(maxCost, dp[i] + res[i]);
		}
		System.out.println(maxCost);

	}
	public static void dijkstra(int start, int[] dp){
		PriorityQueue<Cordi> pq = new PriorityQueue<>();
		pq.add(new Cordi(start, 0));
		while (!pq.isEmpty()){
			Cordi curr = pq.poll();
			// if(curr.cur == end){
			// 	break; // 이짓을 하면 안되는이유는 도착하는 여려경우를 봐야하기때문에
			// }
			if(dp[curr.cur] < curr.cost){
				continue; // 이미 찾은 최소라면( 처리된거라면 무시) 이걸 안하면 여러번 반복한다.
			}
			for(int next : adjList[curr.cur]){
				if( dp[next] > dp[curr.cur] + costArr[curr.cur][next]){
					dp[next] = dp[curr.cur] + costArr[curr.cur][next];
					pq.add(new Cordi(next, dp[next] ));
				}
			}
		}

	}
}

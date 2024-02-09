package gold.BJ5972PackageDelivery;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Node {
	private int idx;
	private int cost;

	public Node(int idx, int cost) {
		this.idx = idx;
		this.cost = cost;
	}

	public int getIdx() {
		return idx;
	}

	public int getCost() {
		return cost;
	}

	@Override
	public String toString() {
		return "Node{" +
			"idx=" + idx +
			", cost=" + cost +
			'}';
	}
}

public class Main {
	private static int N;
	private static int M;
	private static int[] dir;
	private static List<Node>[] adjList; // 리스트 가 느릴지 배열이 느릴지 실험 필요
	// 빠른건 배열 하지만 타입이 정해지는 타이밍이 배열과 재네릭을 다르므로 제네릭으로 쓰는게 좋을듯?
	// 143588kb	780ms 배열
	// 148260kb	836ms 이중 제네릭
	private final static int INF = 100_000_000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		dir = new int[N + 1];
		adjList = new ArrayList[N+1];
		Arrays.fill(dir, INF);
		for(int i = 1; i <=N; i++){
			adjList[i] = new ArrayList<>();
		}
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			adjList[start].add(new Node(end, cost));
			adjList[end].add(new Node(start, cost));
		}
		dijkstra(1 );
		System.out.println(dir[N]);

	}
	private static void dijkstra(int start){
		Queue<Node> q = new ArrayDeque<>();// pq 로 바꿔보자 최소 비용순서로 뜨게
		dir[start] = 0;
		q.add(new Node(start, 0));
		while (!q.isEmpty()){
			Node curr = q.poll();
			List<Node> nextNodes = adjList[curr.getIdx()];
			//System.out.println(nextNodes);
			for(Node next :nextNodes){
				if(dir[next.getIdx()] > dir[curr.getIdx()] + next.getCost()){
					dir[next.getIdx()] = dir[curr.getIdx()] + next.getCost();
					q.offer(next);
				}
			}

		}
	}
}

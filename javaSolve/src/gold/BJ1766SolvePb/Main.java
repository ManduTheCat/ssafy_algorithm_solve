package gold.BJ1766SolvePb;


import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int M;
	static int[] inDegreeTable; // 진입차수 테이블
	static List<Integer>[]  adjList;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		adjList = new List[N+1];
		inDegreeTable = new int[N+1];
		inDegreeTable[0] = -1;
		StringBuilder sb = new StringBuilder();
		for(int node = 0; node <= N; node ++){
			adjList[node] = new ArrayList<Integer>();
		}
		for(int edge = 0; edge < M; edge++){
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			adjList[start].add(end);
			// 진입차수 갱신
			inDegreeTable[end]++;
		}
		Queue<Integer> q = new ArrayDeque<>();
		for(int node = 1; node <= N; node++ ){ // q 초기화
			if(inDegreeTable[node] == 0){
				q.add(node);
			}
		}
		while (!q.isEmpty()){
			int currNode = q.poll();
			// 현재 노드에 연결된 엣지 차감
			sb.append(currNode);
			sb.append(" ");
			List<Integer> nextList = adjList[currNode];
			for(int nextNode : nextList){
				inDegreeTable[nextNode]--;
				if(inDegreeTable[nextNode] == 0){ // 차감된 엣지가 0이라면 q 에 넣어라
					q.add(nextNode);
				}
			}

		}
		System.out.println(sb);
	}
}


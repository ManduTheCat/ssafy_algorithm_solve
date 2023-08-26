package dailySolve.DFS.tripBJ1979;

import java.io.*;
import java.util.*;

//21028 kb	272 ms

public class Main {
	static int N;
	static int M;
	static List<Integer>[] adjList;
	static boolean isAble;
	static int [] plan;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./resources/daily/dfs/BJ1979/input.txt"));
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		adjList = new List[N];
		plan = new int[M];

		for(int n = 0; n < N; n++){
			adjList[n] = new ArrayList<>();
		}
		for(int start = 0; start < N; start++){
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int end = 0; end < N; end++){
				int value = Integer.parseInt(st.nextToken());
				if(value == 1){
					adjList[start].add(end);
				}
			}
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int m = 0; m < M; m++){
			plan[m] = Integer.parseInt(st.nextToken()) - 1;
		}
		// System.out.println(Arrays.toString(adjList));
		// 계획에 대한 값의 범위가 주어지지 않았다 이를 주의해서 짜보자
		if(M >= 2){
			for(int startIdx = 0; startIdx < M -1; startIdx++){
				boolean [] check = new boolean[N];
				isAble = false;
				int start = plan[startIdx];
				int end = plan[startIdx +1];
				// System.out.println(start + " " + end );
				check[start] = true; // 의문 왜 시작점을 체크하고 진입하면 인덱스 에러가 날까
				dfs(start, end, check);
				if(!isAble){
					break;
				}
			}
		}else {
			// 한개라 한곳만 방문하는거다
			isAble = true;
		}

		if(isAble){
			System.out.println("YES");
		}else {
			System.out.println("NO");
		}


	}
	private static void dfs (int curr, int end, boolean[] check){
		if(curr == end){
			isAble = true;
			return;
		}
		for(int nextNode :adjList[curr]){
			if(!check[nextNode]){
				check[nextNode] = true;
				dfs(nextNode, end, check);
				// check[nextNode] = false;
			}
		}
	}
}

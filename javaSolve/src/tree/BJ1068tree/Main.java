package tree.BJ1068tree;

import java.util.*;
import java.io.*;

/**
 * bfs로 지워지는 노드들 체크 -> 리프노드 탐색
 * 주의 할점 루트노드는 꼭 0 번째 노드가 아니다. 루트를 발견하면 갱신해야한다.
 * 트리니까 루트는 무조건 있다.
 */
public class Main {
	static int N;
	static int target;
	static List<Integer>[] adJList;
	static boolean[] deleteCheck;
	static int root = 0;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./resources/daily/tree/BJ1068/input3.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		adJList = new List[N + 1];
		deleteCheck  = new boolean[N ];

		for (int i = 0; i <= N; i++) {
			adJList[i] = new ArrayList<>();
		}
		for (int node = 0; node < N; node++) {
			int p = Integer.parseInt(st.nextToken());
			if (p != -1) {
				adJList[p].add(node);
			}else {
				root = node;
			}
		}

		target = Integer.parseInt(br.readLine());
		deleteCheck[target] = true;
		delete(target);
		System.out.println(findLeaf());


	}
	public static int findLeaf(){
		int count = 0;

		// bfs 돌다가 만약에 다음 노드가 모두 불가능하면 카운트 업
		// 아니라면 false 인것들 중에서 큐에 넣는 형태로 bfs 진행
		Queue<Integer> q  = new ArrayDeque<>();
		q.add(root);
		while (!q.isEmpty()){
			int curr = q.poll();
			List<Integer> nexts = adJList[curr];
			boolean isLeaf = true;
			for(int nextNode : nexts){
				if(!deleteCheck[nextNode]){
					// 지워지지 않았다. 하나라도 자식이 있으면 리프가 아니다.
					isLeaf = false;
					q.add(nextNode);
				}
			}
			if(isLeaf) {
				count++;
			}
		}
		if(target == root){
			return 0;
		}
		return count;
	}

	public static void delete(int target) {
		Queue<Integer> q = new ArrayDeque<>();
		q.add(target);
		while (!q.isEmpty()) {
			int curr = q.poll();
			List<Integer> nexts = adJList[curr];
			for(int nextNode :nexts){
				deleteCheck[nextNode] = true;
				// System.out.println(nextNode);
				q.add(nextNode);
			}
		}

	}

}

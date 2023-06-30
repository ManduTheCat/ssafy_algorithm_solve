package programers.divideWires;

import java.util.*;

class Solution {
	HashSet<Integer>[] adjList;
	boolean[] check;
	static int N;

	public int solution(int n, int[][] wires) {
		adjList = new HashSet[n + 1];
		check = new boolean[n + 1];
		N = n;
		for (int idx = 0; idx <= n; idx++) {
			adjList[idx] = new HashSet<>();
		}
		for (int[] edge : wires) {
			int start = edge[0];
			int dest = edge[1];
			HashSet<Integer> adjustSet = adjList[start];
			adjustSet.add(dest);
			HashSet<Integer> reversSet = adjList[dest];
			reversSet.add(start);
		}
		System.out.println(Arrays.toString(adjList));

		// System.out.println(bfs(4, adjList));
		int answer = Integer.MAX_VALUE;

		for (int[] deleteTarget : wires) { //N^2
			check = new boolean[N + 1];
			List<Integer> groupList = new ArrayList<>();
			HashSet<Integer>[] cutAjdList = cut(deleteTarget);
			for (int node = 1; node <= N; node++){
				if(!check[node]){
					int groupCount = bfs(node, cutAjdList);
					groupList.add(groupCount);
				}
			}
			System.out.println(groupList);
			answer = Math.min(answer,Math.abs((groupList.get(0) - groupList.get(1))));
			// 쪼개고 다돌려본다.. N(N + (N -1))
		}
		System.out.println(answer);
		return answer;
	}

	public HashSet<Integer>[] cut(int[] target) {
		HashSet[] res = new HashSet[N + 1];
		int start = target[0];
		int dest = target[1];
		// 딥카피후 지우자
		for (int n = 0; n <= N; n++) {
			res[n] = (HashSet<Integer>)adjList[n].clone();
		}
		// 제거
		if (res[start] != null && res[start].contains(dest)) {
			res[start].remove(dest);
		}
		if (res[dest] != null && res[dest].contains(start)) {
			res[dest].remove(start);
		}

		return res;
	}

	public int bfs(int startNode, HashSet<Integer>[] deletedAdjSet) {
		Queue<Integer> q = new LinkedList<>();
		q.add(startNode);
		int count = 1;
		check[startNode] = true;
		while (!q.isEmpty()) {
			int curNode = q.poll();
			if(deletedAdjSet[curNode] != null){

				for (Integer nextNode : deletedAdjSet[curNode]) {
					if (!check[nextNode]) {
						check[nextNode] = true;
						count++;
						q.add(nextNode);
					}
				}
			}

		}
		return count;
	}
}

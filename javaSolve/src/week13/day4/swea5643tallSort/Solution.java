package week13.day4.swea5643tallSort;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    // 모든 정점에 대해
    // 앞으로 완탐
    // 뒤로 완탐
    // 둘다 체크가 다 되있어야 내 키순서를 알수 있따
    // 몇명인지 출력
    static int Tc, N, M;
    static ArrayList<Integer>[] adjListForward;
    static ArrayList<Integer>[] adjListBackward;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/week13/day4/swea5643/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Tc = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < Tc; tc++) {
            N = Integer.parseInt(br.readLine());
            M = Integer.parseInt(br.readLine());
            adjListForward = new ArrayList[N + 1];
            adjListBackward = new ArrayList[N + 1];
            for (int vertex = 1; vertex <= N; vertex++) {
                // 앞으로 가는 adjList
                adjListForward[vertex] = new ArrayList<>();
                // 뒤로 가는 adjList
                adjListBackward[vertex] = new ArrayList<>();
            }
            for (int edge = 0; edge < M; edge++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                adjListForward[from].add(to);
                adjListBackward[to].add(from);
            }

            int count = 0;
            for (int curNode = 1; curNode <=N ; curNode++) {
                boolean[] forwardVisit = isConnect(adjListForward, curNode);
                boolean[] backwardVisit = isConnect(adjListBackward, curNode);
                if (isKnow(forwardVisit, backwardVisit, curNode)) {
                    count++;
                }
            }
            System.out.printf("#%d %d\n",tc+1, count);
        }

    }

    private static boolean isKnow(boolean[] forwardVisit, boolean[] backwardVisit, int currNode) {
        for (int i = 1; i <= N; i++) {
            if (!(forwardVisit[i] || backwardVisit[i]) && (i != currNode)){
                return false;
            }
        }
        return true;
    }
    // 탐색을 수행하면서 연결된 친구들은 불리언 배열 에 담아서 준다 0번 인덱스는 안쓴다.
    private static boolean[] isConnect(ArrayList<Integer>[] adjListForwardInput, int startNode) {
        boolean[] visit = new boolean[N + 1];
        Queue<Integer> q = new LinkedList<>();
        q.add(startNode);
        while (!q.isEmpty()) {
            int currNode = q.poll();
            ArrayList<Integer> nextNodes = adjListForwardInput[currNode];
            for (int nextNode : nextNodes) {
                if (!visit[nextNode]) {
                    visit[nextNode] = true;
                    q.offer(nextNode);
                }
            }
        }
        return visit;
    }
}

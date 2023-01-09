package dailySolve.dijkstra.minCost1916;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


class Node implements Comparable<Node> {

    public int index;
    public int weight;

    public Node( int index, int weight) {

        this.index = index;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "dest=" + index +
                ", weight=" + weight +
                '}';
    }
}

public class Main {
    private static int N;
    private static int M;
    private static int[] dp;
    private static boolean[] check;
    private static ArrayList<Node>[] adjList;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/daily/dijkstra/1916/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        adjList = new ArrayList[N];
        dp = new int[N];
        check = new boolean[N];
        for (int n = 0; n < N; n++) {
            adjList[n] = new ArrayList<Node>();
            dp[n] = Integer.MAX_VALUE;
        }
        for (int m = 0; m < M; m++) {
            // 마이너스 1은 별로
            // 인덱스라는 명칭이 올바른거 같다
            // 명치 디스트로
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            int weight = Integer.parseInt(st.nextToken());
            adjList[start].add(new Node(end, weight));
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken()) - 1;
        int target = Integer.parseInt(st.nextToken()) - 1;
        dijkstra(start);
        dp[start] = 0;

        System.out.println(dp[target]);
    }

    private static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node( start, 0));

        while (!pq.isEmpty()) {//E
            Node curr = pq.poll();
//            System.out.println(curr);


            if (dp[curr.index] < curr.weight) continue;
            for (Node nextNode : adjList[curr.index]) {

                // 다음 노드는 방문하지 않고 최소야한다
                if (dp[nextNode.index] > nextNode.weight + dp[curr.index]) {//E //v
                    // 다음 이동 할 정점까지의 비용을 갱신한다.
                    dp[nextNode.index] = dp[curr.index] + nextNode.weight;
                    pq.add(new Node( nextNode.index, dp[nextNode.index]));
                }
            }
        }

    }
}

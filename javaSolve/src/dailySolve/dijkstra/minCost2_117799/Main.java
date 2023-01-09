package dailySolve.dijkstra.minCost2_117799;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.util.*;


class Edge {
    public int start;


    public Edge(int start) {
        this.start = start;

    }

    @Override
    public String toString() {
        return "Edge{" +
                "start=" + start +
                '}';
    }
}
class Node implements Comparable<Node> {
    public int index;
    public int weight;

    public Node(int index, int weight) {
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
                "index=" + index +
                ", weight=" + weight +
                '}';
    }
}

public class Main {
    private static ArrayList<Node>[] adjList;
    private static int[] dist;
    private static int M;
    private static int N;
    private static Edge[] route;
    private static int count;
    private static Stack<Integer> out;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/daily/dijkstra/117799/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        adjList = new ArrayList[N + 1];
        dist = new int[N + 1];
        route = new Edge[N+1];
        for (int n = 1; n <= N; n++) {
            adjList[n] = new ArrayList<>();
            dist[n] = Integer.MAX_VALUE;
        }
        for (int m = 0; m < M; m++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            adjList[start].add(new Node(end, weight));
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int target = Integer.parseInt(st.nextToken());
        dist[start] = 0;
        dijkstra(start);
        System.out.println(dist[target]);
        out = new Stack<>();
        count = 0;
        out.add(target);
        findRoute(target);
        System.out.println(count);
        while (!out.isEmpty()){
            System.out.printf("%d ", out.pop());
        }
    }

    private static void findRoute(int target) {
        count++;
        if(route[target] == null) return;
//        System.out.println(route[target].start);
        out.add(route[target].start);
        findRoute(route[target].start);
    }

    private static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            if (curr.weight > dist[curr.index]) continue;
            for (Node nextNode : adjList[curr.index]) {
                if (dist[nextNode.index] > dist[curr.index] + nextNode.weight) {
                    dist[nextNode.index] = dist[curr.index] + nextNode.weight;
                    pq.add(new Node(nextNode.index, dist[nextNode.index]));
                    route[nextNode.index] = new Edge(curr.index);
                }
            }
        }

    }
}

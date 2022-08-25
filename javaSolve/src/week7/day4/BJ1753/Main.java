package week7.day4.BJ1753;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node {
    int vertex, weight;
    Node next;

    public Node(int vertex, int weight, Node next) {
        this.vertex = vertex;
        this.weight = weight;
        this.next = next;
    }

    @Override
    public String toString() {
        return "Node{" +
                "vertex=" + vertex +
                ", weight=" + weight +
                ", next=" + next +
                '}';
    }
}
class Vertex {
    int no, weight;

    public Vertex(int no, int weight) {
        this.no = no;
        this.weight = weight;
    }
}
public class Main {
    static int N;//1부터 시작 20,0000
    static int E;//30,000
    static int start;
    static Node [] adjList;
    static int[] D;
    static boolean [] visited;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/week7/day4/bj1753/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(br.readLine());
        adjList = new Node[N +1];
        D = new int[N+1];
        visited = new boolean[N+1];
        for (int i = 0; i <E ; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            adjList[from] = new Node(to, weight, adjList[from]);
        }
        for (int i = 1; i <N + 1 ; i++) {

        }

    }
    public static void dijkstra(int end){
        Arrays.fill(D, Integer.MAX_VALUE);
        D[start] = 0;
        PriorityQueue <Vertex> pq = new PriorityQueue<>(new Comparator<Vertex>() {
            @Override
            public int compare(Vertex o1, Vertex o2) {
                return o1.weight - o2.weight;
            }
        });

        pq.offer(new Vertex(0, adjList[start].weight));
        int min, minVertex = 0;
        for (int i = 1; i <N+1 ; i++) {
            //d에서 최소값찾기
            min = Integer.MAX_VALUE;
            for (int j = 0; j < N; j++) {
                if(!visited[j] && min > D[j]){
                    min = D[j];
                    minVertex = j;
                }
            }
            visited[minVertex] = true;
            if(minVertex == end) break;
        }

    }
}

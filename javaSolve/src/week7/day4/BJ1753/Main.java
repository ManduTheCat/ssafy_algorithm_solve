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
public class Main {
    static int N;//1부터 시작 20,0000
    static int E;//30,000
    static int start;
    static Node [] adjList;
    static int[] D;
    static boolean [] visited;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/week7/day4/bj1753/input3.txt"));
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
        dijkstra();
        // 다익스트라가 돌면서 1-2까지 최소 가중치 1-3까지 가중치 1-4 까지 가중치 1-end 까지 가중치를
        //D 에 넣는다
        for (int i = 1; i <= N; i++) {
            if(D[i] == Integer.MAX_VALUE){
                sb.append("INF").append("\n");
            }
            else sb.append(D[i]).append("\n");

        }
        System.out.println(sb);


    }
    public static void dijkstra(){
        Arrays.fill(D, Integer.MAX_VALUE);
        D[start] = 0;
        int min, minVertex = 0;
        for (int i = 0; i < N; i++){
            //d에서 최소값찾기
            min = Integer.MAX_VALUE;
            for (int j = 1; j <= N; j++) {
                if(!visited[j] && min > D[j]){
                    min = D[j];
                    minVertex = j;
                }
            }
            visited[minVertex] = true;
            //prim 알고리즘 참조 현재 minVertex 의 인접리스트를 돈다
            for(Node temp = adjList[minVertex]; temp!=null; temp = temp.next){
                if(D[temp.vertex] > D[minVertex] + temp.weight){
                    D[temp.vertex] = D[minVertex] +  temp.weight;
                }
            }
        }
    }
}

package week7.day4.BJ1753pq;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Vertex implements Comparable<Vertex>{
    int index;
    int weight;

    public Vertex(int index, int weight) {
        this.index = index;
        this.weight = weight;
    }

    @Override
    public int compareTo(Vertex o) {
        return this.weight - o.weight;
    }
}

public class Main {
    static int N;
    static int E;
    static int start;
    static int [] D ;
    static ArrayList<Vertex>[] adjList;
    static boolean[] isVisited;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/week7/day4/bj1753/input2.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(br.readLine());
        //weight 를 기록하는 배열
        D = new int[N+1];
        isVisited = new boolean[N+1];
        adjList = new ArrayList[N+1];
        for(int a = 0; a <= N; a++){
            adjList[a] = new ArrayList<>();
        }
        for (int i = 0; i <E ; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            //System.out.println(to);
            adjList[from].add(new Vertex(to, weight));
        }

        Arrays.fill(D, Integer.MAX_VALUE);
        D[start]= 0;
        PriorityQueue<Vertex> pq = new PriorityQueue<>();
        pq.offer(new Vertex(start, 0));
        while (!pq.isEmpty()){
            // 최소를 뽑는다
            // 처음에 to = start weight 0버텍스 나온다.
            Vertex minVertex = pq.poll();
            int index = minVertex.index;
            isVisited[index] = true;
            // 인접한 vertex 들을 돌면서 D 갱신
            // asjlist[0]:1, 2

            for(Vertex nextVertex :adjList[index]){
                //inf > start노드Wight + 다음 노드의 weight
                if( !isVisited[nextVertex.index] && D[nextVertex.index] >  D[index] + nextVertex.weight){
                    D[nextVertex.index] = D[index] + nextVertex.weight;
                    pq.add(new Vertex(nextVertex.index, D[nextVertex.index]));
                }
            }
        }
        for (int i = 1; i <= N; i++) {
            if(D[i] == Integer.MAX_VALUE){
                sb.append("INF").append("\n");
            }
            else sb.append(D[i]).append("\n");

        }
        System.out.println(sb);
    }
}

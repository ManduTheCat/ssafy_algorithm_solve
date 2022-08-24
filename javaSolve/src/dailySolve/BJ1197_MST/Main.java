package dailySolve.BJ1197_MST;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import java.util.Collections;
import java.util.StringTokenizer;

class Edge implements Comparable <Edge>{
    int from;
    int to;
    int weight;

    @Override
    public String toString() {
        return "Edge{" +
                "from=" + from +
                ", to=" + to +
                ", weight=" + weight +
                '}';
    }

    public Edge(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge o) {
        return Integer.compare(this.weight , o.weight);
    }
}
public class Main {
    static ArrayList<Edge> edges = new ArrayList<>();
    static int N;
    static int E;
    static int [] parents;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/daily/BJ_1197/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        parents = new int[N+1];
        for (int p = 1; p <= N ; p++) {
            parents[p] = p;
        }
        for (int m = 0; m < E; m++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            edges.add(new Edge(from, to, weight));
        }
        Collections.sort(edges);
        int res = 0;
        int count = 0;
        for(Edge edge : edges){
            if(union(edge.from, edge.to)){
                res+=edge.weight;
                if(++count == N - 1)break;
            }
        }
        System.out.println(res);


    }
    public static int find(int x){
        if(parents[x] == x){
            return x;
        }
        return parents[x] = find(parents[x]);
    }
    public static boolean union(int x, int y){
        int xRoot = find(x);
        int yRoot = find(y);
        if(xRoot == yRoot){
            return false;
        }
        parents[yRoot] = xRoot;
        return true;
    }


}

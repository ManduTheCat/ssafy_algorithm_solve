package week7.day3.D3124;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.*;


class Edge implements Comparable<Edge> {
    int from;
    int to;
    int w;

    public Edge(int from, int to, int w) {
        this.from = from;
        this.to = to;
        this.w = w;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Edge{");
        sb.append("from=").append(from);
        sb.append(", to=").append(to);
        sb.append(", w=").append(w);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int compareTo(Edge o) {
        return this.w -o.w;
    }
}


public class Solution {
    static int Tc;
    static int [] parents;
    static int N;
    static int M;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/week7/day3/swea3124/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Tc = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= Tc; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            parents = new int[N];
            for (int i = 0; i < N; i++) {
                parents[i] = i;
            }
            ArrayList <Edge> edges = new ArrayList<>();
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken()) - 1;
                int b = Integer.parseInt(st.nextToken()) - 1;
                int c = Integer.parseInt(st.nextToken());
                // minEdge  초기화
                edges.add( new Edge(a,b,c));
            }
            Collections.sort(edges);
            long re = 0;
            int count = 0;
            for(Edge edge : edges){
                if(union(edge.from , edge.to)){
                    re+=edge.w;
                    if(++count == N-1)break;
                }
            }
            sb.append("#").append(tc).append(" ").append(re).append("\n");
        }
        System.out.println(sb);
    }
    static int find(int x){
        if(parents[x] == x){
            return x;
        }
        return parents[x] = find(parents[x]);
    }
    static boolean union(int x, int y ){
        int xRoot = find(x);
        int yRoot = find(y);
        if(xRoot == yRoot){
            return false;
        }
        parents[yRoot] = xRoot;
        return true;
    }
}

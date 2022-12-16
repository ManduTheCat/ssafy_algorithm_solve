package dailySolve.bfs.BJ1922;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 크루스칼 풒이
class Edge implements Comparable<Edge>{
    int from;
    int to;
    int weigh;

    public Edge(int from, int to, int weigh) {
        this.from = from;
        this.to = to;
        this.weigh = weigh;
    }
    //내림차순으로 큰게 먼저 나오면 pq 에 넣으면 작은거 부터 나온다.
    @Override
    public int compareTo(Edge o) {
        return this.weigh - o.weigh;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "from=" + from +
                ", to=" + to +
                ", weigh=" + weigh +
                '}';
    }
}

public class Main {
    static int N;
    static int M;
    static int [] parents;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/daily/bfs/Bj1922/input.txt"));
        BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        parents = new int[N+1];
        Edge [] edgeList = new Edge[M];
        for (int n = 1; n <= N; n++) {
            parents[n] = n;

        }
        for (int e = 0; e < M; e++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            edgeList[e] = new Edge(Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
            );
        }
        Arrays.sort(edgeList);
        int res = 0;
        for(Edge edge: edgeList){
            // 웾이트 작은거순으로 합쳐라  같은것끼리는 안합쳐진다
            if(union(edge.from, edge.to)){
                res+= edge.weigh;
            }

        }
        System.out.println(res);
    }
    private static int find(int a){
        if(parents[a] ==a){
            return a;
        }
        return parents[a] = find(parents[a]);
    }

    private static boolean union(int a, int b){
        if(find(a) == find(b) )return false;
        parents[find(b)] = find(a);
        return true;

    }
}

package week7.day3.D41251;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


class Way implements Comparable<Way>{
    int node1, node2;
    double cost;

    public Way(int node1, int node2, double cost) {
        this.node1 = node1;
        this.node2 = node2;
        this.cost = cost;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Way{");
        sb.append("node1=").append(node1);
        sb.append(", node2=").append(node2);
        sb.append(", cost=").append(cost);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int compareTo(Way o) {
        return (int)(this.cost - o.cost);
    }
}

public class Solution {
    static int Tc;
    static int N;
    static Double E;
    static StringBuilder sb = new StringBuilder();
    static int [] root;
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/week7/day3/swea1251/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Tc = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= Tc; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            double[] xinputArr = new double[N];
            double[] yinputArr = new double[N];
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < N; x++) {
                xinputArr[x] = Double.parseDouble(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for (int y = 0; y < N; y++) {
                yinputArr[y] = Double.parseDouble(st.nextToken());
            }

            E = Double.parseDouble(br.readLine());
            //System.out.println(Arrays.toString(xinputArr));
            //System.out.println(Arrays.toString(yinputArr));
            // 유니온 파인드 세팅
            // 자기자신을 부모로 가진다.
            root = new int[N];
            for (int i = 0; i < N; i++) {
                root[i] = i;
            }
            // 우선순위 큐
            PriorityQueue <Way> pq = new PriorityQueue<>();
            for (int i = 0; i < N-1 ; i++) {
                //System.out.println(i);
                for (int j =  i + 1; j < N; j++) {
                    //(x1-x2) ^2
                    double distX = (xinputArr[i] - xinputArr[j]) * (xinputArr[i] - xinputArr[j]);
                    //(y1-y2) ^2
                    double distY = (yinputArr[i] - yinputArr[j]) * (yinputArr[i] - yinputArr[j]);
                    double dist = distX + distY;
                    // 노드는 인덱스 번호
                    pq.offer(new Way(i, j, dist));
                }
            }
            Way currWay = null;
            double cost = 0;
            while (!pq.isEmpty()){
                currWay = pq.poll();
                int node1 = find(currWay.node1);
                int node2 = find(currWay.node2);
                if(node1 == node2)continue;
                union(node1, node2);
                cost += E * currWay.cost;
            }

            sb.append("#").append(tc).append(" ").append(Math.round(cost)).append("\n");
        }
        System.out.println(sb);

    }

    static int find(int x){
        if(x != root[x]){
            return find(root[x]);
        }
        return x;
    }
    static void union(int x, int y){
        if(x < y){
            root[y] = root[x];
        }else {
            root[x] = root[y];
        }
    }
}

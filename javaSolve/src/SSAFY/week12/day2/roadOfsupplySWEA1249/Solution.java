package SSAFY.week12.day2.roadOfsupplySWEA1249;

import java.io.*;
import java.util.PriorityQueue;

class Vertex implements Comparable<Vertex>{
    int r;
    int c;
    int w;
    int moveCount;

    public Vertex(int r, int c, int w, int moveCount) {
        this.r = r;
        this.c = c;
        this.w = w;
        this.moveCount = moveCount;
    }

    @Override
    public int compareTo(Vertex o) {
        return this.w - o.w;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Vertex{");
        sb.append("r=").append(r);
        sb.append(", c=").append(c);
        sb.append(", w=").append(w);
        sb.append(", moveCount=").append(moveCount);
        sb.append('}');
        return sb.toString();
    }
}

public class Solution {
    static int N;
    static int Tc;
    static int[][] D;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/SSAFY.week12/day2/swea1249/input2.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Tc = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < Tc; tc++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            D = new int[N][N];
            for (int row = 0; row < N; row++) {
                String rowInput = br.readLine();
                for (int col = 0; col < N; col++) {
                    map[row][col] = rowInput.charAt(col) - '0';
                    D[row][col] = Integer.MAX_VALUE;
                }
            }
            dijkstra();
            printD();
            System.out.printf("#%d %d\n",tc+1,D[N-1][N-1]);

        }


    }
    
    // 다익스트라를 활용한 최소가중치 최단경로
    public static void dijkstra() {
        int[][] delta = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
        PriorityQueue<Vertex> pq = new PriorityQueue<>();
        pq.add(new Vertex(0, 0, 0, 0));
        D[0][0] = 0;
        while (!pq.isEmpty()) {
            Vertex currVertex = pq.poll();
            // 가중치 최소 우선 pq 를 사용했기에 가장 먼저 도착지에 닿으면 최단경로이다
            if(currVertex.r== N-1 && currVertex.c == N-1){
                return;
            }
            for (int d = 0; d < 4; d++) {
                int nextR = currVertex.r + delta[d][0];
                int nextC = currVertex.c + delta[d][1];
                //dp 다음 노드 > dp 지금 노드 + 다음 노드로 가는 가중치
                //dp 는 해당칸에 올수 있는 최소 비용
                // D[currVertex.r][currVertex.c] + map[nextR][nextC] 오버플로 일어나는데?
                if (isIn(nextR, nextC) && D[nextR][nextC] > D[currVertex.r][currVertex.c] + map[nextR][nextC]) {
                    D[nextR][nextC] = D[currVertex.r][currVertex.c] + map[nextR][nextC];
                    pq.offer(new Vertex(nextR, nextC,D[nextR][nextC],  currVertex.moveCount +1));
                }
            }
        }
    }

    public static boolean isIn(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }

    public static void printMap() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }
    public static void printD() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int value = D[i][j];
                System.out.print(value == Integer.MAX_VALUE? "I ": value+" ");
            }
            System.out.println();
        }
    }
}

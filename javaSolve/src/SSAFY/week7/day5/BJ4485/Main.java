package SSAFY.week7.day5.BJ4485;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Vertex implements Comparable<Vertex> {
    int i;
    int j;
    int w;

    public Vertex(int i, int j, int w) {
        this.i = i;
        this.j = j;
        this.w = w;
    }

    @Override
    public int compareTo(Vertex o) {
        return this.w - o.w;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Vertex{");
        sb.append("i=").append(i);
        sb.append(", j=").append(j);
        sb.append(", w=").append(w);
        sb.append('}');
        return sb.toString();
    }
}

public class Main {
    static int N;
    static int[][] D;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        int count = 0;
        System.setIn(new FileInputStream("resources/SSAFY.week7/day4/bj4485/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            count++;
            N = Integer.parseInt(br.readLine());
            //N에 0입력하면 종료
            if (N == 0) {
                break;
            }
            // 가중치 갱신하는 배열
            D = new int[N][N];
            //링크 가 돌아다닐 맵
            map = new int[N][N];
            for (int row = 0; row < N; row++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int col = 0; col < N; col++) {
                    map[row][col] = Integer.parseInt(st.nextToken());
                    D[row][col] = Integer.MAX_VALUE;

                }
            }
            dijkstar();
            System.out.printf("Problem %d: %d\n",count, D[N-1][N-1]);

        }
    }

    public static void dijkstar() {
        //초기화코드
        //시작
        int startI = 0;
        int startJ = 0;
        //끝
        int endI = N - 1;
        int endJ = N - 1;
        // 시작점도 가중치가 있어 넣어준다
        D[startI][startJ] = map[0][0];
        PriorityQueue<Vertex> pq = new PriorityQueue<>();
        //pq 초기화
        pq.offer(new Vertex(startI, startJ, map[startI][startJ]));
        // 좌상우하
        int[][] alpah = new int[][]{{0, -1}, {-1, 0}, {0, 1}, {1, 0}};

        //다익스트라 수행
        //이동가능한 좌표를 순회하면서 최소값을 갱신하면 우선순위 큐에 넣는다.
        while (!pq.isEmpty()) {
            Vertex currMinVertex = pq.poll();
            for (int d = 0; d < 4; d++) {
                //인접한 좌표를 이동
                int nextI = currMinVertex.i + alpah[d][0];
                int nextJ = currMinVertex.j + alpah[d][1];
                //최소값갱신
                if (isIn(nextI, nextJ) && D[nextI][nextJ] > map[nextI][nextJ] + D[currMinVertex.i][currMinVertex.j]) {
                    D[nextI][nextJ] = map[nextI][nextJ] + D[currMinVertex.i][currMinVertex.j];
                    //끝지점에 도착하면 종료
                    if (endI == nextI && endJ == nextJ) break;
                    pq.offer(new Vertex(nextI, nextJ, D[currMinVertex.i][currMinVertex.j]));
                }
            }
        }
    }

    public static boolean isIn(int i, int j) {
        return i < N && i >= 0 && j < N && j >= 0;
    }
}

package additionalClass.day2.swea1953;

import sun.nio.cs.Surrogate;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Solution {
    // 파티프의 관계는 3 의 보수의 관계
    //즉 상좌우하 0123 이면 3에서 현제 위치 번호 빼면 반대번호가 나온다
    // 반대 방향끼리 통과가 가능하기 때문인다
    // 단순화
    // 즉 보수 를 위해 델타의 순서 중요하다
    static class Pipe {

        int r, c;
        int[] open;

        public Pipe(int r, int c) {
            this.r = r;
            this.c = c;
            this.open = types[map[r][c]];
        }
    }
    static int T;
    static int M, N, R, C, L, A;
    static int[][] deltas = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
    static int[][] types = {
            {},// 1부터 사용하기 위해
            {0, 1, 2, 3},
            {0, 3},
            {1, 2},
            {0, 2}, // 상우
            {2, 3}, // 우하
            {1, 3}, // 좌하
            {0, 1}
    };
    static int[][] map;
    // 반대편이라는 개념을 보수로 잡으면 간단해진다. 이러면 if 덕지덕지x
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/additionalClass/day2/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());
            //System.out.println(L);
            map = new int[N][M];
            for (int n = 0; n < N; n++) {
                st = new StringTokenizer(br.readLine());
                //System.out.println(n);
                for (int m = 0; m < M; m++) {
                    map[n][m] = Integer.parseInt(st.nextToken());
                }
            }
            bfs();
            System.out.printf("#%d %d\n",t+1, A);
        }
    }
    public static void bfs(){
        Queue<Pipe> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];

        q.offer(new Pipe(R,C));
        visited[R][C] = true;
        L--;
        A = 1;
        while (L > 0){
            int size = q.size();
            while (size-- > 0){
                Pipe head = q.poll();
                // 사방탐색 할필요 없이 파이프 열린바향만 가면된다;
                for (int d : head.open) {
                    int nr = head.r+ deltas[d][0];
                    int nc = head.c+ deltas[d][1];
                    // 영역안에 있고 미방문이고 파이프연결이 가능하다면?
                    if(isIn(nr, nc) && !visited[nr][nc] && map[nr][nc]!= 0&& canConnect(nr, nc, 3-d)){
                        visited[nr][nc] = true;
                        q.offer(new Pipe(nr, nc));
                        A++;
                    }
                }
            }
            L--;
        }

    }

    public static boolean isIn(int r, int c){
        return 0 <=r && r<N && 0<=c && c<M;
    }
    public static boolean canConnect(int r, int c, int d){
        for(int i: types[map[r][c]]){
            if(i == d){
                return true;
            }
        }
        return  false;
    }
}

package gold.BJ20165_DOMINO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N; //100
    static int M;
    static int R; //10^4
    static int [][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for(int n = 0; n < N; n++){
            st = new StringTokenizer(br.readLine());
            for(int m = 0; m < M; m++){
                int val  = Integer.parseInt(st.nextToken());
                map[n][m] = val;
            }
        }
        for(int r = 0; r < R; r++){
            st = new StringTokenizer(br.readLine());
            int atRow = Integer.parseInt(st.nextToken());
            int atCol = Integer.parseInt(st.nextToken());
            char dir = String.valueOf(st.nextToken()).charAt(0);
            st = new StringTokenizer(br.readLine());
            int defRow = Integer.parseInt(st.nextToken());
            int defCol = Integer.parseInt(st.nextToken());
            fallDown(atRow, atCol, dir);
        }
    }
    static private void fallDown(int row, int col, char dir){
        int[][] dirArr = {{-1,0},{0,1},{1,0},{0,-1}};// 위 오 아 왼
        Queue<int[]> q = new ArrayDeque<>();
        // 해당 방향으로 진행하면서 큐에 넣는다
        // 큐에서 꺼내면서 엎는다.
        q.offer(new int[] {row, col});
        while (!q.isEmpty()){
            int [] start = q.poll();
            if(map[start[0]][start[1]] > 0){
                int count = map[start[0]][start[1]];
                map[start[0]][start[1]] = -1;
            }
        }
    }
}

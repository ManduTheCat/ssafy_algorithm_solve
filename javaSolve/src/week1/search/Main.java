package week1.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[][] maze = new int[N][M];

        for(int i = 0; i<N; i++){
            String s = br.readLine();

            for(int j = 0; j < M; j++){
                maze[i][j] =  s.charAt(j) - '0';
            }
        }
        boolean[][] visitCheck = new boolean [N][M];
        visitCheck[0][0] = false;
        bfs(0,0, maze, visitCheck);
        System.out.println(maze[N-1][M-1]);
    }
    public static void bfs(int x, int y, int[][] maze, boolean [][] visitCheck){
        Queue <int[]> q = new LinkedList<>();
        q.add(new int [] {x, y});
        int [] dx = { 1, -1, 0, 0};
        int [] dy = { 0, 0, 1, -1 };
        while (!q.isEmpty()){
            int[] cur = q.poll();
            int curX = cur[0];
            int curY = cur[1];
            for(int i = 0; i < 4; i++){
                int nextX = dx[i] + curX;
                int nextY = dy[i] + curY;
                if(nextX >= 0 && nextX < N && nextY >= 0 && nextY < M){
                    if(!visitCheck[nextX][nextY] && maze[nextX][nextY] == 1){
                        q.add(new int[] {nextX, nextY});
                        visitCheck[nextX][nextY] = true;
                        maze[nextX][nextY] = maze[curX][curY] + 1;
                    }
                }
            }
        }
    }
}

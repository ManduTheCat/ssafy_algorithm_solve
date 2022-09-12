package dailySolve.BJ14442crashWall;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


// 벽부스고 목적지까지 이동하는 객체 현재 위치와 남은 카운트 이동횟수(depth) 기억하고 queue 에 담긴다.
class Runner {
    int depth;
    int row;
    int col;
    int remainCrashCount;

    public Runner(int depth, int row, int col, int remianCrashCount) {
        this.depth = depth;
        this.row = row;
        this.col = col;
        this.remainCrashCount = remianCrashCount;
    }

}
//	441352kb	2860ms
public class Main {
    static int N;
    static int M;
    static int K;
    static boolean[][][] visited;
    static int[][][] map;
    static int[][] normalAlpha = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/daily/BJ14442/input2.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        visited = new boolean[N][M][K + 1];
        map = new int[N][M][K + 1];
        for (int row = 0; row < N; row++) {
            String rowLine = br.readLine();
            ;
            for (int col = 0; col < M; col++) {
                int value = (int) rowLine.charAt(col) - '0';
                for (int k = 0; k <= K; k++) {
                    if (value == 1) map[row][col][k] = 1;
                }
            }
        }
        bfs();
        // 다돌아도 도착하지 못하면 -1 출력
        System.out.println(-1);
    }

    public static void bfs() {
        Queue<Runner> q = new LinkedList<>();
        q.add(new Runner(1, 0, 0, K));
        visited[0][0][K] = true;
        while (!q.isEmpty()) {

            Runner currRunner = q.poll();
            if (currRunner.row == N - 1 && currRunner.col == M - 1) {
                System.out.println(currRunner.depth);
                System.exit(0);
            }
            // 벽을 부술수있는 기회가 아직 남았다면?
            if (currRunner.remainCrashCount > 0) {
                // 벽부수고 이동
                for (int d = 0; d < 4; d++) {
                    int nextRow = currRunner.row + normalAlpha[d][0];
                    int nextCol = currRunner.col + normalAlpha[d][1];
                    int nextRemainCrashCount = currRunner.remainCrashCount - 1;
                    // 다음 갈곳은 벽이고 이동가능하면 -> 부수고 이동(맵을 0으로 바꾸고 체크) 꽝!!!
                    if (isIn(nextRow, nextCol) && map[nextRow][nextCol][nextRemainCrashCount] == 1
                            && !visited[nextRow][nextCol][nextRemainCrashCount]) {
                        map[nextRow][nextCol][nextRemainCrashCount] = 0;
                        visited[nextRow][nextCol][nextRemainCrashCount] = true;
                        q.add(new Runner(currRunner.depth + 1, nextRow, nextCol, nextRemainCrashCount));
                    }
                }
            }
            // 그냥이동
            for (int d = 0; d < 4; d++) {
                int nextRow = currRunner.row + normalAlpha[d][0];
                int nextCol = currRunner.col + normalAlpha[d][1];
                // 벽이아니고 현제 벽부순 횟수 visited 봤을때 방문한적이 없다면
                if (isIn(nextRow, nextCol) && !visited[nextRow][nextCol][currRunner.remainCrashCount]
                        && map[nextRow][nextCol][currRunner.remainCrashCount] == 0) {
                    visited[nextRow][nextCol][currRunner.remainCrashCount] = true;
                    q.add(new Runner(currRunner.depth + 1, nextRow, nextCol, currRunner.remainCrashCount));
                }
            }

        }
    }
    public static boolean isIn(int row, int col) {
        return row < N && row >= 0 && col < M && col >= 0;
    }
}

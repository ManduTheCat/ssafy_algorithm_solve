package dailySolve.BJ14442crashWall;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Runner{");
        sb.append("depth=").append(depth);
        sb.append(", row=").append(row);
        sb.append(", col=").append(col);
        sb.append(", remainCrashCount=").append(remainCrashCount);
        sb.append('}');
        return sb.toString();
    }
}

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
        //System.out.println(N + " " + M + " " + K);
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
        System.out.println(-1);
    }

    public static void bfs() {
        Queue<Runner> q = new LinkedList<>();
        q.add(new Runner(1, 0, 0, K));
        visited[0][0][K] = true;
        while (!q.isEmpty()) {

            Runner currRunner = q.poll();
            if(currRunner.row == N -1 && currRunner.col == M-1){
                System.out.println(currRunner.depth);
                System.exit(0);
            }
            if (currRunner.remainCrashCount > 0) {
                // 벽부스고 이동
                for (int d = 0; d < 4; d++) {
                    int nextRow = currRunner.row + normalAlpha[d][0];
                    int nextCol = currRunner.col + normalAlpha[d][1];
                    int nextRemainCrashCount = currRunner.remainCrashCount -1;
                    // 다음 갈곳은 벽이고 이동가능하면 -> 부스고 이동
                    if (isIn(nextRow, nextCol) && map[nextRow][nextCol][nextRemainCrashCount] == 1 && !visited[nextRow][nextCol][nextRemainCrashCount]) {
                        map[nextRow][nextCol][nextRemainCrashCount] = 0;
                        visited[nextRow][nextCol][nextRemainCrashCount]= true;
                        q.add(new Runner(currRunner.depth+1, nextRow, nextCol, nextRemainCrashCount));
                    }
                }
                // 안에 있고 벽이라면, 부스고 이동해라
            }
            // 그냥이동
            for (int d = 0; d < 4; d++) {
                int nextRow = currRunner.row + normalAlpha[d][0];
                int nextCol = currRunner.col + normalAlpha[d][1];
                if(isIn(nextRow, nextCol) && !visited[nextRow][nextCol][currRunner.remainCrashCount]
                        && map[nextRow][nextCol][currRunner.remainCrashCount] == 0){
                    visited[nextRow][nextCol][currRunner.remainCrashCount] = true;
                    q.add(new Runner(currRunner.depth +1, nextRow, nextCol, currRunner.remainCrashCount));
                }
            }

        }

    }

    public static void print(boolean[][][] input) {
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                System.out.print(input[row][col][0] ? "1" : "0");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void print(int[][][] input) {
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                System.out.print(input[row][col][0]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static boolean isIn(int row, int col) {
        return row < N && row >= 0 && col < M && col >= 0;
    }
}

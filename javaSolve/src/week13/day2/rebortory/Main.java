package week13.day2.rebortory;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;


class Point {
    int r;
    int c;

    public Point(int r, int c) {
        this.r = r;
        this.c = c;
    }
}

public class Main {
    static int N;
    static int M;
    static int[][] map;
    static boolean[][] visited;
    static Point[] combeRes = new Point[3];
    static int maxCount = 0;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/week13/day2/rebortory/input3.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < M; col++) {
                int value = Integer.parseInt(st.nextToken());
                map[row][col] = value;
            }
        }

        combination(0, 0);
        System.out.println(maxCount);

    }

    public static void combination(int depth, int r) {
        // 한줄로 펴서 combination을 해야한다.
        if (depth == 3) {
            goBfs();
            return;
        }
        for (int i = r; i < N * M; i++) {
            int newR = i / M;
            int newC = i % M;
            if (map[newR][newC] == 0) {
                combeRes[depth] = new Point(newR, newC);
                combination(depth + 1, i + 1);
            }
        }
    }

    private static void goBfs() {
        int[][] newMap = new int[N][M];
        deepCopy(newMap);
        for (Point p : combeRes) {
            // 벽세우기
            newMap[p.r][p.c] = 1;
        }
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                if (newMap[row][col] == 2) {
                    //System.out.println("in 2");
                    bfs(row, col, newMap);
                }
            }

        }
        countSafeZone(newMap);

    }

    private static void bfs(int row, int col, int[][] map) {
        //System.out.println("in");
        int[][] del = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(row, col));
        // 2로표시
        map[row][col] = 2;
        while (!q.isEmpty()) {
            Point currPoint = q.poll();
            for (int d = 0; d < 4; d++) {
                int nr = currPoint.r + del[d][0];
                int nc = currPoint.c + del[d][1];
                if (isIn(nr, nc) && map[nr][nc] == 0) {
                    map[nr][nc] = 2;
                    q.offer(new Point(nr, nc));
                }
            }
        }
    }

    private static void countSafeZone(int[][] map) {
        // 사실 count safeZone
        int count = 0;
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                if(map[row][col] == 0)count++;

            }
        }

        maxCount = Math.max(count,maxCount );
    }

    private static boolean isIn(int nr, int nc) {
        return nr >= 0 && nr < N && nc >= 0 && nc < M;
    }

    private static void deepCopy(int[][] newMap) {
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                newMap[row][col] = map[row][col];
            }
        }
    }

}

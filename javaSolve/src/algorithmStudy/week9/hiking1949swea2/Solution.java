package algorithmStudy.week9.hiking1949swea2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;


class Point {
    int r, c;
    int value;
    int depth;


    public Point(int r, int c, int value, int depth) {
        this.r = r;
        this.c = c;
        this.value = value;
        this.depth = depth;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Point{");
        sb.append("r=").append(r);
        sb.append(", c=").append(c);
        sb.append(", value=").append(value);
        sb.append(", depth=").append(depth);
        sb.append('}');
        return sb.toString();
    }



}

public class Solution {
    static int N, K;
    static int map[][];
    static int Tc;
    static boolean[][] check;
    static int[][] del = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
    static int maxDepth = 0;

    // 원숭이 풀이 bfs
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/study/week9/swea1949/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Tc = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < Tc; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            map = new int[N][N];

            for (int row = 0; row < N; row++) {
                st = new StringTokenizer(br.readLine());
                for (int col = 0; col < N; col++) {
                    map[row][col] = Integer.parseInt(st.nextToken());
                }
            }
            //printMap();
            ArrayList<Point> startPoints = findTop();
            maxDepth = 0;
            // 돌리기전에 하나씩 증가 시켜보자
            for (int row = 0; row < N; row++) {
                for (int col = 0; col < N; col++) {
                    for (int k = 0; k <= K; k++) {

                        map[row][col] -=k;
                        for (Point startPoint : startPoints) {
                            check = new boolean[N][N];
                            check[startPoint.r][startPoint.c] = true;
                            dfs(startPoint);
                        }
                        map[row][col] +=k;
                    }

                }
            }
            System.out.printf("#%d %d\n",tc+1,maxDepth + 1);

        }

    }

    private static void dfs(Point currPoint) {
        //System.out.println(currPoint);
        maxDepth = Math.max(maxDepth, currPoint.depth);
        for (int d = 0; d < 4; d++) {
            int nr = currPoint.r + del[d][0];
            int nc = currPoint.c + del[d][1];
            if (isIn(nr, nc) && !check[nr][nc]) {
                int nextValue = map[nr][nc];
                if (nextValue < map[currPoint.r][currPoint.c] ) {
                    // 안에들어오고 다음 것이 작다면
                    check[nr][nc] = true;
                    dfs(new Point(nr, nc, map[nr][nc], currPoint.depth + 1));
                    check[nr][nc] = false;
                }
            }
        }

    }

    private static boolean isIn(int nr, int nc) {
        return nr >= 0 && nr < N && nc >= 0 && nc < N;
    }

    private static ArrayList<Point> findTop() {
        ArrayList<Point> res = new ArrayList<>();
        int max = 0;
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                int val = map[row][col];
                max = Math.max(val, max);
            }
        }
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                int val = map[row][col];
                if (max == val) {
                    res.add(new Point(row, col, val, 0));
                }
            }
        }
        return res;
    }

    public static void printMap() {
        for (int[] row : map) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println();
    }
}

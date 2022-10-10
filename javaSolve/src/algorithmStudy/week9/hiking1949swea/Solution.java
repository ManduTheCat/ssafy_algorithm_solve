package algorithmStudy.week9.hiking1949swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


class Point {
    int r, c;
    int value;
    int depth;
    int drillCount;

    public Point(int r, int c, int value, int depth, int drillCount) {
        this.r = r;
        this.c = c;
        this.value = value;
        this.depth = depth;
        this.drillCount = drillCount;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Point{");
        sb.append("r=").append(r);
        sb.append(", c=").append(c);
        sb.append(", value=").append(value);
        sb.append(", depth=").append(depth);
        sb.append(", drillCount=").append(drillCount);
        sb.append('}');
        return sb.toString();
    }
}

public class Solution {
    static int N, K;
    static int map[][];
    static boolean[][][] check;
    static int Tc;
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
            for (Point startPoint : startPoints) {
                //System.out.println(startPoint.value);
                check = new boolean[N][N][2];
                dfs(startPoint);

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
            if (isIn(nr, nc)) {
                int nextValue = map[nr][nc];
                for (int k = 1; k <= K; k++) {
                    if (nextValue - k < currPoint.value && currPoint.drillCount > 0
                            && !check[currPoint.r][currPoint.c][currPoint.drillCount-1]) {
                        // 안에 들어오고 다음것을  k 만큼 파버렸을때 나보다 작다면
                        //System.out.println(nextValue+" - "+ k +" " +(nextValue - k) +" < "+ currPoint.value);
                        check[currPoint.r][currPoint.c][currPoint.drillCount-1] = true;
                        dfs(new Point(nr, nc, Math.max(map[nr][nc] - k, 0),
                                currPoint.depth + 1, currPoint.drillCount - 1));
                        check[currPoint.r][currPoint.c][currPoint.drillCount - 1] = false;

                    }
                }
                if (nextValue < currPoint.value && !check[currPoint.r][currPoint.c][currPoint.drillCount]) {
                    // 안에들어오고 다음 것이 작다면
                    check[currPoint.r][currPoint.c][currPoint.drillCount] = true;
                    dfs(new Point(nr, nc, map[nr][nc], currPoint.depth + 1, currPoint.drillCount));
                    check[currPoint.r][currPoint.c][currPoint.drillCount] = false;
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
                    res.add(new Point(row, col, val, 0, 1));
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

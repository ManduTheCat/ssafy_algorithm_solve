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
            //최대 깊이찾기
            ArrayList<Point> startPoints = findTop();
            maxDepth = 0;
            for (Point startPoint : startPoints) {
                check = new boolean[N][N][2];
                check[startPoint.r][startPoint.c][0] =true;
                check[startPoint.r][startPoint.c][1] =true;
                dfs(startPoint);

            }
            System.out.printf("#%d %d\n",tc+1,maxDepth + 1);

        }

    }

    private static void dfs(Point currPoint) {
        maxDepth = Math.max(maxDepth, currPoint.depth);
        for (int d = 0; d < 4; d++) {
            int nr = currPoint.r + del[d][0];
            int nc = currPoint.c + del[d][1];
            if (isIn(nr, nc)) {
                int nextValue = map[nr][nc];
                // 여기는 무조건 파는 경우 이기 떄문에 k 1부터 시작함
                for (int k = 1; k <= K; k++) {
                    // 팟을때 지금 것보다 작다면 && 팔수 있는 횟수가 남아 있다면 && 방문핸던적이 없다면
                    if (nextValue - k < currPoint.value && currPoint.drillCount > 0
                            && !check[nr][nc][currPoint.drillCount-1]) {
                        // 원숭이처럼 드릴카운트 하나 까고 넘긴다
                        check[nr][nc][currPoint.drillCount-1] = true;
                        dfs(new Point(nr, nc, Math.max(map[nr][nc] - k, 0),
                                currPoint.depth + 1, currPoint.drillCount - 1));
                        check[nr][nc][currPoint.drillCount - 1] = false;

                    }
                }
                // 드릴을 사용하지 않는경우
                if (nextValue < currPoint.value && !check[nr][nc][currPoint.drillCount]) {
                    check[nr][nc][currPoint.drillCount] = true;
                    dfs(new Point(nr, nc, map[nr][nc], currPoint.depth + 1, currPoint.drillCount));
                    check[nr][nc][currPoint.drillCount] = false;
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

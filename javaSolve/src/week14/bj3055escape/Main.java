package week14.bj3055escape;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Point {
    int r, c;

    public Point(int r, int c) {
        this.r = r;
        this.c = c;
    }

    @Override
    public String toString() {
        return "Point{" +
                "r=" + r +
                ", c=" + c +
                '}';
    }
}

/*
 * 큐두개를 활용한 풀ㅇ
 * */
public class Main {
    // 물이 확장하고
    // 고슴도치가 이동해야한다.

    static boolean[][] check;
    static int R, C;
    static Point target;
    static Queue<Point> waterQ;
    static Queue<Point> hedgehogQ;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/week14/BJ2055/input4.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        //map 을안쓰고 해볼까?
        check = new boolean[R][C];
        waterQ = new LinkedList<>();
        hedgehogQ = new LinkedList<>();
        for (int row = 0; row < R; row++) {
            String rowInput = br.readLine();
            for (int col = 0; col < C; col++) {
                char val = rowInput.charAt(col);
                switch (val) {
                    case 'D':
                        target = new Point(row, col);
                        break;
                    case 'S':
                        hedgehogQ.offer(new Point(row, col));
                        check[row][col] = true;
                        break;
                    case 'X':
                        check[row][col] = true;
                        break;
                    case '*':
                        check[row][col] = true;
                        waterQ.offer(new Point(row, col));
                        break;
                }
            }
        }
        bfs();
        System.out.println("KAKTUS");
    }

    private static void bfs() {
        int depth = 0;
        int[][] del = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
        // 고슴도치가 이동못하는 상황이 오면 게임 끝
        // 먼저 물부터 퍼지고
        while (!hedgehogQ.isEmpty()) {
            int waterSize = waterQ.size();
            while (waterSize-- > 0) {
                Point currWater = waterQ.poll();
                for (int d = 0; d < 4; d++) {
                    int nr = currWater.r + del[d][0];
                    int nc = currWater.c + del[d][1];
                    if (isin(nr, nc) && !check[nr][nc] && !(nr == target.r && nc == target.c)) {
                        check[nr][nc] = true;
                        waterQ.offer(new Point(nr, nc));
                    }
                }

            }
            // 두번째로 고슴도치가 움직인다.
            int hedgehogSize = hedgehogQ.size();
            while (hedgehogSize-- > 0) {
                Point currHedgehog = hedgehogQ.poll();
                if (currHedgehog.r == target.r && currHedgehog.c == target.c) {
                    System.out.println(depth);
                    System.exit(0);

                }
                for (int d = 0; d < 4; d++) {
                    int nr = currHedgehog.r + del[d][0];
                    int nc = currHedgehog.c + del[d][1];
                    if (isin(nr, nc) && !check[nr][nc]) {
                        check[nr][nc] = true;
                        hedgehogQ.offer(new Point(nr, nc));
                    }
                }

            }
            depth++;
        }
    }

    private static boolean isin(int nr, int nc) {
        return nr >= 0 && nr < R && nc >= 0 && nc < C;
    }

    private static void printArr(boolean[][] input) {
        for (boolean[] row : input) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println();
    }

}

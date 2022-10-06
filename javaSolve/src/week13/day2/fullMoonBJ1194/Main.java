package week13.day2.fullMoonBJ1194;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;


class Point {
    int r, c;
    int key;
    int depth;

    public Point(int r, int c, int key, int depth) {
        this.r = r;
        this.c = c;
        this.key = key;
        this.depth = depth;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Point{");
        sb.append("r=").append(r);
        sb.append(", c=").append(c);
        sb.append(", key=").append(key);
        sb.append(", depth=").append(depth);
        sb.append('}');
        return sb.toString();
    }
}

public class Main {
    static int N, M;
    static char[][] map;
    static boolean[][][] check;

    static Queue<Point> q = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/week13/day2/bj1194/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        check = new boolean [N][M][64];
        for (int row = 0; row < N; row++) {
            String rowLine = br.readLine();
            for (int col = 0; col < M; col++) {
                char val = rowLine.charAt(col);
                if (val == '#') {
                    //System.out.println("in");
                    for (int i = 0; i < 5; i++) {
                        check[row][col][i] = true;
                        //System.out.println(check[row][col][i]);
                    }
                } else if (val == '0') {
                    q.offer(new Point(row, col, 0,0));
                }
                map[row][col] = val;
            }
        }
        // 열쇠를 들고 이동을할땐 다음 차원으로 간다 들고 있는 상태는 비트마스킹으로 표시한다.
        // 먼저 1 에도착하면 종료하는 bfs 작성
        printArr(map);
        bfs();
        printArr(check);
    }

    private static void bfs() {
        int[][] delta = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};

        while (!q.isEmpty()) {
            Point curPoint = q.poll();
            System.out.println(curPoint);
            if(map[curPoint.r][curPoint.c] == '1'){
                System.out.println(curPoint);
                //System.exit(0);
            }
            for (int d = 0; d < 4; d++) {
                int nr = curPoint.r + delta[d][0];
                int nc = curPoint.c + delta[d][1];
                if (isIn(nr, nc) ) {
                    char val = map[nr][nc];
                    switch (val) {
                        case 'a':
                        case 'b':
                        case 'c':
                        case 'd':
                        case 'e':
                        case 'f':
                            int nkey = 1 << (val - 'a');
                            nkey = curPoint.key | nkey;
                            if(!check[nr][nc][nkey]){

                                check[nr][nc][nkey] = true;
                                System.out.println("put "+ val +", "+ nkey);
                                q.offer(new Point(nr, nc, nkey, curPoint.depth+1));
                                System.out.println("after put " + q);
                            }
                            break;
                        case 'A':
                        case 'B':
                        case 'C':
                        case 'D':
                        case 'E':
                        case 'F':
                            int Bkey = 1 << val - 'A';
                            System.out.println(curPoint+" "+val +", "+ +curPoint.key + "vs" + Bkey);
                            if(!check[nr][nc][Bkey]){
                                if((curPoint.key & Bkey) > 0){
                                    System.out.println("door OPen");
                                    q.offer(new Point(nr, nc, curPoint.key, curPoint.depth+1));
                                }
                            }
                            break;

                        case '.':
                            System.out.println("dot in");
                            if(!check[nr][nc][curPoint.key]){
                                q.offer(new Point(nr, nc, curPoint.key, curPoint.depth+1));
                                check[nr][nc][curPoint.key] = true;
                            }
                            break;
                    }
                }
            }

        }

    }

    public static boolean isIn(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }

    private static void printArr(boolean[][][] check) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(check[i][j][32] ? "1 " : "0 ");
            }
            System.out.println();

        }
    }

    private static void printArr(char[][] map) {
        for (char[] row : map) {
            System.out.println(Arrays.toString(row));
        }
    }
}

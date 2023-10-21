package SSAFY.week12.day2.wannabeHorese1600;


import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Monkey {
    int r;
    int c;
    int depth;
    int remainHorseMode;

    public Monkey(int r, int c, int depth, int remainHorseMode) {
        this.r = r;
        this.c = c;
        this.depth = depth;
        this.remainHorseMode = remainHorseMode;
    }

    @Override
    public String toString() {
        return "monkey{" +
                "r=" + r +
                ", c=" + c +
                ", depth=" + depth +
                '}';
    }
}

public class Main {
    static int[][] moveHose = {{-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}};
    static int[][] moveMonkey = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static int[][] map;
    static boolean check[][][];

    static int K, W, H;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/study/SSAFY.week6/BJ1600/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new int[H][W];
        check = new boolean[H][W][K + 1];
        for (int h = 0; h < H; h++) {
            st = new StringTokenizer(br.readLine());
            for (int w = 0; w < W; w++) {
                int value = Integer.parseInt(st.nextToken());
                map[h][w] = value;
                if (value == 1) {
                    for (int i = 0; i <= K; i++) {
                        check[h][w][i] = true;
                    }
                }
            }
        }
        bfs();
        System.out.println("-1");


    }

    public static void bfs() {
        Queue<Monkey> q = new LinkedList<>();
        q.offer(new Monkey(0, 0, 0, K));
        while (!q.isEmpty()) {
            Monkey curMonkey = q.poll();
            if(curMonkey.r == H-1 && curMonkey.c == W-1){
                System.out.println(curMonkey.depth);
                System.exit(0);
            }
            //힘이 넘치는 원숭이의 말처럼 움직이기
            if (curMonkey.remainHorseMode > 0) {
                for (int[] dh : moveHose) {
                    int nextRow = curMonkey.r + dh[0];
                    int nextCol = curMonkey.c + dh[1];
                    // 점프하면서 이동하는곳이 비어 있어야한다.
                    if (isIn(nextRow, nextCol) && !check[nextRow][nextCol][curMonkey.remainHorseMode - 1]) {
                        check[nextRow][nextCol][curMonkey.remainHorseMode - 1] = true;
                        q.offer(new Monkey(nextRow, nextCol, curMonkey.depth + 1, curMonkey.remainHorseMode - 1));
                    }
                }
            }
            //지친원숭이의 원숭이처럼 움직이기
            for (int[] dm : moveMonkey) {
                int nextRow = curMonkey.r + dm[0];
                int nextCol = curMonkey.c + dm[1];
                if(isIn(nextRow, nextCol) && !check[nextRow][nextCol][curMonkey.remainHorseMode]){
                    check[nextRow][nextCol][curMonkey.remainHorseMode]= true;
                    q.offer(new Monkey(nextRow, nextCol, curMonkey.depth+1, curMonkey.remainHorseMode));
                }

            }
        }

    }

    public static boolean isIn(int r, int c) {
        return r >= 0 && r < H && c >= 0 && c < W;
    }

    public static void printCheck() {
        for (int h = 0; h < H; h++) {
            for (int w = 0; w < W; w++) {
                System.out.print(check[h][w][0]);
            }
            System.out.println();
        }
    }

    public static void printMap() {
        for (int[] row : map) {
            System.out.print(Arrays.toString(row));
            System.out.println();
        }
    }


}

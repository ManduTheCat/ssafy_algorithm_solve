package algorithmStudy.week6.wannaBeHorese1600BFSAndSet;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.*;


// 메모리 초과하는 코드 ㅜㅜ  처음에 큐에 배열을 넣었고 안되서  set으로 변경했지만 터짐 ㅜ

 class Monkey {
    int row;
    int col;
    int remainHorseMode;
    Set<Point> routeCheck;


    public Monkey(int row, int col, int remainHorseMode,  Set<Point> routeCheck) {
        this.row = row;
        this.col = col;
        this.remainHorseMode = remainHorseMode;
        this.routeCheck = routeCheck;
    }

}

public class Main {
    static int K;
    static int W, H;
    static Set<Point> route;
    static int[][] horseModeAlpha = new int[][]{{-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}, {-2, 1}};
    static int[][] monkeyAlpha = new int[][]{{0, 1}, {-1, 0}, {0, -1}, {1, 0}};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/study/SSAFY.week6/BJ1600/input3.txt"));
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(bf.readLine());
        StringTokenizer st = new StringTokenizer(bf.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        route = new HashSet<>();
        for (int row = 0; row < H; row++) {
            st = new StringTokenizer(bf.readLine());
            for (int col = 0; col < W; col++) {
                int val = Integer.parseInt(st.nextToken());
                if (val == 1) route.add(new Point(row, col));
            }
        }
        //System.out.println(route);
        bfs();
        // bfs로 해결할거다
        // 단 두가지 (가지)경우에 대해 본다
        // 1. 말로 이동가능한경우 이동한다 넘길때 horseMode count 내린다;
        // 2. 원숭이로 이동가능한경우 이동한다 넘길때 horseModeCount 건들지 않는다.
        System.out.println(-1);
    }

    public static void bfs() {
        Queue<Monkey> q = new LinkedList<>();
        route.add(new Point(0,0));
        q.add(new Monkey(0, 0, K,  new HashSet<>(route)));
        int depth = 0;
        while (!q.isEmpty()) {
            int qSize = q.size();
            while (qSize-- > 0){
                Monkey currMonkey = q.poll();
                //System.out.println(currMonkey.row + " " + currMonkey.col);
                //print(currMonkey.routeCheck);
                if (currMonkey.row == H - 1 && currMonkey.col == W - 1) {
                    System.out.println(depth);
                    System.exit(0);
                }
                Set<Point> currCheck = currMonkey.routeCheck;
                // 말처럼 이동하는 부분 말처럼 이동하고 이동할수 있는 remainHorseMode 를 하나 내린다.
                if(currMonkey.remainHorseMode > 0){
                    for (int dh = 0; dh < 8; dh++) {
                        int nextRow = currMonkey.row + horseModeAlpha[dh][0];
                        int nextCol = currMonkey.col + horseModeAlpha[dh][1];

                        if (isIn(nextRow, nextCol) && !currCheck.contains( new Point (nextRow, nextCol))) {
                            Set<Point> nextCheck = new HashSet<>(currMonkey.routeCheck);
                            nextCheck.add(new Point(nextRow, nextCol));
                            q.add(new Monkey(nextRow, nextCol, currMonkey.remainHorseMode - 1, nextCheck));
                        }
                    }
                }
                for (int mh = 0; mh < 4; mh++) {
                    int nextRow = currMonkey.row + monkeyAlpha[mh][0];
                    int nextCol = currMonkey.col + monkeyAlpha[mh][1];
                    if (isIn(nextRow, nextCol) && !currCheck.contains(new Point(nextRow, nextCol))) {
                        Set<Point> nextCheck = new HashSet<>(currCheck);
                        nextCheck.add(new Point(nextRow, nextCol));
                        q.add(new Monkey(nextRow, nextCol, currMonkey.remainHorseMode, nextCheck));
                    }
                }
                depth ++;
            }
        }

    }

    public static boolean isIn(int row, int col) {
        return row < H && row >= 0 && col < W && col >= 0;
    }

//// 디버깅용 함수
    public static void print(int[][] input) {
        for (int[] m : input) {
            System.out.println(Arrays.toString(m));
        }
        System.out.println();
    }

    public static void print(boolean[][] input) {
        for (boolean[] b : input) {
            for (boolean el : b) {
                System.out.print(el ? 1 : 0);
            }
            System.out.println();
        }
        System.out.println();
    }

}

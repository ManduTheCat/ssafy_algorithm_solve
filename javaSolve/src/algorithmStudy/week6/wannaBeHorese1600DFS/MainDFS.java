package algorithmStudy.week6.wannaBeHorese1600DFS;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// fail time cause in max w h, time complexity is (2^ n^2)  be bfs
public class MainDFS {
    static int K;
    static int W, H;
    static int[][] map;
    static boolean[][] check;
    static int[][] horseModeAlpha = new int[][]{{-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}, {-2, 1}};
    static int[][] monkeyAlpha = new int[][]{{0, 1}, {-1, 0}, {0, -1}, {1, 0}};
    static ArrayList<Integer> res  = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/study/SSAFY.week6/BJ1600/input.txt"));
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(bf.readLine());
        StringTokenizer st = new StringTokenizer(bf.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new int[H][W];
        check = new boolean[H][W];
        check[0][0] = true;
        for (int row = 0; row < H; row++) {
            st = new StringTokenizer(bf.readLine());
            for (int col = 0; col < W; col++) {
                int val = Integer.parseInt(st.nextToken());
                if (val == 1) check[row][col] = true;
                map[row][col] = val;
            }
        }
        // dfs로 해결할거다
        // 단 두가지 (가지)경우에 대해 본다
        // 1. 말로 이동가능한경우 이동한다 넘길때 horseMode count 내린다;
        // 2. 원숭이로 이동가능한경우 이동한다 넘길때 horseModeCount 건들지 않느다.
        // 재귀를 해재 할때 check 방문을 해제 해준다. 왜? 사방탐색 갔던곳에 같은 경로로말로 다시 갈수 있다/
        // dfs 중 목적지 [H -1][W-1]  에 도착함녀 res 에 넘긴다 .
        dfs(0,0, 0,K);
        if(res.size() == 0){
            System.out.println(-1);
        }
        else {
            System.out.println(Collections.min(res));
        }

    }

    public static void dfs(int row, int col, int depth, int horseModeCount) {
        if (row == H - 1 && col == W - 1) {
            //System.out.println("touch END " + row + " " + col + " " + depth);
            res.add(depth);
            return;
        }
        //hoseMode
        if(horseModeCount > 0){
            for (int dh = 0; dh < 8; dh++) {
                int nextRow = row + horseModeAlpha[dh][0];
                int nextCol = col + horseModeAlpha[dh][1];
                if (isIn(nextRow, nextCol) && !check[nextRow][nextCol]) {
                    //System.out.println("in");
                    check[nextRow][nextCol] = true;
                    dfs(nextRow, nextCol, depth + 1, horseModeCount - 1);
                    check[nextRow][nextCol] = false;
                }
            }
        }
        // 원숭이의 경우
        for (int dm = 0; dm < 4; dm++) {
            int nextRow = row + monkeyAlpha[dm][0];
            int nextCol = col + monkeyAlpha[dm][1];
            if (isIn(nextRow, nextCol) && !check[nextRow][nextCol]) {
                check[nextRow][nextCol] = true;
                dfs(nextRow, nextCol, depth + 1, horseModeCount);
                check[nextRow][nextCol] = false;
            }
        }

    }

    public static boolean isIn(int row, int col) {
        return row < H && row >= 0 && col < W && col >= 0;
    }
}

package gold.ladderAdjust;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Cordi {
    int row;
    int col;

    public Cordi(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public String toString() {
        return "Cordi{" +
                "row=" + row +
                ", col=" + col +
                '}';
    }
}

public class Main {
    static int N; // 가로선
    static int M; // 세로선

    static int H; // 놓을수 있는 겟수
    static boolean[][] ladder;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        ladder = new boolean[M][N];
        if (H == 0) {
            System.out.println(0);
            return;
        }
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            ladder[a - 1][b - 1] = true;
            ladder[a - 1][b] = true;
        }

        for (int row = 0; row < M; row++) {
            System.out.println();
            for (int col = 0; col < N; col++) {
                System.out.print(ladder[row][col] ? 1 + " " : 0 + " ");
            }
        }

    }

    private static void dfs(boolean[][] state, int row ,int count){
        // 두가로선이 연속하거나 접하면 안된다.. 한줄이 되선 안된다. // 놓는게 H 를 넘어선 안된다
        // 놓고 넘긴다 .
        boolean [][] nextState = new boolean[M][N];
        for(int r = 0; r < M; r ++){ // deep copy
            for(int c = 0; c < N; c++){
                nextState[r][c] = state[r][c];
            }
        }
        for(int col = 0; col < N; col++){
            if(!nextState[row][col]){ // 빈칸이라면 체크하고 사다리 배치
                // 사다리 배치 로직을 어케 하지
                // 선택 하는 경우 안하는 경우가 있다.
                // 연속해서 두개 이상을 배치하는 경우가 있다.
                // 설계를 바꿔야하나.. 사다리 다리만 존제하는 배열로 cur 이 이동한다.
            }
        }
    }

    private static void sim(boolean [][] state) {
        for (int col = 0; col < N; col++) {
            Cordi cur = new Cordi(0, col);
            while (cur.row < M) {
                if (state[cur.row][cur.col]) {
                    if (cur.col + 1 < N && state[cur.row][cur.col + 1]) {// 오늘쪽 에 사다리가 있다면
                        cur.col += 1;
                        cur.row += 1;
                    } else if (cur.col - 1 >= 0 && state[cur.row][cur.col - 1]) {// 왼쪽에 사다리가 있다면
                        cur.col -= 1;
                        cur.row += 1;
                    }
                } else {
                    cur.row += 1;
                }
            }
            System.out.println(cur);
        }
    }
}

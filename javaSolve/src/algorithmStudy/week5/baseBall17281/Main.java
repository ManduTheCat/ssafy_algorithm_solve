package algorithmStudy.week5.baseBall17281;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


class Player {
    int number;
    boolean runState;
    int position;

    public Player(int number, boolean runState, int position) {
        this.number = number;
        this.runState = runState;
        this.position = position;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Player{");
        sb.append("number=").append(number);
        sb.append(", runState=").append(runState);
        sb.append(", position=").append(position);
        sb.append('}');
        return sb.toString();
    }
}

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int N;
    static int[][] innings;
    static int countPer;
    static Player[] battingOrder = new Player[8];
    static int[] playerIndexSet;
    static boolean[] check = new boolean[9];
    static int maxScore = 0;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/study/week5/BJ17281/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        innings = new int[N][9];
        for (int n = 0; n < N; n++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int inn = 0; inn < 9; inn++) {
                innings[n][inn] = Integer.parseInt(st.nextToken());
            }
        }

        permutation(0);
        System.out.println(countPer);
        System.out.println(maxScore);


    }

    // 1번이 사번타자 고정인 permutation
    public static void permutation(int depth) {
        if (depth == 8) {
            countPer++;
            Player[] including4th = new Player[9];
            int idx = -1;
            int i = 0;
            while (++idx < 9) {
                if (idx == 3) including4th[3] = new Player(1, false, 0);
                else including4th[idx] = battingOrder[i++];
            }
            maxScore = Math.max(maxScore,goGame(including4th));
            return;
        }
        for (int i = 1; i < 9; i++) {
            if (!check[i]) {
                check[i] = true;
                battingOrder[depth] = new Player(i + 1, false, 0);
                permutation(depth + 1);
                check[i] = false;
            }
        }
    }

    public static int goGame(Player[] battingOrder) {
        int outCount = 0;
        int currPlayer = 0;
        int score = 0;
        //4가지 경우의수
        // 홈런이면 주자 갯수 +1 점수 추가 , 현재 모든 주자 위치 0; 모든 주자 상태 false
        // m 루타면 타자 위치+1 현재 모든 주자 위치  +1, 위치가 3넘어가면 주자상태false ,점수+
        // out 이면 아무것도 안하고 outcount++;
        return score;
    }
}

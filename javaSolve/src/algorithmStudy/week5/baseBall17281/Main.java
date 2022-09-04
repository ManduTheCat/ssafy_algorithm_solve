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

}

public class Main {
    static int N;
    static int[][] innings;
    static Player[] battingOrder = new Player[8];
    static int maxScore = 0;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/study/week5/BJ17281/input6.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        innings = new int[N][9];
        for (int n = 0; n < N; n++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int inn = 0; inn < 9; inn++) {
                innings[n][inn] = Integer.parseInt(st.nextToken());
            }
        }
        permutation(0, 0);
        System.out.println(maxScore);
    }

    // 1번이 사번타자 고정인 permutation
    public static void permutation(int depth, int flag) {
        if (depth == 8) {
            Player[] including4th = new Player[9];
            int idx = -1;
            int i = 0;
            while (++idx < 9) {
                if (idx == 3) including4th[3] = new Player(1, false, 0);
                else including4th[idx] = battingOrder[i++];
            }
            maxScore = Math.max(maxScore, goGame(including4th));
            return;
        }
        for (int i = 1; i < 9; i++) {
            if ((flag & 1 << i) == 0) {
                battingOrder[depth] = new Player(i + 1, false, 0);
                permutation(depth + 1, flag | 1 << i);
            }
        }
    }

    public static int goGame(Player[] fullBattingOrder) {
        int outCount = 0;
        int currPlayerIdx = 0;
        int score = 0;
        for (int n = 0; n < N; n++) {
            clearRunner(fullBattingOrder);
            outCount = 0;
            while (outCount != 3) {
                int action = innings[n][fullBattingOrder[currPlayerIdx].number - 1];
                //4가지 경우의수
                // 홈런이면 주자 갯수 +1 점수 추가 , 현재 모든 주자 위치 0; 모든 주자 상태 false
                // m 루타면 타자 위치+1 현재 모든 주자 위치  +1, 위치가 3넘어가면 주자상태false ,점수+
                // out 이면 아무것도 안하고 outcount++;
                switch (action) {
                    case 0:
                        outCount++;
                        break;
                    case 4:
                        // 홈런이면 점수 = 현재 주자수 + 1
                        // 초기화 해야한다
                        int runcount = 0;
                        for (Player p : fullBattingOrder) {
                            if (p.runState) {
                                runcount++;
                            }
                        }
                        score += runcount + 1;
                        clearRunner(fullBattingOrder);
                        break;
                    case 1:
                    case 2:
                    case 3:
                        // 안타의 경우
                        // 주자들을 m 만큼 진루한다
                        for (Player p : fullBattingOrder) {
                            if (p.runState) {
                                p.position += action;
                            }
                        }
                        // 안타를 친 선수 도 진루 하고 진루 상태를 true로 바꿔준다.
                        fullBattingOrder[currPlayerIdx].runState = true;
                        fullBattingOrder[currPlayerIdx].position += action;
                        // position 이 3 넘어가면 점수를 1점 올리고 position 을 초기화한다.
                        for (Player p : fullBattingOrder) {
                            if (p.runState && p.position > 3) {
                                p.runState = false;
                                p.position = 0;
                                score += 1;
                            }
                        }
                        break;
                }
                currPlayerIdx = (currPlayerIdx + 1) % 9;
            }
        }
        return score;
    }

    /**
     * 현재 주자 초기화 함수
     *
     * @param inputBattingOrder
     */
    public static void clearRunner(Player[] inputBattingOrder) {
        // 홈런치면 모든 선수 포지션 0된다, 주자 상태도 false가된다.
        // 용도 한이닝이 끝나면, 홈런을 치면
        for (Player p : inputBattingOrder) {
            p.runState = false;
            p.position = 0;
        }
    }
}

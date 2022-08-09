package week5.day2.swea6804;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {

    static int meSocore;
    static int enermyScore;

    static int meWinCount;
    static int enermyWinCount;
    static int[] res;
    static ArrayList<Integer> me;
    static ArrayList<Integer> enmermy;
    static int N;
    static boolean[] isPick;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/week5/day2/swea6804/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int Tc = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= Tc; tc++) {
            int[] cardDeck = new int[19];
            StringTokenizer st = new StringTokenizer(br.readLine());
            me = new ArrayList<>();
            for (int t = 0; t < 9; t++) {
                int idx = Integer.parseInt(st.nextToken());
                me.add(idx);
                cardDeck[idx] = 1;
            }
            System.out.println();
            enmermy = new ArrayList<>();
            for (int i = 1; i < 19; i++) {
                if (cardDeck[i] != 1) {
                    enmermy.add(i);
                }
            }
            isPick = new boolean[19];
            //입력

            // 규영이는 순서대로 제출
            // 라운드마다 규영이가 내는 카드는 정해져있다
            // 라운드는 규영 = 인덱스
            res = new int[9];
            meSocore = 0;
            enermyScore = 0;
            meWinCount = 0;
            enermyWinCount = 0;
            game(0, 0);
            System.out.printf("#%d %d %d",tc, meWinCount, enermyWinCount);
        }
    }

    public static void game(int round, int currIndex) {
        if (round == 9) {
            for (int i = 0; i < 9; i++) {
                if (me.get(i) > res[i]) {
                    meSocore += me.get(i) + res[i];
                } else if (me.get(i) < res[i]) {
                    enermyScore += me.get(i) + res[i];
                }
            }
            if (meSocore > enermyScore) {
                meWinCount++;
            } else if (meSocore < enermyScore) {
                enermyWinCount++;
            }
            meSocore = 0;
            enermyScore = 0;
            return;
        }
        int len = enmermy.size();
        for (int i = 0; i < len; i++) {
            if (isPick[i] == false) {
                isPick[i] = true;
                res[round] = enmermy.get(i);
                game(round + 1, i);
                isPick[i] = false;
            }
        }
    }
}

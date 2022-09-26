package additionalClass.day1.swimpool1952;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    // plan>=0?
    // 모든 경우의 수
    // 변수를 잡을때는 의미 있는 단어를 쓰럯 - 가독성, 소스 해석능력
    static int T;
    static int[] ticket;
    static int[] plan;
    static int A;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("resources/additionalClass/day1/input.txt"));
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(bf.readLine());
        for (int tc = 0; tc < T; tc++) {
            ticket = new int[4];
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for (int ti = 0; ti < 4; ti++) {
                ticket[ti] = Integer.parseInt(st.nextToken());
            }
            plan = new int[13];
            StringTokenizer st1 = new StringTokenizer(bf.readLine());
            for (int i = 1; i < plan.length; i++) {
                int val = Integer.parseInt(st1.nextToken());
                //System.out.println(val);
                plan[i] = val;
            }
            //System.out.println(Arrays.toString(plan));
            //System.out.println(Arrays.toString(ticket));
            A = ticket[3]; // year ticket is max

            check(1, 0);
            System.out.printf("#%d %d\n", tc + 1, A);
        }

    }
    // 바뀌는것 먼저 찾아라 = 문제의 크기 = 달이 바뀐다

    /**
     * 재귀의 파라미들을 final 로 처리해서 부지불식간에 변경하는 것을 막아보자
     *
     * @param month 문제의 크기 --> 기저 조건
     * @param money 가격 구해야할 값
     */
    static void check(final int month, final int money) {
        // 기저조건 13 월이 되면 종료한다
        // basecase
        if (month > 12) {
            // 정산
            A = Math.min(A, money);
            return;
        }
        // inductive case
        if (plan[month] == 0) {
            check(month + 1, money);
        } else {
            // 계획이 있다면
            // 1일궝 사용
            check(month + 1, plan[month] * ticket[0] + money);
            // 1개월권 사용
            check(month + 1, money + ticket[1]);
            // 3개월권 사용
            check(month + 3, money + ticket[2]);
        }

    }
}

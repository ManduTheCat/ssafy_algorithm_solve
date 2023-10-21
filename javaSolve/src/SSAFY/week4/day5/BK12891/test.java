package SSAFY.week4.day5.BK12891;

import java.util.Arrays;
import java.util.Scanner;

public class test {

    static int[] numbers;
    static int N, totalCnt;
    static boolean[] isSelected;

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        //
        Scanner sc = new Scanner(System.in);
        //주사위 갯수
        N = sc.nextInt();
        numbers = new int[N];
        // 던지기 모드
        int mode = sc.nextInt();
        switch (mode) {
            case 1:
                dice1(0); //중복순열
                break;
            case 2:
                isSelected = new boolean[7];
                dice2(0); //순열
                break;
            case 3:
                dice3(0, 1);// 중복조합
                break;
            case 4:
                dice4(0, 1);//조합
                break;


            default:
                break;
        }

        System.out.println("count total: " + totalCnt);
    }

    // 주사위 던지기 중복순열
    private static void dice1(int cnt) {
        //가능한 모든 수 시도
        if (cnt == N) {
            totalCnt++;
            System.out.println(Arrays.toString(numbers));
            return;
        }
        //가능한 수 시도 주사위 1 ~6
        for (int i = 1; i <= 6; i++) {
            //수의 중복선택 가능하기때문에 중복체크 하지 않는다
            //해당수 선택
            numbers[cnt] = i;
            //다음 주사위가기
            dice1(cnt+1);
        }
    }

    //주사위 던지기2 순열
    private static void dice2(int cnt) {
        //가능한 모든 수 시도
        if (cnt == N) {
            totalCnt++;
            System.out.println(Arrays.toString(numbers));
            return;
        }
        //가능한 수 시도 주사위 1 ~6
        for (int i = 1; i <= 6; i++) {
            // 중복 확인 필요
            if (isSelected[i]) continue;
            //수의 중복선택 가능하기때문에 중복체크 하지 않는다
            //해당수 선택
            numbers[cnt] = i;
            isSelected[i] = true;
            //다음 주사위가기
            dice2(cnt++);
            isSelected[i] = false;
        }
    }

    // 주사위 3 : 중복 조합
    private static void dice3(int cnt, int start) {
        //1-1-1~6 같은수를 뽑을수 있다 1-2-1 은 다음에 못뽑는다 다음1-3-1 1-3-2 안된다.
        if (cnt == N) {
            totalCnt++;
            System.out.println(Arrays.toString(numbers));
            return;
        }
        for (int i = start; i <= 6; i++) {
            numbers[cnt] = i;
            //내가뽑은 위치 부터 봐야한다값 1이명 1부터 시작 값2면 2부터 시작값 2에서 1보면 전에 이미 본거다
            //값3이면 3부터 시작해야한다
            dice3(cnt + 1, i);

        }
    }

    //주사위 던지기 4 : 조합
    private static void dice4(int cnt, int start) {
        if (cnt == N) {
            totalCnt++;
            System.out.println(Arrays.toString(numbers));
            return;
        }
        for (int i = start; i <= 6; i++) {
            numbers[cnt] = i;
            dice4(cnt + 1, i + 1);

        }

    }
}


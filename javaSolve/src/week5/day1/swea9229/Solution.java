package week5.day1.swea9229;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


/**
 * @author 김명진
 */
public class Solution {
    
    public static int[] snackList; // 입력받은 과자 무게들
    public static int N;
    public static int M;
    public static int[] res = new int[2]; // 뽑은 결과를 담을 배열 계속 갱신된다
    public static ArrayList<Integer>  maxList; // 결과들의 합을 담아 놓는 리스트
    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("resources/week5/day1/swea9229/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int Tc = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < Tc; tc++) {
            maxList = new ArrayList<>();
            StringTokenizer NMToken = new StringTokenizer(br.readLine());
            N = Integer.parseInt(NMToken.nextToken());
            M = Integer.parseInt(NMToken.nextToken());
            StringTokenizer snackToken = new StringTokenizer(br.readLine());
            snackList = new int[N];
            for (int i = 0; i < N; i++) {
                snackList[i] = Integer.parseInt(snackToken.nextToken());
            }
            combi(0, 0);
            // maxList 가 비어 있다면 적절하게 뽑은 경우가 없다는의미로 -1 넣습니다
            if(maxList.size() == 0){
                maxList.add(-1);
            }
            System.out.printf("#%d ",tc+1);
            // Collections max 내장함수를활용해 최대값구함
            System.out.printf("%d\n",Collections.max(maxList));
        }
    }

    // 조합 재귀 온라인 수업떄 만든 거랑 유사
    //두개 뽑으면 종료
    // res에 뽑은 경우를 담는다
    // 재귀 다니면서 res 의 고정된 인덱스에서 갱신되는 형태
    // maxList 에 초과해서 뽑지 않는경우를 모두 저장한다
    public static void combi(int cnt, int start) {
        if (cnt == 2) {
            if(res[0] + res [1] <=M){
                maxList.add(res[0] + res [1]);
            }
            return;
        }
        for (int i = start; i < N; i++) {
            res[cnt] = snackList[i];
            combi(cnt + 1, i + 1);
        }
    }
}

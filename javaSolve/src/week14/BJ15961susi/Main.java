package week14.BJ15961susi;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, D, K, C;
    static int[] selected;
    static int[] window;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/week14/BJ15961/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());// 접시
        D = Integer.parseInt(st.nextToken());// 종류
        K = Integer.parseInt(st.nextToken());// 연속
        C = Integer.parseInt(st.nextToken());// 보너스
        window = new int[N + K];
        selected = new int[D + 1];
        for (int n = 0; n < N; n++) {
            window[n] = Integer.parseInt(br.readLine());
        }
        // 보너스 미리추가
        int result = 1;
        selected[C]++;
        for (int i = N; i < N + K; i++) {
            window[i] = window[i -N];
            if(selected[window[i]] == 0){
                result+=1;
            }
            selected[window[i]]++;
        }// 초반부분 추가

        // 초기화
        int sum = result;
        for (int i = K; i < N + K; i++) {
            int delNum = window[i-K];
            selected[delNum]--;
            if(selected[delNum] == 0)sum--;
            int addNum = window[i];
            selected[addNum]++;
            if(selected[addNum]== 1)sum++;
            result = Math.max(result, sum);
        }
        System.out.println(result);

    }
}

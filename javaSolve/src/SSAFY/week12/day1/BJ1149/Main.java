package SSAFY.week12.day1.BJ1149;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] RGBset; // 입력겸 dp  역활 dp 함수는 해당 줄의 RGB 을 선택할경우 최소비용
    //RGBset[3][R] 3번째 줄에서 R을 선택 할때 최소비용 각 줄의 최소비용을 구한다
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/SSAFY.week12/day1/BJ1149/input5.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st ;
        RGBset = new int[N][3];

        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < 3; col++) {
                int value = Integer.parseInt(st.nextToken());
                RGBset[row][col] = value;
            }
        }
        for (int i = 1; i < N; i++) {
            // 두번째 줄부터 구한다 선택할수 있는 RGB중 최소를 선택하고 현재 비용을 더해 기록한다
            // 즉 지금 선택한 색 + 이전라인 고를수 있는 색 최소비용중 최소 를 가져오면 최소비용이된다
            RGBset[i][0] = Math.min(RGBset[i-1][1], RGBset[i-1][2]) + RGBset[i][0];
            RGBset[i][1] = Math.min(RGBset[i-1][0], RGBset[i-1][2]) + RGBset[i][1];
            RGBset[i][2] = Math.min(RGBset[i-1][0], RGBset[i-1][1]) + RGBset[i][2];
        }
        int min = Integer.MAX_VALUE;
        for(int lastRow: RGBset[N-1]){
            min = Math.min(lastRow,min);
        }
        System.out.println(min);
    }

}

package week14.day2.sweaGOzero8458;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Solution {
    static int Tc, N;
    static int[] dist;
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/week14/swea8458/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Tc = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < Tc; tc++) {
            N = Integer.parseInt(br.readLine());
            dist = new int[N];
            int sum = 0;
            int count = 0;
            StringTokenizer st  = new StringTokenizer(br.readLine());
            dist[0] = Math.abs(Integer.parseInt(st.nextToken())) +Math.abs(Integer.parseInt(st.nextToken()));
            int max = dist[0];
            for (int row = 1; row < N; row++) {
                st  = new StringTokenizer(br.readLine());
                dist[row] = Math.abs(Integer.parseInt(st.nextToken())) +Math.abs(Integer.parseInt(st.nextToken()));
                // 최장거리만 결과에 영향을 준다
                max = Math.max(dist[row] , max);
                if(dist[row]%2 != dist[row-1]%2) {
                    count=-1;
                }
            }
            // 1둘다 홀수 또는 짝수인가?

            if(count == 0) {
                while(true){ // max와의 차이가 짝수일때 원점 도달 가능
                    boolean isE = true;
                    if(sum < max || (max - sum)% 2 != 0){
                        isE = false;
                    }
                    if(isE)break;
                    sum+= ++count;
                }
            }
            System.out.printf("#%d %d\n", tc + 1, count);


        }

    }
}

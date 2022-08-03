package week4.day3.BJ_11660;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("resources/BJ_11660/input.txt"));
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int [][] dp = new int [N+1][N+1];
        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(bf.readLine());
            for(int j = 1; j <= N; j++){
                dp[i][j] = dp[i][j -1] + Integer.parseInt(st.nextToken());
            }
        }

//        for(int [] d : dp){
//            System.out.println(Arrays.toString(d));
//        }
        StringBuilder sb = new StringBuilder();

        for(int m = 0; m < M; m++){
            st = new StringTokenizer((bf.readLine()));
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            int sum = 0;
            for(int i  = x1; i <= x2; i++){
                //System.out.printf("x1: %d, y1: %d, x2: %d, y2: %d\n", x1, y1, x2, y2);

                sum = sum+ (dp[i][y2]-dp[i][y1-1]);
            }
            sb.append(sum).append("\n");
            //System.out.println(sum);

        }
        System.out.println(sb);
    }
}

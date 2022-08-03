package swexp.fly_2011;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution {

    public static void main(String [] argv)throws IOException {
        String path = Solution.class.getResource("").getPath();
        System.setIn(new FileInputStream(path+"test2.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int count_input = Integer.parseInt(br.readLine());
        for(int tc = 0; tc< count_input; tc++){
            String[] NM = br.readLine().split(" ");
            int N = Integer.parseInt(NM[0]);
            int M = Integer.parseInt(NM[1]);
            int [][] fild = new int[N][N];
            for(int r = 0; r< N; r++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int c = 0; c < N; c++){
                    fild[r][c] = Integer.parseInt(st.nextToken());
                }
            }
            int max = 0;
            for (int i = 0; i < N - M + 1; i++) {
                for (int j = 0; j < N - M + 1; j++) {
                    int tmp = 0;
                    for (int x = 0; x < M; x++) {
                        for (int y = 0; y < M; y++) {
                            tmp += fild[i + x][j + y];
                        }
                    }
                    if (tmp > max) {
                        max = tmp;
                    }
                }
            }
            System.out.printf("#%d %d\n", tc+1, max);

        }
    }
}

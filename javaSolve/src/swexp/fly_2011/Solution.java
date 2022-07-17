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
        System.setIn(new FileInputStream("E:\\JavaProjects\\ssafy_algorithm_solve\\javaSolve\\src\\swexp\\fly_2011\\test1.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int count_input = Integer.parseInt(br.readLine());
        for(int i = 0; i< count_input; i++){
            String[] NM = br.readLine().split(" ");
            int n = Integer.parseInt(NM[0]);
            int m = Integer.parseInt(NM[1]);
            int [][] fild = new int[n][n];
            for(int r = 0; r< n; r++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int c = 0; c < n; c++){
                    fild[r][c] = Integer.parseInt(st.nextToken());
                }
            }
            System.out.println(Arrays.deepToString(fild));
        }



    }
}

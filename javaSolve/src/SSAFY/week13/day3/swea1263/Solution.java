package SSAFY.week13.day3.swea1263;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    static int[][] map;
    static int Tc;
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/SSAFY.week13/day3/swea1263/input2.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        Tc = Integer.parseInt(in.readLine());
        for (int tc = 0; tc < Tc; tc++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int N = Integer.parseInt(st.nextToken());
            map = new int[N][N];

            for (int row = 0; row < N; row++) {
                for (int col = 0; col < N; col++) {
                    map[row][col] = Integer.parseInt(st.nextToken());
                    if (row != col && map[row][col] == 0)
                        map[row][col] = 123456789;
                }
            }
            for (int k = 0; k < N; k++) {
                //경로
                for (int i = 0; i < N; i++) {
                    //출발
                    for (int j = 0; j < N; j++) {
                        //도착
                        if (map[i][j] > map[i][k] + map[k][j])
                            map[i][j] = map[i][k] + map[k][j];
                    }
                }
            }
            //printMap();
            // CC(i) = ∑ j dist(i,j)
            int min = Integer.MAX_VALUE;
            for (int col = 0; col < N; col++) {
                int cc = 0;
                for (int row = 0; row < N; row++) {
                    cc += map[row][col];
                }
                min = Math.min(cc, min);
            }

            System.out.printf("#%d %d\n",tc +1, min);



        }
    }

    private static void printMap() {
        for(int[] row : map){
            System.out.println(Arrays.toString(row));
        }
    }

}

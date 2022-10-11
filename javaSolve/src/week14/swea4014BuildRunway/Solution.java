package week14.swea4014BuildRunway;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Pattern{
    int type, freq;

    public Pattern(int type, int freq) {
        this.type = type;
        this.freq = freq;
    }
}
public class Solution {
    static int Tc, N, M;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/week14/swea4014/input2.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Tc = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < Tc; tc++) {
            StringTokenizer  st = new StringTokenizer(br.readLine());
            N  = Integer.parseInt(st.nextToken());
            M  = Integer.parseInt(st.nextToken()); // 활주로 길이
            map = new int[N][N];
            for (int row = 0; row < N; row++) {
                st = new StringTokenizer(br.readLine());
                for (int col = 0; col < N; col++) {
                    map[row][col] = Integer.parseInt(st.nextToken());
                }
            }
            for(int [] row : map){
                System.out.println(Arrays.toString(row));
            }
            System.out.println();
            ArrayList<Pattern>[] rowPattern = findRowPattern();
        }
                
    }

    private static ArrayList<Pattern>[] findRowPattern() {
        ArrayList<Pattern>[] rowPatternMap = new ArrayList[N];
        for (int n = 0; n < N; n++) {
            rowPatternMap[n] =  new ArrayList<>();
        }
        for (int row = 0; row < N; row++) {
            int pattern = 100;
            for (int col = 0; col < N; col++) {
                if(pattern != map[row][col]) {
                    pattern = map[row][col];
                }

            }
        }
        return null;
    }
}

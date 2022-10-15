package week14.filme2112;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    static int Tc;
    static int D, W, K;
    static int[][] map;
    static int[] permutaionRes;
    static int [][] originMap;
    static int min;
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/swea2112/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        Tc = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < Tc; tc++) {
            min = Integer.MAX_VALUE;
            StringTokenizer st = new StringTokenizer(br.readLine());
            D = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            originMap = new int[D][W];
            map = new int[D][W];

            for (int d = 0; d < D; d++) {
                st = new StringTokenizer(br.readLine());
                for (int w = 0; w < W; w++) {
                    int value = Integer.parseInt(st.nextToken());
                    originMap[d][w] = value;
                    map[d][w] = value;

                }

            }
            // printArr(map);
            permutaionRes = new int [D];
            dfs(0,map, 0);
            System.out.printf("#%d %d\n",tc+1 ,min);
        }
        // D 만큼의 배열중 0,1,3 중 하나를 선택
    }
    private static void dfs(int depth, int[][] map, int isChage) {
        if(depth == D) {
            if(checkLine(map)) {
                min = Math.min(min, isChage);
            }

            return;
        }
        if(isChage == min) return;
        dfs(depth + 1, map,isChage );
        Arrays.fill(map[depth], 0);
        dfs(depth + 1, map, isChage+1);
        map[depth] = originMap[depth].clone();

        Arrays.fill(map[depth], 1);
        dfs(depth + 1, map, isChage+1);
        map[depth] = originMap[depth].clone();


    }
    // 전체 돌면서 맍는지판단
    private static boolean checkLine(int[][] inputMap) {

        for (int col = 0; col < W; col++) {
            int max = 0;
            int count = 1;
            int pattern = inputMap[0][col];
            for (int row = 1; row < D; row++) {
                if(pattern == inputMap[row][col]) {
                    count ++;
                }
                else {
                    count = 1;
                    pattern = inputMap[row][col];
                }
                max = Math.max(count, max);
            }
            if(max < K) return false;

        }
        return true;

    }

    static void printArr(int[][] input) {
        for (int[] row : input) {
            System.out.println(Arrays.toString(row));
        }
    }

}

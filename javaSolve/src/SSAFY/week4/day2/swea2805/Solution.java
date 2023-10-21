package SSAFY.week4.day2.swea2805;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/2805Input/input.txt"));
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int TcCount = Integer.parseInt(bf.readLine());
        for (int tc = 0; tc < TcCount; tc++) {
            int n = Integer.parseInt(bf.readLine());
            int[][] map = new int[n][n];
            for (int i = 0; i < n; i++) {
                map[i] = Arrays.stream(bf.readLine().split("")).mapToInt(Integer::parseInt).toArray();
            }
            System.out.printf("#%d %d\n",tc+1, getSum(map));
        }
    }

    /**
     * 증가 함수 감소함수 호출후 합 리턴
     * @param map
     * @return
     */
    public static int getSum(int[][] map) {
        int sum = 0;
        int halfLine = map.length / 2;
        sum += increase(map, halfLine);
        sum +=decrease(map, halfLine);
        return sum;
    }

    /**
     * 점점 증가하는 범위로 삼각형 조회
     * @param map
     * @param halfLine
     * @return
     */
    public static int increase(int[][] map, int halfLine) {
        int sum = 0;
        for (int i = 0; i <= halfLine; i++) {
            sum += map[i][halfLine];
            for (int add = 1; add <= i; add++) {
                sum += map[i][halfLine + add];
                sum += map[i][halfLine - add];

            }
        }

        return sum;

    }

    /**
     * 점점 감소하는 범위로 삼각형 조회
     * @param map
     * @param halfLine
     * @return
     */
    public static int decrease(int[][] map, int halfLine) {
        int sum = 0;
        //System.out.println();
        for (int i = halfLine+1; i < map.length; i++) {
            
            sum += map[i][halfLine];
            for(int add = map[i].length -i-1;  add >= 1; add--){
                sum += map[i][halfLine + add];
                sum += map[i][halfLine - add];
            }
        }
        return sum;
    }
}

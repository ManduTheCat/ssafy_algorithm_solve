package week4.day2.swea1954;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/swea1954/input1.txt"));
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int inputCount = Integer.parseInt(bf.readLine());
        for (int tc = 0; tc < inputCount; tc++) {
            int n = Integer.parseInt(bf.readLine());
            boolean [][] check = new boolean[n][n];
            int[][] map = new int[n][n];

            //move(map, 1);
        }


    }

    public static void move(int[][] map, int startNum, boolean[][]check) {
        petteernJUP(map, 0,0, startNum,check);
    }

    public static void patteernIDown(int[][] map,int i, int j, int startNum) {

    }

    public static void petteernJUP(int[][] map, int i, int j, int startNum, boolean[][]check) {
        int len = map.length;
        for (int dj = j; dj < len; dj++) {
            map[i][dj] = ++startNum;
            if (j+1 > 0 ) {
                System.out.println("touched");
                patteernIDown(map, i,j, map[i][j]);
            }
        }
    }

}

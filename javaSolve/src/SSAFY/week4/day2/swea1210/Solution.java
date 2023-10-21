package SSAFY.week4.day2.swea1210;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {
    public static final int n = 100;

    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("resources/swea1210/input.txt"));
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int[][] map = new int[n][n];
        for(int count = 0; count < 10; count++){

            int TCnum = Integer.parseInt(bf.readLine());
            for (int i = 0; i < n; i++) {
                map[i] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }
            System.out.printf("#%d ", TCnum);
            for (int i = 0; i < n; i++) {
                if (map[0][i] == 1) {
                    boolean[][] check = new boolean[n][n];
                    move(map, 0, i, check);
                }
            }
        }
    }

    /**
     * 사다리 이동하면서 목적지에 닿으면 리턴하는 함수
     * @param map 사다리 지도
     * @param startR 입력받은 출발 row
     * @param startC 입력받은 출발 column
     * @param check 방문 체크 함수
     */
    public static void move(int[][] map, int startR, int startC, boolean[][] check) {
        int[] dx = new int[]{-1, 1, 0};
        int[] dy = new int[]{0, 0, 1};
        int resR = startR;
        int resC = startC;

        // 출발 좌표에서 이동
        while (startR <= 99) {
            for (int di = 0; di < 3; di++) {
                if (checkBound(startR + dy[di], startC + dx[di])) {
                    if ((map[startR + dy[di]][startC + dx[di]] == 1 || map[startR + dy[di]][startC + dx[di]] == 2) && !check[startR + dy[di]][startC + dx[di]]) {
                        check[startR + dy[di]][startC + dx[di]] = true;

                        startR += dy[di];
                        startC += dx[di];
                        break;
                    }
                }
            }
            if (map[startR][startC] == 2) {
                System.out.println(resC);
                return;
            }
            if (startR >= n - 1) {
                return;
            }

        }
    }

    /**
     * 이동하면서 주어진 지도를 넘어가는치 확인하는 함수
     * @param i 이동할 row
     * @param j 이동할 column
     * @return 벗어나면 false 맞으면 true
     */
    public static boolean checkBound(int i, int j) {
        return i < n && i >= 0 && j < n && j >= 0;
    }
}

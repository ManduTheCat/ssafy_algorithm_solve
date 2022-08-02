package swea1210;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {
    public static final int n = 100;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/swea1210/input.txt"));
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int[][] map = new int[n][n];
        for(int count = 0; count < 10; count++){

            int TCnum = Integer.parseInt(bf.readLine());
            for (int i = 0; i < n; i++) {
                map[i] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                //for (int j = 0; j < n; j++) {
                //if(map[i][j] == 1){
                //}
                //}
            }
            System.out.printf("#%d ", TCnum);
            for (int i = 0; i < n; i++) {
                if (map[0][i] == 1) {
                    //System.out.printf(" 들어가는 위치  0, %d\n", i);
                    boolean[][] check = new boolean[n][n];
                    move(map, 0, i, check);
                }
            }
        }
        //System.out.println(Arrays.deepToString(check));
    }

    public static void move(int[][] map, int startR, int startC, boolean[][] check) {
        int[] dx = new int[]{-1, 1, 0};
        int[] dy = new int[]{0, 0, 1};
        int resR = startR;
        int resC = startC;

        // 출발 좌표에서 이동
        while (startR <= 99) {
            for (int di = 0; di < 3; di++) {
                //System.out.printf("%d,%d\n",dx[di], dy[di]);
                if (checkBound(startR + dy[di], startC + dx[di])) {
                    //System.out.println(di);
                    // System.out.println(map[startR + dy[di]][startC + dx[di]]);
                    //System.out.println(check[startR + dy[di]][startC + dx[di]] == true);
                    //System.out.println(map[startR + dy[di]][startC + dx[di]] == 1);
                    //System.out.printf("map %d, %d, %d\n", startR + dy[di],startC + dx[di], map[startR + dy[di]][startC + dx[di]]);
                    if ((map[startR + dy[di]][startC + dx[di]] == 1 || map[startR + dy[di]][startC + dx[di]] == 2) && !check[startR + dy[di]][startC + dx[di]]) {
                        //System.out.println("in");
                        check[startR + dy[di]][startC + dx[di]] = true;

                        startR += dy[di];
                        startC += dx[di];
                        break;
                    }
                }
            }
            //System.out.printf("%d, %d\n",startR,startC );
            if (map[startR][startC] == 2) {
                //System.out.printf("정답이다!!!! %d, %d\n",resR, resC);
                System.out.println(resC);
                return;
            }
            if (startR >= n - 1) {
                return;
            }

        }
        // 이동중 범위 넘는지 체크
    }

    public static boolean checkBound(int i, int j) {
        if ((i >= n || i < 0 || j >= n || j < 0)) {
            //System.out.printf("%d, %d\n", i, j);
            //System.out.println("in");
            return false;
        }
        return true;
    }
}

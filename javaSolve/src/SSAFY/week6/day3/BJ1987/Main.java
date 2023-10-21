package SSAFY.week6.day3.BJ1987;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author krocd
 * bfs 푼다 다음 알파뱃 - 64   (A = 65)
 * 알파벳 이 비트마스크 에 들어 가 있다면 가지 않는다 && 벽넘으면 가지 않는다.
 */
public class Main {
    static int R;
    static int C;
    static char[][] map;
    static boolean[][] check;
    static int max = 0;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/SSAFY.week6/day3/BJ1987/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        check = new boolean[R][C];
        for (int r = 0; r < R; r++) {
            String row = br.readLine();
            for (int c = 0; c < C; c++) {
                map[r][c] = row.charAt(c);
            }
        }
        dfs(0, 0, 0, 0);
        System.out.println(max);


    }

    public static void dfs(int i, int j, int countLen, int flag) {
        max = Math.max(countLen, max);
        int[][] direction = new int[][]{{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
        if (isIn(i, j) && (flag & (1 << (map[i][j] - 64))) == 0 && !check[i][j]) {
            check[i][j] = true;
            for (int[] dir : direction) {
                dfs(i + dir[1], j + dir[0], countLen + 1, flag | 1 << (map[i][j] - 64));
            }
            //System.out.println(checkSet);
            check[i][j] = false;
        }
    }

    public static boolean isIn(int i, int j) {
        return i >= 0 && i < R && j >= 0 && j < C;

    }

}

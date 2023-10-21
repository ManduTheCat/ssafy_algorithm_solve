package SSAFY.week6.day3.BJ3109;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int R;
    static int C;
    static char[][] map;
    private static int count;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/SSAFY.week6/day3/BJ3109/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        for (int r = 0; r < R; r++) {
            String row = br.readLine();
            for (int c = 0; c < C; c++) {
                map[r][c] = row.charAt(c);
            }
        }
        for (int i = 0; i < R; i++) {
            if (goBakery(i, 0)) count++;
        }
        System.out.println(count);

    }

    //3방향으로 탐사
    public static boolean goBakery(int i, int j) {
        map[i][j] = '-';
        if (j == C - 1) {
            //map[i][j] = '-';
            return true;
        }
        //계속올라간다
        if (isIn(i - 1, j + 1) && map[i - 1][j + 1] == '.')
            // 오위
            if (goBakery(i - 1, j + 1)) return true;
        // 오
        if (isIn(i, j + 1) && map[i][j + 1] == '.')
            if (goBakery(i, j + 1)) return true;
        // 오아래
        if (isIn(i + 1, j + 1) && map[i + 1][j + 1] == '.')
            if (goBakery(i + 1, j + 1)) return true;
        //map[i][j] = '.';
        return false;

    }

    public static boolean isIn(int i, int j) {
        return i >= 0 && i < R && j >= 0 && j < C;
    }
}

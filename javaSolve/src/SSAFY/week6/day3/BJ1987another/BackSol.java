package SSAFY.week6.day3.BJ1987another;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * !!!!!!!!!!!!!!!!!!!!! 해결안됨!!!!!!!!!!!!!!!!!!
 * @author krocd
 * bfs 푼다 다음 알파뱃 - 64   (A = 65)
 * 알파벳 이 비트마스크 에 들어 가 있다면 가지 않는다 && 벽넘으면 가지 않는다.
 */
public class BackSol {
    static int R;
    static int C;
    static char[][] map;
    static int[][] visited;
    static boolean[] check;
    static int max = 0;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/SSAFY.week6/day3/BJ1987/input2.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        check = new boolean[26];
        for (int r = 0; r < R; r++) {
            String row = br.readLine();
            for (int c = 0; c < C; c++) {
                map[r][c] = row.charAt(c);
            }
        }
        visited = new int[R][C];
        dfs(0, 0,  0,1 << map[0][0] - 'A');
        System.out.println(max);
    }

    public static void dfs(int i, int j, int countLen, int flag) {
        //System.out.println(map[i][j]);
        if (visited[i][j] == flag) return;
        visited[i][j] = flag;
        //System.out.println(Integer.toBinaryString(flag));
        max = Math.max(countLen, max);
        int[][] direction = new int[][]{{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
        for (int[] dir : direction) {
            int nextI = i + dir[1];
            int nextJ = j + dir[0];
            if (isIn(nextI, nextJ) && (flag & 1 <<map[nextI][nextJ] - 'A') == 0) {
                dfs(nextI, nextJ, countLen + 1, (flag | 1 << (map[nextI][nextI] - 'A')));
            }
        }
    }

    public static boolean isIn(int i, int j) {
        return i >= 0 && i < R && j >= 0 && j < C;
    }
}

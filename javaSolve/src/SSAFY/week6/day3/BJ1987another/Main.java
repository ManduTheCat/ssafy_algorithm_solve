package SSAFY.week6.day3.BJ1987another;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author krocd
 * bfs 푼다 다음 알파뱃이 map 에 들어 가 있다면 가지 않는다
 */
public class Main {
    static int R;
    static int C;
    static char[][] map;
    static boolean[][] check;
    static int max = 0;
    static Set<Character> checkSet = new HashSet<>();

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/SSAFY.week6/day3/BJ1987/input2.txt"));
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
        //처음건 저장
        //printMap();
        dfs(0, 0, 0);
        System.out.println(max);


    }

    public static void dfs(int i, int j, int countLen) {
        //printCheck();
        max = Math.max(countLen, max);
        int[][] direction = new int[][]{{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
        if (isIn(i, j) && !isDuplicate(map[i][j]) && !check[i][j]) {
            //System.out.println(map[i][j]);
            check[i][j] = true;
            for (int[] dir : direction) {
                dfs(i + dir[1], j + dir[0], countLen + 1);
            }
            //System.out.println(checkSet);
            checkSet.remove(map[i][j]);
            check[i][j] = false;
        }
    }

    // 중복 체크 중복 아니면 true, 중복 이면 false
    public static boolean isDuplicate(char input) {
        if (checkSet.contains(input)) {
            return true;
        }
        // 없으면 map 에 넣는다
        checkSet.add(input);
        return false;
    }

    public static boolean isIn(int i, int j) {
        return i >= 0 && i < R && j >= 0 && j < C;

    }

    public static void printMap() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }

    public static void printCheck() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (check[i][j] == true) {
                    System.out.print(1);
                } else {
                    System.out.print(0);
                }
            }
            System.out.println();
        }
    }
}

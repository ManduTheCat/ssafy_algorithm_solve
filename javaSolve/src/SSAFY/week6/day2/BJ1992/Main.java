package SSAFY.week6.day2.BJ1992;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int n;
    static int[][] map;
    static int maxDepth;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/SSAFY.week6/day2/BJ1992/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        for (int r = 0; r < n; r++) {
            String st = br.readLine();
            for (int c = 0; c < n; c++) {
                map[r][c] = st.charAt(c) - '0';
            }
        }
        maxDepth = (int) (Math.log(n) / Math.log(2));
        makeQuad(0, 0, n, 0);
        System.out.println(sb);
    }

    public static void makeQuad(int i, int j, int size, int depth) {
        if (depth == maxDepth) {
            if (map[i][j] == 1) sb.append(1);
            else sb.append(0);
            return;
        }
        // 꽉차있지 않다면 쪼개라
        if (!isFull(i, j, size)) {
            sb.append('(');
            // 위왼
            makeQuad(i, j, size / 2, depth + 1);
            // 위오
            makeQuad(i, j + size / 2, size / 2, depth + 1);
            // 아래 왼
            makeQuad(i + size / 2, j, size / 2, depth + 1);
            // 아래 오
            makeQuad(i + size / 2, j + size / 2, size / 2, depth + 1);
            sb.append(')');
        }
        // 꽉차 있다면
        else {
            // 1로꽉차면 1
            if (map[i][j] == 1) sb.append(1);
                // 0으로 꽉차면 0
            else sb.append(0);
        }
    }

    public static boolean isFull(int i, int j, int size) {
        int oneCount = 0;
        int zeroCount = 0;
        for (int row = i; row < i + size; row++) {
            for (int col = j; col < j + size; col++) {
                if (map[row][col] == 1) oneCount++;
                else zeroCount++;
            }
        }
        return zeroCount == size * size || oneCount == size * size;
    }

    public static void printMap() {
        for (int[] m : map) {
            for (int e : m) {
                System.out.print(e);
            }
            System.out.println();
        }

    }
}

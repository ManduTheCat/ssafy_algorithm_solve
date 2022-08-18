package week6.day3.BJ1987;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * @author krocd
 * bfs 푼다 다음 알파뱃이 map 에 들어 가 있다면 가지 않는다
 */
public class Main {
    static int R;
    static int C;
    static char[][] map;
    static Map<Character, Integer> checkMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/week6/day3/BJ1987/input1.txt"));
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
        //처음건 저장
        checkMap.put(map[0][0], 0);
        printMap();
        dfs(0,0,1);

    }

    public static void dfs(int i, int j, int countLen) {
        System.out.println(countLen);

        // 왼위오아
        int[][] direction = new int[][]{{-1, 0}, {0, -1}, {1, 0}, {0, -1}};
        for (int[] dir : direction) {
            int nextI = i + dir[1];
            int nextJ = j + dir[0];
            // 다음 범위가 범위 안이라면 && 다음게 중복이 아니라면
            if (isIn(nextI, nextJ) && !isDuplicate(map[nextI][nextJ])) {
                System.out.println(checkMap);
                dfs(nextI, nextJ, countLen + 1);
            }
        }
    }

    // 중복 체크 중복 아니면 true, 중복 이면 false
    public static boolean isDuplicate(char input) {
        if(checkMap.containsKey(input)){
            return true;
        }
        // 없으면 map 에 넣는다
        checkMap.put(input, 0);
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
}

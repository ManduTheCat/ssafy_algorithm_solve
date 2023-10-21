package SSAFY.week13.day3.BJ2636;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int cheeseCnt;

    static int[][] del = {{-1, 0}, {0, 1}, {0, -1}, {1, 0}};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/SSAFY.week13/day3/bj2636/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
//        for(int [] row : map){
//            System.out.println(Arrays.toString(row));
//        }
        visited = new boolean[N][M];
        // 치즈인지 확인하고 돌린다
        int ans;
        for (ans = 0; isCheese(); ans++) {
            for (boolean[] arr : visited) {
                Arrays.fill(arr, false);
            }
            visited[0][0] = true;
            cheeseCnt = 0;

            dfs(0, 0);
        }

        System.out.println(ans);
        System.out.println(cheeseCnt);
    }

    // 판 위에 치즈가 존재하는지 검사
    public static boolean isCheese() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 2) {
                    map[i][j] = 0;
                }
            }
        }

        // 판 위에 치즈가 존재하는지 체크.
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1) {
                    return true;
                }
            }
        }

        return false;
    }

    //  공기와 맞닿은 치즈를 찾음.
    public static void dfs(int r, int c) {
        for (int i = 0; i < 4; i++) {
            int nr = r + del[i][0];
            int nc = c + del[i][1];
            if (isIn(nr, nc) && !visited[nr][nc]) {
                visited[nr][nc] = true;
                if (map[nr][nc] == 1) {
                    map[nr][nc] = 2;
                    cheeseCnt++; // 다음에 지워질 치즈의 개수
                } else {
                    dfs(nr, nc);
                }
            }
        }
    }

    private static boolean isIn(int nr, int nc) {
        return nr >= 0 && nr < N && nc >= 0 && nc < M;
    }


}
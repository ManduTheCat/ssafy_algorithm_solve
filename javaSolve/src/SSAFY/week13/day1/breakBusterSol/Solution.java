package SSAFY.week13.day1.breakBusterSol;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * swexpert.SW_5656_벽돌깨기
 * author djunnni
 */
public class Solution {
    static int N,W,H, answer;
    static int dr[] = { -1, 1, 0, 0 };
    static int dc[] = { 0, 0, -1, 1 };
    public static void main(String[] args) throws IOException {
        /**
         * 1. 구슬을 던진다(투하) => 같은 곳에 떨어트릴 수 있다 (중복순열)
         * 2. 구슬에 맞는 벽돌 처리(연쇄적) => 부서지는 벽돌 모두처리
         * => 완전탐색으로 모두 방문하기 (DFS, BFS => 시간차이 없다고 생각)
         * 3. 부서진 벽돌 제거. 빈공간 없이 벽돌 내리기(중력처리)
         */
        System.setIn(new FileInputStream("resources/SSAFY.week13/day1/breakBreak/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int TC = Integer.parseInt(br.readLine());

        for(int tc=1; tc<= TC;tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            int[][] map = new int[H][W];

            for(int i = 0; i < H; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for(int j = 0; j < W; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            answer = Integer.MAX_VALUE;
            go(map,0);

            System.out.println("#" + tc + " " + answer);
        }
    }
    // 구슬 던지기 게임
    static void go(int[][] map, int cnt) {
        if(cnt == N) {
            int sum = 0;
            for(int i = 0; i < H; i++) {
                for(int j = 0; j < W; j++) {
                   if(map[i][j] > 0) sum++;
                }
            }
            answer = Math.min(answer, sum);
            return;
        }
        // 구슬 던지기(중복 순열)
        int[][] newMap = null;
        for(int c = 0; c < W; c++) {

            // 구슬에 맞는 시작벽돌 찾기
            int r = 0;
            while(r < H && map[r][c] == 0) ++r;
            if(r == H) { // 맞는 시작벽돌이 없는 상태
                go(map, cnt + 1);
            } else { // 맞는 시작벽돌이 있는 상태
                newMap = copy(map); // 이전결정이 현재결정에 영향을 주지 않기 위해 [**]
                // 제거될 벽돌 연쇄 처리
                boom(newMap, r, c);
                // 벽돌 중력 처리
                down(newMap);
                // 다음 구슬로 가기
                go(newMap, cnt + 1);
            }
        }
    }
    static void down(int[][] map) {
        for(int c = 0; c < W; c++) {
            int r = H - 1;
            while(r > 0) {
                if(map[r][c] == 0) {
                    int nr = r - 1;
                    while(nr > 0 && map[nr][c] == 0) nr--;
                    map[r][c] = map[nr][c];
                    map[nr][c] = 0;
                }
                r--;
            }
        }
    }
    static void boom(int[][] map, int r, int c) {
        //BFS
        Queue<Point> queue = new ArrayDeque<Point>();
        if (map[r][c] > 1)
            queue.offer(new Point(r, c, map[r][c])); // map[r][c]>=2 일경우, 주변에 영향을 미친다.
        map[r][c] = 0; // 방문처리 => 제거처리
        // 방문처리를 해도 되고 0으로 바꿔버려도 된다.
        while (!queue.isEmpty()) {
            Point p = queue.poll(); // 주변에 영향을 주는 벽돌 정보

            // 벽돌의 크기 -1 만큼 연쇄(4방) 벽돌 처리
            for (int i = 0; i < dr.length; i++) {
                int nr = p.r;
                int nc = p.c;

                for (int k = 1; k < p.cnt; k++) {
                    nr += dr[i];
                    nc += dc[i];
                    if (nr >= 0 && nr < H && nc >= 0 && nc < W && map[nr][nc] > 0) {
                        if (map[nr][nc] > 1)
                            queue.offer(new Point(nr, nc, map[nr][nc]));
                        map[nr][nc] = 0; // 방문처리 => 제거처리
                    }
                }
            }
        }
    }
    static int[][] copy(int [][] map) {
        int temp[][] = new int[H][W];

        for(int i = 0; i < H; i++) {
            for(int j = 0; j < W; j++) {
                temp[i][j] = map[i][j];
            }
        }

        return temp;
    }
    static class Point {
        int r, c, cnt;
        Point(int r, int c, int cnt) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }
    }
}

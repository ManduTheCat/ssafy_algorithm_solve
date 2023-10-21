package SSAFY.week13.day1.breakbusterBFS1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution {
    //1. 구슬을 던진다 (투하)
    // 맨위에서 낙하  w열 만큼 중복 위치에 떨굴수 있다.
    // 순서에 따라 영향이 있다. 중복 + 순열 wPIN = W^N = 12 ^ 4
    //2. 구슬에 맞는 벽돌처리 (연쇠 처리) => 부서지는 벽돌 모두처리
    //3. 부서진 벽돌 제거하고 빈공간 없이 벽돌 내리기(중력처리)
    //남은 벽돌의 최소를 출력하면된다.
    // 열 우선 탐색
    static int N, W, H, min;
    static int[][] de = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/SSAFY.week13/day1/breakBreak/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int Tc = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < Tc; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            int[][] map = new int[H][W];
            for (int row = 0; row < H; row++) {
                st = new StringTokenizer(br.readLine());
                for (int col = 0; col < W; col++) {
                    map[row][col] = Integer.parseInt(st.nextToken());
                }
            }
            min = Integer.MAX_VALUE;
            go(map, 0);
            System.out.printf("#%d %d\n", tc + 1, min);
        }

    }

    // 구슬던지기 게임
    // cnt how many throw ball
    // 각경우수마다 맵은 계속 전달해줘야한다. 그리고 w-1 선택이 w 선택에 영향주면안된다.
    // 상태가 독립적이여야된다
    static boolean go(int[][] map, int cnt) { // map 은 직전 구슬까지 던진 상태의 배열
        int result = getRemain(map);
        if(result == 0) {
            min = result;
            return true;
        }// 남아 있는게 0 개면 돌아가게 만든다.
        // 구슬 던지기(중복순열)
        if (cnt == N) {
            // 남은 벽돌수  카운트 최소값 갱신
            //System.out.println(result);
            if (min > result) min = result;
            return false;
        }
        int[][] newMap = new int[H][W];
        for (int c = 0; c < W; c++) {
            // 구슬이 떨어지면 처음맞는 벽돌을 찾아야한다.
            int r = 0;
            // 범위가 벗어날수도 있다.+ 빈칸에 떨굴수 도 있다.
            while (r < H && map[r][c] == 0) ++r;
            if (r == H) {
                // 맞는 시작 벽돌이 없는 상태
                continue;
            } else {
                // 시작 벽돌 이 있는 상태
                // map 은 직전상태
                // 구슬 하나 던질때마다 벽돌 연쇄처리
                copy(map, newMap);
                // 배열 복사해서 쓴다
                boom(newMap, r, c);
                // 벽돌 중력 처리
                down(newMap);
                // 다음 구슬 던지기
                if(go(newMap, cnt + 1)) return true;
            }
        }
        return false;
    }

    private static int getRemain(int[][] map) {
        int count = 0;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (map[i][j] > 0) {
                    count++;
                }
            }
        }
        return count;
    }

    private static void down1(int[][] map) {
        for (int c = 0; c < W; c++) {
            int r = H - 1; // 아래부터
            while (r > 0) {
                if (map[r][c] == 0) {
                    // 빈칸이면 내릴 벽돌 찾는다.
                    int nr = r - 1; // 현재 행 위부터
                    while (nr > 0 && map[nr][c] == 0) nr--;// 빈칸이면 께속 올라간다

                    map[r][c] = map[nr][c]; // 벽돌 끌고 내려온다.
                    map[nr][c] = 0; //끌어내린 벽돌 0 처리
                }
                //벽돌이면 올라가야한다.
                --r;
            }
        }
    }

    // 실수 방지를 위햔 down 자료구조 활용
    static Stack<Integer> stack = new Stack<>();

    private static void down(int[][] map) {
        for (int c = 0; c < W; c++) {
            // 남은 벽돌 스택에 넣기
            for (int r = 0; r < H; r++) {
                if (map[r][c] > 0) {
                    stack.push(map[r][c]);
                    map[r][c] = 0;
                }
            }
            // 여기까지 모양 남은 벽돌은 스텍에 들어가 있고 모든 칸은 빈칸 상태;
            int nr = H-1;
            while (!stack.empty()){
                map[nr--][c] = stack.pop();
            }
        }
    }

    // 0 아닌 위치랎이 온다 1이상 위치값 벽돌 1: 주변에 영향을 안준다
    // 2부터 영향을 주기 때문에 2부터 넣어도된다.
    private static void boom(int[][] map, int r, int c) {
        Queue<Point> q = new ArrayDeque<>();
        // 방문관리 vs 벽돌있던자리 0으로 바꾼다: 빈칸으로 만드어서 방문처리
        if (map[r][c] > 1) {
            // map을 비짓으로도 쓰기 떄문에 원래 벽돌의 크기를 모르기 때문에 q 에 넣는다.
            q.offer(new Point(r, c, map[r][c]));
        }
        map[r][c] = 0;
        while (!q.isEmpty()) {
            Point currPoint = q.poll(); // 항상 주변에 영향주는 벽돌 정보;
            // 벽돌의 크기 -1 만큼 주변 벽돌 연쇄 처리
            for (int d = 0; d < 4; d++) {
                // 방향 고정하고;
                int nr = currPoint.r;
                int nc = currPoint.c;
                for (int i = 1; i < currPoint.count; i++) { //cnt-1 까지 벽돌본다
                    nr += de[d][0];
                    nc += de[d][1];
                    // q에 들어가는 행위는 초기화과정과 같다
                    if (nr >= 0 && nr < H && nc >= 0 && nc < W) {
                        if (map[nr][nc] > 1) {
                            q.offer(new Point(nr, nc, map[nr][nc])); // 영향을 주는것만 넣는다
                        }
                        map[nr][nc] = 0; // 넣든 않넣든 방문처리 == 제거처리
                    }
                }
            }
        }

    }

    private static void copy(int[][] map, int[][] newMap) {
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                newMap[i][j] = map[i][j];
            }
        }
    }

}

class Point {
    int r;
    int c;
    int count;

    public Point(int r, int c, int count) {
        this.r = r;
        this.c = c;
        this.count = count;
    }
}

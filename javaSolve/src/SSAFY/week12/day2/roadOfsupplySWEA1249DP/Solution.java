package SSAFY.week12.day2.roadOfsupplySWEA1249DP;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    // 정점은 100 제곱
    // 간선은 4개
    // bfs O(VE)
    // 간선의 가중치는 파혀진깊이
    // bfs 는 도착점 뿐만 아니라 어떤 점이든 먼저도착하면 그게 최단이다
    // 하지만 만약 가중치가 있는상태에서 t/f 방문체크를 진행하여
    // 뒤에 오는 친구를 못오게 만든는 상황을 만들게 되면
    // 뒤에 오는 경우에서 가중치가 가장 낮은 경우가 존재할수 있기 때문에 최단 최소를 찾을수 없다
    // 그렇닿고 방문체크를 안하면 재방문 때문에 무한 루프를 돈다.
    // 우리는 이렇게 구현해야한다
    // 먼저온친구가 있지만 가중치가 낮으면 이친구가 최단거리다라고 체킹해야한다.
    // 칸마다 그칸으로 올수 있는 최소비용을 기록해야한다.
    // 너비우선이 아니라 비용우선이기에 pq 를 사용해 먼저도착한 친구를 구하는 방식으로도 가능하다.
    // pq 를 쓰면 너비가 2지만 비용이 5 너비가 3이지만 비용이3 이면 너비가 3이지만 비용이3친구가 먼저온다.
    // 실질적으로 비용 유리한놈으로만 탐색한다.
    // 그리고 양의 가중치니 다익스트라이다..
    // 문제 입력이 붙어 있다 가중치는 2자리가 되지 않는다 9 이하이다
    // 100 재곱  * 9 다더하면 인트 사용가능
    // 희소 그래프 간선이 적다 희소에서 다익스트라는 pq 쓰는게 유리하다 간선만큼 pq 에들어가기 때문에 공간복잡도 때문에 희소에서 pq 가 유리
    static int N;
    static int Tc;
    static int[][] minCost;
    static int[][] map;
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/SSAFY.week12/day2/swea1249/input2.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Tc = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < Tc; tc++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            minCost = new int[N][N];
            for (int row = 0; row < N; row++) {
                String rowInput = br.readLine();
                for (int col = 0; col < N; col++) {
                    map[row][col] = rowInput.charAt(col) - '0';
                    minCost[row][col] = Integer.MAX_VALUE;
                }
            }
        }
        int INF = Integer.MAX_VALUE;
        int minTime = 0;
        int r = 0;
        int c = 0;
        int nr, nc;
        int[][] de = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
        visited = new boolean[N][N];
        minCost[0][0] = 0; // minCost
        while (true) {
            // step1 미방문 정점중 최소비용 정점찾기
            r = -1;
            c = -1;
            minTime = INF;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j] && minTime > minCost[i][j]) {
                        minTime= minCost[i][j];
                        r = i;
                        c = j;
                    }
                }
            }
            // r c -1 일 경우는 더이상 갈수 있는 정점이 없다.
            if (r == -1) {
                System.out.println(-1);
                System.exit(0);
            }
            ;
            visited[r][c] = true;
            if (r == N - 1 && c == N - 1) {
                System.out.println(minTime);
                System.exit(0);
            }
            // step2  현재 정점 기준으로 인접한 정점들을 들여다보면 경유비용이 유리한지 계산
            for (int d = 0; d < 4; d++) {
                // 인접은 4방이 기에 4방 탐색 ㅋ드
                nr = r + de[d][0];
                nc = r + de[d][1];
                if (isIn(nr, nc) && !visited[nr][nc] && minCost[nr][nc] > minTime + map[nr][nc]) {
                    minCost[nr][nc] = minTime + map[nr][nc];
                }


            }
        }

    }

    public static boolean isIn(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
}

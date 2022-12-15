package dailySolve.DFS.BJ1245sec;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Cordi {
    int r;
    int c;

    public Cordi(int r, int c) {
        this.r = r;
        this.c = c;
    }


    @Override
    public String toString() {
        return "Cordi{" +
                "r=" + r +
                ", c=" + c +
                '}';
    }
}

public class Main {
    static int[][] dArr = {{0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}};
    static int N;
    static int M;
    static int[][] map;
    static boolean[][] check;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/daily/bfs/BJ1245/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        check = new boolean[N][M];
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < M; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());
            }
        }
        int count = 0;
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                if (!check[row][col] && map[row][col] != 0) {
                    // 이전에 방문 했던 봉우리면 다시 가지 않는다. 0 은 봉우리가 아니다
                    boolean result = bfs(new Cordi(row, col));
                    if (result) {
                        count++;
                    }
                }
            }
        }
        System.out.print(count);

    }

    private static boolean bfs(Cordi startCordi) {
        Queue<Cordi> q = new ArrayDeque<>();
        q.offer(startCordi);
        if(!isTop(startCordi.r, startCordi.c)){// 처음 부분 확인
            return false;
        }
        boolean[][] bfsCheck = new boolean[N][M];
        bfsCheck[startCordi.r][startCordi.c] = true;
        while (!q.isEmpty()) {
            Cordi curr = q.poll();
            for (int d = 0; d < 8; d++) {
                int nextR = curr.r + dArr[d][0];
                int nextC = curr.c + dArr[d][1];
                // 자기랑 같은 친구들만 방문한내
                if (isIn(nextR, nextC) && !bfsCheck[nextR][nextC] && map[curr.r][curr.c] == map[nextR][nextC]) {
                    if (isTop(nextR, nextC)) {
                        // 8방 탐색 해서 자기보다 주변이 작거나 같을때만 넣는다. 나보다 높을땐 false
                        bfsCheck[nextR][nextC] = true;
                        q.offer(new Cordi(nextR, nextC));
                        check[nextR][nextC] = true;
                    } else return false;

                }
            }
        }
        return true;
    }

    private static boolean isTop(int curR, int curC) {
        // 8 방탐색 진행해서 자기보다 큰값 존재하면 false
        for (int d = 0; d < 8; d++) {
            int checkR = curR + dArr[d][0];
            int checkC = curC + dArr[d][1];
            if (isIn(checkR, checkC) && map[checkR][checkC] > map[curR][curC]) {
                return false;
            }
        }
        return true;
    }

    private static boolean isIn(int nextR, int nextC) {
        return nextR < N && nextR >= 0 && nextC < M && nextC >= 0;
    }
}

package gold.BJ4179fire;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Cordi {
    int row;
    int col;
    char roll;

    public Cordi(int row, int col, char roll) {
        this.row = row;
        this.col = col;
        this.roll = roll;
    }

    public Cordi() {
    }

    @Override
    public String toString() {
        return "Cordi{" +
                "row=" + row +
                ", col=" + col +
                ", roll=" + roll +
                '}';
    }
}

public class Main {
    private static int N;
    private static int M;
    private static boolean[][] map;
    private static Queue<Cordi> fq = new ArrayDeque<>();
    private static Queue<Cordi> mq = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        // 불 , 지훈 순으로 탈출
        // 맵 밖으로 나가면 성공
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Cordi start = new Cordi();
        Cordi fire = new Cordi();
        map = new boolean[N][M];
        for (int row = 0; row < N; row++) {
            String[] split = br.readLine().split("");
            for (int col = 0; col < M; col++) {
                String val = split[col];
                if (val.equals("#")) {
                    map[row][col] = true;
                } else if (val.equals("F")) {
                    fire = new Cordi(row, col, 'F');
                    fq.add(fire);
                    map[row][col] = true;
                } else if (val.equals("J")) {
                    start = new Cordi(row, col, 'S');
                    mq.add(start);
                    map[row][col] = true;
                }
            }
        }
        int res = bfs(fire, start);
        if (res == -1) {
            System.out.println("IMPOSSIBLE");
        } else {
            System.out.println(res);
        }


    }

    private static int bfs(Cordi fire, Cordi start) {

        fq.add(fire);
        mq.add(start);
        //System.out.println(mq);

        int count = 0;
        int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        while (!mq.isEmpty()) {
            int fqSize = fq.size();
            while (fqSize-- > 0) {
                Cordi currFire = fq.poll();
                for (int d = 0; d < 4; d++) {
                    int nextRow = currFire.row + dir[d][0];
                    int nextCol = currFire.col + dir[d][1];
                    if (isIn(nextRow, nextCol) && !map[nextRow][nextCol]) {
                        fq.add(new Cordi(nextRow, nextCol, 'F'));
                        map[nextRow][nextCol] = true;
                    }
                }
            }


            int manQSize = mq.size();
            while (manQSize-- > 0) {
                Cordi currMan = mq.poll();
                for (int d = 0; d < 4; d++) {
                    int nextRow = currMan.row + dir[d][0];
                    int nextCol = currMan.col + dir[d][1];
                    if (!isIn(nextRow, nextCol)) {
                        count++;
                        return count;
                    }
                    if (isIn(nextRow, nextCol) && !map[nextRow][nextCol]) {
                        mq.add(new Cordi(nextRow, nextCol, 'S'));
                        map[nextRow][nextCol] = true;
                    }
                }
            }

            count++;
            //System.out.println(mq);
            //System.out.println(count);
            //printArr(map);
        }
        return -1;
    }

    private static boolean isIn(int row, int col) {
        return row >= 0 && row < N && col >= 0 && col < M;
    }

    private static void printArr(boolean[][] arr) {
        for (boolean[] row : arr) {
            for (boolean val : row) {
                System.out.print(val ? "1 " : "0 ");

            }
            System.out.println();
        }
    }
}

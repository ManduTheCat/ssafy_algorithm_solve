package dailySolve.BJ1913;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

class Cordi {
    int row;
    int col;

    public Cordi() {
    }

    public Cordi(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(row).append(" ").append(col);
        return sb.toString();
    }
}

public class Main {
    static int N;
    static int TARGET;
    static int curNum;
    static int[][] map;
    static Cordi curCodi;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/daily/BJ_1913/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        TARGET = Integer.parseInt(br.readLine());
        curNum = 2;
        map = new int[N][N];
        // 한개 위로 한개 오른쪽
        // 두개 아래로 두개 왼쪽;
        map[N / 2][N / 2] = 1;
        curCodi = new Cordi(N / 2, N / 2);
        for (int depth = 1; depth <= N; depth += 2) {
            turn(curCodi, curNum, depth);
        }

        printArr(map);

    }


    private static void printArr(int[][] map) {
        Cordi findTarget = new Cordi();
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if (map[row][col] == TARGET) findTarget = new Cordi(row + 1, col + 1);
                sb.append(map[row][col]).append(" ");
            }
            sb.append("\n");
        }
        sb.append(findTarget);
        System.out.print(sb);
    }

    public static void turn(Cordi startCordi, int startNum, int depth) {
        // 위로 depth 만큼 오른쪽으로 depth만큼
        int[][] d = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        int num = startNum;
        int nextRow = startCordi.row;
        int nextCol = startCordi.col;
        for (int i = 0; i < depth; i++) {
            nextRow += d[0][0];
            nextCol += d[0][1];
            if (isOut(nextRow, nextCol)) {
                map[nextRow][nextCol] = num;
                num += 1;
            }
        }
        for (int i = 0; i < depth; i++) {
            nextRow += d[1][0];
            nextCol += d[1][1];
            if (isOut(nextRow, nextCol)) {
                map[nextRow][nextCol] = num;
                num += 1;
            }

        }
        for (int i = 0; i < depth + 1; i++) {
            nextRow += d[2][0];
            nextCol += d[2][1];
            if (isOut(nextRow, nextCol)) {
                map[nextRow][nextCol] = num;
                num += 1;
            }
        }
        for (int i = 0; i < depth + 1; i++) {
            nextRow += d[3][0];
            nextCol += d[3][1];
            if (isOut(nextRow, nextCol)) {

                map[nextRow][nextCol] = num;
                num += 1;
                curCodi.row = nextRow;
                curCodi.col = nextCol;
                curNum = num;
            }
        }
    }

    private static boolean isOut(int row, int col) {
        return row < N && row >= 0 && col < N && col >= 0;
    }


}

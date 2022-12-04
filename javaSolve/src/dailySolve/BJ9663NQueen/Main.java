package dailySolve.BJ9663NQueen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class Main {
    static int N;
    static int[][] map;
    static boolean[][] check;
    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        check = new boolean[N][N];
        map = new int[N][N];
        count = 0;
        //printArr(markQueen(3,3,check));
        dfs(0, check);
        System.out.println(count);

    }

    private static void printArr(boolean[][] markQueen) {
        for (boolean[] row : markQueen) {
            for (boolean el : row) {
                System.out.print((el ? 1 : 0) + " ");
            }
            System.out.println();
        }
    }

    private static void dfs(int row, boolean[][] check) {
        if (row == N) {
            count++;
            //printArr(map);
            return;
        }
        for (int ncol = 0; ncol < N; ncol++) {
            // 가능한 퀸이면 체크에 체크하고 넘긴다.
            if (isValidQueen(row, ncol, check)) {
                //int[][] newMap = deepCopy(map);
                //newMap[row][ncol] = 1;
                boolean newCheck[][] = markQueen(row, ncol, check);
                dfs(row + 1, newCheck);
            }

        }
    }

    private static int[][] deepCopy(int[][] map) {
        int [][] newMap = new int[N][N];
        int idx = 0;
        for(int [] row : map){
            newMap[idx++] = row.clone();
        }
        return newMap;
    }

    private static void printArr(int[][] map) {
        for(int [] row : map){
            System.out.println(Arrays.toString(row));
        }
        System.out.println();
    }


    private static boolean[][] markQueen(int row, int col, boolean[][] check) {
        // 8 방 체크 시계방향으로 돈다.
        boolean[][] nextCheck = new boolean[N][N];
        int idx = 0;
        for (boolean[] checkRow : check) {
            nextCheck[idx++] = checkRow.clone();
        }
        int[][] dr = {{0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}};
        for (int d = 0; d < 8; d++) {
            int nextRow = row;
            int nextCol = col;
            nextCheck[nextRow][nextCol] = true;
            while (true) {
                nextRow += dr[d][0];
                nextCol += dr[d][1];
                if (!isOut(nextRow, nextCol)) break;
                nextCheck[nextRow][nextCol] = true;
            }
        }
        return nextCheck;
    }

    private static boolean isOut(int nextRow, int nextCol) {
        return nextRow < N && nextRow >= 0 && nextCol < N && nextCol >= 0;
    }

    private static boolean isValidQueen(int row, int ncol, boolean[][] check) {
        return !check[row][ncol]; // 마킹 되어 있다면 true 니 false 를 반환한다.
    }
}

package SSAFY.week7.day3.BJ10026;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static char[][] map;
    static boolean[][] check;
    static int[][] alpha = new int[][]{{0, -1}, {-1, 0}, {0, 1}, {1, 0}};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/SSAFY.week7/day3/bj10026/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        check = new boolean[N][N];

        for (int n = 0; n < N; n++) {
            String row = br.readLine();
            for (int col = 0; col < N; col++) {
                map[n][col] = row.charAt(col);
            }
        }
        // 일반인 시야 그룹
        int normalCount = 0;
        for (int row = 0; row < N; row++) {
            for (int col = 0; col <N ; col++) {
                if (!check[row][col]){
                    normalCount++;
                    dfs(row,col,map[row][col]);
                    //System.out.println();
                }

            }
        }
        // 적록 색약 시야 그룹
        check = new boolean[N][N];
        for (int row = 0; row < N; row++) {
            for (int col = 0; col <N ; col++) {
                if(map[row][col] == 'R') map[row][col] = 'G';
            }
        }

        int weakCount= 0;
        for (int row = 0; row < N; row++) {
            for (int col = 0; col <N ; col++) {
                if (!check[row][col]){
                    weakCount++;
                    dfs(row,col,map[row][col]);
                    //System.out.println();
                }
            }
        }
        System.out.printf("%d %d",normalCount ,weakCount);

    }

    public static void dfs(int row, int col, char RGB) {
        //System.out.printf("%d, %d , %s\n",row,col, RGB);
        check[row][col] = true;
        for (int d = 0; d < 4; d++) {
            int nextRow =  row + alpha[d][0];
            int nextCol = col + alpha[d][1];
            if(isIn(nextRow, nextCol) && map[nextRow][nextCol] == RGB && !check[nextRow][nextCol]){
                dfs(nextRow, nextCol, RGB);
            }
        }
    }

    public static boolean isIn(int i , int j) {
        return i >= 0 && i < N && j >= 0 &&  j < N;
    }
}

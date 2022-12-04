package dailySolve.BJ9663NQueen.colArr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class Main {
    static int N;
    static int count = 0;
    static int[] colArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        colArr = new int[N];
        Arrays.fill(colArr, -1);
        dfs(0);
        System.out.println(count);
    }

    private static void dfs(int row) {
        if (row == N) {
            count++;
            return;
        }
        for (int col = 0; col < N; col++) { // 최대 15
            if (isValid(row, col)) { // 최대 15
                colArr[row] = col;
                dfs(row + 1);
            }
        }
    }


    private static boolean isValid( int curRow, int curCol) {
        for (int beforeRow = 0; beforeRow < curRow; beforeRow++) {
            if (colArr[beforeRow] != -1) { // 이거 없어도 될듯

                int beforeCol = colArr[beforeRow];
                if(curCol == beforeCol){
                    return false;
                }
                if(isDiagonal(curRow, curCol, beforeRow, beforeCol)){
                    return false;
                }

            }
        }
        return true;
    }
    public static boolean isDiagonal(int curRow, int curCol, int beforeRow, int beforeCol){
        return (Math.abs(curCol- beforeCol) == Math.abs(curRow - beforeRow));
    }


}

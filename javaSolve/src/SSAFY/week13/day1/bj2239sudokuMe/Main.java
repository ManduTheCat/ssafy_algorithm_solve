package SSAFY.week13.day1.bj2239sudokuMe;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    // 안됨
    static boolean[][] isPick = new boolean[9][10];
    static int [][] map = new int[9][9];
    //static int [] lineRes = new int[10];
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/SSAFY.week13/day1/sudoku2239/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int row = 0; row < 9; row++) {
            String inputRow = br.readLine();
            for (int col = 0; col < 9; col++) {
                int value = inputRow.charAt(col) - '0';
                //System.out.println("vlaude " + value);
                map[row][col] = value;
                // 사용중인 숫자 true 체크
                isPick[row][value] = true;
                if(row == 0 && value == 9) System.out.println(isPick[row][9]);
            }
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(isPick[i][j] ? "1 ": "0 ");
            }
            System.out.println();
        }
        int [] lineRes = new int[10];
        int count = 0;
        Arrays.fill(lineRes, 0);
        count = getZeroCount(0);

        permutation(0,0, count, lineRes);
    }

    private static int getZeroCount(int r) {
        int count = 0;
        for (int i = 0; i < 9; i++) {
            if(map[r][i]  == 0){
                count++;
            }
        }
        return count;
    }

    // 0일때 껴넣는 함수 필요함
    private static void permutation( int r, int c, int count, int[] lineRes) {
        if(r == 9){
            System.out.println("in");
            return;
        }
        if(count == 0){
            //System.out.println(Arrays.toString(lineRes));
            //res 초기화
            int [] newLineRes = new int[10];
            if(r+1 < 9){

                int zeroCount = getZeroCount(r+1);
               // System.out.println(zeroCount);
                permutation(r +1, 0,zeroCount, newLineRes);
            }
            // line 할당
            // r 증가 c 0 해서 라인 할당 다시 돌린다 r== 9 가 될떄까지 재귀
            return;
        }
        // count 갯수만큼만 뽑자
        for (int i = 1; i < 10; i++) {
            if(!isPick[r][i]){
                isPick[r][i] = true;
                lineRes[c] = i;
                permutation(r,c+1, count-1 , lineRes );
                isPick[r][i]= false;
            }
        }
    }
}

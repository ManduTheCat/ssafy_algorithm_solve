package SSAFY.week4.day3.BJ1659_fail;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/BJ11659/input.txt"));
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        //첫째 줄에 수의 개수 N과 합을 구해야 하는 횟수 M이 주어진다
        int []NM = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        //둘째 줄에는 N개의 수가 주어진다.
        int [] inputList = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        // 셋째 줄부터 M개의 줄에는 합을 구해야 하는 구간 i와 j가 주어진다.
        //메모의 0,0 = inputList[0]
        int [] memo = new int[NM[0]];
        for(int m = 0; m < NM[1]; m++){
            int [] IJ = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int start = IJ[0];
            int end = IJ[1];
            //System.out.println(Arrays.toString(subArray));
            
            // 스트링 빌더
            System.out.println(getSum(start-1, end-1,memo, inputList));
        }
    }
    public static int getSum (int i, int j, int[]memo, int []inputList){


        //메모에 할당
        System.out.println(Arrays.toString(memo));
        if(memo[j] != 0 || i == j){
            System.out.printf("int memeo i, j (%d %d) memo :%d\n", i, j ,memo[j] );
            return memo[j];
        }
        memo[j] = getSum( i, j-1, memo, inputList) + inputList[j];


        return memo[j];
    }
}

package SSAFY.week4.day3.BJ1659_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("resources/BJ11659/input.txt"));
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        //첫째 줄에 수의 개수 N과 합을 구해야 하는 횟수 M이 주어진다
        int []NM = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        //둘째 줄에는 N개의 수가 주어진다.
        int [] inputList = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        // 셋째 줄부터 M개의 줄에는 합을 구해야 하는 구간 i와 j가 주어진다.
        StringBuilder sb = new StringBuilder();

        int [] sumArr = new int[NM[0]];
        sumArr[0] = inputList[0];
        for(int i = 1; i < NM[0]; i++){
            sumArr[i] +=sumArr[i-1] + inputList[i];
        }

        for(int m = 0; m < NM[1]; m++){
            int [] IJ = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int start = IJ[0];
            int end = IJ[1];
            if(start -1  == 0){
                sb.append(sumArr[end-1] - 0).append("\n");;
            }
            else {
                sb.append(sumArr[end-1] - sumArr[start-2]).append("\n");
            }

        }
        System.out.println(sb);
    }
}
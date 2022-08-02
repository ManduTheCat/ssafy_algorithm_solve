package week4.day1.swea1289Memory;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/swea1289/input.txt"));
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int Tc = Integer.parseInt(bf.readLine());
        StringBuilder sb = new StringBuilder();
        for(int tc = 0; tc < Tc; tc++){
            int [] target = Arrays.stream(bf.readLine().split("")).mapToInt(Integer::parseInt).toArray();
            int [] source = new int[target.length];
            int count = 0;
            for(int i = 0; i < target.length; i++){
                if(target[i] != source[i]){
                    count++;
                    for(int j = i; j < target.length; j++){
                        source[j]  = target[i];
                    }
                }
            }
            int Tcnum = tc +1;
            sb.append("#").append(Tcnum).append(" "+count+"\n");
        }
        System.out.println(sb.toString());
    }
}

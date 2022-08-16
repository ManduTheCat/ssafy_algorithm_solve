package week6.day1.BJ1074;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int r;
    static int c;
    static int [][]map;
    static int count;
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/week6/BJ1074/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        System.out.printf("%d, %d, %d", n, r, c);
        map = new int[n][n];

    }
    static void searchZ(int depth, int startR, int startC, int endR, int endC){
        if(depth == n){
            for(int i =  startR; i < endR; i++){
                for(int j = startC; j < endC; j++){
                    count++;
                }
            }
            return;
        }


    }
}

package SSAFY.week5.day2.BK2563;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/SSAFY.week5/day2/BK2563/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int SquareCount = Integer.parseInt(br.readLine());
        int [][] map = new int[100][100];
        for(int tc = 0; tc < SquareCount; tc++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int ifrom = Integer.parseInt(st.nextToken())-1;
            int jfrom = Integer.parseInt(st.nextToken())-1;
            for(int i = ifrom; i < ifrom+ 10; i++){
                for (int j = jfrom; j < jfrom+10; j++){
                    map[i][j] = 1;
                }
            }

        }
        int count = 0;
        for(int [] m : map){
            for(int el : m){
                if(el == 1){
                    count++;
                }
            }
        }
        System.out.println(count);

    }
}

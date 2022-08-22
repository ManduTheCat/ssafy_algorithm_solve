package week7.day1.BJ1759;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int L;
    static int C;
    static char [] inputList;

    ArrayList <Character> vowel = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/week7/day1/BJ1759/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        inputList = new char[C];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C ; i++) {
            // 자음 모음 구분해야한다
            char cur = st.nextToken().charAt(0);
        }
        System.out.printf("%d, %d ", L, C);
        System.out.println(Arrays.toString(inputList));
        
    }
    //1. 합쳐서 L 이되는 경우의 수 구해야한다
}

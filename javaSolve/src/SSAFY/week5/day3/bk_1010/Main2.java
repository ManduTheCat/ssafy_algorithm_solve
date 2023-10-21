package SSAFY.week5.day3.bk_1010;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 김명진
 * 앞에서 선택한 수 이상으로 선택해야한다 1->10 을 선택하면 2는 10 보다 큰수를 선택해야한다
 * nCr = (n-1)C(r-1)+(n-1)Cr
 */
public class Main2 {

    static int N;
    static int [][] memo;
    static int R;
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/SSAFY.week5/day3/bk1010/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int Tc = Integer.parseInt(br.readLine());
        for(int tc = 0; tc < Tc; tc++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            R = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            memo = new int [31][31];
            combi(N, R);
            System.out.println(memo[N][R]);
        }
    }
    public static int combi (int n, int r){
        if(memo[n][r] > 0){
            return memo[n][r];
        }
        if(n == r || r == 0){
            memo[n][r] = 1;
            return 1;
        }
        return memo[n][r] = combi(n-1, r-1)+ combi(n-1, r);
    }
}

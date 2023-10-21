package SSAFY.week5.day3.bk_1010;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

/**
 * @author 김명진
 * 앞에서 선택한 수 이상으로 선택해야한다 1->10 을 선택하면 2는 10 보다 큰수를 선택해야한다
 * 구하라는건 경우의 수를 구하는거다 결국 2개를 중복되지 않게 선택하는거다.조합의경우의 수로 풀어봄
 * 모든 경우의수 활용하면 시간초과발생
 * 팩토리얼 사용시long 의 범위를 넘기기때문에 BigInteger 사용
 * nCr  = n!/r!*(n-r)! 을 활용 팩토리얼활용
 */
public class Main {

    static int N;
    static int R;
    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("resources/SSAFY.week5/day3/bk1010/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int Tc = Integer.parseInt(br.readLine());
        for(int tc = 0; tc < Tc; tc++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            R = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            int NsubB = N - R;
            System.out.println(factorial(N).divide(factorial(NsubB).multiply(factorial(R))));
        }
    }
    public static BigInteger factorial(int n){
        if(n == 1 | n == 0){
            return new BigInteger("1");
        }
        BigInteger bigN = new BigInteger(String.valueOf(n));
        return bigN.multiply(factorial( n-1));
    }
}

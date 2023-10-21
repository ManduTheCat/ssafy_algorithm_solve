package SSAFY.week14.swea5607Combination;

import java.io.*;
import java.util.StringTokenizer;

public class Solution {
    static int Tc;
    static int N;
    static int P;
    static final int Mod = 1234567891;
    // 페르마 마지막 정리를 활용한 풀이
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/SSAFY.week14/swea5607/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Tc = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < Tc; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            P = Integer.parseInt(st.nextToken());
            long memo[] = new long[N+1];
            memo[0] = 1;
            for (int i = 1; i <= N; i++) {
                memo[i] = (memo[i - 1] * i) %Mod;

            }
            long bottom =(memo[P] * memo[N -P]) %Mod;
            long ferma = divideConquerPower(bottom, Mod -2);
            System.out.printf("#%d %d\n",tc+ 1,(ferma%Mod * memo[N]%Mod)%Mod);
        }
    }



    public static long divideConquerPower(long n, long k) {
        if(k == 0) return 1;
        long fecto = (divideConquerPower(n , k/2)) % Mod;
        long res = fecto * fecto %Mod;
        if (k % 2 == 0) return res;
        else return res * n %Mod;
    }
}

package week12.day1.yelloBlue;

import java.util.Arrays;

public class Solution {
    static int N;
    static int[] memo;
    public static void main(String[] args) {
        N = 8;
        memo = new int[N+1];
        Arrays.fill(memo, -1);
        memo[0] = 0;
        memo[1] = 2;
        memo[2] = 3;
        System.out.println(fibo(N));
    }
    public static int fibo(int n){
        if(memo[n] == -1){
            return memo[n] = fibo(n-1) + fibo(n-2);
        }
        return memo[n];
    }
}

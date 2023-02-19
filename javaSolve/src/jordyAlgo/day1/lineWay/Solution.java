package jordyAlgo.day1.lineWay;

import java.util.Arrays;

public class Solution {
    public static int[] res;
    public static boolean[] check;
    public static int N;
    public static Long K;
    public static int count;
    public static int[]  answer = {};


    public int[] solution(int n, long k) {
        check = new boolean[n];
        res = new int[n];
        N = n;
        K = k;
        count= 0;

        permutaion(0,n);
        //System.out.println(Arrays.toString(answer));
        return answer;
    }

    public void permutaion(long depth,long n){
        count++;
        if(depth == N){
            if(count == (K * n) - 1){
                answer = res.clone();
            }
            return;
        }
        for(int i = 0; i < N; i++){
            if(!check[i]){
                check[i] = true;
                res[(int) depth] = i+1;
                permutaion(depth+1,n);
                check[i] = false;

            }
        }
    }
}

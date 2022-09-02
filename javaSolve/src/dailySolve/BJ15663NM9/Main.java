package dailySolve.BJ15663NM9;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static int[] set;
    static int [] res;
    static int maxVal = Integer.MIN_VALUE;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/daily/BJ_15663/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        set = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            set[i] = Integer.parseInt(st.nextToken());
        }
        res = new int[M];
        Arrays.sort(set);
        for(int el:set){
            maxVal = Math.max(el, maxVal);
        }
        permutation(0,0);
        System.out.println(sb);


    }
    public static void permutation(int depth, int flag){
        if(depth == M){
            for(int el : res){
                sb.append(el).append(" ");
            }
            sb.append("\n");
            return;
        }
        boolean[] duplicateCheck = new boolean[maxVal+1];
        for(int i = 0;  i< N; i ++){
            if( (flag & 1<<i) == 0  &&!duplicateCheck[set[i]]){
                res[depth] = set[i];
                duplicateCheck[set[i]] = true;
                permutation(depth+1,flag | 1<<i);

            }
        }
    }
}

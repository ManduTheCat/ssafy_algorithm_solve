package dailySolve.BJ_15679NM1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int m;
    static int [] res;
    static int [] numList;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        res = new int[m];
        numList = new int[n];
        for (int i = 0; i < n; i++) {
            numList[i] = i + 1;
        }
        permutation(0,0);
        System.out.println(sb);
    }
    static void permutation(int depth, int flag){
        if(depth == m){
            for(int r: res){
                sb.append(r).append(" ");
            }
            sb.append("\n");
            return;
        }
        for(int i = 0;  i< n; i++){
            if((flag & 1<<i) == 0){
                //System.out.println(numList[i]);
                //System.out.println("depth : "+ depth);
                res[depth] = numList[i];
                permutation(depth+1, flag | 1<<i);
            }
        }

    }
}


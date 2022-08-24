package dailySolve.NM3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static int[] input;
    static int[] res;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        input = new int[N];
        res = new int[M];
        for (int i = 0; i < N; i++) {
            input[i] = i + 1;
        }
        perm(0);
        System.out.println(sb);

    }
    public static void perm(int depth){
        if(depth == M){
            for(int e : res){
                sb.append(e).append(" ");
            }
            sb.append("\n");
            return;
        }
        for (int i = 0; i < N ; i++) {
            res[depth] = input[i];
            perm(depth+1);
        }
    }
}

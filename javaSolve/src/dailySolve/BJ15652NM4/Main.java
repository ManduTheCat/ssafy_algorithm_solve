package dailySolve.BJ15652NM4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static int[] set;
    static int[] res;
    static boolean[] check;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        set = new int[N];
        res = new int[M];
        check = new boolean[N];
        for (int i = 0; i < N; i++) {
            set[i] = i + 1;
        }
        combination(0, 0);
        System.out.println(sb);

    }

    public static void combination(int depth, int start) {
        if (depth == M) {
            for(int el : res){
                sb.append(el).append(" ");
            }
            sb.append("\n");
            return;
        }
        for (int i = start; i < N; i++) {
                res[depth] = set[i];
                combination(depth + 1, i);
        }
    }
}

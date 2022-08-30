package dailySolve.BJ15655NM6;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static int [] set;
    static int [] res;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/daily/bj_15655/input3.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        res = new int[M];
        set = new int[N];
        for (int n = 0; n < N; n++) {
            set[n] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(set);
        combination(0,0);
        System.out.println(sb);

    }
    public static void combination (int start, int depth){
        if(depth == M){
            for (int i = 0; i < M; i++) {
                sb.append(res[i]).append(" ");
            };
            sb.append("\n");
            return;
        }
        for(int i = start; i < N ;i++){
            res[depth] = set[i];
            combination(i+1, depth+1);
        }
    }
}

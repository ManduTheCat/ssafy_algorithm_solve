package dailySolve.BJ15650NM2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static int [] resCombi;
    public static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        resCombi  = new int[m];
        combination(0, 1, n, m);
        System.out.println(sb);
    }
    public static void combination(int depth,int start, int n, int m){
        if(depth == m){
            for(int el : resCombi){
                sb.append(el).append(" ");
            }
            sb.append("\n");
            return;
        }
        for (int i = start; i <=n ; i++) {
            resCombi[depth] = i;
            combination(depth +1, i+1, n, m);
        }
    }

}

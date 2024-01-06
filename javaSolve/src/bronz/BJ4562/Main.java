package bronz.BJ4562;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringBuilder res = new StringBuilder();
        for(int n = 0; n < N; n++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int brain  = Integer.parseInt(st.nextToken());
            int zombi  = Integer.parseInt(st.nextToken());
            if(brain  < zombi){
                res.append("NO BRAINS");
                res.append("\n");
            }else {
                res.append("MMM BRAINS");
                res.append("\n");
            }
        }
        System.out.println(res);

    }
}

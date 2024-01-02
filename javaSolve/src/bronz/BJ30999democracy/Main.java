package bronz.BJ30999democracy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int threshold = M / 2;
        int res = 0;
        for (int n = 0; n < N; n++) {
            int Ocount = 0;
            String[] line = br.readLine().split("");
            for (int m = 0; m < M; m++) {
                String input = line[m];
                if(input.equals("O")){
                    Ocount++;
                }
            }
            if(Ocount >threshold){
                res++;
            }
        }
        System.out.println(res);
    }
}

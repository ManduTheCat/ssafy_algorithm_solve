package dailySolve.BJ10871;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static  int N, X;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++) {
            int value = Integer.parseInt(st.nextToken());
            System.out.print( value < X ? value + " " : "");
        }
    }
}

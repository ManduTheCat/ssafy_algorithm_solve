package bronz.BJ13752;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int n = 0; n < N; n++){
            int val = Integer.parseInt(br.readLine());
            for(int v = 0; v < val; v++){
                sb.append("=");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}

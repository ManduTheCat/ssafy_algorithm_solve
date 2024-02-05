package bronz.BJ8393;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int sum = 0;
        for(int n = 1; n <=N; n++){

            sum += n;
        }
        System.out.println(sum);
    }
}

package bronz.BJ5522;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
        int sum = 0;
        for(int n = 0; n < 5; n++){
            sum += Integer.parseInt(br.readLine());
        }
        System.out.println(sum);
    }
}

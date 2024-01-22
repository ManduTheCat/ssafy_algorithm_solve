package bronz.BJ6810;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int base = 91;
        int sum = 0;
        for (int i = 1; i < 4; i++) {
            int magic = (i % 2 == 0 ? 3 : 1);
            int input = Integer.parseInt(br.readLine());
            sum += input * magic;
        }
        //System.out.println(sum);
        System.out.printf("The 1-3-sum is %d", base + sum);

    }
}

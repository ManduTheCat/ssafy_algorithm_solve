package bronz.BJ23037;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int sum = 0;
        int input = Integer.parseInt(br.readLine());
        while (input > 0){
            int val = input % 10;
            sum += (int)Math.pow(val ,5);
            input/=10;

        }
        System.out.println(sum);
    }
}

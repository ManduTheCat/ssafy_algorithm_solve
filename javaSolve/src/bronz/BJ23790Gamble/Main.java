package bronz.BJ23790Gamble;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int sum = 0;
        while (true){
            int input = Integer.parseInt(br.readLine()) ;
            if(input == -1){
                break;
            }
            sum += input;
        }
        System.out.println(sum);
    }
}

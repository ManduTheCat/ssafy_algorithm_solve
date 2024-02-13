package bronz.BJ25372;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for(int n = 0; n < N; n++){
            String input = br.readLine();
            if(input.length() >= 6 && input.length() < 10){
                System.out.println("yes");
            }else {
                System.out.println("no");
            }
        }
    }
}

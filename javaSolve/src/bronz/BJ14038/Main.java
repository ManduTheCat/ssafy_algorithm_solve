package bronz.BJ14038;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int lCount = 0;
        int wCount = 0;
        for(int i = 0;  i < 6; i++){
            String val = br.readLine();
            if(val.equals("L")){
                lCount++;
            }else {
                wCount++;
            }
        }
        if(wCount == 5 || wCount == 6){
            System.out.println(1);
        } else if (wCount == 3 || wCount == 4) {
            System.out.println(2);
        }else if (wCount == 1 || wCount== 2){
            System.out.println(3);
        }else if(wCount == 0){
            System.out.println(-1);
        }
    }
}

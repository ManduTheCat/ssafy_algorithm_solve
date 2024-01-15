package bronz.BJ5217;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int T;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringBuilder res = new StringBuilder();
        for(int t = 0; t < T; t++){
            int target = Integer.parseInt(br.readLine());
            int count = 0;
            res.append("Pairs for ");
            res.append(target);
            res.append(": ");
            for(int first = 1; first <= target/2; first++){
                int second = target - first;
                    if(first + second == target && first != second){
                        if(count >= 1){
                            res.append(",");
                            res.append(" ");
                        }
                        res.append(String.valueOf(first));
                        res.append(" ");
                        res.append(String.valueOf(second));
                        count++;


                }
            }
            res.append("\n");
        }
        System.out.println(res);
    }
}

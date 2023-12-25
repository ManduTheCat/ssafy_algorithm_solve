package silver.BJ2217;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(br.readLine());
        //모든 로프를 사용할필요 없이
        Integer [] inputs = new Integer[k];
        for(int i = 0; i < k; i++){
            inputs[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(inputs, Collections.reverseOrder());
        int count = 1;
        int res = 0;
        for(Integer num :inputs){
            res = Math.max(num * count, res);
            count++;
        }
        System.out.println(res);

    }
}

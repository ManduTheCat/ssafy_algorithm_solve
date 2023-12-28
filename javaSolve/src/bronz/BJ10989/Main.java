package bronz.BJ10989;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int [] res = new int[n];
        for(int row = 0; row < n; row++){
            int num  = Integer.parseInt(br.readLine());
            res[row] = num;
        }
        Arrays.sort(res);
        StringBuilder  sb = new StringBuilder();
        for(int num:res){
            sb.append(num);
            sb.append("\n");
        }
        System.out.println(sb);
    }
}

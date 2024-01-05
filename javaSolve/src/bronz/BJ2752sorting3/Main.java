package bronz.BJ2752sorting3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int [] input = new int[3];
        for(int i = 0; i < 3; i ++){
            int val = Integer.parseInt(st.nextToken());
            input[i] = val;
        }
        Arrays.sort(input);
        StringBuilder sb = new StringBuilder();
        for(int re : input){
            sb.append(re);
            sb.append(" ");
        }
        System.out.println(sb);
    }
}

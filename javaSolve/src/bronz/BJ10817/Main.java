package bronz.BJ10817;

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
            input[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(input);
        System.out.println(input[1]);
    }
}

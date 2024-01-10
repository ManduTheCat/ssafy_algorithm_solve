package bronz.BJ22966;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] input = new String[5];
        for (int n = 0; n < N; n++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            String pb = st.nextToken();
            int diff = Integer.parseInt(st.nextToken());
            input[diff] = pb;
        }
        for (String st : input){
            if(st != null){
                System.out.println(st);
                return;
            }

        }
    }
}

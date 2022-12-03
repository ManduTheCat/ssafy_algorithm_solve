package dailySolve.BJ2535WeatherCast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int K;
    static int[] arr;
    static int maxNum;
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/daily/BJ2435/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        maxNum = -10000;
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] =Integer.parseInt( st.nextToken());
        }
        for (int n = 0; n < N-K+1; n++) {
            //System.out.println("index :  " + n);
            int num = 0;
            for (int j = n; j < n+K ; j++) {
                //System.out.println("어디까지 더했지  " + j);
                num += arr[j];
            }
            maxNum = Math.max(num , maxNum);
        }
        System.out.println(maxNum);


    }
}

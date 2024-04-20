package silver.BJ2343;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int [] input = new int[N];
        int left = 0;
        int right = 0;
        for(int n = 0; n < N; n++){
            int value= Integer.parseInt(st.nextToken());
            input[n] = value;
            left = Math.max(left, value);
            right += value;
        }
        while (left <= right) {
            int mid = (left + right) / 2;

            int count = countSplit(input, N, mid);

            if(count > M){
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }

        System.out.println(left);

    }

    private static int countSplit(int [] input, int n, int mid){
        int split = 0;
        int sum = 0;
        for(int i = 0; i < n; i++){
            if(sum + input[i] > mid){
                sum = 0;
                split++;
            }
            sum += input[i];
        }
        if(split != 0) split++;
        return split;
    }
}

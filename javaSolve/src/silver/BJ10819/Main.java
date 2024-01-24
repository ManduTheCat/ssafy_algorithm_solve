package silver.BJ10819;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static boolean[] check;
    static int[] dfsRes;
    static int[] input;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        // 모든 경우를 다보고 최대값을 고른다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        input = new int[N];
        check = new boolean[N];
        dfsRes = new int[N];
        for (int n = 0; n < N; n++) {
            int val = Integer.parseInt(st.nextToken());
            input[n] = val;
        }
        dfs(0);
        System.out.println(max);


    }

    public static void dfs(int depth) {
        if (depth == N) {
            //System.out.println(Arrays.toString(dfsRes));
            max = Math.max(getSum(), max);
            return;
        }
        for (int idx = 0; idx < N; idx++) {
            if (!check[idx]) {
                check[idx] = true;
                dfsRes[depth] = input[idx];
                dfs(depth + 1);
                check[idx] = false;

            }
        }
    }

    public static int getSum() {
        int sum = 0;
        for (int idx = 1; idx < N; idx ++) {
            sum += Math.abs(dfsRes[idx] - dfsRes[idx -1]);
        }
        return sum;
    }
}

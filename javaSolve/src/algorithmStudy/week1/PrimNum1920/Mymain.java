package algorithmStudy.week1.PrimNum1920;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Mymain {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        for (int m = N; m <= M; m++) {
            if (m == 1) {
                continue;
            }
            // 2는 소수 이기때문에
            if (m == 2) {
                sb.append(2).append("\n");
            }
            if (isPrime(m)) {
                sb.append(m).append("\n");
            }
        }
        System.out.println(sb);
    }

    public static boolean isPrime(int src) {
        if (src % 2 == 0) {
            return false;
        }
        for (int i = 3; i <= (int) Math.sqrt(src); i++) {
            if (src % i == 0) {
                return false;
            }
        }
        return true;
    }
}

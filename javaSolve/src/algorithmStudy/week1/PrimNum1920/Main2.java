package algorithmStudy.week1.PrimNum1920;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main2 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        //입력은 1,000,000 을 최대갯수로 가지기 때문에 시간 복잡도를 줄여보기 위해 StringBuilder를 활용했습니다.
        StringBuilder sb = new StringBuilder();
        //한줄을 받아 int 배열로 만들기 위해 stram 사용
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        for (int m = M; m <= N; m++) {
            if(m == 1){
                continue;
            }
            // 2는 소수
            if(m == 2){
                sb.append(2).append("\n");
            }
            if (isPrime(m)) {
                sb.append(m).append("\n");
            }
        }
        System.out.println(sb);
    }

    public static boolean isPrime(int src) {

        //소수의 배수는 소수가 아니다 라는 성질을 활용해 제일 작은 소수 2의 배수를 제거 했습니다.
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


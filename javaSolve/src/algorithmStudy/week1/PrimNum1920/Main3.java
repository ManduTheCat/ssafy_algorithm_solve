package algorithmStudy.week1.PrimNum1920;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main3 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        //입력은 1,000,000 을 최대갯수로 가지기 때문에 시간 복잡도를 줄여보기 위해 StringBuilder를 활용했습니다.
        StringBuilder sb = new StringBuilder();
        //한줄을 받아 int 배열로 만들기 위해 stram 사용
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        Boolean[] er = Eratos(N);
        int len = er.length;
        for(int i = 3; i < len; i++){
            if(er[i]){
                sb.append(i).append("\n");
            }
        }

        System.out.println(sb);
    }
    // 에라토스테네스의 체
    private static Boolean[] Eratos(int N) {

        Boolean[] primeArray = new Boolean[N + 1];
        Arrays.fill(primeArray, true);
        primeArray[0] = false; primeArray[1] = false; primeArray[2] = true;

        for(int n = 2; n <= (int)Math.sqrt(N) + 1; ++n) {
            if(primeArray[n])
                for(int x = n * 2; x <= N; x+=n)
                    primeArray[x] = false;
        }

        return primeArray;
    }
}


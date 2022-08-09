package algorithmStudy.week1.PrimNum1920;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main3 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] line = br.readLine().split(" ");
        int M = Integer.parseInt(line[0]), N = Integer.parseInt(line[1]);

        StringBuilder sb = new StringBuilder();
//		for (int index = M; index <= N; ++index) {
//			if(isPrime(index))
//				sb.append(index).append("\n");
//		}
        Boolean[] primeArray = Eratos(N);
        for(int index = M; index <= N; ++index)
            if(primeArray[index])
                sb.append(index).append("\n");
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
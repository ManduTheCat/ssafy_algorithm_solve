package dailySolve.combination.BJ2407combi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {
	public static int N;
	public static int M;
	public static BigInteger count;
	//(5 ≤ n ≤ 100, 5 ≤ m ≤ 100, m ≤ n)
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		//nCm  = n! / (n-m)! * m!
		BigInteger denomiator = BigInteger.ONE; // 분모
		BigInteger numerator = BigInteger.ONE; // 분자
		for(int i = 0;  i < M; i++){ //n! / (n-m)! 약분 된다 분자는 n * n-1 * .. * n-m + 1
			numerator = numerator.multiply( new BigInteger(String.valueOf(N-i)));
			denomiator = denomiator.multiply(new BigInteger(String.valueOf( i + 1))); // m!
		}
		System.out.println(numerator.divide(denomiator));

	}

}

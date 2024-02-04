package silver.BJ1629product;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static long C;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long A = Long.parseLong(st.nextToken());
		long B = Long.parseLong(st.nextToken());
		C = Long.parseLong(st.nextToken());
		System.out.println(pow(A, B));
	}

	private static Long pow(Long A, Long exponent) {
		if (exponent == 1) {
			return A % C;
		}
		long temp = pow(A, exponent / 2);
		if (exponent % 2 == 1) { // 지수 가 홀수 인경우 한번더 곱해주면된다.
			return (temp * temp % C) * A % C; // 분배법칙
		}// 짝수면 그냥 간다.
		return temp * temp % C;
	}
}

package slideWindow.BJ2559;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int K;
	static int[] input;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("resources/daily/slideWindow/BJ2559/input2.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer  st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		input = new int[N];
		for (int idx = 0; idx < N; idx++) {
			input[idx] = Integer.parseInt(st.nextToken());
		}
		int sum = 0;

		for(int k = 0; k < K; k++){
			sum +=input[k];
		}
		int max = sum;
		int startIdx = 0;
		for(int next = K; next < input.length; next++){
			sum += input[next];
			sum -= input[startIdx];
			startIdx++;
			max = Math.max(max, sum);
		}
		System.out.println(max);
	}
}

package towpoint.liquid;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("resources/daily/towpoint/2467/input2.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int[] input = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int n = 0; n < N; n++) {
			input[n] = Integer.parseInt(st.nextToken());
		}
		int start = 0;
		int end = input.length - 1;
		long min = Long.MAX_VALUE;
		int [] res = new int[2];
		res[0] = input[start];
		res[1] = input[end];
		while (start < end) {
			long sum = input[start] + input[end];
			if(Math.abs(sum) < min){
				min = Math.abs(sum);
				res[0] = input[start];
				res[1] = input[end];
			}
			if (sum >= 0) {
				end--;
			} else  {
				start++;
			}
		}
		System.out.println(res[0] + " "+ res[1]);
	}
}

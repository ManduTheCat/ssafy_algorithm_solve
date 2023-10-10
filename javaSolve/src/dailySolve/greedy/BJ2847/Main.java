package dailySolve.greedy.BJ2847;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int[] input = new int[N];
		for (int n = 0; n < N; n++) {
			int val =  Integer.parseInt(br.readLine());
			input[n] = val;

		}
		int max = input[N-1];
		int res = 0;

		for(int idx = N-2; idx >=0; idx--){
			// System.out.println(target + " - "+ input[idx] + " = " +( target - input[idx]));
			if((idx >=1 && input[idx-1] < input[idx])){
				int target = input[idx];
				System.out.println(target);
				res += Math.abs(target - input[idx]);
			}
		}
		System.out.println(res);
	}
}

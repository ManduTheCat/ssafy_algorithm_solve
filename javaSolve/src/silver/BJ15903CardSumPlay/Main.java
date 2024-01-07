package silver.BJ15903CardSumPlay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.*;


public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N;
		int M;
		StringTokenizer  st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		List <Long> input = new ArrayList<>();
		for(int n = 0; n <N; n++){
			input.add(Long.parseLong(st.nextToken()));
		}
		for(int m  = 0; m < M; m++){
			Collections.sort(input);
			Long x = input.get(0);
			Long y = input.get(1);
			Long sum  = x + y;
			input.set(0, sum);
			input.set(1, sum);
		}
		// System.out.println(input);
		Long res = 0L;
		for(Long val : input){
			res += val;
		}
		System.out.println(res);
	}
}

package dailySolve.binarySearch.BJ1920;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	public static void main(String[] args) throws IOException {
		BufferedReader br  =new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st  = new StringTokenizer(br.readLine());
		long [] set = new long[N];
		for(int n = 0; n < N; n++){
			long val = Long.parseLong(st.nextToken());
			set[n] = val;
		}
		Arrays.sort(set);
		M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for(int m = 0; m < M ; m++){
			long val = Long.parseLong(st.nextToken());
			int res = Arrays.binarySearch(set, val);
			if(res >= 0){
				System.out.println(1);
			}else
				System.out.println(0);
		}

	}
}

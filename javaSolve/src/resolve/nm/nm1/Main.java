package resolve.nm.nm1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static boolean [] check;
	static int[] resultList;
	static int n;
	static int m;
	static int[] numList;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("resources/daily/resolve/NM/nm1/input.txt"));
		BufferedReader bufferedReader  = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		numList = new int[n];
		for (int i = 1; i <= n; i++) {
			numList[i-1] = i;
		}
		resultList = new int[m];
		check = new boolean[n];

		permutation(0);

	}

	private static void permutation(int depth) {
		if(depth == m){
			System.out.println(Arrays.toString(resultList));
			return;
		}
		for(int idx = 0; idx < n; idx++){
			if(!check[idx]){
				check[idx] = true;
				resultList[depth] = numList[idx];
				permutation(depth + 1);
				check[idx] = false;
			}
		}
	}
}

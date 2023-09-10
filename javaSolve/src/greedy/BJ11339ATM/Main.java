package greedy.BJ11339ATM;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int [] arr;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		int [] sum = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++){
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		sum[0] = arr[0];
		for(int i = 1; i < N; i++){
			sum[i] = sum[i -1 ] + arr[i];
		}
		// System.out.println(Arrays.toString(sum));
		int res = 0;
		for(int val :sum){
			res += val;
		}
		System.out.println(res);

	}
}

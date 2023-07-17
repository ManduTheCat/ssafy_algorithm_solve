package greedy.BJ3020;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int H;
	public static void main(String[] args) throws IOException{
		System.setIn(new FileInputStream("resources/daily/greedy/BJ3020/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		int [] sum = new int[H + 1];
		int min  = Integer.MAX_VALUE;
		for(int n = 1; n <= N/2; n++){
			st = new StringTokenizer(br.readLine());
			int bottom = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			int up = Integer.parseInt(st.nextToken());
			min  = Integer.MAX_VALUE;
			// 최악의 경우 N*H = 10 ^ 10
			for(int idx = 1; idx <= bottom; idx++){
				sum[idx]+=1;
				min = Math.min(min, sum[idx]);
			}
			for(int idx = H; idx >H-up; idx--){
				sum[idx]+=1;
				min = Math.min(min, sum[idx]);
			}
			// if(n == N/2){
			// 	System.out.println(min);
			// }
		}
		int count = 0;
		for(int i = 1; i<=H; i++){
			if(sum[i] == min){
				count++;
			}
		}
		System.out.println(min+" "+count);
	}
}

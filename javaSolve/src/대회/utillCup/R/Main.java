package 대회.utillCup.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int [] act;
	static int [] plan;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		act = new int[N];
		plan = new int[N];
		int res = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int a = 0; a < N; a++){
			int val = Integer.parseInt(st.nextToken());
			plan[a] = val;
		}
		st = new StringTokenizer(br.readLine());
		for(int b = 0; b < N; b++){
			int val = Integer.parseInt(st.nextToken());
			if(plan[b] <= val ){
				res++;
			}
		}
		System.out.println(res);


	}
}

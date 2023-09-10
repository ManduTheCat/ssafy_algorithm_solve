package beak.simul.BJ10093NUM;

import java.util.*;
import java.io.*;
public class Main {
	static long N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=  new StringTokenizer(br.readLine());
		N = Long.parseLong(st.nextToken());
		M = Long.parseLong(st.nextToken());
		if( N == M){
			System.out.println(0);
			return;
		}
		else {

			System.out.println(Math.abs(N - M) -1 );
		}
		if( M < N){
			for(long num = M + 1; num < N; num++){
				System.out.print(num + " ");
			}
		}else {
			for(long num = N + 1; num  < M; num ++){
				System.out.print(num+ " ");
			}
		}
	}
}

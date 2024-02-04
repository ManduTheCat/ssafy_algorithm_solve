package silver.BJ11403;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 모든 경로 정점에 대학 경로 구하기 , 플로이드 와샬
public class Main {
	static int N;
	final static int MAX = 100_000_000;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int[][] adjArr =  new int [N][N];
		for(int row = 0; row < N; row++){
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int col = 0; col < N; col++){
				int val = Integer.parseInt(st.nextToken());
				if(val == 0) adjArr[row][col] = MAX;
				else adjArr[row][col] = val;
				//adjArr[row][col] = val;
			}
		}

		for(int k = 0; k < N; k++){
			for(int start = 0; start < N; start++){
				for(int end = 0; end < N; end++){
					if(adjArr[start][end] >0){
						adjArr[start][end] = Math.min(adjArr[start][end], adjArr[start][k]+ adjArr[k][end]);
					}

				}
			}
		}
		printArr(adjArr);
	}

	private static void printArr(int[][] adjArr) {
		for(int start = 0; start < N; start++){
			for(int end = 0; end < N; end++){
				int val = adjArr[start][end];
				if(val == MAX){
					System.out.print(0 + " ");
				}else System.out.print(1 + " ");
			}
			System.out.println();
		}
	}
}

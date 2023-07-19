package greedy.BJ3020.sol;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int H;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("resources/daily/greedy/BJ3020/input3.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		int [] countUp = new int[H +1];
		int [] countDown = new int[H+1];
		for(int n = 1; n <= N/2; n++){
			st = new StringTokenizer(br.readLine());
			int bottom = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			int up = Integer.parseInt(st.nextToken());
			countDown[bottom] +=1;
			countUp[up] += 1;
		}
		for(int n = H-1; n > 0; n--){
			countUp[n] += countUp[n + 1];
			countDown[n] += countDown[n + 1];
		}
		int min = Integer.MAX_VALUE;
		int [] res = new int[H +1];
		for(int h = 1; h <=H; h++){
			res[h] = countUp[h] + countDown[H-h+1];
			min = Math.min(res[h],min);
		}
		int count = 0;
		for(int i = 1; i <= H; i++){
			if(min == res[i]){
				count++;
			}
		}
		System.out.println(min + " " + count);
	}

}

package bronz.BJ168;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		// n 마리가 다 날라아가는데 걸리는 시간
		int res = 0;
		int countNum = 1;
		while (n > 0){
			if(countNum <= n){
				n -=countNum;
			}else {
				countNum = 1;
				n -=countNum;
			}
			countNum++;
			res++;

		}
		System.out.println(res);
	}

}

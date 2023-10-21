package silver.BJ3273;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.StringTokenizer;

public class Main {
	static int N;
	public static void main(String[] args) throws IOException {
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		Set<Integer> inputSet = new HashSet<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int n = 0; n < N; n++){
			int value = Integer.parseInt(st.nextToken());
			inputSet.add(value);

		}
		int taret = Integer.parseInt(br.readLine());
		int count = 0;
		for(int cur : inputSet){
			int findTarget = taret - cur;
			// 마이너스 면 안된다, 자기 자신이면 안된다
			if(findTarget > 0 && inputSet.contains(findTarget) && findTarget != cur){
				// System.out.println(cur+" + "+findTarget);
				count++;
			}
		}
		System.out.println(count/2);

	}
}

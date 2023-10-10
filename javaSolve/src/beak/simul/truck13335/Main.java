package beak.simul.truck13335;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int W;// 다리길이
	static int L;// 다리 하중

	public static void main(String[] args) throws IOException {
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		ArrayDeque<Integer> bridge = new ArrayDeque<>();
		Queue<Integer> left = new ArrayDeque<>();
		st = new StringTokenizer(br.readLine());
		for(int n = 0; n < N; n++){
			left.add(Integer.parseInt(st.nextToken()));
		}
		for(int blink = 0; blink < W; blink++){
			bridge.offer(0);
		}
		int time = 0;
		while (!left.isEmpty() || !bridge.isEmpty()){

			time++;
			if(!bridge.isEmpty()){
				if(bridge.peek() == 0){
					bridge.poll();
				}


			}
			System.out.println(bridgeConditionCheck(bridge));
			if(bridgeConditionCheck(bridge)){
				int curW = left.poll();
				bridge.offer(curW);
			}
			// 브릿지는 초마다 계속 이동한다.



		}
		System.out.println(time);
	}
	public static boolean bridgeConditionCheck(Queue<Integer> bridge){
		int sum = 0;
		for(int t : bridge){
			sum += t;
		}
		if(sum > L){
			return false;
		}
		if(bridge.size() > W){
			return false;
		}
		return true;
	}
}

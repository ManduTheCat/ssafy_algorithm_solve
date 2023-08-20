package greedy.BJ28703;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int [] inputs;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./resources/daily/greedy/BJ28703/input.txt"));
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		inputs = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		int initMax = Integer.MIN_VALUE;

		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for(int n = 0; n < N; n++){
			int value = Integer.parseInt(st.nextToken());
			initMax = Math.max(initMax,value);
			pq.add(value);
		}
		// System.out.println(pq);
		int max = initMax;
		int res = max - pq.peek();
		while (pq.peek() < initMax ){
			// 모든 수를 두배하는 순간  처음 (max - min ) 에서 2(init max - min) 가 된다
			// 즉 전부다 두배하면 최소가 될수가 없다. 그래서 초기 initMax 보다 작아야한다.
			int minCandidate = pq.poll();
			res = Math.min(max - minCandidate, res);
			int next = minCandidate * 2;
			max = Math.max(next, max);
			pq.offer(next);
		}
		res = Math.min(res, max - pq.peek());
		System.out.println(res);
	}
}

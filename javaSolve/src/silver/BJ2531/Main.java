package silver.BJ2531;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static int N;
	static int d; // 초밥의 가지수
	static int k; // 연속 해서 먹을 갯수
	static int c;
	static int max = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st  = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());


		Deque<Integer> susis = new ArrayDeque<>();
		for(int n = 0;  n < N; n++){
			int susi = Integer.parseInt(br.readLine());
			susis.add(susi);
		}
		//  연속으로 먹은 갯수 만큼 이동 하는데 전체 길이 만큼 반복한다.
		for(int n = 0; n < N; n++){
			int peek = susis.peekFirst();
			if (peek != c){
				max = Math.max(check(susis), max);
			}
			int first = susis.pollFirst();
			susis.offerLast(first);
		}
		System.out.println(max);

	}

	private static int check (Deque<Integer> susiLine){
		HashSet<Integer> susiSet = new HashSet<>();
		List<Integer> temp = new ArrayList<>();
		for(int i = 0; i < k; i++){
			int val = susiLine.pollFirst();
			//System.out.println("val:" + val);
			temp.add(val);
			susiSet.add(val);
		}
		for(int i = temp.size() -1 ; i >= 0; i--){ // 원복
			susiLine.addFirst(temp.get(i));
		}

		susiSet.add(c);
		//System.out.println(susiLine);
		return susiSet.size();
	}
}

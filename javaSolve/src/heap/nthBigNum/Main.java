package heap.nthBigNum;

import java.io.*;
import java.util.*;

public class Main {
	static int N;
	public static void main(String[] args) throws IOException{
		System.setIn(new FileInputStream("resources/daily/heap/nthBigNum/input.txt"));
		BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>((o, o1)-> o1-o);
		for(int row = 0; row < N; row++){
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int col = 0; col < N; col++){
				pq.offer(Integer.parseInt(st.nextToken()));
			}
		}
		int count = N;
		int res = 0;
		while (count-->0){
			res = pq.poll();
		}
		System.out.println(res);

	}
}

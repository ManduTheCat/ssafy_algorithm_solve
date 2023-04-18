package codingTest.gabia.coffe;

import java.util.PriorityQueue;

public class Solution {
	static class Coffee implements Comparable<Coffee> {
		int orderNumber;
		int finishTime;
		// 시간이 빨리 끝나는걸 먼저 뱉는다. 만약 시간이 같으면 주문순서를 이 낮은걸 먼저 처리한다.
		@Override
		public int compareTo(Coffee o) {
			if(this.finishTime == o.finishTime){
				return this.orderNumber - o.orderNumber;
			}
			else {
				return this.finishTime - o.finishTime;
			}
		}

		public Coffee(int orderNumber, int finishTime) {
			this.orderNumber = orderNumber;
			this.finishTime = finishTime;
		}
	}
	public int[] solution(int N, int[] coffee_times) {
		PriorityQueue<Coffee> pq = new PriorityQueue<>();
		int orderLen = coffee_times.length;
		int [] ans = new int[orderLen];
		for (int n = 0; n < N; n++) {
			pq.add(new Coffee(n + 1, coffee_times[n]));
		}
		int curIdx = N;
		int ansIdx = 0;
		while (!pq.isEmpty()){
			Coffee coffee = pq.poll();
			ans[ansIdx++] = coffee.orderNumber;
			if(orderLen > curIdx){
				// 새로들어온건 방금 나간것의 시간을 누산 시켜줘야 시간이 반영된다
				pq.offer(new Coffee(curIdx+1, coffee.finishTime + coffee_times[curIdx]));
				curIdx++;
			}
		}
		return ans;
	}
}

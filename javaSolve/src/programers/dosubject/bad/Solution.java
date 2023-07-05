package programers.dosubject.bad;

import java.util.*;
class Subject implements Comparable<Subject> {
	String name;
	int startTime;
	int endTime;
	Subject(String name, int startTime, int endTime){
		this.name = name;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append(this.name);
		sb.append(" ");
		sb.append(this.startTime);
		sb.append(" ");
		sb.append(this.endTime);
		sb.append(" ");
		return sb.toString();
	}
	@Override
	public int compareTo(Subject o){
		return this.startTime - o.startTime;
	}

}
public class Solution {
	public String[] solution(String[][] plans) {
		List<Subject > startOrderList = new ArrayList<>();
		for(String [] time : plans){
			String[] startTimeSplit = time[1].split(":");
			int startTime =
				Integer.valueOf(startTimeSplit[0])*60 + Integer.valueOf(startTimeSplit[1]);
			int endTime =
				startTime + Integer.valueOf(time[2]);
			startOrderList.add(new Subject(time[0], startTime, endTime));

		}
		Collections.sort(startOrderList);
		Deque<Subject> orderQ = new ArrayDeque(startOrderList);
		List<String> complete = new ArrayList<>();
		Stack<Subject> s = new Stack<>();

		while(!orderQ.isEmpty()){
			System.out.println(orderQ);
			Subject currTime = orderQ.poll();
			// System.out.println(currTime);
			if(orderQ.peek() == null){
				complete.add(currTime.name);
				break;
			}
			Subject nextTime = orderQ.peek();
			System.out.println(currTime + " vs " + nextTime );
			if(currTime.endTime > nextTime.startTime){
				// 지금 과제 끝나는시간보다 다음것 시작시간이 빠르다면
				System.out.println("다음게 빠릅니다");
				s.add(currTime);
			}
			else if(currTime.endTime < nextTime.startTime){
				// 지금 과제 끝나는시간보다 다음과제 시간이 더 뒤에 있다면 다음 과제를 진행
				System.out.println("과제가 끝났고 다음걸로 넘어가기전에 ");
				complete.add(currTime.name);
				if(!s.isEmpty()){
					// 다음과제를 할지 않할지 여기서 평가를 해야하나?
					orderQ.addFirst(s.pop());
				}
			}
			else{
				// 종료 시간이 같다면
				System.out.println("same time");
				complete.add(currTime.name);


			}
			System.out.println(s);

		}
		while(!s.isEmpty()){
			String name = s.pop().name;
			System.out.println(name);
			complete.add(name);
		}
		String[] res = complete.toArray(new String[0]);
		return res;
	}
}

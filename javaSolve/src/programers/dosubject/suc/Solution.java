package programers.dosubject.suc;

import java.util.*;

class Subject{
	String name;
	int startTime;
	int endTime;
	int remain;

	public Subject(String name, int startTime, int endTime, int remain) {
		this.name = name;
		this.startTime = startTime;
		this.endTime = endTime;
		this.remain = remain;
	}

	@Override
	public String toString() {
		return "Subject{" +
			"name='" + name + '\'' +
			", startTime=" + startTime/60 +":"  + startTime%60 +
			", endTime=" + endTime /60 + ":" + endTime %60 +
			", remain=" + remain +
			'}';
	}
}
public class Solution {
	static Map<Integer, Subject> subjectMap = new HashMap<>();
	static Set<Integer> orderListSet = new HashSet<>();
	public String[] solution(String[][] plans) {
		for(String[] sub : plans){
			System.out.println(Arrays.toString(sub));
			String[] subSplit = sub[1].split(":");
			int startTime = Integer.parseInt(subSplit[0]) * 60 + Integer.parseInt(subSplit[1]);
			int endTime = startTime + Integer.parseInt(subSplit[1]);
			int remain = Integer.parseInt(subSplit[1]);
			subjectMap.put(startTime, new Subject(sub[0],startTime, endTime, remain ));
			orderListSet.add(startTime);
			orderListSet.add(endTime);
		}
		List<Integer> orderList = new ArrayList<>(orderListSet);
		List<Subject> res = new ArrayList<>();
		Stack<Subject> waitStack = new Stack<>();
		Collections.sort(orderList);
		Queue<Integer> orderQ = new ArrayDeque<>(orderList);
		System.out.println(subjectMap);
		// System.out.println(orderList);
		while (!orderQ.isEmpty()){// 순서를 큐로 바꾼다면
			int currTime = orderQ.poll();
			if(orderQ.peek() == null){
				if(subjectMap.containsKey(currTime)) res.add(subjectMap.get(currTime));
				break; // 이게 마지막이면 멈춰야한다.
			}
			int nextTime = orderQ.peek();
			if(subjectMap.containsKey(currTime)){
				// 이시간대에 할수 있는 과제가 있다.
				// 비교 해야한다
				Subject currSubject = subjectMap.get(currTime);

				if(currTime + currSubject.remain <= nextTime){
					// 다음시간이 더크다 = 다음시간보다빨리 끝나거나 일치하면
					res.add(currSubject);
					System.out.println(res);
				}else {
					// 넣기 전에 짤라야한다.
					System.out.println("스텍에 들어갑니다 " + (currTime +currSubject.remain) /60 +":" + (currTime +currSubject.remain) %60
						+" vs "+ nextTime/60 + ": " + nextTime%60);
					currSubject.remain = currSubject.remain - (nextTime - currTime);
					waitStack.add(subjectMap.get(currTime));
				}

			}else {
				// 이시간대에 할수 있는 과제가 없다
				// 스텍에서 가져온다.
				waitStack.pop();

			}
		}
		return null;
	}

}

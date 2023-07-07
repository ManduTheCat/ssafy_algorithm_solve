package programers.dosubject.suc;

import java.util.*;

class Subject implements Comparable<Subject> {
	public int startTime, endTime, remain;
	public String name;

	public Subject(String name, int startTime, int endTime, int remain) {
		this.startTime = startTime;
		this.endTime = endTime;
		this.remain = remain;
		this.name = name;
	}

	@Override
	public String toString() {
		return name + "[ " + startTime + " ] " +
			+startTime / 60 + ":" + startTime % 60 +
			"~" + endTime / 60 + ":" + endTime % 60 +
			", remain=" + remain + '\'';
	}

	@Override
	public int compareTo(Subject o) {
		return o.startTime - this.startTime;
	}
}

public class Solution {
	static Map<Integer, Subject> subMap;

	public String[] solution(String[][] plans) {
		PriorityQueue<Integer> timePq = new PriorityQueue<>();
		subMap = new HashMap<>();
		for (String[] subjectArr : plans) {
			String name = subjectArr[0];
			String[] startSplit = subjectArr[1].split(":");
			int remain = Integer.parseInt(subjectArr[2]);
			int startTime = Integer.parseInt(startSplit[0]) * 60 + Integer.parseInt(startSplit[1]);
			int endTime = startTime + remain;
			timePq.add(startTime);
			subMap.put(startTime, new Subject(name, startTime, endTime, remain));
		}
		Stack<Subject> waitStack = new Stack<>();
		List<Subject> complete = new ArrayList<>();
		while (!timePq.isEmpty()) {
			int currTime = timePq.poll();
			if (timePq.peek() == null) {
				complete.add(subMap.get(currTime));
				break;
			}
			int nextTime = timePq.peek();
			if(currTime == nextTime){
				continue;
			}
			if (subMap.containsKey(currTime)) {

				//현제 진행가능한 과제가 존재한다면.
				Subject currSubject = subMap.get(currTime);
				// System.out.println(currSubject + " -> " + nextTime + " :" + timePq);
				if (subMap.containsKey(nextTime)) {
					// 다음걸 확인 해야하늗데
					// 다음시간에 가능한 과제가 존재 할수도 없을수도 있다.
					// 있다면 비교한다.
					// 다음시간을 보는게 문제가 있는듯?
					Subject nextSubject = subMap.get(nextTime);
					// 현제 과제가 다음시간 전에 가능한지 불가능한지 확인한다.
					if (currTime + currSubject.remain <= nextSubject.startTime) {
						// 같거나 작으면 현제 과제 완료 가능하다
						// System.out.println("넣습니다 " + (currTime  + currSubject.remain) +" vs "+ nextSubject.endTime);
						complete.add(currSubject);
						subMap.remove(currSubject.startTime);
						timePq.add(currTime + currSubject.remain); // 종료시간을 타임라인에 넣는다.
					} else {
						// 불가능하다면 자르고 stack 에 넣는다.
						currSubject.remain = currSubject.remain - (nextSubject.startTime - currTime);
						waitStack.add(currSubject);
					}
				}
			} else {
				// 현제 시간에 진행 가능한 과제가 없다면.
				if (!waitStack.isEmpty()) {
					Subject currSubject = waitStack.pop();
					Subject nextSubject = subMap.get(nextTime);
					if (currTime + currSubject.remain <= nextSubject.startTime) {
						complete.add(currSubject);
						subMap.remove(currSubject.startTime);
						timePq.add(currTime + currSubject.remain);
					} else {
						currSubject.remain = currSubject.remain - (nextSubject.startTime - currTime);
						waitStack.add(currSubject);
					}

				}
				// 스택이 비어 있으면 그냥 진행해라
			}
		}
		// System.out.println(complete);
		// System.out.println(waitStack);
		while (!waitStack.isEmpty()){
			complete.add(waitStack.pop());
		}
		String[] answer = new String[complete.size()];
		int idx = 0;
		for(Subject subject : complete){
			answer[idx++] = subject.name;
		}
		return answer;
	}
}

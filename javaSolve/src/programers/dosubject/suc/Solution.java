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
		List<String> complete = new ArrayList<>();
		while (!timePq.isEmpty()) {
			int currTime = timePq.poll();
			if (timePq.peek() == null) {
				complete.add(subMap.get(currTime).name);
				break;
			}
			int nextTime = timePq.peek();
			if(currTime == nextTime){// 로직상 중복된 시간이 들어갈수 있다
				continue;
			}
			//지금 시간에 진행가능한 과제가 있는지 판단한다.
			if (subMap.containsKey(currTime)) {
				// 1.현제 진행가능한 과제가 존재한다면.
				Subject currSubject = subMap.get(currTime);
				if (subMap.containsKey(nextTime)) {
					// 있다면 비교한다.
					Subject nextSubject = subMap.get(nextTime);
					// 현제 과제가 다음시간 전에 가능한지 불가능한지 확인한다.
					if (currTime + currSubject.remain <= nextSubject.startTime) {
						// 같거나 작으면 현제 과제 완료 가능하다
						complete.add(currSubject.name);
						subMap.remove(currSubject.startTime);
						timePq.add(currTime + currSubject.remain); // 진행한 만큼 시간이 지났다
					} else {
						// 불가능하다면 자르고 stack 에 넣는다.
						currSubject.remain = currSubject.remain - (nextSubject.startTime - currTime);
						waitStack.add(currSubject);
					}
				}
			} else {
				// 현제 시간에 진행 가능한 과제가 없다면. 스텍에 꺼내 가능한지 불가능한지 확인하다.
				if (!waitStack.isEmpty()) {
					Subject currSubject = waitStack.pop();
					Subject nextSubject = subMap.get(nextTime);
					if (currTime + currSubject.remain <= nextSubject.startTime) {
						complete.add(currSubject.name);
						subMap.remove(currSubject.startTime);
						timePq.add(currTime + currSubject.remain);
					} else {
						currSubject.remain = currSubject.remain - (nextSubject.startTime - currTime);
						waitStack.add(currSubject);
					}

				}
				// 스택이 비어 있는경우  대기중인 과제가 없다는거다 다음시간을 확인
			}
		}
		while (!waitStack.isEmpty()){
			complete.add(waitStack.pop().name);
		}
		String[] answer = complete.toArray(new String[0]);

		return answer;
	}
}

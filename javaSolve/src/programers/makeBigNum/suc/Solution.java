package programers.makeBigNum.suc;

import java.util.*;

// 큰수만 스텍에 넣는다
public class Solution {
	public String solution(String number, int k) {
		Stack<Integer> stack = new Stack<>();
		int count = k;
		String[] splited = number.split("");
		for (String st : splited) {
			int value = Integer.parseInt(st);
			while (!stack.isEmpty() && stack.peek() < value && count-- > 0) {
				stack.pop();
			}
			stack.push(value);
		}
		String answer = "";
		System.out.println(k);
		for (int i = 0; i < number.length() - k; i++) {
			answer += stack.get(i);
		}
		return answer;
	}
}
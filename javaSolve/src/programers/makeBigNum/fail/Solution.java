package programers.makeBigNum.fail;

import java.util.*;
//정렬하고 len-k 갯수 만큼 추출 돌면서 해당되면 지우기
public class Solution {
	public String solution(String number, int k) {
		// int count = number.length() -k;
		int count = k;
		String [] splited = number.split("");
		PriorityQueue<Integer> q = new PriorityQueue<>();
		for(String s :splited){
			q.offer(Integer.parseInt(s));
		}
		Set<Integer> banSet = new HashSet<>();
		while(count-- > 0){
			banSet.add(q.poll());
		}
		// 제일 작은 len-k 를 구한다 -> 갯수만큼 제외한다...
		// 갯수가 안맞는 경우는? -> 숫자를 뽑아 왔는데 갯수가 부족한경우
		// 많이 있을수 있는데 부족할수 없다 중복된 경우가 많은 경우 작으면 애초에 와일문 에서 성립이 안됨
		int rmCount = k;
		StringBuilder sb = new StringBuilder();
		int idx = 0;
		while(rmCount > 0 && idx < number.length()){
			int value = Integer.parseInt(splited[idx]);
			if(banSet.contains(value)){
				rmCount--;
			}else{
				sb.append(value);// 포함이 안되면 넣어라
			}
			idx++;
		}
		for(;idx < number.length(); idx++){
			sb.append(splited[idx]);
		}
		int totalCount = number.length() - k;
		StringBuilder res = new StringBuilder();
		int resIdx = 0;
		while(totalCount-- >0){
			res.append(sb.toString().charAt(resIdx++));

		}
		System.out.println(banSet);
		return res.toString();
	}
}
package programers.kakao.nGame;

import java.util.*;
public class Solution {
	public String solution(int n, int t, int m, int p) {
		int count = 0;
		int num = 0;
		StringBuilder res = new StringBuilder();
		Queue<String> q = new ArrayDeque<>();
		while (count < t){ // t번 내차래가 올때까지 돈다.
			for(int order = 1; order <= m; order ++){// 전체 순서를 한번 돈다.
				String[] bin = Integer.toString(num, n).split("");
				num++;// 돌면서 숫자를 쪼개고
				for(String s:bin){
					q.offer(s); // 큐에 넣는다
				}
				String val = q.poll();// 하나씩 꺼낸다
				if(order == p){
					res.append(val.toUpperCase());
					count++;
				}
			}
		}
		return res.toString();
	}
}
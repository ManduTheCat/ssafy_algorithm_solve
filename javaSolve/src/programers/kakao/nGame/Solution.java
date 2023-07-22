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
/**
 *
 테스트 1 〉	통과 (0.39ms, 72.8MB)
 테스트 2 〉	통과 (0.77ms, 75MB)
 테스트 3 〉	통과 (1.09ms, 70.8MB)
 테스트 4 〉	통과 (1.46ms, 73.1MB)
 테스트 5 〉	통과 (7.39ms, 78.4MB)
 테스트 6 〉	통과 (9.85ms, 81.9MB)
 테스트 7 〉	통과 (14.97ms, 75.8MB)
 테스트 8 〉	통과 (7.00ms, 77.7MB)
 테스트 9 〉	통과 (7.96ms, 80.6MB)
 테스트 10 〉	통과 (7.25ms, 70.2MB)
 테스트 11 〉	통과 (7.79ms, 78.6MB)
 테스트 12 〉	통과 (5.94ms, 81.3MB)
 테스트 13 〉	통과 (8.63ms, 83.2MB)
 테스트 14 〉	통과 (202.62ms, 156MB)
 테스트 15 〉	통과 (155.25ms, 168MB)
 테스트 16 〉	통과 (172.47ms, 149MB)
 테스트 17 〉	통과 (23.42ms, 88.7MB)
 테스트 18 〉	통과 (18.74ms, 83.1MB)
 테스트 19 〉	통과 (11.08ms, 88.9MB)
 테스트 20 〉	통과 (20.19ms, 93.3MB)
 테스트 21 〉	통과 (103.86ms, 108MB)
 테스트 22 〉	통과 (40.65ms, 93.9MB)
 테스트 23 〉	통과 (83.58ms, 116MB)
 테스트 24 〉	통과 (130.78ms, 143MB)
 테스트 25 〉	통과 (182.58ms, 181MB)
 테스트 26 〉	통과 (31.79ms, 113MB)
 */
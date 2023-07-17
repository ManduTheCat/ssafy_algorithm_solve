package programers.kakao.cach;

import java.util.*;
public class Solution {
	public int solution(int cacheSize, String[] cities) {
		//linked list
		// 히트면
			// 지우고 offer 크기는 유지된다는 가정
		// 미스면
			// 크기넘기면 poll 하고 offer
			// 사이즈 보다 작으면 그냥 offer
		int res = 0;
		LinkedList<String> cache = new LinkedList<>();
		for(String data : cities){ // 전체 시간 복잡도 O(N * cachSize)
			data = data.toUpperCase();
			int idx = cache.indexOf(data);
			if(cacheSize == 0){
				// cache 크기가 0 이면 무조건 미스다
				res +=5;
				continue;
			}
			if(idx != -1){
				// 히트
				res +=1;
				cache.remove(idx);
				cache.offer(data);
			}else{
				// 미스
				res +=5;
				if(cache.size() >= cacheSize){
					//크기 넘기면 옛날꺼 빼고 넣는다.
					cache.poll();
					cache.offer(data);
				}else{
					cache.offer(data);
				}
			}
		}
		return res;
	}
}
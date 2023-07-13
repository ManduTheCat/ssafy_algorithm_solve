package programers.pressString;

import java.util.*;
// 최대 테스트 10 〉	통과 (25.85ms, 89.5MB)
public class Solution {
	public int solution(String s) {
		int minLen = Integer.MAX_VALUE;
		for(int groupLen = 1; groupLen < s.length(); groupLen++ ){
			List<String> subRes = new ArrayList<>();
			int startIdx = 0;
			int endIdx = startIdx + groupLen;
			int groupLimit = s.length()/groupLen;
			while(endIdx <= s.length() -1){
				subRes.add(s.substring(startIdx, endIdx));
				endIdx = endIdx+groupLen;
				startIdx = startIdx + groupLen;
			}
			subRes.add(s.substring(startIdx, s.length()));
			String res = "";
			int count = 1;
			for(int curIdx = 0; curIdx < subRes.size(); curIdx++){
				String currStr = subRes.get(curIdx);
				int nextIdx = curIdx + 1;
				if(curIdx == subRes.size()-1){
					// 나머지 기록해라
					if(count != 1) res+=count;
					res+=currStr;
					break;
				}
				String nextStr = subRes.get(nextIdx);
				if(currStr.equals(nextStr)){
					count++;
				}else{
					if(count != 1) res+=count;
					res+=currStr;
					count = 1;
				}
			}
			minLen = Math.min(minLen,res.length());
		}
		// 마지막에 전체 문장길이랑비교해야함
		minLen = Math.min(minLen,s.length());
		return minLen;
	}
}
package programers.pressString;

import java.util.*;
// 최대 테스트 10 〉	통과 (25.85ms, 89.5MB)

/**
 * 로직
 * 1.문자열 쪼개는 크기 점차 늘려간다-> 2.자른것 돌면서 자기랑 같으면 갯수를 샌다.
 * 자르는 크기를 기준으로 startIdx,endIdx 이동하며 subRes 에 기록한다
 * subRes = a,b,c,d
 * subRes = ab,cd
 * subRes = abc,d
 * + 마지막 부분은 갯수가 맞아떨어지지 않으면 남기에 따로 끝나고 추가해준다
 * 순서로 진행
 * 2.자른것 돌면서 자기랑 같으면 갯수를 샌다.
 * 	돌면서 다음것과 같으면 압축 갯수 (count ++)
 * 	다음것과 다르면 압축 갯수 1 로 초기화 하고 현재 문자열 res 에 기록
 *  res 문자열 길이로 최소 갱신
 * 전체 크기를 빼고 비교해서 마지막에 전체 크기비교를 해줘야한다.
 *
 */
public class Solution {
	public int solution(String s) {
		int minLen = Integer.MAX_VALUE;
		// 1.쪼개는 부분
		// 문자열 쪼개는 크기 for 로 변경
		for(int groupLen = 1; groupLen < s.length(); groupLen++ ){
			List<String> subRes = new ArrayList<>();
			int startIdx = 0;
			int endIdx = startIdx + groupLen;
			while(endIdx <= s.length() -1){
				subRes.add(s.substring(startIdx, endIdx));
				endIdx = endIdx+groupLen;
				startIdx = startIdx + groupLen;
			}
			// 마지막것 누락 될수 있으니 추가
			subRes.add(s.substring(startIdx, s.length()));
			String res = ""; // 압축 결과 기록하는 변수
			int count = 1; // 시작 압축카운터는 1로

			//2. 압축 갯수를 새는 부분
			for(int curIdx = 0; curIdx < subRes.size(); curIdx++){
				String currStr = subRes.get(curIdx);// 지금것과 다음것 비교할거다
				int nextIdx = curIdx + 1; // 다음것 인덱스
				if(curIdx == subRes.size()-1){
					// 만약 마지막 인덱스면 다음것 없으니 지금까지 압축갯수 샌거 랑 문자열 기록해라
					if(count != 1) res+=count;
					res+=currStr;
					break;
				}
				String nextStr = subRes.get(nextIdx);// 다음 문자열 조각
				if(currStr.equals(nextStr)){ // 같다면
					count++;// 압축 갯수를 올려라
				}else{// 다음것과 다르면 기록하고 압추카운터 초기화해라
					if(count != 1) res+=count;
					res+=currStr;
					count = 1;
				}
			}
			minLen = Math.min(minLen,res.length()); // 마지막 전체 문자열과 비료
		}
		minLen = Math.min(minLen,s.length());
		return minLen;
	}
}
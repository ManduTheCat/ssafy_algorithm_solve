package additionalClass.daiilyAlgo.week5.bracketTransform;

import java.util.StringTokenizer;

public class Solution {
    public String solution(String p) {
    	transform(p);
    	
        return null;
    }

	private void transform(String p) {
		if(balanceString(p)) {
			//균형잡혀 있따면  올바른 괄호로 바꿀수 있다.
		}
	}

	private boolean balanceString(String p) {
		int leftCount = 0;
		int rightCount = 0;
		for(int i = 0; i < p.length(); i++) {
			char currChar = p.charAt(i);
			if(currChar == '(')leftCount++;
			else rightCount++;
		}
		if(leftCount != rightCount ) return false;
		return true;
		
	}
    
}

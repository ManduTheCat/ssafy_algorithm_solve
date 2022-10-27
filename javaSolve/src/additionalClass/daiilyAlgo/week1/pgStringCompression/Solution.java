package additionalClass.daiilyAlgo.week1.pgStringCompression;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    static Map<String, Integer> patternCount;
    public int solution(String s) {
        int ans = 0;

        //i 번째 문자로 시작하는 패턴을 찾는다.// 같은 단위로자른다.그러니까 2개 로 잘랐는데 갑자기 3개가 나오진않는다.
        System.out.println("target " + s);
        for (int patternLen = 1; patternLen < s.length(); patternLen++) {
            // 방식을 바꿔야한다 2개씩 자르기 3개씩 자르기
            patternCount = new HashMap<>();
            for (int idx = 0; idx < s.length()- patternLen  + 1; idx++) {
                String startPattern = s.substring(idx, idx + patternLen);
                System.out.println(startPattern);
                check(s.length(), s,startPattern);
            }

            System.out.println(patternCount);
        }

        return ans;
    }
    //aabbaccc
    // 길이만큼 이동하자 지금 이상하게 되는중
    private void check( int end, String target, String startPattern) {
        // 돌면서 몇개 있는지 찾는다.
        for (int idx = 0; idx < end -  startPattern.length()+1; idx++) {
            String compare = target.substring(idx,idx+startPattern.length());
            System.out.println(startPattern + " " + compare);
            if(startPattern.equals(compare)){
                //System.out.println("in");
                if(!patternCount.containsKey(compare)){
                    patternCount.put(compare, 1);
                }else patternCount.put(compare, patternCount.get(compare)+1);

            }
        }
    }
}

package additionalClass.daiilyAlgo.week1.pgStringCompression2;

import java.util.ArrayList;

// 목요일 처음 문제를 잘못 이해해서 4시간 동안 풀고 실패함 생각시간: 1시간
// 토요일 5시반부터 재도전 해서 3시간 만에품 생각시간 : 30분
public class Solution {
    static String res; // 압축된 결과를 담을 변수
    static int min; // 최소값을 기록할 변수

    public int solution(String s) {
        if(s.length() == 1){
            //1 일때 처리 안하면 min 이 그대로나옴
            return 1;
        }
        min = Integer.MAX_VALUE;
        int lenS = s.length();
        for (int len = 1; len < lenS / 2 + 1; len++) {// 쪼갤 길이를 바꿔가며 패턴 모음집을 만든다.
            res = "";//
            ArrayList<String> splited = split(len, s);
            int selectedIdx = 0;
            while (selectedIdx < splited.size()){//처음에 이부분 재귀로 만들수 있을거 같은데 시도하다 오래걸려서 while로 완성
                // 만약 자기와 다른 패턴을 만나면 다시 그패턴부터 같은 패턴을찾는다
                // count 함수는 다른패턴을 만나면 해당 인덱스 번호를 리턴해준다
                selectedIdx = count(selectedIdx , splited);
            }
            min = Math.min(res.length(), min);
        }
        return min;
    }


    private int count(int selectedIdx, ArrayList<String> splited) {
       // 탐색 대상의 다음 패턴이 다른 패턴이면 다음친구 인덱스를 리턴한다
        int count  = 1;
        for (int i = selectedIdx; i < splited.size()-1; i++) {
            if(!splited.get(i).equals(splited.get(i+1))) {
                res += (count > 1? count:"")  + splited.get(selectedIdx);
                return i +1;
            }
            count++;
        }
        // 끝까지 돌았다면 마지막이라는 소리니까 패턴 모음집길이를 리턴해 while 을 정지한다.
        res += (count > 1? count:"") + splited.get(selectedIdx);
        return splited.size();

    }
    
    private ArrayList<String> split(int len, String s) {
        ArrayList<String> res = new ArrayList<String>();
        // 입력받은 패턴 길이 만큼 잘라주는 함수
        int idx = 0;
        for (int i = 0; i < s.length() / len; i++) {
            res.add(s.substring(idx, idx + len));
            idx += len;
        }
        // 다잘랐는데 나머지가 생기는 경우가 있다 aaabb 를 3개씩 쪼개면 bb 가 남는다
        // 남는걸 리스트에 넣어준다
        if (!s.substring(idx, idx + s.length() % len).equals("")) {
            res.add(s.substring(idx, idx + s.length() % len));
        }
        return res;
    }

}

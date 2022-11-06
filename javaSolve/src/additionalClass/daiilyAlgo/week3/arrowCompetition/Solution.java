package additionalClass.daiilyAlgo.week3.arrowCompetition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution {
    static int [] combinationRes;
    static int N;
    static int [] INFO;
    static ArrayList<int[]> lionWinCase;// lion 이 이긴 결과들을 저장해 놓은 ArrayList
    static PriorityQueue<ShotListANDDiff> findMaxQue = new PriorityQueue<>();
    public int[] solution(int n, int[] info) {
        N = n;
        INFO = info.clone();
        lionWinCase = new ArrayList<int[]>();
        // 타겟점수 를선택하는 컴비네이션 구하기
        // i번째 화살이 면번 타겟을 맞췄는지
        combinationRes = new int[N]; // 0~11
        combination(0, 0);

        findMaxDiff();
        return getShotMinTarget();

    }

    private int[] getShotMinTarget() {
        if(lionWinCase.size() == 0){
            return new int[]{-1};
        }
        if(lionWinCase.size() == 1){
            return  lionWinCase.get(0);
        }

        // 여러개라면 인덱스를 가중치로 했을때 점수가 높은 친구를 리턴한다.
        int [] maxRes = new int[11];
        int maxWightScore = 0;
        for(int [] res:lionWinCase){
            int currWightScore = 0;
            for (int i = 0; i < res.length; i++) {
                currWightScore += (i + 1)* res[i];
            }
            if(currWightScore > maxWightScore){
                maxRes = res.clone();
                maxWightScore = currWightScore;
            }


        }
        return maxRes;

    }

    private void findMaxDiff() {
        // 제일 큰 친구와 같은 경우 구하기
        if(findMaxQue.isEmpty()){
            return;
        }
        int maxDiff = findMaxQue.peek().diff;
        while (!findMaxQue.isEmpty()){
            ShotListANDDiff curr = findMaxQue.poll();
            if(curr.diff == maxDiff){
                lionWinCase.add(curr.lionRes);
            }
        }
        // 같은 최대점수차 끼리 가장 작은 점수를 많이 쓴 경우를 찾는 함수
    }

    private void combination(int depth , int start) {
        if(depth ==N){
            //System.out.println(Arrays.toString(combinationRes));
            calculateScore();
            return;
        }
        // 중복을 허용하는 컴비네이션
        for (int i = start; i < 11; i++) {
            combinationRes[depth] = i;
            combination(depth+1, i);
        }
    }

    private void calculateScore() {
        int[] res = new int[11];
        int lionScore = 0;
        int apeachScore = 0;

        // 10 - i 인덱스에 값이 들어간다.
        for(int targetScore : combinationRes){
            res[10 - targetScore] +=1;
        }
        //System.out.println("res : "+Arrays.toString(res));
        //System.out.println("info : "+ Arrays.toString(INFO));
        //큰쪽이 점수가져간다
        // 같으면 // 0일때 점수 없다 // 0 이 아니면 어피치가 점수가져간다.
        for (int i = 0; i < 11; i++) {
            if(res[i] ==  INFO[i]){
                // 0 이 아닐떄만 점수를 올려라
                if(res[i] != 0) apeachScore+=10-i;
            } else if (res[i] > INFO[i]) {
                // 라이언이 더 많이 쐈다면
                lionScore+=10-i;
            } else if (res[i]< INFO[i]) {
                // 어피치가 더 많이 쐇다면
                apeachScore+=10-i;
            }
        }
        //System.out.println( "어피치점수 : "+ apeachScore + " 라이언 점수 : " + lionScore + (lionScore > apeachScore? " 라이언 우승":" 어피치 우승"));
        if(lionScore > apeachScore){
            findMaxQue.offer(new ShotListANDDiff(lionScore - apeachScore, res));
        }
    }
    static class ShotListANDDiff implements Comparable<ShotListANDDiff>{
        int diff;
        int [] lionRes;

        public ShotListANDDiff(int diff, int[] lionRes) {
            this.diff = diff;
            this.lionRes = lionRes;
        }

        @Override
        public int compareTo(ShotListANDDiff o) {
            return o.diff - this.diff;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("ShotListANDDiff{");
            sb.append("diff=").append(diff);
            sb.append(", lionRes=").append(Arrays.toString(lionRes));
            sb.append('}');
            return sb.toString();
        }
    }



}

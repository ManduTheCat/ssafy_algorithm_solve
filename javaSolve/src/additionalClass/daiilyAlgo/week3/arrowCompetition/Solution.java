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
        // i번째 화살이 몇번 타겟을 맞췄는지
        combinationRes = new int[N]; // 0~11
        // 화살을 쏘는 경우의 수를 구하는 함수
        // 경우의 수가 나올때마다 점수를 계산
        // info 와 같은 array 를 만든다
        combination(0, 0);
        //priorityQueue 를 활용해 가장 차이가 많이나는 이기는 경우의 수를 찾는다.
        findMaxDiff(); // 
        // 결과를 리턴하기 전에
        // 만약 이기는 경우가 여러개일때 작은 점수를 많이쏜 2가지 경우를 찾는 함수

        return getShotMinTarget();

    }

    private int[] getShotMinTarget() {
        if(lionWinCase.size() == 0){
            return new int[]{-1};
        }
        if(lionWinCase.size() == 1){
            return  lionWinCase.get(0);
        }
        // 같은 점수일땐 작은 점수를 많이 쏜 경우를 반환해야함으로
        // 인덱스를 가중치로  삼아 했을때 점수가 높은 친구를 리턴한다.
        int [] maxRes = new int[11];
        int maxWightScore = 0;
        for(int [] res:lionWinCase){
            int currWightScore = 0;
            for (int i = 0; i < res.length; i++) {
                currWightScore += (i + 1)* res[i]; // 0을 곱하면 안되서 +1
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
            calculateScore();// 경우의 수가 나올때마다 점수를 계산
            return;
        }
        // 중복을 허용하는 컴비네이션
        for (int i = start; i < 11; i++) {
            combinationRes[depth] = i;
            combination(depth+1, i);
        }
    }

    private void calculateScore() {
        // 화살을 쏜 결과를 가지고 점수를 계산하는 함수
        int[] res = new int[11];
        int lionScore = 0;
        int apeachScore = 0;

        for(int targetScore : combinationRes){
            res[10 - targetScore] +=1;
        }
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
        if(lionScore > apeachScore){
            findMaxQue.offer(new ShotListANDDiff(lionScore - apeachScore, res));
        }
    }

    // 차와 화살을쏜 결과를 보관하기 위한 class  pq 사용하기 위해 comparable implements
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

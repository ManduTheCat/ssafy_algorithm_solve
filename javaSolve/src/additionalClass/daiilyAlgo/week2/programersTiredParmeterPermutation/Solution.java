package additionalClass.daiilyAlgo.week2.programersTiredParmeterPermutation;

import java.util.Arrays;

// Permutation 풀이
public class Solution {
    int[][] permutationRes;
    int dungeonLen;
    boolean [] check;
    int K;
    int maxSearchCount = 0;
    public int solution(int k, int[][] dungeons) {
        permutationRes = new int[dungeons.length][];
        K = k;
        dungeonLen = dungeons.length;
        check = new boolean[dungeonLen];
        permutation(0, dungeons);
        return maxSearchCount;
    }
    
    // 가능한 방문순서 구하는순열 최대 8! 4만정도
    private void permutation(int depth, int[][] dungeons) {
        if(depth == dungeonLen){
            System.out.println(Arrays.deepToString(permutationRes));
            maxSearchCount = Math.max(maxSearchCount, countingSearch());
            return;
        }
        for (int i = 0; i < dungeonLen; i++) {
            if(!check[i]){
                check[i] = true;
                permutationRes[depth] = dungeons[i].clone();
                permutation(depth+1, dungeons);
                check[i] = false;
            }
        }
    }

    // 현재 경우의 수에서 가능한 방문 깊이를 구하는 (searchCount) 함수
    private int countingSearch() {
        int currK = K;
        int searchCount = 0;
        for(int [] dungeon : permutationRes){
            searchCount+=1;
            int requiredTired = dungeon[0]; // 요구하는 피로도
            int payTired = dungeon[1]; // 소모하는 피로도
            if(currK < requiredTired) {// 방문이 불가능하면
                searchCount-=1; // 카운트 까고 
                return searchCount; // 리턴한다
            }
            currK-=payTired; // 피로도 소모
        }
        return searchCount; // 다도는경우 리턴

    }
}

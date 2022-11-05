package additionalClass.daiilyAlgo.week2.programersTiredParmeterPermutation;

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

    private void permutation(int depth, int[][] dungeons) {
        if(depth == dungeonLen){
            //System.out.println(Arrays.deepToString(permutationRes));
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

    private int countingSearch() {
        int currK = K;
        int searchCount = 0;
        for(int [] dungeon : permutationRes){
            searchCount+=1;
            int requiredTired = dungeon[0];
            int payTired = dungeon[1];
            //System.out.println(currK + "vs" + requiredTired);
            if(currK < requiredTired) {
                //System.out.println("isBreaked");
                searchCount-=1;
                return searchCount;
            }
            currK-=payTired;
        }
        return searchCount;

    }
}

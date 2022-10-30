package additionalClass.daiilyAlgo.week2.programersTiredParmeter;


import java.util.ArrayList;

public class Solution {
    static int K;
    static ArrayList [] adjList;
    // dungeons 의 인덱스가 노드 의 번호
    static ArrayList <Dungeon> dungeonList;
    static boolean check[];
    static int maxDepth;


    public int solution(int k, int[][] dungeons) {
        K = k;
        adjList = new ArrayList[dungeons.length];
        dungeonList = new ArrayList<>();
        check = new boolean[dungeons.length];
        maxDepth = 0;
        for (int nodeNum = 0; nodeNum < dungeons.length; nodeNum++) {
            adjList[nodeNum] = new ArrayList<Integer>();
            dungeonList.add(new Dungeon(dungeons[nodeNum][0], dungeons[nodeNum][1]));
        }
        // 자기 자신을 제외한 모든 노드가 연결된 포롸 그래프 adjList 생성
        for (int nodeNum = 0; nodeNum < dungeons.length; nodeNum++) {
            for (int contectedNodeNum = 0; contectedNodeNum < dungeons.length; contectedNodeNum++) {
                if(nodeNum != contectedNodeNum) adjList[nodeNum].add(contectedNodeNum);
            }
        }

        //System.out.println(Arrays.toString(adjList));
        //System.out.println(dungeonList);

        for (int i = 0; i <dungeons.length ; i++) {
            check[i] = true;
            dfs(i, K,1);
            check[i] = false;
        }
        //System.out.println(maxDepth);
        return maxDepth;
    }

    private void dfs(int startNode, int k, int depth) {
        maxDepth = Math.max(maxDepth, depth);
        //check[startNode] = true;
        ArrayList<Integer> nextNodeList = adjList[startNode];
        for (int nextNodeIndex = 0; nextNodeIndex < nextNodeList.size() ;nextNodeIndex++) {
            Dungeon nextNode = dungeonList.get(nextNodeIndex);
            if(!check[nextNodeIndex] && k >= nextNode.requiredTired ){
                //System.out.println(k + " vs " + nextNode);

                dfs(nextNodeIndex, k- nextNode.spendTired, depth + 1);
                //check[startNode] = false;
            }
        }

    }
    static class Dungeon{
        int requiredTired;
        int spendTired;

        public Dungeon(int requiredTired, int spendTired) {
            this.requiredTired = requiredTired;
            this.spendTired = spendTired;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Dungeon{");
            sb.append("requiredTired=").append(requiredTired);
            sb.append(", spendTired=").append(spendTired);
            sb.append('}');
            return sb.toString();
        }
    }


}

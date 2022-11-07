package additionalClass.daiilyAlgo.week2.programersTiredParmeter;


import java.util.ArrayList;

public class Solution {
    static int K;
    static ArrayList[] adjList;
    // dungeons 의 인덱스가 노드 의 번호
    static ArrayList<Dungeon> dungeonList;
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
                if (nodeNum != contectedNodeNum) adjList[nodeNum].add(contectedNodeNum);
            }
        }

        for (int i = 0; i < dungeons.length; i++) {
            //피로도가 충분한지 검사
            if(K >= dungeonList.get(i).requiredTired){
                check[i] = true; //80
                // 들어갈때 빼줘야했다.

                dfs(i, K-dungeonList.get(i).spendTired, 1);
                check[i] = false;
            }
        }

        return maxDepth;
    }

    private void dfs(int startNode, int k, int depth) {
        maxDepth = Math.max(maxDepth, depth);
        //dfs 가 재대로 돌지 않는거 같은데 .. 리턴이 없어서 그런건가?
        //check[startNode] = true;

        // type check
        ArrayList<Integer> nextNodeList = adjList[startNode];// 다음방문 가능한adj 리스트 가져오기
        for (int nextNodeIndex = 0; nextNodeIndex < nextNodeList.size(); nextNodeIndex++) {
            Dungeon nextNode = dungeonList.get(nextNodeList.get(nextNodeIndex));
            if (!check[nextNodeList.get(nextNodeIndex)] && k >= nextNode.requiredTired) {
                // 다음 노드는 방문한적이 없어야하고 요구한 피로도보다 현재피로도보다 많아야한다
                check[nextNodeList.get(nextNodeIndex)] = true;
                dfs(nextNodeList.get(nextNodeIndex), k - nextNode.spendTired, depth + 1);
                check[nextNodeList.get(nextNodeIndex)] = false; // 경로를 구해야하기 때문에 재방문이 가능해야한다.
            }

        }

    }

    static class Dungeon {
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

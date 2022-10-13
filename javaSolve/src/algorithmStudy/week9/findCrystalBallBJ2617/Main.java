package algorithmStudy.week9.findCrystalBallBJ2617;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static ArrayList<Integer>[] forwardAdjList;
    static ArrayList<Integer>[] backwardAdjList;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/study/week9/BJ2617/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        forwardAdjList = new ArrayList[N + 1];
        backwardAdjList = new ArrayList[N + 1];
        // arrayList 선언
        for (int vertex = 1; vertex <= N; vertex++) {
            forwardAdjList[vertex] = new ArrayList<>();
            backwardAdjList[vertex] = new ArrayList<>();
        }
        // 큰관계 작은 관계 탐색을위한 인접 리스트 생성
        // 사실 간선수가 많은 완전그래프면 인접 행렬의 장점이 있지만
        // 간선수가 완전이 아니니 인접리스트를 사용함
        for (int edge = 0; edge < M; edge++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            forwardAdjList[from].add(to);
            backwardAdjList[to].add(from);
        }
        int[] forwardMap = new int[N+1];
        int[] backwardMap = new int[N+1];

        for (int startNode = 1; startNode <= N; startNode++) {
            int forwardRes = bfs(forwardAdjList, startNode);
            int backwardRes = bfs(backwardAdjList, startNode);
            forwardMap[startNode] = forwardRes;
            backwardMap[startNode] = backwardRes;
        }
        System.out.println(Arrays.toString(forwardMap));
        System.out.println(Arrays.toString(backwardMap));
        int count = 0;
        for(int forwardCount : forwardMap){
            if(forwardCount > N/2)count++;
        }
        for(int backward : backwardMap){
            if(backward > N/2)count++;
        }

        System.out.println(count);
    }

    private static int bfs(ArrayList<Integer>[] adjList, int startNode) {
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] check = new boolean[N + 1];
        q.add(startNode);
        check[startNode] = true;
        while (!q.isEmpty()){
            int curNode = q.poll();
            ArrayList<Integer> nextNodeList = adjList[curNode];
            for (Integer nextNode : nextNodeList){
                if(!check[nextNode]){
                    check[nextNode] = true;
                    q.offer(nextNode);
                }
            }
        }

        int count = 0;
        for(boolean el : check){
            if(el)count++;
        }
        return count-1;
    }


}

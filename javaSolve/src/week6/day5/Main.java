package week6.day5;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static int N;
    static int M;
    static int startNode;
    static ArrayList <Integer>[] adjList;
    static StringBuilder sb = new StringBuilder();
    static boolean [] isVisit;
    public static void main(String[] args) throws IOException{

        //System.setIn(new FileInputStream("resources/week6/day5/BJ1260/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer( br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        startNode = Integer.parseInt(st.nextToken());
        // 방문 기록 행렬
        isVisit = new boolean[N+1];

        // 인접 행렬 생성
        //adjArr = new int[N][N];

        // 인접 리스트 생성
        adjList = new ArrayList[N+1];
        for(int i = 0; i < N+1; i++) {
            adjList[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            adjList[from].add(to);
            adjList[to].add(from);
        }
        sortAdj();
        //System.out.println(Arrays.toString(adjList));
        dfs(startNode);
        sb.append("\n");
        //System.out.println(sb);
        Arrays.fill(isVisit, false);
        bfs();
        System.out.println(sb);
    }

    public static void sortAdj(){
        for(ArrayList <Integer> list : adjList ){
            Collections.sort(list);
            //System.out.println(list);
        }
    }

    // bfs 재귀
    public static void dfs(int node){
        sb.append(node).append(" ");
        isVisit[node] = true;
        ArrayList<Integer> currNode = adjList[node];
        for(int nextNode : currNode){
            if(!isVisit[nextNode]){
                dfs(nextNode);
            }
        }
    }

    //bfs
    public static void bfs(){
        Queue<Integer> q = new ArrayDeque<>();
        q.add(startNode);
        isVisit[startNode] = true;
        while (!q.isEmpty()){
            int currNode = q.poll();
            sb.append(currNode).append(" ");
            for(Integer nextNode : adjList[currNode]){
                if(!isVisit[nextNode]){
                    isVisit[nextNode] = true;
                    q.add(nextNode);
                }
            }
        }
    }

}

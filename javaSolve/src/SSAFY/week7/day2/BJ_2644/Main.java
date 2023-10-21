package SSAFY.week7.day2.BJ_2644;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static int start;
    static int target;
    static boolean[] check;
    static int res = -1;
    static ArrayList<Integer> [] adjList;
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/SSAFY.week7/day2/bj2644/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        target = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(br.readLine());
        adjList = new ArrayList[N+1];
        check = new boolean[N+1];
        for (int i = 0; i < N+1; i++) {
            adjList[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            adjList[from].add(to);
            adjList[to].add(from);
        }
        dfs(0, start);
        System.out.println(res);
    }
    static void dfs(int depth, int node){
        if(node == target){
            res = depth;
        }
        check[node] = true;

        ArrayList<Integer> nextLists = adjList[node];
        for(Integer nextNode : nextLists){
            if(!check[nextNode]){
                dfs(depth+1, nextNode);
            }
        }

    }
}

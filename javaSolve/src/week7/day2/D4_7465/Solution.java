package week7.day2.D4_7465;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    static int Tc;
    static int N;
    static int M;
    static boolean[] check;
    static ArrayList<Integer>[] adjList;
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/week7/day2/swea7465/input1.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Tc = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < Tc ; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            adjList = new ArrayList[N+1];
            check = new boolean[N+1];
            for (int n = 0; n < N+1; n++) {
                adjList[n] = new ArrayList<Integer>();
            }
            for(int n = 0; n < M; n++){
                st = new StringTokenizer(br.readLine());
                int from  = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                adjList[from].add(to);
                adjList[to].add(from);
            }
            int count = 0;
            for(int i = 1; i <= N; i++){
                if(!check[i]){
                    count++;
                    bfs(i);
                }
            }
            System.out.printf("#%d %d\n",tc+1,count);

        }

    }

    public static void bfs(int start){
        Queue<Integer> q = new ArrayDeque<>();
        q.add(start);
        while (!q.isEmpty()){
            int currNode = q.poll();
            ArrayList<Integer> nextNodeList = adjList[currNode];
            for(Integer nextNode:nextNodeList){
                if(!check[nextNode]){
                    check[nextNode] = true;
                    q.add(nextNode);
                }
            }
        }
    }

}

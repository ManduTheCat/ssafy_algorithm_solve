package SSAFY.week13.day4.swea5643tallSortLive;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    //방향성: a -> b a  보다 b 가 키가크다
    // 간선의 의미가 역 이 성립하지 않는다. 
    // 방향성이 있는 유향 그래프이다
    // 간선의 의미대로 탐색: 키가큰 학생따라 탐색
    /*
        학생은 최대 500명이다.  dfs bfs 상관 없다.
        dfs 의 호출 한계는 메서드의 사용된 지역변수 등에 따라 갈린다.
        간선의 갯수  N * (N - 1)/2 -> 간선이 노드당 하나 이기 때문에 
        무향 그래프 에선 간선의 의미때문에 역을 성립하지 않다 /2/ 로 나누어 졌다
        완전 그래프
        인접리스트의 이점인 메모리 절약부분이 인접 행렬과 차이가 없기 때문에 인접 행렬로 간다
        한 정점을 잡고
        인접 행렬에서 오른쪽 방향으로 가면 row 탐색하면 나보다 큰 학생 탐색
        인접 행렬에서 아래 방향으로 가면 col 탐색 하면 나보다 작은 학생 탐색
     */
    static int N,M,Tc;
    static int[][] adjArr;
    static int gtDFScount;
    static int ltDFScount;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/SSAFY.week13/day4/swea5643/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Tc = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < Tc; tc++) {
            N = Integer.parseInt(br.readLine());
            M = Integer.parseInt(br.readLine());
            adjArr = new int[N+1][N+1];
            for (int row = 0; row < M; row++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int to = Integer.parseInt(st.nextToken());
                int from = Integer.parseInt(st.nextToken());
                adjArr[from][to] = 1;
            }

            int res = 0;
            for (int i = 1; i <=N ; i++) {
                if(gtBFS(i) + ltBFS(i) ==N-1){
                    res++;
                }
            }

            int res2 = 0;
            for (int i = 1; i <=N ; i++) {
                gtDFScount = 0; // 나자신은 안샐거니 0 부터 시작
                ltDFScount = 0;
                gtDFS(i, new boolean[N+1]);
                ltDFS(i, new boolean[N+1]);
                if(gtDFScount + ltDFScount ==N-1)res2++;
            }
            System.out.printf("#%d %d\n", tc+1, res2);

        }
    }
    public static int gtBFS(int start){ // start 학생부터 자신부다 키다큰 학생따라 탐색
        Queue<Integer> queue = new ArrayDeque<>();
        boolean [] visited = new boolean[N+1];
        visited[start] = true;
        queue.offer(start);
        int count = 0;
        while (!queue.isEmpty()){
            int currNode = queue.poll();
            for (int i = 1; i <= N; i++) {
                if(adjArr[currNode][i] == 1 && !visited[i]){
                    visited[i] = true;
                    queue.add(i);
                    count++;
                }
            }
        }
        return count; // 큰학생수 리턴
    }

    public static int ltBFS(int start){ // start 학생부터 자신부다 키가 작은 학생따라 탐색
        Queue<Integer> queue = new ArrayDeque<>();
        boolean [] visited = new boolean[N+1];
        visited[start] = true;
        queue.offer(start);
        int count = 0;
        while (!queue.isEmpty()){
            int currNode = queue.poll();
            for (int i = 1; i <= N; i++) {
                // 열로 바꾸면 나를 가르키는 학생으로 간다.
                if(adjArr[i][currNode] == 1 && !visited[i]){
                    visited[i] = true;
                    queue.add(i);
                    count++;
                }
            }
        }
        return count;// 작은 학생수 리턴
    }

    public static void gtDFS(int curNode, boolean[]visited) { // start 학생부터 자신부다 키다큰 학생따라 탐색
        visited[curNode] = true;
        for (int i = 1; i <= N; i++) {
            if (adjArr[curNode][i] == 1 && !visited[i]) {
                gtDFScount++;
                gtDFS(i, visited);
            }
        }
    }
    public static void ltDFS(int curNode, boolean[]visited) { // start 학생부터 자신부다 키다큰 학생따라 탐색
        visited[curNode] = true;
        for (int i = 1; i <= N; i++) {
            if (adjArr[i][curNode] == 1 && !visited[i]) {
                gtDFScount++;
                ltDFS(i, visited);
            }
        }
    }
}

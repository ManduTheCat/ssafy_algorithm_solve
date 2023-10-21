package SSAFY.week13.day4.swea5643tallSortLiveSimplify;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    /*
        인접행렬의 값을 1이 아닌걸로 바꿔서 매서드를 단순화 시켜보자
        1 -> 5 adj[i][j] == 1  i 보다 j 가 키가작다.
        거꾸로 만들어서 쓴다면  i 보다 j 가 키가크다가된다.
        adjArr 두개 써서 메서드 하나만 써도된다. 즉 역 인접행렬을 만들어서 쓴다

     */
    static int N,M,Tc;
    static int count;



    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/SSAFY.week13/day4/swea5643/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Tc = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < Tc; tc++) {
            N = Integer.parseInt(br.readLine());
            M = Integer.parseInt(br.readLine());
            int [][]  adjArr = new int[N+1][N+1];
            int [][] rAdjArr = new int[N+1][N+1];
            for (int row = 0; row < M; row++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int to = Integer.parseInt(st.nextToken());
                int from = Integer.parseInt(st.nextToken());
                adjArr[from][to] = 1;
                rAdjArr[to][from] = 1;

            }



            int res = 0;
            for (int i = 1; i <=N ; i++) {
                count = 0;
                DFS(i, adjArr, new boolean[N+1]);
                DFS(i, rAdjArr, new boolean[N+1]);
                if(count== N-1)res ++;
            }
            System.out.printf("#%d %d\n", tc+1, res);

        }
    }




    public static void DFS(int curNode,int[][] adjArr, boolean[]visited) { // start 학생부터 자신부다 키다큰 학생따라 탐색
        visited[curNode] = true;
        for (int i = 1; i <= N; i++) {
            if (adjArr[curNode][i] == 1 && !visited[i]) { // i 가 cur  보다 키가 큰경우 (i 가 cur 보다 키가 작은ㄱ경우 )
                //아직 탐색되지 않았다면
                count++;
                DFS(i, adjArr, visited);
            }
        }
    }

}

package SSAFY.week13.day4.swea5643tallSortLiveOverLapSolve;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    /*
        중복되는 지점을 여러번 방문하는 문제 해결
        cur 을 따라가다보니 i 가 있고 i 다음에j 가 있다
        cur < i < j 간접적인 의미가
        cur < j 이렇게 직접적인 결론이 가능하다.
         1-> 5 ->2
           ->4 ->6
       1탐색 완료 하면 -> 5탐색 완료 -> 2탐색 완료
                        5탐색 완료 -> 4탐색 완료 -> 6탐색 완료
        1 번만 탐색하면 모두 하는데?

        0열 메모 : 탐색 끝난 정점 메모 나보타 큰 정점 개수 메모  -1: 탐색 x 0:탐색 0<=n 자기보단 큰 정점갯수
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
            for (int i  =0; i <= N; i++)adjArr[i][0] = -1;
            //for (int i  =0; i <= N; i++)adjArr[0][i] = -1;
            for (int row = 0; row < M; row++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int to = Integer.parseInt(st.nextToken());
                int from = Integer.parseInt(st.nextToken());
                adjArr[from][to] = 1;

            }



            int res = 0;
            for (int i = 1; i <=N ; i++) {
                // 재귀안에여 탐색이 되지 않으면 돌렷다. 여기도 마찬가리로
                if(adjArr[i][0] == -1) DFS(i, adjArr);
            }
            // 모등 정점이 알고 있는 관계로 탐색을 마친 상태 (큰정점을 따라 탐색 해서 간접관계를 다 반영해서
            // 인접행렬 update
            // 나보다 큰거 갯수 작은거 갯수 더했을때 N-1 이면 나의 위치를 알수 있다.
            for (int k = 1; k <= N; k++) {
                for (int i = 1; i <=N ; i++) {
                    adjArr[0][k] += adjArr[i][k];
                }
            }
            for (int k = 1; k <=N; k++) {
                if(adjArr[k][0]  + adjArr[0][k] == N-1)res++;

            }
            //printAdj(adjArr);
            System.out.printf("#%d %d\n", tc+1, res);

        }
    }
    public static void printAdj(int [][] adj){
        for(int [] row: adj){
            System.out.println(Arrays.toString(row));
        }

    }



    public static void DFS(int curNode,int[][] adjArr) { // start 학생부터 자신부다 키다큰 학생따라 탐색
        for (int i = 1; i <= N; i++) {
            if (adjArr[curNode][i] == 1 ) {
                if(adjArr[i][0] == -1){
                    // 탐색 안한 상태
                    DFS(i, adjArr);
                }
                // 나보다 큰 정점의 탐색 정보를 메모
                if(adjArr[i][0]>0){
                    //i 보다 큰 정점이 존재  : cur < i< j 를 만족하는 j 존재
                    //cur < j 상태로 매모
                    for (int j = 1; j <=N ; j++) {
                        if(adjArr[i][j] == 1 ) adjArr[curNode][j] = 1;
                        // 탐색해서 나에게 반영
                    }
                }
            }

        }
        // 자싲보다 큰 정점 탐색을 모두 완료 메모하기
        int cnt  =0;
        for (int k = 1; k <=N; k++) {
            cnt += adjArr[curNode][k];
        }
        adjArr[curNode][0] = cnt;
    }

}

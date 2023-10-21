package SSAFY.week7.day1.D4Contac1238;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author 김명진
 * bfs 를 구현해 완전탐색을 이용했고
 * 특별하게
 * check를 할때 방문할떄마다 이전 으로부터 숫자가 증가하게 했습니다
 * 즉 방문순서를 check를 에 기록했습니다
 * check의 최대값을 찾아 그에 맞는 인덱스중 가장 큰값을 출력했습니다.
 */
public class Solution {
    static int TC = 10;
    static int N;
    static int start;
    static int[] check;
    static ArrayList[] adjList;
    static ArrayList<Integer> res;
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/SSAFY.week7/day1/d41238/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int tc = 1; tc <= TC; tc++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            start = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            adjList = new ArrayList[N + 1];
            check = new int[N + 1];
            res = new ArrayList<>();
            for (int i = 0; i < N+1; i++) {
                adjList[i] = new ArrayList<Integer>();
            }
            for (int n = 0; n <N/2; n++) {
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                adjList[from].add(to);
            }

            bfs(start);
            int max = 0;
            for(int el : check){
                max = (Math.max(el, max));
            }
            for(int i = 0; i < N + 1; i++){
                if(max == check[i]){
                    res.add(i);
                }
            }
            System.out.printf("#%d %d\n",tc,Collections.max(res));
        }
    }
    static void bfs(int start){
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(start );
        check[start] = 0;
        while (!q.isEmpty()){
            int curNode = q.poll();
            ArrayList<Integer> nextNodes = adjList[curNode];
            for(Integer nextNode : nextNodes){
                if(check[nextNode] == 0){
                    check[nextNode] = check[curNode]+1;
                    q.add(nextNode);
                }
            }
        }
    }
}

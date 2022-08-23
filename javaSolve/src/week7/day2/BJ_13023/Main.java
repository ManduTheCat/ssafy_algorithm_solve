package week7.day2.BJ_13023;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static boolean[] check;
    static int N;
    static int M;
    static ArrayList[] adjList;
    static boolean flag;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/week7/day2/bj13023/input1.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        //System.out.println(M + " " + N);
        adjList = new ArrayList[N+1];
        check = new boolean[N+1];
        for (int i = 0; i <= N; i++) {
            adjList[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            adjList[from].add(to);
            adjList[to].add(from);
        }

        for (int start = 0; start < N; start++) {
            check = new boolean[N+1];
            dfs(0, start);

        }
        System.out.println(!flag ? 0:1 );

    }

    static void dfs(int depth, int node) {
        //check[node] += 1;
        check[node] = true;
        if (depth == 4) {
            flag = true;
            return;

        }
        //System.out.print(depth + " ");
        //System.out.print(node + " ");
        ArrayList<Integer> nextNodes = adjList[node];
        for (Integer nextNode : nextNodes) {
            if (!check[nextNode]) {
                //check[nextNode] = true;
                dfs(depth + 1, nextNode);
            }
        }
        check[node] = false;
    }
}

package SSAFY.week7.day4.BJ10971;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] adjArr;
    static int N;
    static boolean[] isVisit;
    static int[] weights;
    static int min;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/SSAFY.week7/day4/bj10971/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        adjArr = new int[N][N];
        isVisit = new boolean[N];
        weights = new int[N];
        min = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                adjArr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int start = 0; start < N; start++) {
            dfs(start, 0, start, 0);
        }
        System.out.println(min);

    }

    public static void dfs(int start, int depth, int i, int sum) {
        if(depth == N && i == start){
            if(min > sum )min = sum;
            return;
        }
        // 인접을 돈다
        for (int j = 0; j < N; j++) {
            if (adjArr[i][j] == 0) continue;
            if (!isVisit[j] && adjArr[i][j] >0) {
                isVisit[j] = true;
                sum += adjArr[i][j];
                if(min >= sum){
                    dfs(start,depth +1, j, sum);
                }
                isVisit[j] = false;
                sum-= adjArr[i][j];
            }
        }
    }
}

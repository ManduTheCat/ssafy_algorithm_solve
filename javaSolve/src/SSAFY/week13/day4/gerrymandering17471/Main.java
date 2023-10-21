package SSAFY.week13.day4.gerrymandering17471;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int[] populationList;
    static ArrayList<Integer>[] adjList;
    static boolean[] powerSet;
    static int [] parents;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/SSAFY.week13/day4/BJ17471/input4.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        populationList = new int[N];
        adjList = new ArrayList[N];
        parents = new int[N];
        powerSet = new boolean[N];
        for (int node = 0; node < N; node++) {
            adjList[node] = new ArrayList<Integer>();
            //parents[node] = node;
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++) {
            populationList[n] = Integer.parseInt(st.nextToken());
        }
        for (int node = 0; node < N; node++) {
            st = new StringTokenizer(br.readLine());
            int numberOfAd = Integer.parseInt(st.nextToken());
            for (int ad = 0; ad < numberOfAd; ad++) {
                adjList[node].add(Integer.parseInt(st.nextToken()) -1);
            }
        }

        findGroup(0);
        System.out.println(min != Integer.MAX_VALUE ? min : -1);
    }

    private static void findGroup(int depth) {
        if (depth == N) {
            ArrayList <Integer> team1 = new ArrayList<>();
            ArrayList <Integer> team2 = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                if(powerSet[i]){
                    team1.add(i);
                }else {
                    team2.add(i);
                }
            }
            if(isConnected(team1, team2)){
            System.out.println(team1);
            System.out.println(team2);
            System.out.println(isConnected(team1, team2));
                int team1Sum = 0;
                int team2Sum = 0;
                for(int populationIdx : team1){
                    team1Sum += populationList[populationIdx];
                }
                for(int populationIdx : team2){
                    team2Sum += populationList[populationIdx];
                }
                min = Math.min(Math.abs(team1Sum - team2Sum),min);
            }
            return;
        }

        powerSet[depth] = true;
        findGroup(depth + 1);
        powerSet[depth] = false;
        findGroup(depth + 1);

    }

    private static boolean isConnected(ArrayList<Integer> team1, ArrayList<Integer> team2) {
        boolean team1Check[] = new boolean[N];
        boolean team2Check[] = new boolean[N];
        // 미리 방문처리를 해서 서로 침범 못하게 한단
        if(team1.size() == 0 || team2.size() == 0){
            return false;
        }
        for(int node : team2){
            team1Check[node] = true;
        }
        for(int node : team1){
            team2Check[node] = true;
        }
        // 두팀 다 모두 순환이 가능해야한다.
        return (isValidBFS(team1, team1Check) && isValidBFS(team2, team2Check));
    }

    private static boolean isValidBFS(ArrayList<Integer> team1, boolean[] team1Check) {
        Queue<Integer> q = new LinkedList<>();
        q.add(team1.get(0));
        team1Check[team1.get(0)] = true;
        while (!q.isEmpty()){
            int currNode = q.poll();
            for(Integer nextNode :adjList[currNode]){
                if(!team1Check[nextNode]){
                    team1Check[nextNode] = true;
                    q.offer(nextNode);
                }
            }
        }
        for(boolean el : team1Check){
            if(!el) return false;
        }
        return true;
    }


//    public static int find(int x){
//        if(parents[x] == x)return x;
//        return parents[x] = find(parents[x]);
//    }
//    public static boolean union(int x, int y){
//        int pX = find(x);
//        int pY = find(y);
//        if(pX == pY) return false;
//        parents[pY] = pX;
//        return true;
//    }


}

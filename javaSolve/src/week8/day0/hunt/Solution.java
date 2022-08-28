package week8.day0.hunt;


import java.awt.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    static int Tc;
    static int N;
    static int[][] map;
    static int rangeMonster;
    static int rangeHouse;
    static int [] resultPermutation;
    static boolean[][] bfsCheck;
    static int[] totalTarget;
    static ArrayList <Integer> countList;
    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("resources/week8/hunt/input.txt"));
        System.setIn(new FileInputStream("resources/week8/hunt/smalInput.txt"));


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Tc = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < Tc; tc++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            ArrayList<Integer> subHouse = new ArrayList<>();
            ArrayList<Integer> subMonster = new ArrayList<>();
            for (int n = 0; n < N; n++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int col = 0; col < N; col++) {
                    int value =  Integer.parseInt(st.nextToken());
                    map[n][col] = value;
                    if(value > 0) subMonster.add(value);
                    else if(value < 0) subHouse.add(value);
                }
            }
            rangeHouse = subHouse.size();
            rangeMonster = subMonster.size();
            int totalTargetSize = rangeHouse + rangeMonster;
            resultPermutation = new int[totalTargetSize];
            totalTarget = new int[totalTargetSize];
            Collections.sort(subMonster);
            subHouse.sort((o1, o2) -> o2 - o1);
            int idx = 0;
            for(int Monster: subMonster){
                totalTarget[idx++] = Monster;
            }
            for(int house : subHouse){
                totalTarget[idx++] = house;
            }
            countList = new ArrayList<>();
            permutation(0, 0, rangeMonster);
            System.out.println(countList);
            System.out.println(Collections.min(countList));
        }

    }
    // [1,2,3,-1,-2,-3] 을 순열한다
    static void permutation(int depth, int flag, int range){
        if(depth == totalTarget.length){
            System.out.println(Arrays.toString(resultPermutation));
            pathCount();
            return;
        }
        for (int i = 0; i < range; i++) {
            if((flag & 1 << i) == 0){
                // 뽑은게 음수인데 이전에 선택하지 않았다면 넘어가라
                if(totalTarget[i] < 0 && (flag & 1<<i-rangeMonster) == 0 ) continue;
                resultPermutation[depth] = totalTarget[i];
                // 다음 번 순열 의 범위는 1을 뽑으면 -1까지
                permutation(depth +1,flag | 1<<i,rangeMonster + Math.abs(totalTarget[i]));
            }
        }
    }
    public static void pathCount(){
        // 생성된 순서대로 좌표를 돈다.
        // 좌표를 리스트를 만든다.
        ArrayList<Point> pointRoutes = new ArrayList<>();
        for(int target : resultPermutation){
        //for(int target : test){
            for (int row = 0; row < N; row++) {
                for (int col = 0; col < N; col++) {
                    if(map[row][col] == target){
                        pointRoutes.add(new Point(row, col));
                    }
                }
            }
        }
        Point start = new Point(0,0);
        int count = 0;
        for(Point target : pointRoutes){
            //System.out.println(start + " "+ target);
            bfsCheck = new boolean[N][N];
            count +=bfs(start, target);
            //count += Math.abs(start.x - target.x) + Math.abs(start.y - target.y);
            start = target;
        }
        countList.add(count);
    }
    // 최단 경로를 구한다. 장에물이 없기에 bfs 방법보다 멘하튼 거리가 유리하지만 연습겸 bfs 구현
    public static int bfs(Point startPoint, Point targetPoint){
        int time = 0;
        int [][] alpha = new int[][]{{0,-1},{-1,0},{0,1},{1,0}};
        Queue<Point> q = new ArrayDeque<>();
        Queue<Integer> timeQ = new ArrayDeque<>();
        q.add(startPoint);
        timeQ.add(time);
        bfsCheck[startPoint.x][startPoint.y] = true;
        while (!q.isEmpty()){
            Point currPoint = q.poll();
            Integer currTime = timeQ.poll();
            int currRow = currPoint.x;
            int currCol = currPoint.y;
            //System.out.println(currPoint+" " +currRow + " " +currCol);
            if(currRow == targetPoint.x && currCol == targetPoint.y){
                //System.out.println("in");
                return currTime;
            }
            for (int d = 0; d < 4; d++) {
                int nextRow = currRow + alpha[d][0];
                int nextCol = currCol + alpha[d][1];
                if(isIn(nextRow, nextCol) && !bfsCheck[nextRow][nextCol]){
                    //System.out.println("in");
                    bfsCheck[nextRow][nextCol] = true;
                    //System.out.println(nextRow + " " + nextCol);
                    q.add(new Point(nextRow, nextCol));
                    ///System.out.println(currTime);
                    timeQ.add(currTime + 1);
                }
            }
        }
        return -1;
    }
    public static boolean isIn(int i, int j){
        return i <  N && i >=0 && j < N && j >=0;
    }
}

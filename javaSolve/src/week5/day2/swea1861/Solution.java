package week5.day2.swea1861;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    static int max = 0;
    static int minMapValue;
    static boolean flag;
    static int [][] map;
    static int N;
    static final int [] dx = new int[]{1,0,-1, 0};
    static final int [] dy = new int[]{0,1,0,-1 };
    static boolean [][] visited;
    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("resources/week5/day2/swea1816/input1.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int Tc = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= Tc; tc++){
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            for(int i = 0; i < N; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            max = 0;
            minMapValue = N*N*1000;
            ArrayList<Integer>[]  maxRoomStartList = new ArrayList[N*N];
            for(int m = 0; m< N*N; m++){
                maxRoomStartList[m] = new ArrayList<>();
            }
            for(int i = 0; i < N; i++){
                for(int j = 0; j <N; j++){
                    flag = false;
                    //System.out.println("new");
                    visited = new boolean[N][N];
                    dfs(i,j, 0);
                    if(flag){
                        maxRoomStartList[max].add(map[i][j]);
                    }
                }
            }
            System.out.printf("#%d %d %d\n",tc, Collections.min(maxRoomStartList[max]), max+1);
        }

    }
    public static void dfs( int i, int j, int count){
        if (count >0 & count >= max ){
            max = count;
            flag = true;
        };
        for(int dIndex = 0; dIndex < 4; dIndex++){
            int x = dx[dIndex];
            int y = dy[dIndex];
            // 검사 항목 : 방문 했던곳아니여야한다, 내 다음수 인지 + 1인지 , 배열 범위안넘어가는지
            if(check(i, j, x, y) && !visited[i][j] && map[i][j]+1 == map[i + x][j + y]){
                visited[i][j] = true;
                dfs(i+x,j+y, ++count);
            }
        }

    }

    public static boolean check(int i , int j, int x, int y){
        return i + x >= 0 && i + x < N && j + y >= 0 && j + y < N;
    }
}

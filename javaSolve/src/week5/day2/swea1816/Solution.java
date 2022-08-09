package week5.day2.swea1816;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

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
        System.setIn(new FileInputStream("resources/week5/day2/swea1816/input1.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int Tc = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= Tc; tc++){
            N = Integer.parseInt(br.readLine());
            //System.out.println("------------------N : "+N);
            map = new int[N][N];
            for(int i = 0; i < N; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            for(int[] m : map){
                //System.out.println(Arrays.toString(m));
            }


            max = 0;
            minMapValue = N*N;
            loop:
            for(int i = 0; i < N; i++){
                for(int j = 0; j <N; j++){
                    flag = false;
                    //System.out.println("new");
                    visited = new boolean[N][N];
                    dfs(i,j, 0);
                    if(flag){

                        System.out.printf( "#%d %d ", tc , map[i][j]);
                        break loop;
                    }
                }
            }
            System.out.printf("%d\n", max+1);
            //System.out.println("min: "+ minMapValue);
        }

    }
    public static void dfs( int i, int j, int count){
        // 사방 돌면서
        // 검사 : 방문 했던곳, 내 다음수 , 배열 넘어가는지
        //System.out.println("count : " + count);
        if (count > max ){

            max = count;
            flag = true;

        }
        //System.out.printf("i: %d, j: %d,  %d\n", i,j ,map[i][j]);
        for(int dIndex = 0; dIndex < 4; dIndex++){
            int x = dx[dIndex];
            int y = dy[dIndex];
            if(check(i, j, x, y) && visited[i][j] == false&& map[i][j]+1==map[i + x][j + y]){
                visited[i][j] = true;
                dfs(i+x,j+y, ++count);
            }
        }

    }

    public static boolean check(int i , int j, int x, int y){
            if((i + x < 0 || i + x >= N || j + y < 0 || j + y >= N)){
                return  false;
            }
        return true;
    }
}

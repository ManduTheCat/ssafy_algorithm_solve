package SSAFY.week5.day3.bk_16926;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int[] dr = new int[]{0, 1, 0, -1};
    public static int[] dc = new int[]{1, 0, -1, 0};
    public static int n;
    public static int m;
    public static int r;
    public static Deque<Integer>[] dq;
    public static boolean[][] check;


    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("resources/SSAFY.week5/day3/bk16926/input3.txt"));
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer NMRtk = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(NMRtk.nextToken());
        m = Integer.parseInt(NMRtk.nextToken());
        r = Integer.parseInt(NMRtk.nextToken());
        int[][] map = new int[n][m];

        for (int row = 0; row < n; row++) {
            StringTokenizer rowLine = new StringTokenizer(bf.readLine());
            for (int column = 0; column < m; column++) {
                map[row][column] = Integer.parseInt(rowLine.nextToken());
            }
        }
        dq = new Deque[(int)n/2];
        for(int i = 0; i< n/2; i++){
            dq[i] = new ArrayDeque<>();
        }
        readMap(map);
        StringBuilder sb = new StringBuilder();
        for(int [] m : map){
            for(int el : m){
                sb.append(el).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);

    }

    // 맵을 돌면서 큐를 만드는 함수
    public static void readMap(int[][] map) {
        // 방향은 바뀐다 언제? 끝에 닿았을때
        check = new boolean[n][m];
        //wirteQMove4Way(map, 0);
        //start Point 별로 시작
        for (int start = 0; start < n / 2; start++) {
            //check = new boolean[n][m];
            wirteQMove4Way(map, start);
            for(int i = 0; i< r; i++){
                dq[start].addLast(dq[start].poll());
            }

        }

        check = new boolean[n][m];
        for (int start = 0; start < n / 2; start++) {
            writeMapMove4Way(map, start);
        }


    }

    public static void wirteQMove4Way(int[][] map, int startPoint) {

        int i = startPoint;
        int j = startPoint;
        int d = 0;
        while (checkBound(map, i, j)&& !check[i][j]) {
            check[i][j] = true;
            dq[startPoint].addLast(map[i][j]);
            j += dc[d];
        }
        j-=1;
        d++;
        i+=1;
        while (checkBound(map, i, j)&& !check[i][j]) {
            check[i][j] = true;
            dq[startPoint].addLast(map[i][j]);
            i += dr[d];

        }
        i-=1;
        d+=1;
        j-=1;
        while (checkBound(map, i, j)&& !check[i][j]) {
            check[i][j] = true;
            dq[startPoint].addLast(map[i][j]);
            j += dc[d];
            if(!checkBound(map, i, j)){
                break;
            }
        }
        j+=1;
        d++;
        i-=1;
        while (checkBound(map, i, j)&& !check[i][j]) {
            check[i][j] = true;

            dq[startPoint].addLast(map[i][j]);
            i += dr[d];
        }

    }



    // 큐를 읽으면서 맵을 만드는 함수
    public static void writeMapMove4Way(int[][] map, int startPoint) {
        int i = startPoint;
        int j = startPoint;
        int d = 0;
        while (checkBound(map, i, j)&& !check[i][j]) {
            check[i][j] = true;
            if(dq.length > 0){
                map[i][j] = dq[startPoint].poll();
            }
            j += dc[d];
        }
        j-=1;
        i+=1;
        d++;
        while (checkBound(map, i, j)&& !check[i][j]) {
            check[i][j] = true;
            if(dq.length > 0){
                map[i][j] = dq[startPoint].poll();
            }
            i += dr[d];
        }
        i-=dr[d];
        j-=1;
        d++;
        while (checkBound(map, i, j)&& !check[i][j]) {
            check[i][j] = true;
            if(dq.length > 0){
                map[i][j] = dq[startPoint].poll();
            }
            j += dc[d];
        }
        j-=dc[d];
        i-=1;
        d++;
        while (checkBound(map, i, j)&& !check[i][j]) {
            check[i][j] = true;
            if(dq.length > 0){
                map[i][j] = dq[startPoint].poll();
            }
            i += dr[d];
        }

    }




    public static boolean checkBound(int[][] map, int addedRow, int addedColumn) {
        return !(addedColumn < 0 || addedColumn >= m || addedRow < 0 || addedRow >= n);
    }

}

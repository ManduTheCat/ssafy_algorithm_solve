package SSAFY.week5.day3.bk_16926;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2 {
    public static int[] dx = new int[]{0, 1, 0, -1};
    public static int[] dy = new int[]{1, 0, -1, 0};
    public static int n;
    public static int m;
    public static int r;

    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("resources/SSAFY.week5/day3/bk16926/input.txt"));
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
        int G = Math.min(n, m)/2;
        for(int i = 0; i < r; i++){
            for(int j = 0; j < G; j++){
                int y = j;
                int x = j;
                int temp = map[x][y];
                int idx = 0;
                while(idx< 4){
                    int nx = x + dx[idx];
                    int ny = y + dy[idx];
                    if(nx >= j && ny >=j && nx< n-j && ny <m-j){
                        map[x][y] = map[nx][ny];
                        x= nx;
                        y =ny;
                    }
                    else idx++;
                }
                map[j + 1][j] = temp;
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++){
            for(int j= 0; j < m; j++){
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }


}

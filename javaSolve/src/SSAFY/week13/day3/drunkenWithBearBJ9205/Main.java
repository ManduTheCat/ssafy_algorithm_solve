package SSAFY.week13.day3.drunkenWithBearBJ9205;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Point{
    int r, c;

    public Point(int r, int c) {
        this.r = r;
        this.c = c;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Point{");
        sb.append("r=").append(r);
        sb.append(", c=").append(c);
        sb.append('}');
        return sb.toString();
    }
}

public class Main {
    static int Tc;
    static int N;
    static boolean [][]adjArr;
    static ArrayList<Point> points;
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/SSAFY.week13/day3/BJ9205/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Tc = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < Tc; tc++) {
            N = Integer.parseInt(br.readLine());
            adjArr = new boolean[N+2][N+2];
            points = new ArrayList<>();
            for (int n = 0; n < N+2; n++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int row = Integer.parseInt(st.nextToken());
                int col = Integer.parseInt(st.nextToken());
                points.add(new Point(row, col));
            }
            // adjArr초기화
            for (int start = 0; start < N+2; start++) {
                for (int target = 0; target < N+2; target++) {
                    if(start != target){
                        Point startNode = points.get(start);
                        Point targetNode = points.get(target);
                        if( Math.abs(startNode.r - targetNode.r) + Math.abs(startNode.c - targetNode.c) <= 1000){
                            adjArr[start][target] = true;
                        }
                    }
                }
            }
            for (int k = 0; k < N+2; k++) {
                for (int i = 0; i < N+2; i++) {
                    for (int j = 0; j < N+2; j++) {
                        if(adjArr[i][k] && adjArr[k][j]){
                            adjArr[i][j] = true;
                        }
                    }

                }

            }
            System.out.println(adjArr[0][N+1] ? "happy": "sad");
        }
    }
    public static void printAdj(){
        for(boolean[] row : adjArr){
            for(boolean el : row){
                System.out.print(el ? "1 ": "0 ");
            }
            System.out.println();
        }
        System.out.println();
    }
}

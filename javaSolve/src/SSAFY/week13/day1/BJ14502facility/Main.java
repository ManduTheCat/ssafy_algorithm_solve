package SSAFY.week13.day1.BJ14502facility;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Wall{
    int r, c;

    public Wall(int r, int c) {
        this.r = r;
        this.c = c;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Wall{");
        sb.append("r=").append(r);
        sb.append(", c=").append(c);
        sb.append('}');
        return sb.toString();
    }
}

public class Main {
    // 1. 벽을 놓은곳을 고른다
    // 2. 바이러스를 확산시킨다
    // 3. 최대 빈공간인 경우를 구한다.
    static int N;
    static int M;
    static int [][] map;
    static boolean [][] visited;
    static boolean [][] permutationCheck;
    static Wall[] walls = new Wall[3];
    static int count = 0;
    static int falseCount = 0;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/SSAFY.week13/day2/bj14502/input1.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        permutationCheck = new boolean[N][M];
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < M; col++) {
                int value = Integer.parseInt(st.nextToken());
                if(value >0 ){
                    permutationCheck[row][col] = true;
                    falseCount++;
                }
                map[row][col] = value;
            }
        }
        for(int [] row : map){
            System.out.println(Arrays.toString(row));
        }
        System.out.println(falseCount);
        combination(0, 0, 0);
        System.out.println(count);


    }



    public static void combination(int depth, int r, int c){
        if(depth == 3){
            //System.out.println(Arrays.toString(walls));
            count++;
            return;
        }
        // 이차원 배열의 경우 한줄로 풀어서  combination 을해야한다.

    }

}

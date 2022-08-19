package week6.day5.BJ17135;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    //궁수배치
    // 궁수 가까운거 죽이고 가까운게 여러게면 왼쪽부터 하고 턴넘기기
    //땡겨오고
    static int N;
    static int M;
    static int D;
    static StringTokenizer st;
    static int[][] map ;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/week6/day5/BJ17135/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        map= new int[N+1][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        printMap();

    }

    public static void printMap(){
        for (int i = 0; i <N+1 ; i++) {
            for (int j = 0; j <M ; j++) {
                System.out.print(map[i][j]+ " ");
            }
            System.out.println();

        }
    }
}

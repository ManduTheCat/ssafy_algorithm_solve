package SSAFY.week5.day3.bk_16935;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static int R;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("resources/SSAFY.week5/day3/bk16935/input7.txt"));
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int r = 0; r < N; r++) {
            StringTokenizer rowSt = new StringTokenizer(bf.readLine());
            for (int c = 0; c < M; c++) {
                map[r][c] = Integer.parseInt(rowSt.nextToken());
            }
        }
        //printMap();
        StringTokenizer commandLine = new StringTokenizer(bf.readLine());
        for (int i = 0; i < R; i++) {

            switch (Integer.parseInt(commandLine.nextToken())) {
                case 1:
                    upDown();
                    break;
                case 2:
                    leftRight();
                    break;
                case 3:
                    rightOrthogonality();
                    break;
                case 4:
                    leftOrthogonality();
                    break;
                case 5:
                    clockDir();
                    break;
                case 6:

                    counterClockdir();
                    break;
            }
        }
        printMap();
    }


    public static void upDown() {
        int[][] newMap = new int[N][];
        int indx = 0;
        for (int i = N - 1; i >= 0; i--) {
            newMap[indx++] = map[i];
        }
        map = newMap;

    }

    public static void leftRight() {
        for (int[] row : map) {
            int endIndex = M - 1;
            for (int i = 0; i < M / 2; i++) {
                int tem = row[i];
                row[i] = row[endIndex];
                row[endIndex--] = tem;
            }
        }


    }

    public static void rightOrthogonality() {
        int[][] temp = new int[M][N];
        // map row 를 돌면서
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                temp[j][N - i - 1] = map[i][j];
            }
        }
        // tmep 에 이식\
        int t = N;
        N = M;
        M = t;
        map= temp;
    }

    public static void leftOrthogonality() {
        int[][] temp = new int[M][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                temp[M-j-1][i] = map[i][j];
            }
        }
        // tmep 에 이식\
        int t = N;
        N = M;
        M = t;
        map = temp;
    }
    public static void clockDir(){
        int[][] temp = new int[N][M];
        int [] dx = new int[] {1,0,-1, 0};
        int [] dy = new int[] {0,1, 0, -1};

        for(int i = 0; i< N/2; i++){
            for(int j = 0; j < M/2; j++){
                temp[i +dy[0]][(M/2-1)+j+dx[0]] = map[i][j];
            }
        }
        for(int i = 0; i < N/2; i++){
            for(int j =M/2; j < M; j++){

                temp[N/2+ i][j] = map[i][j];
            }
        }
        for(int i = N/2;  i< N; i++){
            for(int j = M/2; j <M; j++){
                temp[i][j-M/2] = map[i][j];
            }
        }

        for(int i = (N/2); i < N; i++ ){
            for(int j = 0; j <M/2; j++ ){
                temp[i-N/2][j] = map[i][j];
            }

        }
        map = temp;
    }


    public static void counterClockdir(){
        int[][] temp = new int[N][M];

        for(int i = 0;  i< N/2; i++){
            for(int j = 0; j < M/2; j++){
                temp[i + N/2][j] = map[i][j];
            }
        }//=>
        for(int i = (N/2);  i< N; i++){
            for(int j = 0; j < M/2; j++){
                temp[i][j + M/2] = map[i][j];
            }
        }
        for(int i = (N/2);  i< N; i++){
            for(int j = M/2; j < M; j++){
                temp[i-N/2][j] = map[i][j];
            }
        }
        for(int i = 0;  i< N/2; i++){
            for(int j = M/2; j < M; j++){
                temp[i][j - M/2] = map[i][j];
            }
        }
//        for (int[] t : temp) {
//            System.out.println(Arrays.toString(t));
//        }
        map = temp;
    }
    static void printMap() {
        StringBuilder sb = new StringBuilder();
        for (int[] m : map) {
            for(int el : m){
                sb.append(el).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

}

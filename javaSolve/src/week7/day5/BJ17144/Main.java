package week7.day5.BJ17144;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Dust {
    int i;
    int j;

    int amount;

    public Dust(int i, int l, int amount) {
        this.i = i;
        this.j = l;
        this.amount = amount;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Dust{");
        sb.append("i=").append(i);
        sb.append(", l=").append(j);
        sb.append(", amount=").append(amount);
        sb.append('}');
        return sb.toString();
    }
}

public class Main {
    static int[][] map;
    static int R, C, T;
    static ArrayList<Dust> dusts = new ArrayList<>();
    static int machineRow; // 마지막에 나온 공기 청정기 row 를 기록한다.
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/week7/day4/bj17144/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        map = new int[R][C];

        for (int r = 0; r < R; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < C; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                if (map[r][c] != 0) dusts.add(new Dust(r, c, map[r][c]));
                if(map[r][c] == -1) machineRow = r;
            }
        }
        diffusion();diffusion();

    }


    // * 확상 방향카운트 필요
    // 확신시 자기자신은 원래값 - ((원래값)/5) * 확산 방향 카운트
    // 옆방향에 덯나다

    public static void diffusion() {
        for(Dust curDust : dusts){
            //맵에다가만 먼지중앙 바꾼다 나중에 한번에 갱신 이후 퍼질때 지장간다
            int dirCount = countDirction(curDust.i, curDust.j);
            map[curDust.i][curDust.j] = map[curDust.i][curDust.j] - (dirCount*(map[curDust.i][curDust.j]/5));
        }
        for (Dust curDust : dusts) {

            //4방에 뿌린다
            int[][] alpha = new int[][]{{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
            for (int i = 0; i < 4; i++) {
                int nextI = curDust.i + alpha[i][0];
                int nextJ= curDust.j + alpha[i][1];
                if(isIn(nextI, nextJ) && !isAirMachine(nextI, nextJ)){
                    map[nextI][nextJ] += curDust.amount/5;
                }
            }

        }
        //dusts 갱신
        dusts = new ArrayList<>();
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if(map[r][c] == -1) continue;
                if (map[r][c] != 0) dusts.add(new Dust(r, c, map[r][c]));

            }
        }
        //printMap();
        System.out.println();
        upsideTurn(machineRow-1);
        printMap();
//        for(Dust dust: dusts){
//            System.out.println(dust);
//        }
        System.out.println();
    }

    public static int countDirction(int i, int j) {
        //4방을 돌면서 갯수 채크
        int ableCount = 0;
        int[][] alpha = new int[][]{{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
        for (int d = 0; d < 4; d++) {
            int nextI = i + alpha[d][0];
            int nextJ = j + alpha[d][1];
            // 청정기에 닿으면안되고 벽을 넘어가면안된다.
            if(isIn(nextI, nextJ) && !isAirMachine(nextI, nextJ)){
                ableCount++;
            }
        }

        return ableCount;
    }
    public static boolean isIn(int i, int j){
        return i >= 0 && i <R && j >=0 &&  j < C;
    }
    public static boolean isAirMachine(int row, int col){
        int firstMachineRow = machineRow -1;
        if ((row == machineRow || row == firstMachineRow) && col == 0){
            return true;
        }
        return false;
    }

    //돌리기
    public static void upsideTurn(int row){
        int goNextLine = map[row][C-1];
        for (int col = C-1; col >=2 ; col--) {
            int temp = map[row][col];
            map[row][col] = map[row][col -1];
            map[row][col -1] = temp;
        }
        map[row][1] = 0;
        goUp(goNextLine, machineRow );
    }
    public static void goUp(int beforeRest, int row){

    }



    public static void printMap(){
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C ; j++) {
                if(map[i][j] == -1) System.out.print("r ");
                else System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

}

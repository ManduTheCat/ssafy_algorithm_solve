package week5.day3.bk_17406;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static int R;
    static int[][] map;
    static ArrayList<Integer[]> commandList;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/week5/day3/bk17406/input.txt"));
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int row = 0; row < N; row++) {
            StringTokenizer rowTk = new StringTokenizer(bf.readLine());
            for (int col = 0; col < M; col++) {
                map[row][col] = Integer.parseInt(rowTk.nextToken());
            }
        }
        // map 인풋
        commandList = new ArrayList<>();
        for (int command = 0; command < R; command++) {
            // 3개 길이를 가지는 명령어 하나 할당
            StringTokenizer commandTk = new StringTokenizer(bf.readLine());
            commandList.add(new Integer[3]);
            for (int index = 0; index < 3; index++) {
                commandList.get(command)[index] = Integer.parseInt(commandTk.nextToken());
            }
        }

        //순열 돌면서 커맨드 실행
        //commandList 의 순열 구현
        runCommand(commandList.get(0));
        runCommand(commandList.get(1));

    }

    private static void runCommand(Integer[] command) {
        int r = command[0]-1;
        int c = command[1]-1;
        int s = command[2];
        startArray(r, c, s);
    }

    private static void startArray(int r, int c, int s) {
        for (int ds = 1; ds <= s; ds++) {
            int startR = r - ds;
            int startC = c - ds;
            int endR = r + ds;
            int endC = c + ds;
            System.out.printf("startR: %d startC: %d ds: %d endR: %d endC: %d\n", startR, startC, ds ,endR, endC);
            turn(startR, startC, endR, endC);
        }
    }
    private static void turn(int startR, int startC, int endR, int endC){
        // 위쪽 오른쪽 방향으로 이동
        // temp 로 맨 오른쪽이 짤리는걸 미리 저장 = 맨 오른쪽이 짤린다.
        // 땡겨오는 느낌으로 교환
        int upTemp = map[startR][endC];
        for(int c = endC; c > startC; c-- ){
            // column 이 오른쪽으로 이동
            map[startR][c] = map[startR][c-1];
        }

        // 오른쪽 의 아래방향으로 이동
        int leftTemp = map[endR][endC];
        // row 방향으로 땡겨오는 느낌으로 교환
        for(int r  = endR; r > startR+ 1; r--){
            map[r][endC] = map[r-1][endC];
        }
        // 이전에 씹현던것은 여기서 넣어줘야한다.
        map[startR+1][endC] = upTemp;

        //아래 의 왼쪽 방향으로 이동
        int downTemp = map[endR][startC];
        for(int c = startC; c < endC-1; c++){
            //System.out.print(map[endR][c]);
            map[endR][c] = map[endR][c+ 1];
        }
        map[endR][endC-1] = leftTemp;

        for(int r = startR; r< endR-1; r++){
            System.out.print(map[r][startC]);
            map[r][startC] = map[r+1][startC];
        }
        map[endR-1][startC] = downTemp;
        System.out.println();

        printMap();

    }

    private static void printCommand() {
        for (Integer[] cmd : commandList) {
            System.out.println(Arrays.toString(cmd));
        }

    }

    public static void printMap() {
        for (int[] m : map) {
            System.out.println(Arrays.toString(m));
        }
        System.out.println();
    }
}

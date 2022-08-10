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
    static ArrayList <Integer []> commandList;
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
        for(int command = 0;  command < R; command++){
            // 3개 길이를 가지는 명령어 하나 할당
            StringTokenizer commandTk = new StringTokenizer(bf.readLine());
            commandList.add(new Integer[3]);
            for(int index = 0; index < 3; index++){
                commandList.get(command)[index] = Integer.parseInt(commandTk.nextToken());
            }
        }
        printCommand();


    }

    private static void printCommand() {
        for(Integer[] cmd : commandList){
            System.out.println(Arrays.toString(cmd));
        }

    }

    public static void printMap() {
        for (int[] m : map){
            System.out.println(Arrays.toString(m));
        }
        System.out.println();
    }
}

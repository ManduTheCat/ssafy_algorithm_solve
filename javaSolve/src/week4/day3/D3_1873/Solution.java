package week4.day3.D3_1873;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
.	평지(전차가 들어갈 수 있다.)
*	벽돌로 만들어진 벽
#	강철로 만들어진 벽
-	물(전차는 들어갈 수 없다.)
^	위쪽을 바라보는 전차(아래는 평지이다.)
v	아래쪽을 바라보는 전차(아래는 평지이다.)
<	왼쪽을 바라보는 전차(아래는 평지이다.)
>	오른쪽을 바라보는 전차(아래는 평지이다.)
* */

public class Solution {
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/D3_1873/input.txt"));
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(bf.readLine());
        for (int tc = 0; tc < TC; tc++) {
            int[] HW = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int H = HW[0];
            int W = HW[1];
            String[][] map = new String[H][W];
            for (int h = 0; h < H; h++) {
                map[h] = bf.readLine().split("");
            }
            Integer.parseInt(bf.readLine());
            String[] commandList = bf.readLine().split("");
            // 명령어 루프
            //명령어 에 따른 실행함수 switch
            for (String command : commandList) {
                switch (command) {
                    case "U":
                        //System.out.println("U");
                        move(map, 0, H, W);
                        //for (String[] m : map) {
                            //System.out.println(Arrays.toString(m));
                        //}
                        break;
                    case "R":
                        //System.out.println("R");
                        move(map, 1, H, W);
                        //for (String[] m : map) {
                            //System.out.println(Arrays.toString(m));
                        //}
                        break;
                    case "S":
                        //System.out.println("S");
                        shot(map, H, W);
                        //for (String[] m : map) {
                            //System.out.println(Arrays.toString(m));
                        //}
                        break;
                    case "D":
                        //System.out.println("D");
                        move(map, 2, H, W);
                        //for (String[] m : map) {
                            //System.out.println(Arrays.toString(m));
                        //}
                        break;
                    case "L":
                        //System.out.println("L");
                        move(map, 3, H, W);
                        //for (String[] m : map) {
                            //System.out.println(Arrays.toString(m));
                        //}
                        break;
                }
            }
            System.out.printf("#%d ", tc+1);
            for(String []m : map){
                for(String e : m){
                    System.out.print(e);
                }
                System.out.println();
            }
        }
    }
    // 시작지점을 찾는함수
    public static void shot(String[][] map, int h, int w) {
        // 현재위치탐색
        int[] currPoint = findStartPoint(map, h, w);
        // 바라보는 방향 찾기
        assert currPoint != null;
        int dir = findDIr(map, currPoint);
        // 바라보는 방향 앞으로 이동하다
        switch (dir){
            case 1 :
                for(int j = currPoint[1]; j< w; j++){
                    // 강철벽 만나면 break;
                    if(map[currPoint[0]][j].equals("#")){
                        break;
                    }
                    // 벽돌벽 만나면 해당 좌표 에 있는 벽 -> * 로
                    if(map[currPoint[0]][j].equals("*")){
                        map[currPoint[0]][j] = ".";
                        break;
                    }
                }
                break;
            case 3:
                for(int l = currPoint[1]; l >=0; l--){
                    if(map[currPoint[0]][l].equals("#")){
                        break;
                    }
                    if(map[currPoint[0]][l].equals("*")){
                        map[currPoint[0]][l] = ".";
                        break;
                    }
                }
                break;
            case 0:
                for(int u = currPoint[0]; u >=0; u--){
                    if(map[u][currPoint[1]].equals("#")){
                        break;
                    }
                    if(map[u][currPoint[1]].equals("*")){
                        map[u][currPoint[1]] = ".";
                        break;
                    }
                }
                break;
            case 2:
                for(int d = currPoint[0]; d < h; d++){
                    if(map[d][currPoint[1]].equals("#")){
                        break;
                    }
                    if(map[d][currPoint[1]].equals("*")){
                        map[d][currPoint[1]] = ".";
                        break;
                    }
                }
                break;
        }
    }

    public static int findDIr(String[][] map, int[] currPoint) {

        int res = -1;
        switch (map[currPoint[0]][currPoint[1]]) {
            case "^":
                res = 0;
                break;
            case ">":
                res = 1;
                break;
            case "v":
                res = 2;
                break;
            case "<":
                res = 3;
                break;
        }
        return res;
    }

    public static int[] findStartPoint(String[][] map, int h, int w) {
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                switch (map[i][j]) {
                    case "<":
                    case ">":
                    case "^":
                    case "v":
                        return new int[]{i, j};
                }
            }
        }
        // 좌표 없으면 null
        return null;
    }

    // s 바라보는 방향으로 포탄이 이동하며 처음만난 장에물 * 벽돌처리
    public static void move(String[][] map, int dir, int H, int W) {
        int[] dx = new int[]{0, 1, 0, -1};
        int[] dy = new int[]{-1, 0, 1, 0};
        int[] startPoint = findStartPoint(map, H, W);
        String turnDir = "!!";
        switch (dir) {
            case 0:
                turnDir = "^";
                break;
            case 1:
                turnDir = ">";
                break;
            case 2:
                turnDir = "v";
                break;
            case 3:
                turnDir = "<";
                break;
        }
        // U,R, D,L 현제 위치에서 방향을 바꾸고 방향으로 이동
        // 1. 방향돌린다
        assert startPoint != null;
        map[startPoint[0]][startPoint[1]] = turnDir;
        int nextRow = startPoint[0] + dy[dir];
        int nextCol = startPoint[1] + dx[dir];
        // 2. 앞으로 이동 하지만 장에물일때(물, 벽, 강철벽 못간다 = "이동할곳이 평지면 이동한다")
        // 3 map 밖일때 안간다.
        if ((nextRow < H && nextRow >= 0 && nextCol < W && nextCol >= 0) && (map[nextRow][nextCol].equals("."))) {
            map[nextRow][nextCol] = turnDir;
            // 4. 이동한후 자리는 평지가 된다.
            map[startPoint[0]][startPoint[1]] = ".";
        }
    }
}

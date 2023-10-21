package SSAFY.week7.day5.BJ17144;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
    static int[][] map;
    static int R, C, T;
    //static ArrayList<Dust> dusts = new ArrayList<>();
    static int machineRow; // 마지막에 나온 공기 청정기 row 를 기록한다.
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/SSAFY.week7/day5/bj17144/input7.txt"));
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
                if (map[r][c] == -1) machineRow = r;
            }
        }
        for (int t = 1; t <= T; t++) {
            diffusion();
            upsideTurn();
            downSideTurn();
        }
        countMap();
        System.out.println(sb);

    }

    public static void diffusion() {
        //3 번 갈아 엎은 코드 때론 새로운 도화지에 누산 결과를 넣는게 깔끔하다는걸 배웠습니다.
        int [][] newMap = new int[R][C];

        for (int r = 0; r < R ; r++) {
            for (int c = 0; c < C; c++) {
                int[][] alpha = new int[][]{{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
                int count = 0;// 가능한 방향 카운트
                for (int i = 0; i < 4; i++) {
                    int nextI = r + alpha[i][0];
                    int nextJ = c + alpha[i][1];
                    if (isIn(nextI, nextJ) && !isAirMachine(nextI, nextJ)) {
                        // 만약 기존 맵에 += 연산을하면 값이 이상해집니다
                        newMap[nextI][nextJ] += map[r][c] / 5;
                        count++;
                    }
                }
                // 만약 기존 맵에 += 연산을하면 값이 이상해집니다
                newMap[r][c] += map[r][c] - (map[r][c]/5) * count;
            }
        }
        map = newMap;
    }

    public static boolean isIn(int i, int j) {
        return i >= 0 && i < R && j >= 0 && j < C;
    }

    public static boolean isAirMachine(int row, int col) {
        int firstMachineRow = machineRow - 1;
        if ((row == machineRow || row == firstMachineRow) && col == 0) {
            return true;
        }
        return false;
    }

    //돌리기
    public static void upsideTurn() {
        //int restOfLeft = map[0][0];
        int restOfRight = map[machineRow - 1][C - 1];
        int restOfUp = map[0][C - 1];
        int restOfLeft = map[0][0];
        goRight(machineRow - 1);
        goUp(0);
        goLeft(0);
        goDown(machineRow - 2, 0);
        // 뺴낸 부분 갱신
        map[machineRow - 2][C - 1] = restOfRight;
        map[0][C - 2] = restOfUp;
        map[1][0] = restOfLeft;

    }

    public static void downSideTurn() {
        int restRight = map[machineRow][C - 1];
        int restDown = map[R -1][C-1];
        int restLeft = map[R-1][0];
        goRight(machineRow);
        downSideGoDown(R - 1, C - 1);
        goLeft(R - 1);
        downSideGoUp(machineRow+1, 0);
        map[machineRow+1][C-1] = restRight;
        map[R-1][C-2] = restDown;
        map[R-2][0] = restLeft;
    }


    public static void goRight(int pullRow) {

        for (int col = C - 1; col >= 2; col--) {
            int temp = map[pullRow][col];
            map[pullRow][col] = map[pullRow][col - 1];
            map[pullRow][col - 1] = temp;
        }
        // 공기청정기의 먼지제거
        map[pullRow][1] = 0;
    }

    // 윗행 아랫행 두칸이상 떨어져 있다. 3칸 떨어질지 4칸떨어질지 모른다.
    public static void goUp(int pullRow) {
        for (int row = pullRow; row < machineRow - 2; row++) {
            int temp = map[row][C - 1];
            map[row][C - 1] = map[row + 1][C - 1];
            map[row + 1][C - 1] = temp;
        }
    }

    public static void goLeft(int pullRow) {
        for (int col = 0; col < C - 2; col++) {
            int temp = map[pullRow][col];
            map[pullRow][col] = map[pullRow][col + 1];
            map[pullRow][col + 1] = temp;
        }
    }

    public static void goDown(int pullRow, int col) {
        for (int row = pullRow; row > 1; row--) {
            int temp = map[row][col];
            map[row][col] = map[row - 1][col];
            map[row - 1][col] = temp;
        }
    }

    public static void downSideGoDown(int pullRow, int col) {
        for (int row = pullRow; row > machineRow + 1; row--) {
            int temp = map[row][col];
            map[row][col] = map[row - 1][col];
            map[row - 1][col] = temp;
        }
    }

    public static void downSideGoUp(int pullRow, int col) {
        for (int row = pullRow; row < R-2; row++) {
            int temp = map[row][col];
            map[row][col] = map[row+1][col];
            map[row+1][col] = temp;
        }
    }

    public static void countMap() {
        int count = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] != -1) count+=map[i][j];
            }
        }
        sb.append(count);
    }

}

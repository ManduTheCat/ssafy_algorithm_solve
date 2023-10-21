package SSAFY.week6.day2.BJ15683;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Cctv {
    int i;
    int j;
    int kind;

    public Cctv(int i, int j, int kind) {
        this.i = i;
        this.j = j;
        this.kind = kind;
    }
}

public class Main {
    static int n;
    static int m;
    static int[][] map;
    static int cctvCount;
    static ArrayList<Cctv> cctvList = new ArrayList<>();
    static int[] directionList;
    static ArrayList<Integer> zeroLsit = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/SSAFY.week6/day2/BJ15683/input6.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                // 여기서 cctv 갯수도 새자
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] != 0 && map[i][j] != 6) {
                    cctvCount++;
                    cctvList.add(new Cctv(i, j, map[i][j]));
                }
            }
        }
        //System.out.println(cctvList);

        directionList = new int[cctvCount];
        //System.out.println();
        permutation(0);
        if (zeroLsit.size() != 0) {
            System.out.println(Collections.min(zeroLsit));
        } else {
            System.out.println(0);
        }

    }

    // 방향별로 체크 안된곳을 확인
    // 1. cctv 통과
    // 2. 6은 통과 못함
    // 3. 방향 보고 끝까지 간다
    // 방향도 조합으로 1번 4가지 종류의 방향이 있다.
    // 방향 내에서도 조합을 구현 만약 1번의 경우 4번을돌린다.

    static public void seeMap(int[] directionList) {
        // cctv 종류에 따라 방향 다르게 처리
        // for i++ cctvList
        // switch(cctiv종류)
        // case 2:
        // direction2(directionList[i])
        int[][] temp = mapCopy();
        for (int i = 0; i < directionList.length; i++) {
            //무슨 cctv 인지 보고 방향을 넘겨준다
            //System.out.println("kind "+ cctvList.get(i).kind);
            switch (cctvList.get(i).kind) {
                case 1:
                    direction(directionList[i], cctvList.get(i));
                    break;
                case 2:
                    direction(directionList[i], cctvList.get(i));
                    direction(directionList[i] + 2, cctvList.get(i));
                    break;
                case 3:
                    direction(directionList[i], cctvList.get(i));
                    direction(directionList[i] + 1, cctvList.get(i));
                    break;
                case 4:
                    direction(directionList[i], cctvList.get(i));
                    direction(directionList[i] + 1, cctvList.get(i));
                    direction(directionList[i] + 2, cctvList.get(i));
                    break;
                case 5:
                    direction(directionList[i] - 1, cctvList.get(i));
                    direction(directionList[i], cctvList.get(i));
                    direction(directionList[i] + 1, cctvList.get(i));
                    direction(directionList[i] + 2, cctvList.get(i));
                    break;
            }
        }
        zeroLsit.add(countZero());
        map = temp;
    }

    static public int[][] mapCopy() {
        int[][] temp = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                temp[i][j] = map[i][j];
            }
        }
        return temp;
    }

    static public boolean isValid(int i, int j) {
        if (i < n && i >= 0 && j < m && j >= 0 && (map[i][j] != 6)) {
            return true;
        }
        return false;
    }

    static public void direction(int direction, Cctv cctv) {
        int[] dx = new int[]{-1, 0, 1, 0};
        int[] dy = new int[]{0, -1, 0, 1};
        // 벽에 닿거나 넘어갈때까지 계속 이동해라
        int i = cctv.i;
        int j = cctv.j;
        //@author 준영 하드케리
        direction %= 4;
        while (true) {
            //System.out.println(direction);
            if (direction == 0) {
                if (isValid(i + dy[direction], j + dx[direction])) {
                    i += dy[direction];
                    j += dx[direction];
                    map[i][j] = 9;

                } else {
                    break;
                }
            } else if (direction == 1) {
                if (isValid(i + dy[direction], j + dx[direction])) {
                    i += dy[direction];
                    j += dx[direction];
                    map[i][j] = 9;
                } else {
                    break;
                }
            } else if (direction == 2) {
                if (isValid(i + dy[direction], j + dx[direction])) {

                    i += dy[direction];
                    j += dx[direction];
                    map[i][j] = 9;
                } else {
                    break;
                }
            } else if (direction == 3) {
                if (isValid(i + dy[direction], j + dx[direction])) {
                    i += dy[direction];
                    j += dx[direction];
                    map[i][j] = 9;
                } else {
                    break;
                }
            }
        }
        //printMap();
        //System.out.println();

    }
    //이동 하는 함수
    //기준 방향 0 왼, 1 위, 2오른쪽, 3아래쪽
    // 애는 앞으로 한번만 가는함수
    // 0 이면 왼쪽으로 1이면 위로 2면 오른쪽으로 3이면 아래로

    static public int countZero() {
        int zero = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) {
                    zero++;
                }
            }
        }
        return zero;
    }

    static public void printMap() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }

    }

    //1111 1112 1113 1114
    // 1 2 3 4 5
    // 중복을 허용하는 permutation;
    // cctv 종류에 따라 방향을 permutation  해야한다.
    static public void permutation(int depth) {
//        System.out.println("=============");
//        printMap();

        if (depth == cctvCount) {
            //System.out.println(Arrays.toString(directionList));
            seeMap(directionList);
            return;
        }
        switch (cctvList.get(depth).kind) {
            case 1:
            case 4:
            case 3:
                //System.out.println("cctv kind: " + cctvList.get(depth).kind);
                for (int i = 0; i < 4; i++) {
                    directionList[depth] = i;
                    permutation(depth + 1);
                }
                break;
            case 2:
                for (int i = 0; i < 2; i++) {
                    directionList[depth] = i;
                    permutation(depth + 1);
                }
                break;

            case 5:
                directionList[depth] = 1;
                permutation(depth + 1);
                break;
        }
    }


}

package SSAFY.week13.day2.fishing;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Shark {
    int r;
    int c;
    int s;
    int d;
    int z;

    public Shark(int r, int c, int s, int d, int z) {
        this.r = r;
        this.c = c;
        this.s = s;
        this.d = d;
        this.z = z;
    }

    @Override
    public String toString() {
        return "Shark{" +
                "r=" + r +
                ", c=" + c +
                ", s=" + s +
                ", d=" + d +
                ", z=" + z +
                '}';
    }

}

public class Main {
    static int[][] del = {{-1, 0}, {0, 1}, {0, -1}, {1, 0}};
    static ArrayList<Shark> sharks;
    static int N, M, NS;
    static int[][] map;
    static int res;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/SSAFY.week13/day2/BJ17143/input4.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        NS = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        sharks = new ArrayList<>();
        for (int ns = 0; ns < NS; ns++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            switch (d) {
                case 1:
                    sharks.add(new Shark(r, c, s, 0, z));
                    break;
                case 2:
                    sharks.add(new Shark(r, c, s, 3, z));
                    break;
                case 3:
                    sharks.add(new Shark(r, c, s, 1, z));
                    break;
                case 4:
                    sharks.add(new Shark(r, c, s, 2, z));
                    break;
            }

        }

        for (int fisherCol = 0; fisherCol < M; fisherCol++) {

            fishingShark(fisherCol);
            //System.out.println("move");
            move();
            //System.out.println(sharks);
            // 중복 좌표 있으면 잡아먹어라!~
            eatEachOther();
            printMap();
            //System.out.println();
        }
        System.out.println(res);

    }



    private static void eatEachOther() {
        Shark [][] newMap = new Shark[N][M];

        for(int idx = 0; idx < sharks.size(); idx++){
                Shark currShark = sharks.get(idx);
                if(newMap[currShark.r][currShark.c] != null){
                    //싸워야한다.
                    if(currShark.z > newMap[currShark.r][currShark.c].z){
                        newMap[currShark.r][currShark.c] = currShark;
                    }
                }
                else {
                    newMap[currShark.r][currShark.c] = currShark;
                }

            }


        sharks.clear();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(newMap[i][j] != null){
                    sharks.add(newMap[i][j]);
                }
            }
        }
    }

    private static void fishingShark(int fisherCol) {
        int[][] newMap = putShark();
        // 맵에 상어를 박제하고
        for (int row = 0; row < N; row++) {
            if (newMap[row][fisherCol] > 0) {
                for (int idx = 0, size = sharks.size(); idx < size; idx++) {
                    if(sharks.get(idx).r == row && sharks.get(idx).c == fisherCol){

                        res+=sharks.get(idx).z;
                        sharks.remove(idx);
                        return;
                    }
                }
            }
        }

    }

    private static void move() {
        for (Shark shark : sharks) {
            int speed = shark.s;
            for (int move = 0; move < speed; move++) {
                int nextR = shark.r + del[shark.d][0];
                int nextC = shark.c + del[shark.d][1];
                if (isin(nextR, nextC)) {
                    shark.r = nextR;
                    shark.c = nextC;
                } else {
                    shark.r -= del[shark.d][0];
                    shark.c -= del[shark.d][1];
                    shark.d = 3 - shark.d;
                }
            }
        }

    }
    // 잡아먹는 함수
    // 낚시꾼이 잡는 함수
    // 낚시꾼이 이동하는 함수
    public static void printMap() {
        int[][] newMap = new int[N][M];
        for (Shark shark : sharks) {
            newMap[shark.r][shark.c] = shark.z;
        }

    }

    public static int[][] putShark() {
        int[][] newMap = new int[N][M];
        for (Shark shark : sharks) {
                newMap[shark.r][shark.c] = shark.z;
        }
        return newMap;
    }

    private static boolean isin(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }
}

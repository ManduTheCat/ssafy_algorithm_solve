package algorithmStudy.week3.numberOfPaper1780;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/study/week3/BJ1780/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int row = 0; row < N; row++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int col = 0; col < N; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());
            }
        }
        printMap(6, 6, 3);
        System.out.println(isFill(6, 6, 3));
    }

    public static void findSquare(int i, int j, int size){

    }
    public static boolean isFill(int i, int j, int size) {
        int zCount = 0, oneCount = 0, minusCount = 0;
        for (int row = i; row < i + size; row++) {
            for (int col = j; col < j + size; col++) {
                switch (map[row][col]) {
                    case (1):
                        oneCount++;
                        break;
                    case (0):
                        zCount++;
                        break;
                    case (-1):
                        minusCount++;
                        break;
                }
            }
        }
        int fullSize = size * size;
        return zCount == fullSize || oneCount == fullSize || minusCount == fullSize;
    }

    public static void printMap(int i, int j, int size) {
        for (int row = i; row < i + size; row++) {
            for (int col = j; col < j + size; col++) {
                System.out.print(map[row][col] + " ");
            }
            System.out.println();
        }
    }
}

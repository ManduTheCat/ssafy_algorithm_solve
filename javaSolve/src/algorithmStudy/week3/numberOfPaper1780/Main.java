package algorithmStudy.week3.numberOfPaper1780;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] map;
    static Map<Integer, Integer> counts = new HashMap<>();

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
        counts.put(-1, 0);
        counts.put(1, 0);
        counts.put(0,0);
        if(!isFill(0,0, N)){
            findSquare(0,0, N, 0);
        }
        else {
            counts.put(map[0][0],counts.get(map[0][0]) + 1);
        }
        System.out.println(counts.get(-1));
        System.out.println(counts.get(0));
        System.out.println(counts.get(1));

    }

    public static void findSquare(int i, int j, int size, int depth) {

            // 왼위 가운데위 왼쪽위

        int[][] alpha = new int[][]{
                {i, j, size/3}, {i, j+size/3, size/3}, {i, j+(size*2)/3, size/3},
                {i+size/3, j, size/3}, {i + size/3, j+size/3, size/3}, {i + size/3, j+(2*size)/3, size/3},
                {i+(2*size)/3, j, size/3}, {i+(2*size)/3, j+size/3, size/3}, {i+(2*size)/3, j+(2*size)/3, size/3}
        };
        for(int [] row : alpha){
            if(!isFill(row[0], row[1], row[2])){
                findSquare(row[0], row[1], row[2], depth+1);
            }
            else {
                counts.put(map[row[0]][row[1]],counts.get(map[row[0]][row[1]])+1);
            }
        }



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
}

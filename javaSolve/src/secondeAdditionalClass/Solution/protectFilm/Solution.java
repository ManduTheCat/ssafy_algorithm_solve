package secondeAdditionalClass.Solution.protectFilm;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    // 부분집합으로 경우의 수를구하는데 이때 검사하면서 최소 경우의수를 기억한다
    // 만약 다음 부분집합이 최소 경우수보다 크다면 들어가지 않는다.
    static int Tc;
    static int D;
    static int W;
    static int K;
    static int [][] film;
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resouces/secondClass/sweaFilm/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Tc =Integer.parseInt(br.readLine());
        for (int tc = 0; tc < Tc; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            D = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            film = new int[D][W];
            for (int d = 0; d < D; d++) {
                st = new StringTokenizer(br.readLine());
                for (int w = 0; w < W; w++) {
                    film[d][w] = Integer.parseInt(st.nextToken());
                }
            }
            // 할당완료 검사하는 항목 작성

            checkVaild(film);
        }
        

    }

    private static boolean checkVaild(int[][] film) {
        // 세로 를 내려 찍으면서 깊이가 K 개 이상인지 검사한다.
        // 연속된 같은 친구가 3개이상있는가
        //line 만뽑아 내자
        int validCount = 0;
        for (int w = 0; w < W; w++) {
            int [] line = new int[D];
            for (int d = 0; d < D; d++) {
                line[d] = film[d][w];
            }
            if(checkLine(line)){
               validCount++;
            }
        }
        return validCount == K ? true: false;

    }

    private static boolean checkLine(int[] line) {
        return false;
    }
}

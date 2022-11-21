package secondeAdditionalClass.protectFilm.dfsSolve;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Solution {
    static int Tc;
    static int D;
    static int W;
    static int K;
    static int[][] film;
    static boolean[] powerSelect;
    static int minCount;
    static int [][] originMap;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/secondClass/sweaFilm/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Tc = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < Tc; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            D = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            minCount = K; // 왜?
            powerSelect = new boolean[D];
            film = new int[D][W];
            for (int d = 0; d < D; d++) {
                st = new StringTokenizer(br.readLine());
                for (int w = 0; w < W; w++) {
                    film[d][w] = Integer.parseInt(st.nextToken());
                }
            }
            originMap = deepCopy(film);
            dfs( film , 0, 0);
            System.out.printf("#%d %d\n",tc+1,minCount);
        }
    }

    private static void dfs(int [][] map, int depth, int changeCount) {

        if(depth == D){
            if(checkVaild(map)){
                minCount = Math.min(minCount, changeCount);
            }
            return;
        }
        // 1로 체크 하고 dfs 보넨다
        if(changeCount > minCount){
            return;
        }
        Arrays.fill(map[depth] , 1);
        dfs(map, depth + 1, changeCount+ 1);
        map[depth] = originMap[depth].clone();

        // 2로 체크 하고 dfs 보넨다.
        Arrays.fill(map[depth] , 0);
        dfs(map, depth +1, changeCount+1);
        map[depth] = originMap[depth].clone();
        // 체크 안하고 dfs 보넨ㄴ다
        dfs(map, depth + 1, changeCount);

    }

    private static int[][] deepCopy(int[][] origin) {
        int resfilm[][] = new int[D][W];
        for (int d = 0; d < D; d++) {
            resfilm[d] = origin[d].clone();
        }
        return resfilm;
    }

    private static boolean checkVaild(int[][] inputMap) {
        // 한줄 씩 k 개 이상의 같은 패턴이 있나 검사한다.
        for (int col = 0; col < W; col++) {
            int max = 0;
            int count = 1;
            int pattern = inputMap[0][col];
            for (int row = 1; row < D; row++) {
                if(pattern == inputMap[row][col]) {
                    count ++;
                }
                else {
                    count = 1;
                    pattern = inputMap[row][col];
                }
                max = Math.max(count, max);
            }
            if(max < K) return false;

        }
        return true;

    }

}

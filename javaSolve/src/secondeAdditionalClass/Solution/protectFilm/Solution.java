package secondeAdditionalClass.Solution.protectFilm;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    // 부분집합으로 경우의 수를구하는데 이때 검사하면서 최소 경우의수를 기억한다
    // 만약 다음 부분집합이 최소 경우수보다 크다면 들어가지 않는다.
    static int Tc;
    static int D;
    static int W;
    static int K;
    static int[][] film;
    static boolean[] powerSelect;
    static int minCount;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/secondClass/sweaFilm/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Tc = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < Tc; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            D = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            minCount = W;
            powerSelect = new boolean[D];
            film = new int[D][W];
            for (int d = 0; d < D; d++) {
                st = new StringTokenizer(br.readLine());
                for (int w = 0; w < W; w++) {
                    film[d][w] = Integer.parseInt(st.nextToken());
                }
            }
            // 할당완료 검사하는 항목 작성
            //System.out.println(Arrays.deepToString(film));
            //System.out.println(checkVaild(film));
            //System.out.println(checkLine(new int []{1,1,1,1,1,0}));
            if (checkVaild(film) || K == 1) {
                System.out.printf("#%d %d\n", tc + 1, 0);
            }
            else {
                power(0);
                System.out.printf("#%d %d\n", tc + 1, minCount);
            }
        }
    }

    // 깊이중 선택해
    private static void power(int depth) {
        if (depth == D) {
            int selectCount = 0;
            for (boolean idSelect : powerSelect) {
                if (idSelect) selectCount++;
            }
            if (selectCount < minCount) {
                changeFilm(0, deepCopy(film));
            }
            return;
        }

        powerSelect[depth] = true;
        power(depth + 1);
        powerSelect[depth] = false;
        power(depth + 1);
    }

    private static int[][] deepCopy(int[][] origin) {
        int resfilm[][] = new int[D][W];
        for (int d = 0; d < D; d++) {
            resfilm[d] = origin[d].clone();
        }
        return resfilm;
    }

    private static void changeFilm(int idx, int[][] cloneFilm) {
        if (idx == D) {
            if (checkVaild(cloneFilm)) {
                //System.out.println(Arrays.deepToString(cloneFilm));
                int tCount = 0;
                for (boolean el : powerSelect) {
                    if (el) {
                        tCount++;
                    }
                }
                //System.out.println(tCount);
                minCount = Math.min(minCount, tCount);
            }
            return;
        }
        if (powerSelect[idx]) {
            Arrays.fill(cloneFilm[idx], 1);
            changeFilm(idx + 1, deepCopy(cloneFilm));
            Arrays.fill(cloneFilm[idx], 0);
            changeFilm(idx + 1, deepCopy(cloneFilm));
        } else {
            changeFilm(idx + 1, cloneFilm);
        }
    }


    private static boolean checkVaild(int[][] film) {
        // 세로 를 내려 찍으면서 깊이가 K 개 이상인지 검사한다.
        // 연속된 같은 친구가 3개이상있는가
        //line 만뽑아 내자
        int validCount = 0;
        for (int w = 0; w < W; w++) {
            int[] line = new int[D];
            for (int d = 0; d < D; d++) {
                line[d] = film[d][w];
            }
            if (!checkLine(line)) {
                //System.out.println(Arrays.toString(line));
                return false;
            }
        }
        //System.out.println(validCount);
        return true;

    }

    private static boolean checkLine(int[] line) {
        int sameCount = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int idx = line.length - 1; idx >= 0; idx--) {
            stack.push(line[idx]);
        }
        int curr = stack.pop();
        while (!stack.isEmpty()) {
            int stackPeek = stack.pop();
            if (stackPeek == curr) {
                sameCount++;
                if (K - 1 == sameCount) {
                    return true;
                }
            } else {
                sameCount = 0;
                curr = stackPeek;
            }
        }
        return false;
    }
}

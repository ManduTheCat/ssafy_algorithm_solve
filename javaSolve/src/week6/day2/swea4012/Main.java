package week6.day2.swea4012;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static int n;
    static int Tc;
    static int[][] map;
    static ArrayList<Integer> resList;
    static int[] permutationResList;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/week6/day2/swea4012/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int Tc = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < Tc; tc++) {
            n = Integer.parseInt(br.readLine());
            map = new int[n][n];
            resList = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            permutationResList = new int[n];
            // 오름 차순 정렬
            for (int i = n-1; i >= n/2; i--) {
                permutationResList[i] = 1;
            }
            do {
                calc();

            } while (nextPermutation());
            System.out.printf("#%d %d\n",tc+1,Collections.min(resList));
        }
    }
    // 절반a 나머지는 b 인 성질 이용

    public static void calc(){
        int size = permutationResList.length;
        int ia = 0;
        int ib = 0;
        int[] sumA = new int[n/2];
        int[] sumB = new int[n/2];
        for(int i = 0; i < n; i++){
            if(permutationResList[i] == 1)sumA[ia++] = i;
            else sumB[ib++] = i;
        }
        int sub = 0;
        for(int i =0; i< n/2; i++){
            for(int j = 0; j < n/2; j++){
                if(i ==j )continue;
                sub += map[sumA[i]][sumA[j]] - map[sumB[i]][sumB[j]];
            }
        }
        resList.add(Math.abs(sub));


    }

    public static boolean nextPermutation() {
        int len = permutationResList.length;
        int i = len - 1;
        while (i > 0 && permutationResList[i - 1] >= permutationResList[i]) --i;
        if (i == 0) return false;
        int j = len - 1;
        while (permutationResList[i - 1] >= permutationResList[j]) --j;
        swap(permutationResList, i - 1, j);
        int k = len - 1;
        while (i < k) swap(permutationResList, i++, k--);
        return true;
    }

    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
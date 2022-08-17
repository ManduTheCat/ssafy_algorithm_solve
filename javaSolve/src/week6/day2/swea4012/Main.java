package week6.day2.swea4012;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;


public class Main {
    static int n;
    static int Tc;
    static int[][] map;
    static ArrayList<Integer> resList;
    static int[] permutationResList;
    static int[] foodA;
    static int[] foodB;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/week6/day2/swea4012/input3.txt"));
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
            for (int i = 0; i < n; i++) {
                permutationResList[i] = i;
            }
            System.out.println(Arrays.toString(permutationResList));
        }
        do {

            foodA = Arrays.copyOf(permutationResList, (n/2));
            foodB = Arrays.copyOfRange(permutationResList,(n/2),permutationResList.length);
            System.out.print(Arrays.toString(foodA)+ " ");
            System.out.println(Arrays.toString(foodB));
            // 이거 가지고 차만구하면된다

        } while (nextPermutation());

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
package week6.day5.BJ3234;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int N;
    static int Tc;
    static int[] permutationRes;
    static int[] heavyArray;
    static int count;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/week6/day5/BJ3234/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Tc = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < Tc; tc++) {
            N = Integer.parseInt(br.readLine());
            heavyArray = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int n = 0; n < N; n++) {
                heavyArray[n] = Integer.parseInt(st.nextToken());
            }
            permutationRes = new int[N];
            count = 0;

            permutation(0, 0);
            System.out.printf("#%d %d\n", tc + 1, count);
        }

    }

    public static void permutation(int depth, int flag) {
        if (depth == N) {
            powerSet(0, 0, 0);
            return;
        }
        for (int i = 0; i < N; i++) {
            if ((flag & 1 << i) == 0) {
                permutationRes[depth] = i;
                permutation(depth + 1, flag | 1 << i);
            }
        }
    }


    //    오른쪽 부분 집합
    public static void powerSet(int idx, int left, int right) {
        if (right > left) return;
        if (idx == N) {
            count++;
            return;
        }
        powerSet(idx + 1, left + heavyArray[permutationRes[idx]], right);
        powerSet(idx + 1, left, right + heavyArray[permutationRes[idx]]);
    }
}

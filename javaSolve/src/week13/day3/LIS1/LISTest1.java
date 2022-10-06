package week13.day3.LIS1;

import java.util.Arrays;
import java.util.Scanner;

public class LISTest1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] arr = new int[N];
        int[] Lis = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();

        }
        int max = 0;
        for (int i = 0; i < N; i++) {
            // 앞쪽부터 모든 원소기준으로 자신을 끝으로 하는 LIS 산
            Lis[i] = 1;
            for (int j = 0; j < i; j++) {
                // 내 앞까지만돈다
                if (arr[j] < arr[i] && Lis[i] < Lis[j] + 1) {
                    // 나보다 이전수이고
                    // 임시최대가 붙인것보다 작아야한다
                    Lis[i] = Lis[j] + 1;
                }
            }
            max = Math.max(max, Lis[i]);
        }
        System.out.println(Arrays.toString(Lis));
        System.out.println(max);
    }
}

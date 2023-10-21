package SSAFY.week13.day3.Live1006;

import java.util.Arrays;
import java.util.Scanner;

public class LISTest1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] arr = new int[N]; // 수열의 수들
		int[] LIS = new int[N]; // 동적테이블 : 각 원소를 끝으로하는 LIS값
		
		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}
		
		int max = 0;
		for (int i = 0; i < N; i++) { // 앞쪽부터 모든 원소기준으로 자신을 끝으로하는  LIS 계산
			LIS[i] = 1;
			for (int j = 0; j < i; j++) {
				if(arr[j]<arr[i] && LIS[i] < LIS[j]+1) {
					LIS[i] = LIS[j]+1;
				}
			}
			max = Math.max(max, LIS[i]);
		}
		
		System.out.println(Arrays.toString(LIS));
		System.out.println(max);
	}

}

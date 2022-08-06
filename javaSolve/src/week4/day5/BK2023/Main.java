package week4.day5.BK2023;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * @author 김명진
 * 11544KB	76ms
 */
public class Main {
    static int N;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        makePrime(0, 0);

    }

    /**
     * N 개의 숫자를 만들기 위해 1의자리를 하나씩 넣어 숫자를 만들어가면 전체 경우의 수를
     * 찾는 로직
     * 한자리수 만들때마다 소수검사를 하여 문제 조건중 자리수별로도 소수를 만족하는 숫자를
     * 찾을수 있다
     *
     * @param count     제귀를 돌면 목표한 N 개의 수를 찾으면 출력하고 재귀 종료한다
     * @param currPrime 재귀를 돌며 쌓이는 숫자 재귀를 돌면 숫자가 붙을때마다 소수인지검사한다
     */
    public static void makePrime(int count, int currPrime) {
        if (count == N) {
            // 요구한 N개의 숫자를 만들면 이제 출력하고 재귀를 종료한다.
            System.out.println(currPrime);
            return;
        }
        for (int p = 1; p < 10; p++) {
            // 기존 숫자 자리수를 올리고 1의자리수 추가
            int nextPrime = (currPrime * 10) + p;
            // 만들어진 숫자가 소수인지 검사하고 아니면 진행하지 않는다
            if (isPrime(nextPrime)) {
                makePrime(count + 1, nextPrime);
            }
        }
    }

    public static Boolean isPrime(int input) {
        if (input == 1) return false;
        // 소수찾기 위헤 Math.sqrt(input) 범위 만큼만 조사한다
        // 어떤수의 약수는 항상 Math.sqrt(input) 보다 작은 곳에 존재하기 땜문에
        for (int i = 2; i * i <= input; i++) {
            if (input % i == 0) return false;
        }
        return true;
    }
}

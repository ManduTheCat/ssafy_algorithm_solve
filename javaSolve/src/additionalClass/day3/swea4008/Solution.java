package additionalClass.day3.swea4008;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 11!부터 순열은 거이 안된다.
// 가지치기를 생각해봐야한다
// 더하기 를 연속으로 배치하는 부분을 제거하면 된다 = 중복 제거
// 하지만 중간에 힘들다
// 일단 순열을 구하고 중복을 제거 - 즉 연산할때 중복연산을 안한다.
/*
102,288 kb
메모리
265 ms
실행시간
 */
// 1 1 2 = 121 = 211= 211= 112=121
// 넥퍼를 쓰면 중복 순열을 떨굴수 있다

public class Solution {
    static int Tc;
    static int N;

    static int [] operatorPermutationRes;
    static int [] ableOperatorCnt;
    static int [] numbers;
    static int max;
    static int min;
    public static void main(String[] args)throws IOException {
        System.setIn(new FileInputStream("resources/additionalClass/day3/swea4008/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Tc = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < Tc; tc++) {
            min = Integer.MAX_VALUE;
            max = -100000000;
            N = Integer.parseInt(br.readLine());
            ableOperatorCnt = new int[4];
            operatorPermutationRes = new int[N-1];
            numbers = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int op = 0; op <4 ; op++) {
                int value = Integer.parseInt(st.nextToken());
                //System.out.println(value);
                ableOperatorCnt[op] = value;
            }
            // 플마곱나 순서
            st= new StringTokenizer(br.readLine());
            for (int n = 0; n < N; n++) {
                numbers[n] = Integer.parseInt(st.nextToken());
            }

            //System.out.println(Arrays.toString(numbers));
            permutation(0,0);
            //System.out.println(max+" - "+ min);
            System.out.printf("#%d %d\n",tc+1,max - min);
        }

    }
    public static void permutation(int depth, int index){
        if(depth == N-1){
            //System.out.println(depth);
            //System.out.println(Arrays.toString(operatorPermutationRes));
            calculate(operatorPermutationRes);
        }
        for (int i = 0; i < 4; i++) {
            if(ableOperatorCnt[i] > 0){
                ableOperatorCnt[i]--;
                operatorPermutationRes[index] = i;
                permutation(depth+1, index+1);
                ableOperatorCnt[i]++;
            }
        }

    }

    private static void calculate(int[] operatorPermutationRes) {
        Deque<Integer> st = new LinkedList<>();
        for (int i = 0; i <N-1 ; i++) {
            st.offerLast(numbers[i]);
            st.offerLast(operatorPermutationRes[i]);
        }
        st.offerLast(numbers[numbers.length-1]);
        int count = 0;
        //System.out.println(st);
        while (count++ < N-1){
            int num = st.pollFirst();
            int op = st.pollFirst();
            int num2 = st.pollFirst();

            //System.out.print(" op " + op);

            switch (op){
                case 0:
                    st.offerFirst(num + num2);
                    break;
                case 1:
                    st.offerFirst(num - num2);
                    break;
                case 2:
                    st.offerFirst(num * num2);
                    break;
                case 3:
                    st.offerFirst(num / num2);
                    break;
            }
        }
        //System.out.println(st.peek());
        max = Math.max(max, st.peekFirst());
        min = Math.min(min, st.peekFirst());
    }

}

package additionalClass.day3.swea4008NextPermutaion;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

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
/*
*  2 3 4 5 1
*   1. lastPeak = 2;
*   2. getBeforelastPeak = 3
*   3. 1, 2 번스왑
*   4. 1 -> 끝까지 팰린드룸 스왑 24 5 3 1 -> 24 1 3 5
*   넥퍼는 오름차순 정렬을 해야한다.
*   d
* */

/*
* 넥퍼의 효능
* 가지치기 못하는대신
* 어짜피 다구해야한다
* 중복순열애서 중복되지 않는것을 구할때
* */


public class Solution {
    static int Tc;
    static int N;

    static int [] operatorPermutationRes;
    static int [] ableOperatorCnt;
    static int [] numbers;
    static int max;
    static int min;
    static int [] opers;
    public static void main(String[] args)throws IOException {
        System.setIn(new FileInputStream("resources/additionalClass/day3/swea4008/input2.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Tc = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < Tc; tc++) {
            min = Integer.MAX_VALUE;
            max = -100000000;
            N = Integer.parseInt(br.readLine());
            ableOperatorCnt = new int[4];
            operatorPermutationRes = new int[N-1];
            numbers = new int[N];
            opers = new int[N-1];
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int op = 0, o = 0; op <4 ; op++) {
                int value = Integer.parseInt(st.nextToken());
                //System.out.println(value);
                ableOperatorCnt[op] = value;
                for (int i = 0; i <value ; i++) {
                    opers[o++] = op;
                }

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
            do{
                System.out.println(Arrays.toString(opers));
            }while (nextPermutation());
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

    public static boolean nextPermutation(){
        //1 라스트 픽 찾기 제일 큰수
        //== 맨꼭대기 찾기
        // 1234-> 4321 ㅇㅣ되면 np 가 없는 케이스다 처음에 정렬하고 들어가기 때문이다.
        int lastPeak = opers.length-1;
        while (lastPeak > 0 && opers[lastPeak -1] >= opers[lastPeak]){
            lastPeak--;
        }
        if(lastPeak == 0){// 이미 마지막인 상황 -- 다음은 없다
            return false;
        }
        // 2. 라스트픽보다 (왼쪽)앞에 있는 것보다 큰놈을 찾는다.
        int gtBeforeLastPeak =  opers.length-1;
        while (opers[lastPeak-1]>= opers[gtBeforeLastPeak]){
            gtBeforeLastPeak--;
        }
        swap(lastPeak-1, gtBeforeLastPeak);
        for (int reverseIdx = opers.length -1; lastPeak < reverseIdx;){
            swap(lastPeak++, reverseIdx--);
        }
        return true;
    }
    public static void swap(int a, int b){
        int temp = opers[a];
        opers[a] = opers[b];
        opers[b] = temp;
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

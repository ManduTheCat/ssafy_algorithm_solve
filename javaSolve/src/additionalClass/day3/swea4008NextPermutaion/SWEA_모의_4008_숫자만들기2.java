package additionalClass.day3.swea4008NextPermutaion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author 은서파
 * @since 2022. 10. 4.
 * @see
 * @git
 * @youtube
 * @performance 28,320 kb, 138 ms   31,944 kb, 142 ms
 * @category #
 * @note
 */

public class SWEA_모의_4008_숫자만들기2 {

    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder output = new StringBuilder();
    static StringTokenizer tokens;
    static int T;
    static int N;
    static int[] operCnts;
    static int[] nums;
    static int MIN, MAX;

    public static void main(String[] args) throws IOException {
        input = new BufferedReader(new StringReader(instr));
        T = Integer.parseInt(input.readLine());
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(input.readLine());
            tokens = new StringTokenizer(input.readLine());
            operCnts = new int[4];
            for (int i = 0; i < 4; i++) {
                operCnts[i] = Integer.parseInt(tokens.nextToken());
            }
            tokens = new StringTokenizer(input.readLine());
            nums = new int[N];
            for (int n = 0; n < N; n++) {
                nums[n] = Integer.parseInt(tokens.nextToken());
            }
            //System.out.println(Arrays.toString(opers)+" : "+Arrays.toString(nums));
            // 입력 완료

            MIN = Integer.MAX_VALUE;
            MAX = Integer.MIN_VALUE;

            
            makePermutationUsingCount(0, new int[N - 1]);
            
            
            output.append(String.format("#%d %d%n", t, MAX - MIN));

        }// T.C
        System.out.println(output);
    }
    
    static void makePermutationUsingCount(int nth, int[] selected) {
        if (nth == N-1 ) {
            int result = calc(selected);
            MAX = Math.max(MAX, result);
            MIN = Math.min(MIN, result);
            return;
        }

        for (int i = 0; i < operCnts.length; i++) { 
            if (operCnts[i]>0) {
                operCnts[i]--;
                selected[nth] = i; // i 자체가 연산자의 종류, operCnts[i]는 해당 연산자의 사용 가능한 개수
                makePermutationUsingCount(nth + 1,  selected);
                operCnts[i]++;
            }
        }
    }

    static void makePermutation(int nth, boolean[] visited, int[] selected) {
        if (nth == N-1 ) {
            int result = calc(selected);
            MAX = Math.max(MAX, result);
            MIN = Math.min(MIN, result);
            return;
        }

        for (int i = 0; i < operCnts.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                selected[nth] = operCnts[i];
                makePermutation(nth + 1, visited, selected);
                visited[i] = false;
            }
        }
    }

    // 1   +2   *3
    static int calc(int[] selected) {
        int num = nums[0];
        for (int i = 0; i < selected.length; i++) {
            int oper = selected[i];
            int next = nums[i + 1];
            if (oper == 0) { // +
                num += next;
            } else if (oper == 1) { // -
                num -= next;
            } else if (oper == 2) { // *
                num *= next;
            } else {
                num /= next;
            }
        }
        return num;
    }


    // REMOVE_START
    private static String instr = "10\n"
                                  + "5\n"
                                  + "2 1 0 1\n"
                                  + "3 5 3 7 9\n"
                                  + "6\n"
                                  + "4 1 0 0\n"
                                  + "1 2 3 4 5 6 \n"
                                  + "5\n"
                                  + "1 1 1 1\n"
                                  + "9 9 9 9 9 \n"
                                  + "6\n"
                                  + "1 4 0 0\n"
                                  + "1 2 3 4 5 6 \n"
                                  + "4\n"
                                  + "0 2 1 0\n"
                                  + "1 9 8 6\n"
                                  + "6\n"
                                  + "2 1 1 1\n"
                                  + "7 4 4 1 9 3 \n"
                                  + "7\n"
                                  + "1 4 1 0\n"
                                  + "2 1 6 7 6 5 8 \n"
                                  + "8\n"
                                  + "1 1 3 2\n"
                                  + "9 2 5 3 4 9 5 6 \n"
                                  + "10\n"
                                  + "1 1 5 2\n"
                                  + "8 5 6 8 9 2 6 4 3 2 \n"
                                  + "12\n"
                                  + "2 1 6 2\n"
                                  + "2 3 7 9 4 5 1 9 2 5 6 4 ";
    // REMOVE_END

}

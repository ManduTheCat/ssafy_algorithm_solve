package gold.BJ6198loopTopDeco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long res = 0;
        Stack<Integer> stack = new Stack<>();
        /**
         * 10  3  7  4  12 2
         * [1][2][3][4][5][6]
         */
        // 돌면서 스텍을 저장 공간으로 활용해야함

        for(int n = 0; n < N; n++){
            int cursor = Integer.parseInt(br.readLine());
            while (!stack.isEmpty() && stack.peek() <= cursor){
                stack.pop(); // 나보다 약한 놈은 다 뺀다
            }
            stack.push(cursor);
            //System.out.println(stack);
            res +=stack.size() -1;
        }
        System.out.println(res);
    }
}

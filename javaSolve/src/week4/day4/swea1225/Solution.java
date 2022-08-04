package week4.day4.swea1225;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    static final int TC = 10;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/swea1225/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < TC; i++) {
            int tcNum = Integer.parseInt(br.readLine());
            int[] password = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            Queue<Integer> q = new LinkedList<>();
            Arrays.stream(password).forEach(q::add);
            // q 를돌면서
            loop:
            while (true){
                //1-5 를 돌아가면서
                for(int sub = 1; sub <=5; sub++){
                    // pop 을하고 - 연산을 한다
                    int curr = q.poll() - sub;
                    // 만약 연산결과가 0보다 작으면 add 하고 break 한다
                    if(curr <= 0){
                        q.add(0);
                        break loop;
                    }
                    q.add(curr);
                }
            }
            System.out.printf("#%d", tcNum);
            q.forEach(e-> System.out.printf(" %d", e));
            System.out.println();
        }
    }
}

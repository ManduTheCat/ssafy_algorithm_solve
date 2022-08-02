package week4.day2.swea1208;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {
    /**
     *  테스트케이스 10개 고정 이라 상수로 선언
     */
    public static final int n = 10;

    /**
     * sorting 하면서 dumpcount 값만큼 가장 큰값-- 작은값++ 반복 해 최대-최소 값을 출력
     * @param args main 입력 받기
     * @throws IOException 입출력 예외
     */
    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("resources/1208Input/input.txt"));
        BufferedReader bf  = new BufferedReader(new InputStreamReader(System.in));
        for(int tc = 0; tc < n; tc++){
            int dumpCount = Integer.parseInt(bf.readLine());
            int [] h = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for(int dump = 0; dump < dumpCount; dump++){
                Arrays.sort(h);
                h[0] += 1;
                h[h.length-1] -= 1;
            }
            Arrays.sort(h);
            System.out.printf("#%d %d\n",tc+1 , h[h.length-1]-h[0]);

        }
    }
}

package SSAFY.week14.sweaExam.treeTall;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//1 최대한 쉬지않고 나무에 물을 줘야하낟
//2. 나무가 성장하는 길이가 홀수면 홀수 날자 + 1 줘야한다
//3/ 짝수 날에 물을 주는것은 홀수 날에 물을 두번 주는것으로대체 가능
// 짤수날에 물을 무조건 두번 주고 홀수날에 물을 무조건 한번 주기 떄문에
//swea 나무 높이 14510
public class Solution {
    static int Tc;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/SSAFY.week14/swea14510/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Tc = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < Tc; tc++) {
            int N = Integer.parseInt(br.readLine());
            int[] trees = new int[N];
            int max = 0;
            int min = Integer.MAX_VALUE;
            int odd = 0;
            int even = 0;
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int n = 0; n < N; n++) {
                int value = Integer.parseInt(st.nextToken());
                max = Math.max(value, max);
                trees[n] = value;
            }

            for (int i = 0; i < N; i++) {
                odd +=  (max - trees[i]) % 2;
                even += (max - trees[i])/2;
            }
            int temp = Math.max(even - odd, 0)/3;
            // 짝수 가 더 많은데 차가 3의 배수라면
            odd += temp*2;
            even-= temp;
            int minOddEven = Math.min(odd, even);
            int ans = minOddEven * 2 +
                    // 홀수가 남은경우 0일떄를 주의한다. 홀수이면 물을 한번 안줘야하니 하루를 뺴야한다
                    // 물 = (1일 : A, 2일 : 물을 주지 않음, 3일 : B ... ((odd - oddEvenMin) * 2 - 1)일 : Z)
                    Math.max((odd - minOddEven)*2-1,0)+
                    // 2 2 남으면 1 2 1 로 변환 // 2 하나 남으면 홀수 일 2개로 면환 // 홀수일이 개이득인다
                    (even - minOddEven)/2 *3 + (even - minOddEven)%2 * 2;
            System.out.printf("#%d %d\n", tc+1, ans);
        }
    }
}

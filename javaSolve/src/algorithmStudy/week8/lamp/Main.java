package algorithmStudy.week8.lamp;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static int K;
    static String[] input;
    static Map<String, Integer> patternSet = new HashMap<>();

    public static void main(String[] args) throws IOException {
        // M 20 > k 1 일때 0의 갯수와 K 의 갯수가 같은 패턴을 찾는다.
        // k 가 홀수 인경우
        // k 가 짝수 인경우
        // M 20 < K 999 일땐 같은 패턴중  0 이 가장 많은 수를 가진  것을 찾는다. = 가능한경우중 가장 횟수가 많은거? 같은 패턴이 하나 이상인것중
        // M = 1 이면 1 의 갯수를 찾는다 K = 짝수
        // M = 1 이면 0 의 갯수를 찾는다 K = 홀수
        // N = 1 이면 k 갯수만큼 0을 바꿀수 있다  K + (1의갯수)가  <N 이면 0리턴  == N 이면 1 리턴
        System.setIn(new FileInputStream("resources/study/week8/1034lamp/input9.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        input = new String[N];
        for (int n = 0; n < N; n++) {
            input[n] = br.readLine();
        }
        K = Integer.parseInt(br.readLine());
        for (String row : input) {
            if (!patternSet.containsKey(row)) {
                patternSet.put(row, 1);
            } else {
                patternSet.put(row, patternSet.get(row) + 1);
            }
        }
        if (M == 1) {
            // 무조건 K 번 다돌리니까 짝수 홀수로 갈린다
            int count = 0;
            if (K % 2 == 0) {
                //짝수면 두번 누르니 안바뀐다
                // 1의 갯수 출력
                for (String row : input) {
                    for (int idx = 0; idx < M; idx++) {
                        if (row.charAt(idx) == '1') {
                            count++;
                        }
                    }
                }
            } else {
                // 홀수면 바뀐다.
                // 0의 갯수출력
                for (String row : input) {
                    for (int idx = 0; idx < M; idx++) {
                        if (row.charAt(idx) == '0') {
                            count++;
                        }
                    }
                }
            }
            System.out.println(count);
        } else {
            int maxPatternCount = 0;
            for (String key : patternSet.keySet()) {
                int count = 0;
                for (int idx = 0; idx < M; idx++) {
                    //0의 갯수를 세고
                    if (key.charAt(idx) == '0') {
                        count++;
                    }
                }
                if (count <= K && K % 2 == count % 2) {
                    //0의 갯수보다 k 갯수가 크면 최대 값이 나오기에 패턴 갯수를 출력한다.
                    maxPatternCount = Math.max(maxPatternCount, patternSet.get(key));
                }
            }
            System.out.println(maxPatternCount);
        }
    }
}

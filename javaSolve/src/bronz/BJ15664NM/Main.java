package bronz.BJ15664NM;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int M;
    static boolean[] cheek;
    static int[] input;
    static int[] permuRes;
    static Set<String> set;
    public static void main(String[] args) throws IOException {
        // NpM 중 오름차순만 구하자
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        permuRes = new int[M];
        set = new LinkedHashSet<>();
        input = new int[N];
        cheek = new boolean[N];
        st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++) {
            input [n] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(input);
        permutation(0);
        for(String res :set){
            System.out.println(res);
        }
    }

    public static void permutation(int depth) {
        if (depth == M) {
            StringBuilder chunk = new StringBuilder();
            for(int i = 0; i < permuRes.length; i++){ // set 을 활용해 중복된 선택 결과를 제거한다.
                if(i == permuRes.length - 1){
                    chunk.append(permuRes[i]);
                }else {
                    chunk.append(permuRes[i]);
                    chunk.append(" ");
                }
            }
            set.add(chunk.toString());
            return;
        }
        for (int i = 0; i < input.length; i++) {
            if (!cheek[i]) {
                if(depth == 0){ // 첫번째 수를 선택할땐 비교할 이전수가 없어서 조건없이 선택가능
                    cheek[i] = true;
                    permuRes[depth] = input[i];
                    permutation(depth + 1);
                    cheek[i] = false;
                }
                else if(depth > 0){ // 2번자 자리 선택할때부터 이전 선택을 확인가능하다.
                    if(permuRes[depth - 1] <= input[i]){ // 조합 결과가 이전 것보다 크거나 같아을때만 선택
                        cheek[i] = true;
                        permuRes[depth] = input[i];
                        permutation(depth + 1);
                        cheek[i] = false;
                    }
                }
            }
        }
    }
}

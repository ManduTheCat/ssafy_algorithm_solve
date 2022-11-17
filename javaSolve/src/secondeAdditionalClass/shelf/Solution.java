package secondeAdditionalClass.shelf;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


// 부분집합풀이가능
// 조합으로 풀이가능
// target 을 몇개 뽑을거냐 를 나눠서 계산한다
// dfs 가능
public class Solution {
    static int Tc;
    static int N;
    static int B;
    static int[] arrH;
    static boolean[] choice;
    static int minRes;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/secondClass/sweaShelf/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Tc = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < Tc; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            arrH = new int[N];
            choice = new boolean[N];


            for (int n = 0; n < N; n++) {
                arrH[n] = Integer.parseInt(st.nextToken());
            }
            minRes = Arrays.stream(arrH).sum();
            pow(0);
            System.out.printf("#%d %d \n",tc+ 1, minRes- B);

        }
    }

    private static void pow(int depth) {
        if (depth == N) {
            int len  = 0;
            for(boolean isChoice : choice){
                if(isChoice) len++;
            }
            int [] powRes = new int[len];
            int idx = 0;
            for(int choiceIdx = 0;  choiceIdx < N; choiceIdx++){
                if(choice[choiceIdx]) powRes[idx ++ ] = arrH[choiceIdx];
            }
            int sum = 0;
            for(int el : powRes){
                sum+=el;
            }
            if(sum >= B){
                minRes = Math.min(sum, minRes);
                //System.out.println(Arrays.toString(powRes));
                //System.out.println(minRes);
            }
            return;
        }

        choice[depth] = true;
        pow(depth + 1);
        choice[depth] = false;
        pow(depth + 1);


    }
}

package gold.BJ1062Tech.passCOde;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static int max = 0;
    static boolean visit[] = new boolean[26];
    static String[] sArr;



    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        sArr = new String[N];
        //char[] checkWords = {'a', 'n', 't', 'i', 'c'};

        // 모든 단어는 "anta-'로 시작하고, "-tica"로 끝남. a n t i c
        if(K<5) {
            System.out.println("0");
            return;
        }

        // 알파벳 = 26개이므로 26이면 모두 읽을 수 있음.
        else if(K==26) {
            System.out.println(N);
            return;
        }
        else {
            // 접두사 anta 와 접미사 tica 제거.
            for(int i=0; i<N; i++) {
                String str = bf.readLine();
                sArr[i] = str.substring(4, str.length()-4);
            }

            // a n t i c 5개의 단어는 반드시 포함해야 하므로 -5
            K -= 5;

            // a n t i c -> 방문 체크
            visit['a' - 97] = true;
            visit['n' - 97] = true;
            visit['t' - 97] = true;
            visit['i' - 97] = true;
            visit['c' - 97] = true;

            // 재귀 + 백트래킹을 통해 최대 읽을 수 있는 단어 찾기
            dfs(0, 0);
            System.out.println(max);
        }

        bf.close();
    }

    public static void dfs(int start, int count) {
        /*
         * 기저사례: 주어진 K개 만큼 단어를 골랐을 때
         * visit[]에 체크된 단어들과 비교해서 배울 수 있는 단어의 최댓값 구하기.
         */
        if(count == K) {
            int num = 0;
            for(int i=0; i<sArr.length; i++) {
                boolean flag = true;
                for(int j=0; j<sArr[i].length(); j++) {

                    /* K개를 골랐을 때, 고른 단어로 해당 문자열을 읽을 수 있는지 판단 */
                    if(!visit[sArr[i].charAt(j) - 97]) {
                        flag = false;
                        break;
                    }
                }
                if(flag)	num ++;
            }
            max = Math.max(max, num);
            return;
        }

        // 아직 K개를 배우지 않은 경우 -> 완전 탐색(알파벳 갯수: 26개)
        for(int i=start; i<26; i++) {
            if(!visit[i]) {
                visit[i] = true;
                dfs(i, count + 1);
                visit[i] = false;
            }
        }
    }

}

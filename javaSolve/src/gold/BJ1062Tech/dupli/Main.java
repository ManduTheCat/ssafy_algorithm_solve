package gold.BJ1062Tech.dupli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N;
    private static int K;
    private static boolean[] check;
    //private static Map<Character, Integer> charToIntMap;
    //private static Map<Integer, Character> intToCharMap;
    //private static List<Set<Character>> inputSet;
    private static String[] inString;
    //private static Set<Integer> banList;
    private static int res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        inString = new String[N];
        check = new boolean[26];
        if (K < 5) {
            System.out.println(0);
            return;
        }else if(K == 26){
            System.out.println(N);
            return;
        }else {

            for (int n = 0; n < N; n++) {
                String val = br.readLine();
                inString[n] = val.substring(4, val.length()-4);

            }
            res = 0;
            check['a' - 97] = true;
            check['n' - 97] = true;
            check['t' - 97] = true;
            check['i' - 97] = true;
            check['c' - 97] = true;
            K -= 5; //antic 가 이미 포함되어야한다.
            dfs(0, 0);
            System.out.println(res);
        }
        br.close();


    }

    private static void dfs(int depth, int start) {
        if (depth == K) {
            int count = 0;
            for(int row = 0; row < inString.length; row++){
                boolean isContain = true;
                for (int col = 0; col < inString[row].length(); col++) {
                    if (!check[inString[row].charAt(col) - 97]) {
                        isContain = false;
                        break;
                    }
                }
                if(isContain){
                    count++;
                }
            }
            res = Math.max(res,count);
            return;
        }
        for (int i = start; i < 26; i++) {
            if (!check[i] ) {
                check[i] = true;
                dfs(depth+ 1, i);
                check[i] = false;
            }
        }
    }


//    private static StringBuilder printCheck() {
//        StringBuilder sb = new StringBuilder();
//        for(int i = 0; i < check.length; i++){
//            if(check[i]){
//                sb.append((char)i);
//            }
//        }
//        return sb;
//    }
}

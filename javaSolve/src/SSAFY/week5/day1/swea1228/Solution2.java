package SSAFY.week5.day1.swea1228;

import java.io.*;
import java.util.*;


public class Solution2 {

    public static void main(String[] args) throws NumberFormatException, IOException {
        System.setIn(new FileInputStream("resources/SSAFY.week5/day1/swea1228/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = 10;
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            List<String> codeList = new ArrayList<String>();
            int N = Integer.parseInt(br.readLine());

            // 원본 암호문 읽고
            StringTokenizer st = new StringTokenizer(br.readLine());
            // 암호 리스트에 넣기
            for (int i = 0; i < N; i++) {
                codeList.add(st.nextToken());
            }
            int k = Integer.parseInt(br.readLine());

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < k; i++) {
                String command = st.nextToken();
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                // y개 넣기
                for (int j = 0; j < y; j++) {
                    codeList.add(x + j, st.nextToken());
                }
            }

            sb.append("#").append(tc).append(" ");
            for (int i = 0; i < 10; i++) {
                sb.append(codeList.get(i)).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

}

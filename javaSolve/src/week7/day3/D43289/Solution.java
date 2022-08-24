package week7.day3.D43289;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int Tc;
    static int N;
    static int M;

    static int[] parents;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/week7/day3/swea3289/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Tc = Integer.parseInt(br.readLine());
        for (int tc = 0; tc++ < Tc; ) {
            //System.out.println(tc);
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            parents = new int[N + 1];
            for (int n = 1; n < N + 1; n++) {
                parents[n] = n;

            }
            StringBuilder sb = new StringBuilder();
            for (int m = 0; m < M; m++) {
                st = new StringTokenizer(br.readLine());
                switch (Integer.parseInt(st.nextToken())) {
                    case 0:
                        union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
                        break;
                    case 1:
                        int xRoot = find(Integer.parseInt(st.nextToken()));
                        int yRoot = find(Integer.parseInt(st.nextToken()));
                        if (xRoot == yRoot) {
                            sb.append(1);
                        } else sb.append(0);
                        break;
                }

            }
            System.out.printf("#%d %s\n", tc, sb);
        }
    }

    public static int find(int x) {
        if (parents[x] == x) {
            return x;
        }
        return parents[x] = find(parents[x]);
    }

    public static boolean union(int x, int y) {
        int xRoot = find(x);
        int yRoot = find(y);
        if (xRoot == yRoot) {
            return false;
        }
        parents[yRoot] = xRoot;
        return true;
    }
}

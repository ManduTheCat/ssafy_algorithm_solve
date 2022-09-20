package algorithmStudy.week7.lie1043;

import sun.print.resources.serviceui_zh_TW;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static int[] parents;
    static ArrayList<Integer> partys = new ArrayList<>();
    static int truePersonCount;
    static Set<Integer> trueSet = new HashSet<>();

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/study/week7/1043/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int truePeopleNumber = st.nextToken().charAt(0);
        parents = new int[N + 1];
        for (int n = 1; n <= N; n++) {
            parents[n] = n;// 어짜피 N 명 다나온다
        }
        st = new StringTokenizer(br.readLine());
        truePeopleNumber = Integer.parseInt(st.nextToken());
        for (int tpn = 0; tpn < truePeopleNumber; tpn++) {
            trueSet.add(Integer.parseInt(st.nextToken()));
        }


    }

    static int find(int x) {
        if (parents[x] == x) {
            return x;
        }
        return parents[x] = find(x);
    }

    static boolean union(int x, int y) {
        int rooX = find(x);
        int rootY = find(y);
        if (rootY == rooX) return false;
        parents[rootY] = rooX;
        return true;
    }
}

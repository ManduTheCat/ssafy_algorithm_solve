package week6.day5.BJ1697;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static boolean[] isVisited = new boolean[100000 * 3];

    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("resources/week6/day5/BJ1697/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        if (n == m) {
            System.out.println(0);
        } else bfs();
    }

    public static void bfs() {
        // 같은 숫자 안밟아야한다
        // - 넘어가면 안된다.
        // n ==m  이면 0 이다.
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{n, 0});

        while (!q.isEmpty()) {
            int[] currNode = q.poll();
            if (currNode[0] == m) {
                System.out.println(currNode[1]);
                return;

            }
            isVisited[currNode[0]] = true;
            if (currNode[0] + 1 <= 100000 &&!isVisited[currNode[0] + 1]) {
                isVisited[currNode[0] + 1] = true;
                q.add(new int[]{currNode[0] + 1, currNode[1] + 1});
            }
            if (currNode[0] - 1 <= 100000 && currNode[0] - 1 >= 0 && !isVisited[currNode[0] - 1]) {
                isVisited[currNode[0] - 1] = true;
                q.add(new int[]{currNode[0] - 1, currNode[1] + 1});
            }
            if (currNode[0] * 2 <= 100000 &&currNode[0] * 2 > 0 && !isVisited[currNode[0] * 2]) {
                isVisited[currNode[0] * 2] = true;
                q.add(new int[]{currNode[0] * 2, currNode[1] + 1});
            }
        }
    }
}

package secondeAdditionalClass.CompetitionSwim;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;


class Node {
    int row;
    int col;
    int time;

    public Node(int row, int col, int time) {
        this.row = row;
        this.col = col;
        this.time = time;
    }

    @Override
    public String toString() {
        return "Node{" +
                "row=" + row +
                ", col=" + col +
                ", time=" + time +
                '}';
    }
}

public class Solution {
    static int Tc;
    static int[][] map;
    static boolean[][] check;
    static int N;
    static Node start;
    static Node end;
    static int res;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/secondClass/sweaSwim/input1.txt"));
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        Tc = Integer.parseInt(bf.readLine());
        for (int tc = 0; tc < Tc; tc++) {
            res = -1;
            N = Integer.parseInt(bf.readLine());
            map = new int[N][N];
            check = new boolean[N][N];
            for (int row = 0; row < N; row++) {
                StringTokenizer st = new StringTokenizer(bf.readLine());
                for (int col = 0; col < N; col++) {
                    int val = Integer.parseInt(st.nextToken());
                    map[row][col] = val;
                    if (val == 1) {
                        check[row][col] = true;
                    }
                }
            }
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(bf.readLine());
            int endRow = Integer.parseInt(st.nextToken());
            int endCol = Integer.parseInt(st.nextToken());
            start = new Node(row, col, 0);
            end = new Node(endRow, endCol, -1);
            bfs();
            System.out.printf("#%d %d\n",tc+1, res);
        }
    }

    private static void bfs() {
        int[][] arrD = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
        Queue<Node> q = new ArrayDeque<>();
        q.offer(start);
        while (!q.isEmpty()) {
            Node curr = q.poll();
            if(curr.row == end.row && curr.col == end.col){
                res = curr.time;
                return ;
            }
            for (int d = 0; d < 4; d++) {
                int nextR = curr.row + arrD[d][0];
                int nextC = curr.col + arrD[d][1];
                if (isOut(nextR, nextC) && !check[nextR][nextC]) {
                    if (map[nextR][nextC] == 2 ) {
                        if((curr.time - 2) % 3 == 0){
                            // 2인경우 만약 열리는 시간이면 체크하고 통과해라
                            check[nextR][nextC]= true;
                            q.offer(new Node(nextR, nextC, curr.time+1));

                        }else {
                            // 아니라면 시간 증가하고 큐에넣는다
                            q.offer(new Node(curr.row, curr.col, curr.time+1));
                        }
                    }else {
                        // 2 가 아니라면  체크하고 넘어가라
                        check[nextR][nextC] = true;
                        q.offer(new Node(nextR, nextC, curr.time+1));
                    }
                }
            }
        }
    }

    private static boolean isOut(int nextR, int nextC) {
        return nextR < N && nextR >= 0 & nextC < N & nextC >= 0;
    }
}

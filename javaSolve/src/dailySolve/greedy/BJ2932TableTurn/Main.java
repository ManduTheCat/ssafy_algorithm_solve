package dailySolve.greedy.BJ2932TableTurn;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int K;
    static int target;
    static int targetRow;
    static int targetCol;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        // 바뀌는 부분은 정해져 있다.
        System.setIn(new FileInputStream("resources/daily/greedy/Bj2932/input2.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        int num = 1;
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                map[row][col] = num++;
            }
        }
        for (int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());
            target = Integer.parseInt(st.nextToken());
            targetRow = Integer.parseInt(st.nextToken()) - 1;
            targetCol = Integer.parseInt(st.nextToken()) - 1;
            int curRow = (target - 1) / N;
            int curCol = (target - 1) % N; // 6의 위치가 계속 바뀐다........
            // 해당 로우 빼써 데큐에 담는다.

            System.out.printf("from %d %d -> target %d %d \n", curRow, curCol, targetRow, targetCol);
            turnLineRow(curRow, curCol);
            turnLineCol(targetRow, targetCol, curRow, curCol);
            // 해당 컬럼 빼온다.
        }
    }

    private static int turnLineCol(int targetRow, int targetCol, int curRow, int curCol) {
        Deque<Integer> dq = new ArrayDeque(); //N
        int res;
        for (int row = 0; row < N; row++) {
            dq.addLast(map[row][targetCol]);
        }
        int moveCount = 0;
        if (targetRow - curRow > 0) moveCount = targetRow - curRow;
        else if (targetRow - curRow < 0) {
            System.out.println("in");
            moveCount = (N - curRow - 1) + targetRow + 1;
        }
        res = moveCount;
        while (moveCount-- > 0) { //N
            int pop = dq.pollLast();
            dq.addFirst(pop);
        }
        int row = 0;
        while (!dq.isEmpty()) { //N
            int val = dq.pollFirst();
            map[row++][targetCol] = val;
        }
        printArr(map);
        return res;
    }

    private static void printArr(int[][] arr) {
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                System.out.print(arr[row][col] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static int turnLineRow(int curRow, int curCol) { // 3N
        Deque<Integer> dq = new ArrayDeque(); //N
        int res;
        for (int col = 0; col < N; col++) {
            dq.addLast(map[curRow][col]);
        }
        int moveCount = 0;
        if (targetCol - curCol > 0) moveCount = targetCol - curCol;
        else if (targetCol - curCol < 0) {
            moveCount = (N - curCol - 1) + targetCol + 1;
        }
        res = moveCount;
        while (moveCount-- > 0) { //N
            int pop = dq.pollLast();
            dq.addFirst(pop);
        }
        int col = 0;
        while (!dq.isEmpty()) { //N
            int val = dq.pollFirst();
            map[curRow][col++] = val;
        }
        printArr(map);
        return res;
    }
}

package week4.day2.swea1954;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {
    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("resources/swea1954/input.txt"));
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int inputCount = Integer.parseInt(bf.readLine());
        for (int tc = 0; tc < inputCount; tc++) {
            int n = Integer.parseInt(bf.readLine());
            boolean[][] check = new boolean[n][n];
            int[][] map = new int[n][n];
            System.out.printf("#%d ", tc+1);
            move(map, 1, check);
        }
    }

    public static void move(int[][] map, int startNum, boolean[][] check) {
        //증가 방향 오 아 왼 위
        // 바뀌는 기준 벽에 닿거나 이미 간곳을 간다
        // 만약 다음것 벽에 닿는다면 방향 전환 or 다음곳이 이미간곳이면 방향전환
        // 방향 오른쪽 0,0 -> 0,1 -> 0,2 -> 0,3 벽 ->방향 아레 전환->모든곳을 방문할때까지진행
        System.out.println();
        int i = 0;
        int j = 0;
        int dir = 0;
        int len = map.length;
        int[] dx = new int[]{1, 0, -1, 0};
        int[] dy = new int[]{0, 1, 0, -1};
        check[0][0] = true;
        map[0][0] = startNum;

        // 모든 곳을 다돌지 않았다면 계속해서 진행해라
        while (!isCheckFull(check)) {
            // 범위를 벗어나지 않고 처음 간곳이면 방향 그대로 진행
            if ((j + dx[dir] >= 0 && len > j + dx[dir] && i + dy[dir] >= 0 && len > i + dy[dir]) && (!check[i + dy[dir]][j + dx[dir]])) {
                j += dx[dir];
                i += dy[dir];
                map[i][j] = ++startNum;
                check[i][j] = true;

            }
            // 범위를 벗어나거나 이미 갔던 곳이면 방향을 전환
            // 만약 dir 이 dx dy 를 벗어나면 안된기데 나머지 연산을 통해 범위를 넘어가면 넘어간만큼 0부터 시작하게 한다.
            // 일종의 배열 길이를 활용한 순환 꼼수
            else dir = (dir + 1) % dx.length;
        }
        for (int[] m : map) {
            Arrays.stream(m).forEach(e-> System.out.printf("%d ",e));
            System.out.println();
        }
    }

    public static boolean isCheckFull(boolean[][] check) {
        for (boolean[] c : check) {
            for (boolean el : c) {
                // 하나의 false 라도 존재하면 return false
                if (!el) return false;
            }
        }
        return true;
    }
}

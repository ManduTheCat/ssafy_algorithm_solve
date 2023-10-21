package SSAFY.hunt;


import java.awt.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 잘하는 사람들 사이에서 시간 도 메모리도 좋진않지만
// 수빈님 강의듣고 순열로 구현하시는 분들 참조하시면 좋을거 같아 올려봅니다.
// 114,180kb
// 1,477ms
public class Solution {
    static int Tc;
    static int N;
    static int[][] map;
    static int rangeMonster;
    static int rangeHouse;
    static int[] resultPermutation;
    static boolean[][] bfsCheck;
    static int[] totalTarget;
    static ArrayList<Integer> countList;

    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("resources/week8/hunt/input.txt"));
        System.setIn(new FileInputStream("resources/week8/hunt/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Tc = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < Tc; tc++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            ArrayList<Integer> subHouse = new ArrayList<>();
            ArrayList<Integer> subMonster = new ArrayList<>();
            for (int n = 0; n < N; n++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int col = 0; col < N; col++) {
                    int value = Integer.parseInt(st.nextToken());
                    map[n][col] = value;
                    if (value > 0) subMonster.add(value);
                    else if (value < 0) subHouse.add(value);
                }
            }
            rangeHouse = subHouse.size();
            rangeMonster = subMonster.size();
            int totalTargetSize = rangeHouse + rangeMonster;
            resultPermutation = new int[totalTargetSize];
            totalTarget = new int[totalTargetSize];
            //몬스터 오름차순 정렬
            Collections.sort(subMonster);
            //- 값인 고객 내림차순 정렬 -1 -3 -2 => -1 -2 -3
            subHouse.sort((o1, o2) -> o2 - o1);
            int idx = 0;
            //몬스터 와 고객 배열 합침 1 2 3 -1 -2 -3
            for (int Monster : subMonster) {
                totalTarget[idx++] = Monster;
            }
            for (int house : subHouse) {
                totalTarget[idx++] = house;
            }
            countList = new ArrayList<>();
            // 가능한 모든경로를 찾는다.
            permutation(0, 0, totalTargetSize);
            // 모든 시간이 들어간 countList 의 최소 값을 출력
            System.out.printf("#%d %d\n", tc + 1, Collections.min(countList));
        }
    }

    // 예 ) [1,2,3,-1,-2,-3] 을 순열한다
    // 단, 몬스터 전에 고객이 나오면안된다.
    static void permutation(int depth, int flag, int range) {
        if (depth == totalTarget.length) {
            //만들어진 경로 시간계산 하는 함수 실행
            pathCount();
            return;
        }
        for (int i = 0; i < range; i++) {
            if ((flag & 1 << i) == 0) {
                // 몬스터 전에 고객이 나오면안된다 처리한부분 뽑은게 음수인데 이전에 선택하지 않았다면 넘어가라
                if (totalTarget[i] < 0 && (flag & 1 << i - rangeMonster) == 0) continue;
                resultPermutation[depth] = totalTarget[i];
                permutation(depth + 1, flag | 1 << i, range);
            }
        }
    }

    public static void pathCount() {
        // 생성된 루트 순서대로 좌표를 돈다.
        // 좌표 리스트를 만든다.
        ArrayList<Point> pointRoutes = new ArrayList<>();
        for (int target : resultPermutation) {
            for (int row = 0; row < N; row++) {
                for (int col = 0; col < N; col++) {
                    if (map[row][col] == target) {
                        pointRoutes.add(new Point(row, col));
                    }
                }
            }
        }
        //좌표리스트를 순환하면서 경로따라감
        Point start = new Point(0, 0);
        int count = 0;
        for (Point target : pointRoutes) {
            // bfs 수행
            bfsCheck = new boolean[N][N];
            // 전체 경로를 돌면 전체 시간을 누산한다
            count += bfs(start, target);
            // 만약 bfs 를 안쓴다면 다음과 같이 풀이가능
            //count += Math.abs(start.x - target.x) + Math.abs(start.y - target.y);
            start = target;
        }
        // 이번 루트에 대한 시간을 contList 에 넣는다, 최종적으로 countList에는 모든 루트의 시간이 들어간다.
        countList.add(count);
    }

    // 최단 경로를 구한다. 장에물이 없기에 bfs 방법보다 멘하튼 거리가 유리하지만 연습겸 bfs 구현
    // 4방탐색 기반  bfs
    public static int bfs(Point startPoint, Point targetPoint) {
        int time = 0;
        int[][] alpha = new int[][]{{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
        Queue<Point> q = new ArrayDeque<>();
        q.add(startPoint);
        bfsCheck[startPoint.x][startPoint.y] = true;

        while (!q.isEmpty()) {
            int size = q.size();
            // 어짜피 큐가 빌때가지 도는데 깊이당 1초임을 이용해 초 를 재는 부분
            while (size-- > 0) {
                Point currPoint = q.poll();
                int currRow = currPoint.x;
                int currCol = currPoint.y;
                //target 에 도착 끝네고 시간 반환
                if (currRow == targetPoint.x && currCol == targetPoint.y) {
                    return time;
                }
                for (int d = 0; d < 4; d++) {
                    int nextRow = currRow + alpha[d][0];
                    int nextCol = currCol + alpha[d][1];
                    if (isIn(nextRow, nextCol) && !bfsCheck[nextRow][nextCol]) {
                        bfsCheck[nextRow][nextCol] = true;
                        q.add(new Point(nextRow, nextCol));
                    }
                }
            }
            time++;
        }
        //실패시 -1
        return -1;
    }
    public static boolean isIn(int i, int j) {
        return i < N && i >= 0 && j < N && j >= 0;
    }
}

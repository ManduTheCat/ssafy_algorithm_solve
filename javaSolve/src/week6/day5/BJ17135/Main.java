package week6.day5.BJ17135;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int M;
    static int D;
    static StringTokenizer st;
    static int[][] map;
    static int count = 0;
    static int[][] tempMap;

    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("resources/week6/day5/BJ17135/input5.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        tempMap = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int el = Integer.parseInt(st.nextToken());
                map[i][j] = el;
                tempMap[i][j] = el;
            }
        }
        int[] initArcher = new int[M];
        for (int i = M - 3; i < M; i++) {
            initArcher[i] = 9;
        }
        int max = 0;
        do {
            startGame(initArcher);
            max = Math.max(max, count);
        }
        while (nextPermutation(initArcher));
        System.out.println(max);
    }

    public static int[][] clearCopyMap(int[][] src) {
        int[][] tmp = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                tmp[i][j] = src[i][j];
            }
        }
        return tmp;
    }

    /**
     * 궁수자리를 섞어주는 함수
     * 새로운 경우의 수를 찾는거기때문에 map 과 count 를 초기회해줘야한다.
     *
     * @param initArcher 초기 오름차순으로 정렬된 아쳐 위치
     */
    public static boolean nextPermutation(int[] initArcher) {
        count = 0;
        map = clearCopyMap(tempMap);
        int len = initArcher.length;
        int i = len - 1;
        while (i > 0 && initArcher[i - 1] >= initArcher[i]) --i;
        if (i == 0) return false;
        int j = len - 1;
        while (initArcher[i - 1] >= initArcher[j]) --j;
        swap(initArcher, i - 1, j);
        int k = len - 1;
        while (i < k) swap(initArcher, i++, k--);
        return true;
    }

    /**
     * nextPermutation swap helper 함수
     */
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 남은 적이 없을떄까지 돈다
     * 1. 조건에 맞는 적을 찾는다
     * 2. 적을 제거하고 0 으로 표시한다 + count 올린다
     *
     * @param archerArray 조합으로 생성된 궁수 배치
     */
    private static void startGame(int[] archerArray) {
        for (int row = N - 1; row >= 0; row--) {
            if (!isFinish()) {
                Queue<Point> targetQ = findEnemy(row + 1, archerArray);
                shot(targetQ);
                moveArcher(archerArray, row);
            } else { // 중간에 끝나면 정지
                break;
            }
        }
    }

    /**
     * 궁수 위로 한줄 이동하는 함수
     *
     * @param archerArray permutation 을 거친 아쳐 배열을 받는다
     * @param row         현제 궁수가 위치한 map 에서 row;
     */
    public static void moveArcher(int[] archerArray, int row) {

        // 해당라인 0 으로 으로 만들고 해당된 아쳐리스트를 받아와 이동시킨다
        Arrays.fill(map[row], 0);
        for (int col = 0, size = archerArray.length; col < size; col++) {
            map[row][col] = archerArray[col];
        }
        // 이전 궁수 흔적 지우는 그러지 않으면 남은 적탐색이 힘들어진다.
        if (row < N - 1) {
            Arrays.fill(map[row + 1], 0);
        }
    }

    /**
     * 남은 적이 있는지 탐색하는 함수 return boolean
     */
    public static boolean isFinish() {
        for (int[] row : map) {
            for (int el : row) {
                if (el == 1) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 탐색을 하면 1 가까운 적, 가까운적이 많으면 가장왼쪽인 친구들을찾아 queue에 담아리턴하는 함수
     * 가까운적이 많으면 가장왼쪽인 친구들을 찾을때 PriorityQueue 를 사용
     *
     * @param row        궁수 위치 row
     * @param archerList 궁수의 배치 nextPermutation 결과
     * @return 가장 가까운적 우선, 가까운 적이 여럿이면 맨 왼쪽 인적 을 담은 queue
     */
    public static Queue<Point> findEnemy(int row, int[] archerList) {
        Queue<Point> targetQ = new ArrayDeque<>();
        Point[] archerPoints = new Point[3];
        int[] colList = new int[3];
        int colLIstIdx = 0;
        // 궁수들의 좌표를 Point 를 활용해 archerPoints 에 할당하는 부분
        // 1. col 위치를 구해 기록 (어짜피 row 는 고정되어서)
        for (int col = 0, size = archerList.length; col < size; col++) {
            if (archerList[col] == 9) {
                colList[colLIstIdx++] = col;
            }
        }
        //2 궁수의 row 와 col을 Point 객체를 사용해 archerPoints에 담는다.
        colLIstIdx = 0;
        for (int i = 0; i < 3; i++) {
            archerPoints[i] = new Point(row, colList[colLIstIdx++]);
        }

        // 거리를 비교하면 pq에 담는 부분
        // 1 PriorityQueue 규칙 설정
        // 2.1번 궁수 2번궁수 3번궁수  순서대로 진행
        // (row + 1, 0)에서 오른쪽으로 이동하며 거리안에 있으면 PriorityQueue 에 담기
        // 3.pq.poll 해서 가장 가까우면서 ,가까운것들중 왼쪽 좌표 q에 담아두기
        for (Point archer : archerPoints) {
            // 검색하면서 거리 안에 있고 값이 1이면 PriorityQueue 에 담는다.
            PriorityQueue<Point> pq = new PriorityQueue<>((o1, o2) -> {
                int distO1 = Math.abs(archer.x - o1.x) + Math.abs(archer.y - o1.y);
                int distO2 = Math.abs(archer.x - o2.x) + Math.abs(archer.y - o2.y);
                if (distO2 == distO1) { // 거리가 같은경우 y 가 작은거 우선
                    return Integer.valueOf(o1.y).compareTo(Integer.valueOf(o2.y));
                } else {// 거리 가 다르면 거리가 작은거 우선
                    return Integer.valueOf(distO1).compareTo(distO2);
                }
            });
            // row 를 이동하면서 왼쪽부터 1이거나 거리가 D 안에 있으면 PriorityQueue 에 넣는다.
            for (int d = 1; d <= D; d++) {
                if (row - d >= 0) {
                    for (int c = 0; c < M; c++) {
                        int curRow = row - d;
                        int dist = Math.abs(archer.x - curRow) + Math.abs(archer.y - c);
                        if (dist <= D && map[curRow][c] == 1) {
                            pq.add(new Point(curRow, c));
                        }
                    }
                }
            }
            if (!pq.isEmpty()) targetQ.add(pq.poll());
        }
        return targetQ;
    }

    /**
     * q 를 받고 q를 돌면서 적을 죽인 횟수를 세는 함수
     * 만약 같은 적을 공격하면 count 가 올라가지 않는다.
     *
     * @param targetQ 입력받은 q.
     */
    public static void shot(Queue<Point> targetQ) {
        while (!targetQ.isEmpty()) {
            Point target = targetQ.poll();
            int targetR = target.x;
            int targetC = target.y;
            if (map[targetR][targetC] == 1) {
                count++;
                map[targetR][targetC] = 0;
            }
        }
    }
}

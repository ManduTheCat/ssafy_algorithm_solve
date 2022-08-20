package week6.day5.BJ17135;


import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    // 1 적을 탐색 q 에넣는다 !궁수는 같은 적을 공격할수 있다.
    // 2 q 에 있는걸 차례대로 꺼내 맵을 0 으로 만든다
    // 3 전체 맵에 적이 있는지 확인 한다
    // 있다 -> 맵이동
    // 없다 ->종료
    // redRight
    // 함수를 만들면 꼭 검사해라 -> 이로직이 문제가없다는걸 확신하고 넘어가야한ㄴ다
    // 끝나고 든 지금이든 시간 복잡도를 꼭계산해봐라..
    // 모르면 물어봐라
    // 그런게 어딧어 존나 하는거지
    // 퍼뮤테이션 만들고 함수 하나하나 다시 확인
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
        // 적을 찾는 테스트
//        int [] archerList = new int[]{0,9,0,9,0};
//        findEnemy(0, archerList);

        // 궁수 이동 테스트 row  아래에서위까지 테스트
        // row >= 0?? row >0 궁수가 화살을 쏘면 적을 죽인다 ->  0 까지 갈필요 없다.
        int [] initArcher = new int[M];
        for (int i = M - 3; i < M ; i++) {
            initArcher[i] = 9;
        }
        int max = 0;
        do{
            //System.out.println(Arrays.toString(initArcher));
            startGame(initArcher);
            max = Math.max(max, count);
        }
        while (nextPermutation(initArcher));
        System.out.println(max);
    }
    public static int [][] clearCopyMap(int [][] src){
        int [][] tmp = new int[N][M];
        for (int i = 0; i <N ; i++) {
            for (int j = 0; j <M ; j++) {
                tmp[i][j] = src[i][j];
            }
        }
        return tmp;
    }
    // 궁수자리 섞는 combination
    public static boolean nextPermutation(int []initArcher){
        count= 0;
        map = clearCopyMap(tempMap);
        int len = initArcher.length;
        int i = len -1;
        while (i > 0 && initArcher[i -1] >= initArcher[i])--i;
        if( i == 0) return false;
        int j = len -1;
        while (initArcher[i-1] >= initArcher[j])--j;
        swap(initArcher, i-1, j);
        int k = len -1;
        while (i < k) swap(initArcher,  i++, k--);
        return true;
    }
    public static void swap(int [] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

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
     * 궁수 이동하는 함수
     * @param archerArray permutation 을 거친 아쳐 배열을 받는다
     * @param row 현제 궁수가 위치한 map 에서 row;
     */
    public static void moveArcher(int[] archerArray, int row) {

        // 해당라인 0 으로 으로 만들고 해당된 아쳐리스트를 받아와 이동시킨다
        Arrays.fill(map[row], 0);
        for (int col = 0, size = archerArray.length; col < size; col++) {
            map[row][col] = archerArray[col];
        }
        // 이전 궁수 흔적 지운다
        // bug row 아래쪽에서 시작한다
        //for 문 돌릴필요 없이 바로 아래만 지워보자
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
     *
     * @param row 궁수 위치 row
     * @param archerList 궁수의 배치 nextPermutation 결과
     * @return 가장 가까운적 우선, 가까운 적이 여럿이면 맨 왼쪽 인적 을 담은 queue
     */
    public static Queue<Point> findEnemy(int row, int[] archerList) {
        Queue<Point> targetQ = new ArrayDeque<>();
        Point[] archerPoints = new Point[3];
        int[] colList = new int[3];
        int colLIstIdx = 0;
        for (int col = 0, size = archerList.length; col < size; col++) {
            if (archerList[col] == 9) {
                colList[colLIstIdx++] = col;
            }
        }
        colLIstIdx = 0;
        for (int i = 0; i < 3; i++) {
            archerPoints[i] = new Point(row, colList[colLIstIdx++]);
        }
        for (Point archer : archerPoints) {
            // row 늘려가면서 거리 안에 있으면 pq 에 넣는다.
            //priority Queue 사용해서 거리 올림차순 거리 가 같으면 col 오름차순
            // 뽑으면 가장가까운거 + 거리 같을때 왼쪽 으로
            PriorityQueue<Point> pq = new PriorityQueue<>((o1, o2) -> {
                int distO1 = Math.abs(archer.x - o1.x) + Math.abs(archer.y - o1.y);
                int distO2 = Math.abs(archer.x - o2.x) + Math.abs(archer.y - o2.y);
                if (distO2 == distO1) { // 거리가 같은경우 y 가 작은거 우선
                    return Integer.valueOf(o1.y).compareTo(Integer.valueOf(o2.y));
                } else {// 거리 가 다르면 거리가 작은거 우선
                    return Integer.valueOf(distO1).compareTo(distO2);
                }
            });
            //
            for (int d = 1; d <= D; d++) {
                if (row - d >= 0) {
                    for (int c = 0; c < M; c++) {
                        int curRow = row-d;
                        int dist = Math.abs(archer.x - curRow)+ Math.abs(archer.y - c);
                        if(dist <= D && map[curRow][c] == 1){
                            pq.add(new Point(curRow, c));
                        }
                    }
                }
            }
            if(!pq.isEmpty())targetQ.add(pq.poll());
        }
        return targetQ;
    }

    // 활을 발사하고 count 하는 함수
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

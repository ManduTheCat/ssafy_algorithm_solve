package week6.day5.BJ17135;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

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

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/week6/day5/BJ17135/input3.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        /*
         * while(적탐색){
         *  검색
         *  적처리
         *  궁수이동 !! 이동은 꼭 마지막에 해야한다 궁수는 맨 아래 있기에
         * }
         */

        /*
        test
         */
        // 적을 찾는 테스트
//        int [] archerList = new int[]{0,9,0,9,0};
//        findEnemy(0, archerList);

        // 궁수 이동 테스트 row  아래에서위까지 테스트
        // row >= 0?? row >0 궁수가 화살을 쏘면 적을 죽인다 ->  0 까지 갈필요 없다.
        testArcherMove();


    }

    private static void testArcherMove() {
        for (int row = N-1; row >= 0; row--) {
            // 궁수 배열
            // TODO: 2022-08-20 궁수가 활을쏘면 적을 죽이기 떄문에 row 1 까지 범위로 바꾼다.
            int[] archerArray = new int[]{0, 9, 0, 9, 0};
            if (!isFinish()) {
                findEnemy(row+1, archerArray);
                // 적을 큐에 넣는다.
                // 적을 처리한다.
                System.out.println("go Forward");
                moveArcher(archerArray, row);
                printMap();
            } else { // 중간에 끝나면 정지
                System.out.println("finish");
                break;
            }
            System.out.println();
        }
    }

    // 맵 조회하는 함수
    public static void printMap() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();

        }
    }

    /**
     * 궁수 이동하는 함수
     * 함수안에 함수 넣지 말아보자 이동하고 -> 찾고
     *
     * @param archerArray permutation 을 거친 아쳐 배열을 받는다
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
     * 적이 있는지 탐색하는 함수 return boolean
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

    // 적을 탐색해 큐에 넣는 함수
    public static void findEnemy(int row, int[] archerList) {
        //현재 위치에서 거리만큼 본다. 거리는 계속 줄어든다..
        // 8방탐색을 하면서 거리안에 있다면 q에 넣는다.
        // 궁수는 3명 배치
        Point [] archerPoints  = new Point[3];
        int [] colList = new int[3];
        int colLIstIdx = 0;
        for (int col = 0, size = archerList.length; col < size ; col++) {
            if(archerList[col] == 9) {
                colList[colLIstIdx++] = col;
            }
        }
        colLIstIdx = 0;
        for (int i = 0; i < 3; i++) {
            archerPoints[i] = new Point(row, colList[colLIstIdx++]);
        }
        System.out.println(Arrays.toString(archerPoints));
        // 거리만큼 for row-(dist--)
        // 좌표 가 거리보다 작거나 같으면  좌표를 q 에 넣는다.
    }

    // 활을 발사하는 함수

    // 궁수 배치 하는 permutation

}

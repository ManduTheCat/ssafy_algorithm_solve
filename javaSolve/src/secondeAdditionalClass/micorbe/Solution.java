package secondeAdditionalClass.micorbe;


import com.sun.org.apache.bcel.internal.generic.ACONST_NULL;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


class Microbe implements Comparable<Microbe> {
    int row;
    int col;
    int dir;
    int count;

    public Microbe(int row, int col, int dir, int count) {
        this.row = row;
        this.col = col;
        this.dir = dir;
        this.count = count;
    }

    @Override
    public int compareTo(Microbe o) {
        return o.count - this.count;
    }

    @Override
    public String toString() {
        return "microbe{" + "row=" + row + ", col=" + col + ", dir=" + dir + ", count=" + count + '}';
    }
}

// 시간 1 - 1000
// 군집 5 - 1000
// 1상 2하 3 좌 4 우
// 세로 가로 미생물수 이동방향수
// 갯수가 0 이면 넣으며 안된다.
// 매초 새로운 미생물 리스트
public class Solution {
    static int Tc;
    static int[][] map;
    static int[][] countMap;
    static int N;
    static int M;
    static int K;
    static List<Microbe> microbeList;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/secondClass/sweaMicrobe/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Tc = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < Tc; tc++) {
            microbeList = new ArrayList<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            map = new int[N][N];
            countMap = new int[N][N];
            for (int k = 0; k < K; k++) {
                st = new StringTokenizer(br.readLine());
                int row = Integer.parseInt(st.nextToken());
                int col = Integer.parseInt(st.nextToken());
                int count = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken());
                microbeList.add(new Microbe(row, col, dir, count));
            }

            for (int i = 1; i <= M; i++) {
                go();
            }
            //System.out.println(microbeList);
            int sum  = 0;
            for(Microbe microbe : microbeList){
                sum += microbe.count;
            }
            System.out.printf("#%d %d\n",tc+ 1, sum);
        }
    }

    private static void go() {
        // 1 번 방향대로 이동한다
        List<Microbe> nextList = move();
        // 빨간줄이면 방향돌리고 값 타노스

        nextList = checkRedLine(nextList);
        microbeList =duplicateProcess(nextList);


    }

    private static List<Microbe> duplicateProcess(List<Microbe> nextList) {
        List<Microbe> res = new ArrayList<>();
        //System.out.println("init: " + nextList);
        for (Microbe microbe : nextList) {
            if (countMap[microbe.row][microbe.col] == 1) {
                res.add(microbe);
            }
        }
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if (countMap[row][col] > 1) {
                    // 두개 이상 존재한다면
                    //해당 칸에 해당되는 미생물 pq 에 넣는다
                    PriorityQueue<Microbe> pq = new PriorityQueue<>();
                    for (Microbe microbe : nextList) {
                        if (microbe.row == row && microbe.col == col) {
                            pq.offer(microbe);
                        }
                    }
                    Microbe maxMicro = pq.poll();
                    //System.out.println(maxMicro);
                    int newDIr = maxMicro.dir;
                    int newCount = maxMicro.count;
                    while (!pq.isEmpty()) {
                        newCount += pq.poll().count;
                    }
                    res.add(new Microbe(row, col, newDIr, newCount));
                }
            }
        }
        return res;
    }

    private static List<Microbe> checkRedLine(List<Microbe> nextList) {
        List<Microbe> resList = new ArrayList<>();
        for (Microbe microbe : nextList) {
            if ((microbe.row == 0 || microbe.row == N - 1) || (microbe.col == 0 || microbe.col == N - 1)) {
                // 타노스
                microbe.count = (int) Math.ceil(microbe.count / 2);
                // 1상 2하 3 좌 4 우
                switch (microbe.dir) {
                    case (1):
                        microbe.dir = 2;
                        break;
                    case (2):
                        microbe.dir = 1;
                        break;
                    case (3):
                        microbe.dir = 4;
                        break;
                    case (4):
                        microbe.dir = 3;

                }
                //System.out.println(microbe);
            }
            //만약 0 개라면 넣지 않는다
            if (microbe.count != 0) resList.add(microbe);
        }
        return resList;
    }

    private static List<Microbe> move() {
        // 이동하기 전에  카운트 초기화
        for (int[] row : countMap) Arrays.fill(row, 0);
        int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        List<Microbe> NextMicrobeList = new ArrayList<>();
        for (Microbe microbe : microbeList) {
            int nextRow = microbe.row + dir[microbe.dir - 1][0];
            int nextCol = microbe.col + dir[microbe.dir - 1][1];
            countMap[nextRow][nextCol] += 1;
            NextMicrobeList.add(new Microbe(nextRow, nextCol, microbe.dir, microbe.count));
        }
        return NextMicrobeList;

    }
}


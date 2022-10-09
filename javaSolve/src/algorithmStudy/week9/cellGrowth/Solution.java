package algorithmStudy.week9.cellGrowth;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Cell implements Comparable<Cell> {
    int r, c;
    int liveInfo;
    int state;
    int aliveCount;
    int disableCount;


    public Cell(int r, int c, int liveInfo, int state, int aliveCount, int disableCount) {
        this.r = r;
        this.c = c;
        this.liveInfo = liveInfo;
        this.state = state;
        this.aliveCount = aliveCount;
        this.disableCount = disableCount;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Cell{");
        sb.append("r=").append(r);
        sb.append(", c=").append(c);
        sb.append(", liveInfo=").append(liveInfo);
        sb.append(", state=").append(state);
        sb.append(", aliveCount=").append(aliveCount);
        sb.append(", disableCount=").append(disableCount);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int compareTo(Cell o) {
        return this.liveInfo - o.liveInfo;
    }
}

public class Solution {
    static int Tc;
    static int N, M, Time;
    static int ROW, COL;
    static Cell[][] map;
    static PriorityQueue<Cell> pq;
    static Cell[][] newMap ;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/study/week9/swea5653/input1.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Tc = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < Tc; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            Time = Integer.parseInt(st.nextToken());
            ROW = 500;
            COL = 500;
            map = new Cell[ROW][COL];
            pq = new PriorityQueue<>();
            for (int row = 0; row < N; row++) {
                st = new StringTokenizer(br.readLine());
                for (int col = 0; col < M; col++) {
                    int value = Integer.parseInt(st.nextToken());
                    if (value > 0) {
                        map[row + Time][col + Time] = new Cell(row + Time, col + Time, value, 0, value, value);
                        pq.add(new Cell(row + Time, col + Time, value, 0, value, value));
                    }
                }
            }

            for (int i = 1; i <= Time; i++) {
                //System.out.println(i);
                // n^2 *
                growth();
                // 큐에 다시 넣어줘야한다
                pushPq();
                //printMap(map);


            }
            int resCount = 0;
            for (int row = 0; row < ROW; row++) {
                for (int col = 0; col < COL; col++) {
                    if(map[row][col] != null){

                        Cell currCell = map[row][col];
                        if(currCell.state!= -1 )resCount++;
                    }
                }
            }
            System.out.printf("#%d %d\n",tc+1,resCount);
        }
    }

    private static void pushPq() {
        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {
                Cell curr = map[row][col];
                if (curr != null) {
                    pq.offer(curr);
                }
            }
        }
    }

    private static void growth() {
        PriorityQueue<Cell> q = new PriorityQueue<>();
        int[][] del = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
        while (!pq.isEmpty()) {
            Cell currCell = pq.poll();
            // 0이면 비활성 1이면 활성 -1 이면 죽은거
            int currState = currCell.state;
            switch (currState) {
                case -1:
                    break;
                case 0:
                    // 비활성화
                    currCell.disableCount -= 1;
                    if (currCell.disableCount == 0) {
                        // 만약 마지막 카운트였다면 상태를 바꿔줘야한다
                        currCell.state = 1;
                    }
                    map[currCell.r][currCell.c] = currCell;
                    break;
                case 1:
                    currCell.aliveCount -= 1;

                    // 만약 깍았는데 수명이 0 이면 죽능걸로 처리

                    // 이전에 막 활성화가 된거라면 지금 확장되야한다

                    if (currCell.liveInfo - 1 == currCell.aliveCount) {
                        // 확장
                        for (int d = 0; d < 4; d++) {
                            int nr = currCell.r + del[d][0];
                            int nc = currCell.c + del[d][1];
                            // 범위 안에 있고 만약 죽은 세포가 아니라면 넣어라
                            // pq 이기 떄문에 큰 주기를 가진 친구 나중에 나와서 덮어씌운다
                            if (isIn(nr, nc) && checkDeadCell(nr, nc, currCell)) {
                                q.add(new Cell(nr, nc, currCell.liveInfo, 0,
                                        currCell.liveInfo, currCell.liveInfo));
                            }
                        }
                    }
                    // 만약 수명이 이번 시간에 끝난다면 죽음 처리
                    if (currCell.state != -1 && currCell.aliveCount == 0) {
                        map[currCell.r][currCell.c] = new Cell(currCell.r, currCell.c, currCell.liveInfo, -1,
                                currCell.aliveCount, currCell.disableCount);
                    }
                    break;
            }
        }
        // 다돌고 처리
        processGrowth(q);
    }

    private static void processGrowth(Queue<Cell> q) {
         newMap= new Cell[ROW][COL];
        // 큐를 돌면서 새맵에 먼저기록
        while (!q.isEmpty()){
            Cell currCell = q.poll();
            int nr = currCell.r;
            int nc = currCell.c;
            newMap[nr][nc] = currCell;
        }

        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {
                Cell originMapValue = map[row][col];
                if(originMapValue != null){
                    newMap[row][col] = map[row][col];
                }
            }
        }
        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {
                map[row][col] = newMap[row][col];
            }
        }
        // 기존 맵에서 값가져와서 덮어씌우기
    }

    // 빈칸이면 가능하고 죽은 세포면 불가능한다
    private static boolean checkDeadCell(int nr, int nc, Cell currCell) {
        if (map[nr][nc] != null && map[nr][nc].state == -1) {
            return false;
        }
        else if (map[nr][nc] != null&& !(map[nr][nc].liveInfo >= currCell.liveInfo)) {
            return false;
        }
        //System.out.println(map[nr][nc]+" " + "vs " + currCell);
        return true;
    }

    private static boolean isIn(int nr, int nc) {
        return nr >= 0 && nr < ROW && nc >= 0 && nc < COL;
    }

    // 빈칸은 실제로 널이다  그리고 객체가 들어 가있다.
    private static void printMap(Cell[][] map) {
        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {
                Cell curr = map[row][col];
                if (row == 0 && col != 0) System.out.print(col >=10 ? col+" ": col+"  ");
                else if (col == 0 && row != 0) System.out.print(row >=10?  row: row+" ");
                else if (curr != null) {
                    if (curr.state == -1) System.out.print("D ");

                    else System.out.print(curr.liveInfo + " ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }


}

package algorithmStudy.week9.cellGrowth;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
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

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/study/week9/swea5653/input1.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Tc = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < Tc; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            Time = Integer.parseInt(st.nextToken());
            ROW = N + 2 * Time;
            COL = M + 2 * Time;
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
            //1 시간


            growth();
            // 큐에 다시 넣어줘야한다
            pushPq();


            // 2시간
            growth();
            // 큐에 다시 넣어줘야한다
            pushPq();
            printMap(map);
            //3시간
            growth();
            // 큐에 다시 넣어줘야한다
            pushPq();
            System.out.println(pq);
            printMap(map);
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
        // 비활성 상태면 비활성 수를 내리고
        // 내린수가 영이면 활성화로 바꾼다
        // 0 이 아니여도 pq 에 다시 넣는다

        // 활성상태면 활성카운트 내리고
        // 퍼트리고
        // map 에 넣는다 // 이러면pq 가지고도 할수 있찌 않음>
        // 내릴 수가 영이면
        // 죽은상태로 만들고
        // 0이아니면 pq 에 넣는다.
        // pq 에 넣지 않고 맵에 넣는다..

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
                                //System.out.print(currCell + " -> ");
                                //System.out.printf("nr %d : nc %d\n", nr, nc);
                                // 새로주기를 시작하는 세포들
                                map[nr][nc] = new Cell(nr, nc, currCell.liveInfo, 0,
                                        currCell.liveInfo, currCell.liveInfo);
                                //System.out.println("add" + map[nr][nc]);
                            }
                        }
                    }if(currCell.aliveCount== 0  ){
                        map[currCell.r][currCell.c] = new Cell(currCell.r, currCell.c, currCell.liveInfo, -1,
                                currCell.aliveCount, currCell.disableCount);
                    }

                    break;
            }

        }
    }

    // 빈칸이면 가능하고 죽은 세포면 불가능한다
    private static boolean checkDeadCell(int nr, int nc , Cell currCell) {
        if (map[nr][nc] != null && map[nr][nc].state == -1) {
            return false;
        }else if(map[nr][nc] != null && map[nr][nc].liveInfo >= currCell.liveInfo){
            System.out.println(nr+" "+nc + "vs" + currCell);
            return false;
        }
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
                if (curr != null) {
                    if (curr.state == -1)System.out.print("D ");

                    else System.out.print(curr.liveInfo + " ");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();

        }
    }


}

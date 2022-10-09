package algorithmStudy.week9.cellGrowthBFS;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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
    static Cell[][] newMap;

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


    // 빈칸이면 가능하고 죽은 세포면 불가능한다
    private static boolean checkDeadCell(int nr, int nc, Cell currCell) {
        if (map[nr][nc] != null && map[nr][nc].state == -1) {
            return false;
        } else if (map[nr][nc] != null && !(map[nr][nc].liveInfo >= currCell.liveInfo)) {
            return false;
        }
        //System.out.println(map[nr][nc]+" " + "vs " + currCell);
        return true;
    }

    private static boolean isIn(int nr, int nc) {
        return nr >= 0 && nr < ROW && nc >= 0 && nc < COL;
    }

    // 빈칸은 실제로 널이다  그리고 객체가 들어 가있다.


}

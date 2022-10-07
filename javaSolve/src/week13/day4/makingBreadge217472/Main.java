package week13.day4.makingBreadge217472;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Edge {
    int from;
    int to;
    int weight;

    public Edge(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "from=" + from +
                ", to=" + to +
                ", weight=" + weight +
                '}';
    }
    // sorting 을 위한 컴페어
}

class Point {
    int r;
    int c;
    int group;

    public Point(int r, int c, int group) {
        this.r = r;
        this.c = c;
        this.group = group;
    }

    public Point(int r, int c) {
        this.r = r;
        this.c = c;
    }

    @Override
    public String toString() {
        return "Point{" +
                "r=" + r +
                ", c=" + c +
                ", group=" + group +
                '}';
    }
}

/**
 * 0. 그룹을 찾는다 완탐, 그룹별로 groupList 에 넣는다
 * 1. 간선그래프를 만든다
 * 그룹에서 선을쏴서 맞은 "다른" 그룹 선 count 만큼 edge 로 만든다
 * 그룹간 최소 edge 를 찾을 필요가 있나? 없다 왜 ? 어짜피 정렬할거라
 * 찾는순간 기록된거 랑 min 연산해서 최소만 기록한다.
 * 2. 크루스칼
 * profit!!
 */
public class Main {
    static int N, M;
    static int[][] map;
    static int[][] check;
    static int[] parent;
    static int[][] adjArr;
    static ArrayList<Point>[] groupList;
    static int groupSize;
    static int[][] del = {{0, -1}, {-1, -0}, {0, 1}, {1, 0}};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/week13/day4/BJ17472/input3.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        check = new int[N][M];
        adjArr = new int[N][M];
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            for (int m = 0; m < M; m++) {
                int value = Integer.parseInt(st.nextToken());
                map[n][m] = value;
                check[n][m] = value == 1 ? 0 : -1;
            }
        }
        printArray(map);
        printArray(check);
        makeGroup(map);
        printArray(check);
        shootBridge();
        printArray(check);

    }

    private static void shootBridge() {
        printArray(groupList);
        // 4방검색해서  테두리가 check = -1 이면 쏴라
        for (ArrayList<Point> points : groupList) {
            for (Point p : points) {

                // 왼쪽
                // count = 0
                // for(p.x - 1 ~ 0) 감소하면서 (x)
                //  if check[p.y][x] != check[p.y][p.x] break;
                //  if check[p.y][x] 가 f가 아니면 => 다른 섬에 다은 경우
                //      다은 경우가 여러개일 수 있으므로 Math.min();
                // count++
                int count = 0;
                for (int c = p.c-1; c >=0; c--) {
                    int r = p.r;
                    if(check[r][c] == check[p.r][p.c]) break;
                    if(check[r][c] != -1){
                        //System.out.println("in");
                        if(count > 2){
                            System.out.println("왼쪽" + check[p.r][p.c] + " " + check[p.r][c]);
                            System.out.println("count " + count );
                            if(adjArr[p.r][c] == 0){
                                adjArr[p.r][c] = count;
                            }
                            else {
                                adjArr[check[p.r][p.c]][check[p.r][c]] = Math.min(adjArr[check[p.r][p.c]][check[p.r][c]], count);
                            }
                            break;
                        }
                    }
                    count++;
                }
                //위
                count = 0;
                for (int r = p.r-1; r >= 0; r--) {
                    if(check[r][p.c] == check[p.r][p.c]) break;
                    if(check[r][p.c] != -1){
                        //System.out.println("in");
                        if(count > 2){
                            System.out.println("위로" + check[p.r][p.c] + " " + check[r][p.c]);
                            System.out.println("count " + count );
                            if(adjArr[p.r][p.c] == 0){
                                adjArr[p.r][p.c] = count;
                            }
                            else {
                                adjArr[r][p.c] = Math.min(adjArr[r][p.c], count);
                            }
                            break;
                        }
                    }
                    count++;
                }
                //오
                count = 0;
                for (int c = p.c+1; c < M; c++) {
                    if(check[p.r][p.c] == check[p.r][c]) break;
                    if(check[p.r][c] != -1){
                        //System.out.println("in");
                        if(count > 2){
                            System.out.println("오른 " + check[p.r][p.c] + " " + check[p.r][c]);
                            System.out.println("count " + count );
                            if(adjArr[p.r][c] == 0){
                                adjArr[p.r][c] = count;
                            }
                            else {
                                adjArr[p.r][c] = Math.min(adjArr[p.r][c], count);
                            }
                            break;
                        }
                    }
                    count++;
                }
                //아래
                count = 0;
                for (int r = p.r+1; r < N; r++) {
                    if(check[p.r][p.c] == check[r][p.c]) break;
                    if(check[p.r][p.c] != -1){
                        //System.out.println("in");
                        if(count > 2){
                            System.out.println("오른 " + check[p.r][p.c] + " " + check[r][p.c]);
                            System.out.println("count " + count );
                            if(adjArr[r][p.c] == 0){
                                adjArr[r][p.c] = count;
                            }
                            else {
                                adjArr[r][p.c] = Math.min(adjArr[r][p.c], count);
                            }
                            break;
                        }
                    }
                    count++;
                }
            }
        }

    }

    private static void makeGroup(int[][] map) {
        groupSize = 1;
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                if (check[row][col] == 0) {
                    bfs(row, col);
                    groupSize++;
                }
            }

        }
        groupList = new ArrayList[groupSize - 1];
        for (int g = 0; g < groupSize - 1; g++) {
            groupList[g] = new ArrayList<>();
        }
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                if (check[row][col] >= 1) {
                    groupList[check[row][col] - 1].add(new Point(row, col, check[row][col] - 1));
                }

            }

        }
    }

    private static void bfs(int row, int col) {
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(row, col));
        check[row][col] = groupSize;
        while (!q.isEmpty()) {
            Point currPoint = q.poll();
            for (int d = 0; d < 4; d++) {
                int nr = currPoint.r + del[d][0];
                int nc = currPoint.c + del[d][1];
                if (isin(nr, nc) && map[nr][nc] == 1 && check[nr][nc] == 0) {
                    //System.out.println("in");
                    //System.out.println(groupSize);
                    check[nr][nc] = groupSize;
                    q.offer(new Point(nr, nc));
                }

            }
        }


    }

    private static boolean isin(int nr, int nc) {
        return nr >= 0 && nr < N && nc >= 0 && nc < M;
    }

    public static void printArray(int[][] inputMap) {

        for (int[] row : inputMap) {
            for (int i = 0; i < M; i++) {
                System.out.print(row[i] == -1 ? "f " : row[i] + " ");
            }
            ;
            System.out.println();
        }
        System.out.println();
    }

    public static void printArray(ArrayList<Point>[] inputMap) {
        for (ArrayList<Point> row : inputMap) {
            System.out.println(row);
        }
    }
}

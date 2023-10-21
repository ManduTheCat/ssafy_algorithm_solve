package SSAFY.week13.day4.makingBreadge217472;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Edge implements Comparable<Edge> {
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

    @Override
    public int compareTo(Edge o) {
        return this.weight - o.weight;
    }
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

public class Main {
    static int N, M;
    static int[][] map;
    static int[][] check;
    static int[] parent;
    static ArrayList<Edge> edgeList;
    static int[][] adjArr;
    static ArrayList<Point>[] groupList;
    static int groupSize;
    static int[][] del = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/SSAFY.week13/day4/BJ17472/input6.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        check = new int[N][M];
        //adjArr = new int[N][M]; // 5
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            for (int m = 0; m < M; m++) {
                int value = Integer.parseInt(st.nextToken());
                map[n][m] = value;
                check[n][m] = value == 1 ? 0 : -1; // 체크 하는데 -1 == false
            }
        }
        makeGroup();
        shootBridge();
        edgeList = new ArrayList<>();
        makeEdgeList();
        Collections.sort(edgeList);
        parent = new int[groupSize];
        for (int node = 1; node < groupSize; node++) {
            parent[node] = node;
        }
//        printArray(check);
        int res = 0;
        // 크루스칼
        int cnt = 0;
        for (Edge edge : edgeList) {
            if (union(edge.to, edge.from)) {
                //System.out.println(edge);
                res += edge.weight;
                cnt++;
            }
        }
        // 최소스패닝 트리는 어짜피 정점 -1개이다.
        if (cnt == groupSize - 2) System.out.println(res);
        else System.out.println(-1);
    }

    private static boolean union(int to, int from) {
        int rootTo = find(to);
        int rootFrom = find(from);
        if (rootTo == rootFrom) return false;
        parent[rootFrom] = rootTo;
        return true;

    }

    private static int find(int to) {
        if (parent[to] == to) return to;
        return parent[to] = find(parent[to]);
    }


    private static void makeEdgeList() {
        for (int row = 0; row < groupSize; row++) {
            for (int col = 0; col < groupSize; col++) {
                // 오 간선 리스트 ㅇㅇㅇ 정렬함
                if (adjArr[row][col] > 1) edgeList.add(new Edge(row, col, adjArr[row][col]));
            }

        }
    }

    private static void shootBridge() {
        // 4방검색해서  테두리가 check = -1 이면 쏴라
        adjArr = new int[groupSize][groupSize];
        for (int i = 1; i < groupList.length; i++) {
            ArrayList<Point> points = groupList[i]; // 아 그룹만큼 ㅇㅋ
            for (Point p : points) {
                // f 면 카운트 증가
                // 다른섬이면 만나는 순간 정지
                for (int d = 0; d < 4; d++) {
                    int nr = p.r;
                    int nc = p.c;
                    int count = 0;
                    while (true) {
                        nr += del[d][0];
                        nc += del[d][1];
                        if (!isin(nr, nc)) break;
                        if (check[p.r][p.c] == check[nr][nc]) break; // 출발섬이랑 같으면 정지
                        if (check[nr][nc] == -1) {
                            count++; // 바다면 count++
                        }
                        if (check[nr][nc] != -1) { // -1 이 아니면 다른섬이니까 adjArr 에 기록
                            int from = check[p.r][p.c];
                            int to = check[nr][nc];
                            //System.out.printf("%d [%d, %d]-> %d [%d, %d] : %d\n", from, p.r, p.c, to, nr, nc, count);
                            if (count >= 2) { // 그중 크기가 2 이상인것만
                                if (adjArr[from][to] == 0) {
                                    adjArr[from][to] = count;
                                    // inteager max 를 넣고 min으로 하는것도 좋은 방법
                                } else {
                                    adjArr[from][to] = Math.min(adjArr[from][to], count);
                                }
                            }
                            break;
                        }
                    }

                }
            }
        }


    }

    private static void makeGroup() {
        groupSize = 1;
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                if (check[row][col] == 0) {
                    bfs(row, col);
                    groupSize++; //1부터 증가함
                }
            }

        }
        // 싸이즈가 3이면 3개 만들고 1 2 까지만 돌지 않음? 경우수가 한 16퍼에 서 꺠짐
        groupList = new ArrayList[groupSize];
        for (int g = 1; g < groupSize; g++) {
            groupList[g] = new ArrayList<>();
        }
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                if (check[row][col] >= 1) {
                    groupList[check[row][col]].add(new Point(row, col, check[row][col]));
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

    // 디버깅용
    public static void printArray(int[][] inputMap) {

        for (int[] row : inputMap) {
            for (int i = 0; i < M; i++) {
                System.out.print(row[i] == -1 ? "f " : row[i] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // 디버깅용
    public static void printArray(ArrayList<Point>[] inputMap) {
        for (ArrayList<Point> row : inputMap) {
            System.out.println(row);
        }
    }
}

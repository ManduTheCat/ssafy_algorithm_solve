package SSAFY.week7.day2.BJ16236;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//	295768kb	928ms
class BabyShark {
    int row;
    int col;
    int fat;
    int eatCount = 0;
    int time=0;

    public BabyShark(int row, int col, int fat) {
        this.row = row;
        this.col = col;
        this.fat = fat;

    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BabyShark{");
        sb.append("row=").append(row);
        sb.append(", col=").append(col);
        sb.append(", fat=").append(fat);
        sb.append(", eatCount=").append(eatCount);
        sb.append(", time=").append(time);
        sb.append('}');
        return sb.toString();
    }
}

class Feed {
    int row;
    int col;
    int fat;
    int distanceFromShark;


    public Feed(int row, int col, int fat) {
        this.row = row;
        this.col = col;
        this.fat = fat;

    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Feed{");
        sb.append("row=").append(row);
        sb.append(", col=").append(col);
        sb.append(", fat=").append(fat);
        sb.append(", distanceFromShark=").append(distanceFromShark);
        sb.append('}');
        return sb.toString();
    }
}

public class Main {
    static int N;
    static BabyShark babyShark;
    static ArrayList<Feed> feedList;
    static int[][] map;
    static boolean[][] check;
    static Feed target;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("resources/SSAFY.week7/day2/bj16236/input4.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        feedList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (num == 9) {
                    babyShark = new BabyShark(i, j, 2);

                } else if (num > 0) feedList.add(new Feed(i, j, num));
            }

        }
        // check 초기화 위해 맵을 상어 시야(장애물 먹이) 맵에 기록
        sharkSigt(babyShark, feedList);
        // 먹이가 없을때까지 돈다 feedList 에는 모든 먹이가 들어가있다.
        while (!feedList.isEmpty() ){
            // 우선순위큐를 활용해 상어상태와 거리를 확인해 조건에 최적인 먹이를 구한다
            target = checkTargetDistance();
            // 최적타겟이 null 인경우 중단한다
            if(target == null)break;
            //bfs 전에 check에 장에물을 false 처리한다.
            beforBfsGenCheck(babyShark, feedList);
            // 먹이를 찾아 최단경로로 상어를 이동시킨다
            findTargetBfs(target);

        }

        System.out.println(babyShark.time);
    }

    // target 을 향해 상어가 출발합니다! 슝~
    // 상어 가 이전에프라이어리티 큐로 정해준 target 을 찾아 최단경로로 이동하는bfs
    // 먹이에 도착하면 이동한 거리 time 을 상어 객체에 누산한다
    // 해당 자리는 0으로 만들어준다.
    public static void findTargetBfs(Feed target) {
        // 서 북 동 남
        int[][] alpha = new int[][]{{0, -1}, {-1, 0}, {1, 0}, {0, 1}};
        int time = 0;
        Queue<Point> q = new ArrayDeque<>();
        Queue<Integer> timeq = new ArrayDeque<>();
        timeq.add(time);
        q.add(new Point(babyShark.row, babyShark.col));
        check[babyShark.row][babyShark.col] = true;
        while (!q.isEmpty()){
            Point curNode = q.poll();
            Integer curTime = timeq.poll();
            if(target!=null&&curNode.x == target.row && curNode.y == target.col){
                babyShark.time += curTime;
                babyShark.row = target.row; babyShark.col = target.col;
                babyShark.eatCount+=1;
                if(babyShark.eatCount == babyShark.fat){
                    babyShark.fat+=1;
                    babyShark.eatCount = 0;
                }
                map[babyShark.row][babyShark.col] = 0;
                feedList.remove(target);
                return;
            }
            for (int d = 0; d < 4; d++) {
                int nextR = curNode.x + alpha[d][0];
                int nextC = curNode.y + alpha[d][1];
                if(!isOut(nextR, nextC) &&! check[nextR][nextC]){
                    check[nextR][nextC] = true;
                    timeq.add(curTime+1);
                    q.add(new Point(nextR, nextC));
                }
            }
        }
    }
    
    
    // 맵밖으로 나가면 true
    public static boolean isOut(int r, int c) {
        return !(r >= 0 && r < N && c >= 0 && c < N);
    }

    // feedList 에 있는 물고기들을 살펴보면서 거리별 가장위 왼쪽 정렬해 우선순위 큐 에서 하나 꺼낸다
    // 꺼낸 먹이는 main 에서  target 에 담긴다.
    public static Feed checkTargetDistance() {
        for(Feed f: feedList){
            getDistance(babyShark, f);
        }
        PriorityQueue<Feed> priorityQueue = new PriorityQueue<>(new Comparator<Feed>() {
            @Override
            public int compare(Feed o1, Feed o2) {
                int o1Dist = o1.distanceFromShark;
                int o2Dist = o2.distanceFromShark;
                //거리가 가까운 물고기가 많다면, 가장 위에 있는 물고기, 그러한 물고기가 여러마리라면, 가장 왼쪽에 있는 물고기를 먹는다.
                if (o1Dist == o2Dist) {
                    if(o1.row == o2.row) return Integer.valueOf(o1.col).compareTo(Integer.valueOf(o2.col));
                    return Integer.valueOf(o1.row).compareTo(Integer.valueOf(o2.row));
                }
                else return Integer.valueOf(o1Dist).compareTo(Integer.valueOf(o2Dist));
            }
        });
        
        for (Feed f : feedList) {
            // 먹이의 조건 을 확인 나보다 작고 거리가 할당이 되야합 (먹이와 상어사이에 벽이 없어야함)
            if (babyShark.fat > f.fat && f.distanceFromShark != 0) {
                priorityQueue.offer(f);
            }
        }
        return priorityQueue.poll();


    }

    // 입력받은 상어와 먹이간의 거리를 bfs 최단거리구하는 함수
    public static void getDistance(BabyShark shark, Feed target) {
        beforBfsGenCheck(babyShark, feedList);
        int[][] alpha = new int[][]{{0, -1}, {-1, 0}, {1, 0}, {0, 1}};
        int time = 0;
        Queue<Point> q = new ArrayDeque<>();
        Queue<Integer> timeq = new ArrayDeque<>();
        timeq.add(time);
        q.add(new Point(shark.row, shark.col));
        check[shark.row][shark.col] = true;
        while (!q.isEmpty()){
            Point curNode = q.poll();
            Integer curTime = timeq.poll();
            if(target!=null && curNode.x == target.row && curNode.y == target.col){
                target.distanceFromShark = curTime;
                break;
            }
            for (int d = 0; d < 4; d++) {
                int nextR = curNode.x + alpha[d][0];
                int nextC = curNode.y + alpha[d][1];
                if(!isOut(nextR, nextC) &&! check[nextR][nextC]){
                    check[nextR][nextC] = true;
                    timeq.add(curTime+1);
                    q.add(new Point(nextR, nextC));
                }
            }
        }
    }
    
    // 맵을 현제 상어에 체중에  맞게 못지나는 곳과 먹이를 갱신하는 함수
    public static void sharkSigt(BabyShark shark, ArrayList<Feed> feedList) {
        int curSarkSize = shark.fat;
        for (Feed f : feedList) {
            if (curSarkSize > f.fat) {

                //먹이는 보이고
                map[f.row][f.col] = f.fat;
                // 자신이랑 같은건 지나간다
            } else if (f.fat == shark.fat) map[f.row][f.col]= 0;

             else map[f.row][f.col] = 7;
        }
        map[babyShark.row][babyShark.col] = 9;

    }

    // check 전처리 상어보다 큰건 장애물 처리 상어보다 작은거 먹이 상어랑 같은건통과
    public static void beforBfsGenCheck(BabyShark shark, ArrayList<Feed> feedList) {
        //bfs 하기 전에 장에물에 미리 false 처리 해서 못지나가게 한다
        check = new boolean[N][N];
        int curSarkSize = shark.fat;
        for (Feed f : feedList) {
            if (curSarkSize < f.fat) {
                // 장에물 처리 나보다 큰먹이는 못간다
                check[f.row][f.col] = true;
            } else if (curSarkSize == f.fat) {
                // 이전에 장애물 취급했던 먹이 상어체중이 같아졌으면 false로 되돌린다.
                check[f.row][f.col] = false;
            }
        }
    }
}

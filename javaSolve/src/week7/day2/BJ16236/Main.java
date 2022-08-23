package week7.day2.BJ16236;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


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

        System.setIn(new FileInputStream("resources/week7/day2/bj16236/input5.txt"));
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

        // 상어가 먹이를 찾고 먹이로 이동하면서 카운트가 올라가야한다. 최단거리 - bfs

        // 자신보다 큰건 다 장애물로 표시한다 - check에 true 로 넣는다.
        // 1 현재상어의 크기보다 작은 먹이를 찾는다, 거리를 구한다. 거리가 같은 좌표 중 맨 왼쪽 을 찾은다.
        // 2 최단거리로 이동한다
        //
        //printMap(babyShark, feedList);

        // 자신보다작은 친구 찾기
        sharkSigt(babyShark, feedList);
        //while (findRestFeed() > 0 ){
        while (!feedList.isEmpty() ){
            //sharkSigt(babyShark, feedList);

            //System.out.println("distance!!");

            target = checkTargetDistance();
//            System.out.println();
//            System.out.print(""+ babyShark+" vs ");
//            System.out.print(target);
//            System.out.println();
            beforBfsGenCheck(babyShark, feedList);
//            for (boolean[] c : check) {
//                System.out.println(Arrays.toString(c));
//            }
            if(target == null)break;
            findTargetBfs(target);
            // 먹고 체중늘리고
            //System.out.println(babyShark.toString());
            // 확인
            //sharkSigt(babyShark, feedList);

        }
        // 타겟 거리 탐색 거리순, 왼쪽순으로 정렬해 하나 뱉는다 타겟지정
        //타겟 포착후 타겟을 먹으러 간다 최단거리 bfs 활용
        System.out.println(babyShark.time);
    }
    // 상어 발싸
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

            // 먹이를 찾음
            if(target!=null&&curNode.x == target.row && curNode.y == target.col){

                // 먹고나서 시간을 누산하고, 좌표를 갱신 갯수를 달성하면 체중을 올린다.
                // 그리고 먹이  제거
                babyShark.time += curTime;
                babyShark.row = target.row; babyShark.col = target.col;
                babyShark.eatCount+=1;
                if(babyShark.eatCount == babyShark.fat){
                    babyShark.fat+=1;
                    babyShark.eatCount = 0;
                }
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
    public static int findRestFeed(){
        // 지도를 보고  남은 먹이 갯수 반환
        // 9 0 7 을 제외한 숫자 있는지 확인
        int count = 0;
        for(int [] m : map){
            for(int el : m){
                if (el != 7 && el != 0 && el != 9) count++;
            }
        }
        return  count;
    }
    public static boolean isOut(int r, int c) {
        return !(r >= 0 && r < N && c >= 0 && c < N);
    }

    public static Feed checkTargetDistance() {
        PriorityQueue<Feed> priorityQueue = new PriorityQueue<>(new Comparator<Feed>() {
            @Override
            public int compare(Feed o1, Feed o2) {
                int o1Dist = getDistance(babyShark, o1);
                int o2Dist = getDistance(babyShark, o2);
                //거리가 가까운 물고기가 많다면, 가장 위에 있는 물고기, 그러한 물고기가 여러마리라면, 가장 왼쪽에 있는 물고기를 먹는다.
                if (o1Dist == o2Dist) {
                    if(o1.row == o2.row) return Integer.valueOf(o1.col).compareTo(Integer.valueOf(o2.col));
                    return Integer.valueOf(o1.row).compareTo(Integer.valueOf(o2.row));
                }
                else return Integer.valueOf(o1Dist).compareTo(Integer.valueOf(o2Dist));
            }
        });
        // 상어 시야로 처리된 map
        // 현제 상어 객체보다 작은 친구들 pq 에 넣는다.
        //System.out.println("feedList : ");
        // feed


        //System.out.print(feedList);
        for (Feed f : feedList) {
            if (babyShark.fat > f.fat) {
                System.out.println(f);
                priorityQueue.offer(f);
            }
        }
        System.out.println();
        return priorityQueue.poll();


    }

    public static int getDistance(BabyShark shark, Feed target) {
        beforBfsGenCheck(babyShark, feedList);
        int[][] alpha = new int[][]{{0, -1}, {-1, 0}, {1, 0}, {0, 1}};
        int time = 0;
        int res = 0;
        Queue<Point> q = new ArrayDeque<>();
        Queue<Integer> timeq = new ArrayDeque<>();
        timeq.add(time);
        q.add(new Point(shark.row, shark.col));
        check[shark.row][shark.col] = true;
        while (!q.isEmpty()){
            Point curNode = q.poll();
            Integer curTime = timeq.poll();
            if(target!=null && curNode.x == target.row && curNode.y == target.col){
                res = curTime;
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
        return res;
    }

    // 여기서 체크 재정의 7이면  방문 처리를 미리 한다
    // 함부로 쓰면안된다
    public static void sharkSigt(BabyShark shark, ArrayList<Feed> feedList) {
        int curSarkSize = shark.fat;
        for (Feed f : feedList) {
            if (curSarkSize > f.fat) {

                //먹이는 보이고
                map[f.row][f.col] = f.fat;
                //자신보다 큰면 못지나가고 = 자신이랑 같은건 지나간다
            } else if (f.fat == shark.fat) map[f.row][f.col]= 0;

             else map[f.row][f.col] = 7;
        }
        // 빼야 맵에 9 가 없다
        map[babyShark.row][babyShark.col] = 9;
//        for (int[] m : map) {
//            for (int el : m) {
//                System.out.print(el + " ");
//            }
//            System.out.println();
//        }
    }

    // check 전처리
    public static void beforBfsGenCheck(BabyShark shark, ArrayList<Feed> feedList) {
        check = new boolean[N][N];
        int curSarkSize = shark.fat;
        for (Feed f : feedList) {
            if (curSarkSize < f.fat) {
                check[f.row][f.col] = true;
            } else if (curSarkSize == f.fat) {
                check[f.row][f.col] = false;
            }
        }
    }

    public static void printMap(BabyShark shark, ArrayList<Feed> feedList) {
        map[shark.row][shark.col] = 9;
        for (Feed f : feedList) {
            map[f.row][f.col] = f.fat;
        }
        for (int[] m : map) {
            for (int el : m) {
                System.out.print(el + " ");
            }
            System.out.println();
        }
    }
}

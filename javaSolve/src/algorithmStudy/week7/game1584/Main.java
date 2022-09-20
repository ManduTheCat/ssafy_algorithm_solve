package algorithmStudy.week7.game1584;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 다익스트라
// 한개의 좌표가 하나의 노드라생각
// dp 는 최대 500 * 500
// 최소비용 최단경로 가중치합 = (0, 0)에서 (500, 500)으로 갈 때 잃는 생명의 최솟값


public class Main {
    static int [][] d;
    static int N;
    static int M;
    static int [][] map = new int[500][500];

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/study/week7/BJ1584/input3.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int n = 0; n < N; n++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int dangerX1 = Integer.parseInt(st.nextToken());
            int dangerY1 = Integer.parseInt(st.nextToken());
            int dangerX2 = Integer.parseInt(st.nextToken());
            int dangerY2 = Integer.parseInt(st.nextToken());
            // 좌표 경우의 수  두가지 경우 무조건 반대편이기때문에
            if(dangerX2 > dangerX1) markingZone(dangerX1, dangerY1, dangerX2, dangerY2, 1);
            else markingZone(dangerX2, dangerY1, dangerX1, dangerY2, 1);
        }
        M = Integer.parseInt(br.readLine());
        for (int m = 0; m < M; m++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int dangerX1 = Integer.parseInt(st.nextToken());
            int dangerY1 = Integer.parseInt(st.nextToken());
            int dangerX2 = Integer.parseInt(st.nextToken());
            int dangerY2 = Integer.parseInt(st.nextToken());
            if(dangerX2 > dangerX1) markingZone(dangerX1, dangerY1, dangerX2, dangerY2,9);
            else markingZone(dangerX2, dangerY1, dangerX1, dangerY2, 9);
        }
        for(int [] m: map){
            System.out.println(Arrays.toString(m));
        }
        for (int n = 0; n < N; n++) {
            for (int m = 0; m < M; m++) {
                d[n][m] = Integer.MAX_VALUE;
            }
        }
        d[0][0] = 0;

    }
    public static void DIjkstar (){

    }
    public static void markingZone(int startX, int startY, int endX, int endY, int weight){
        for (int y = startY; y < endY; y++) {
            for (int x = startX; x <endX ; x++) {
                map[y][x] = weight;
            }
        }
    }
}

package dailySolve.SWEA2115honySOl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

    static int N, M, C, maxProfit;
    static int[][] Map;
    static boolean[] Visited;
    static List<Honey> honeyList;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=T; tc++) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken()); //벌통들의 크기
            M = Integer.parseInt(st.nextToken()); //선택 가능한 벌통의 개수
            C = Integer.parseInt(st.nextToken()); //꿀 채취할 수 있는 양

            Map = new int[N][N];
            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++) {
                    Map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            //입력확인
//            for(int i=0; i<N; i++) {
//                for(int j=0; j<N; j++) {
//                    System.out.print(Map[i][j] + " ");
//                }
//                System.out.println();
//            }


            //가로로 연속되는 벌통 선택
            maxProfit=0;
            honeyList = new ArrayList<>();
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    //벌통을 선택할 수 있는지
                    if(j+M <= N) {
                        getHoney(i, j);
                    }
                }
            }

            int Result = 0; //최대수익

            int size = honeyList.size();
            for(int i=0; i<size; i++) {
                Honey h1 = honeyList.get(i);

                for(int j=i+1; j<size; j++) {
                    Honey h2 = honeyList.get(j);

                    //같은 벌통인지 체크, 겹칠수도 있으니까
                    if(h1.x == h2.x && h1.y + M > h2.y) continue;

                    int total = h1.profit + h2.profit;
                    Result = Math.max(Result, total);
                }
            }

            System.out.println("#" + tc + " " + Result);

        }
    }

    private static void getHoney(int x, int y) {
        int sum = 0; //벌통의 무게합
        int profit = 0; //벌통에서 얻는 이득

        for(int i=0; i<M; i++) { //가로로 연속되는 벌통 M개
            sum += Map[x][y+i];
            profit += Map[x][y+i]*Map[x][y+i];
        }

        if(sum <= C) { //벌통에서 채취한 꿀이 가능한 무게보다 같거나 작을 경우
            //System.out.println("1" + profit);
            honeyList.add(new Honey(x, y, profit)); //벌통 리스트에 저장
        } else { //벌통에서 채취한 꿀이 가능한 무게보다 무거운 경우
            //M개 중에 최대이익 내는 것 선택
            Visited = new boolean[M];
            for(int i=1; i<M; i++) {
                getMaxProfit(0, 0, i, 0, x, y, 0);
            }
            //System.out.println(x + "," + y + " " + maxProfit);
            honeyList.add(new Honey(x, y, maxProfit));
            maxProfit = 0;
        }


    }

    private static void getMaxProfit(int cnt, int start, int select, int sum, int x, int y, int profit) {
        if(cnt == select) {
            maxProfit = Math.max(maxProfit, profit);
            return;
        }

        for(int i=start; i<M; i++) {
            //System.out.println("h " + Map[x][y+i]);
            if(sum+Map[x][y+i] <= C && !Visited[i]) {
                Visited[i] = true;
                getMaxProfit(cnt+1, i+1, select, sum+Map[x][y+i], x, y, profit+Map[x][y+i] * Map[x][y+i]);
                Visited[i] = false;
            }
        }

    }

    //벌통 정보
    static class Honey {
        int x, y, profit;

        public Honey(int x, int y, int profit) {
            super();
            this.x = x;
            this.y = y;
            this.profit = profit;
        }

        @Override
        public String toString() {
            return "Honey [x=" + x + ", y=" + y + ", profit=" + profit + "]";
        }

    }
}
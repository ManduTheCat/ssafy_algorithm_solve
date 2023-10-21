package SSAFY.week5.day2.swea6804;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SolutionDFS {

    static int meCount;
    static int enermyCount;
    static int[] res ;
    static ArrayList<Integer> me ;
    static ArrayList<Integer> enmermy;
    static int N;
    static boolean[] Visited;
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/SSAFY.week5/day2/swea6804/input1.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int Tc = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= Tc; tc++){
            int [] cardDeck = new int[19];
            StringTokenizer st = new StringTokenizer(br.readLine());
            me = new ArrayList<>();
            for(int t = 0; t < 9; t++){
                int idx = Integer.parseInt(st.nextToken());
                me.add(idx);
                cardDeck[idx] = 1;
            }
            System.out.println();
            enmermy = new ArrayList<>();
            for(int i = 1; i< 19; i++){
                if(cardDeck[i] != 1){
                    enmermy.add(i);
                }
            }
            System.out.println(me);
            System.out.println(enmermy);
            Visited = new boolean [19];
            //입력

            // 규영이는 순서대로 제출
            // 라운드마다 규영이가 내는 카드는 정해져있다
            // 라운드는 규영 = 인덱스

            dfs(0, 0);
            System.out.println(meCount + " " + enermyCount );


        }


    }
    public static void dfs (int round, int currIndex){

    }

}

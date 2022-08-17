package week6.day2.swea4012;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class ij {
    int i;
    int j;
    int sum;

    public ij(int i, int j, int sum) {
        this.i = i;
        this.j = j;
        this.sum = sum;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(i).append(" ").append(j). append(" ").append(sum);
        return sb.toString();
    }
}

public class Main {
    static int n;
    static int Tc;
    static int [][] map;
    static ArrayList <Integer> resList;
    static ArrayList<ij> sumLists;
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/week6/day2/swea4012/input3.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int Tc = Integer.parseInt(br.readLine());
        for(int tc  = 0; tc< Tc; tc++){
            n = Integer.parseInt(br.readLine());
            map = new int[n][n];
            sumLists = new ArrayList<>();
            resList = new ArrayList<>();
            for(int i = 0;  i< n; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            sumFood();
            //System.out.println(sumLists);
            absSubFood();
            System.out.println(Collections.min(resList));
        }
    }


    static boolean isDiff(ij stand, ij move){
        if(stand.i != move.j && stand.i != move.i && stand.j != move.j && stand.j !=move.i){
            System.out.println(stand +" vs " +  move);
            return true;
        }
        return false;
    }
    private static void absSubFood() {
        //System.out.println(sumLists);
        int size = sumLists.size();
        for(int stand  = 0; stand < size; stand++){
            for(int move = 0; move < size; move++){
                if(isDiff(sumLists.get(stand), sumLists.get(move))){
                    resList.add(Math.abs(sumLists.get(stand).sum - sumLists.get(move).sum));
                }

            }
        }

    }

    // n 번 돌면서 합들 채우기
    // 01 10 의 합 은 0 식재료  + 1 식제료의 합
    static void sumFood (){
        for(int i = 0; i< n; i++){
            for(int j  = i + 1;  j < n; j ++){
                int ij = map[i][j];
                int ji = map[j][i];
                //System.out.printf("%d + %d = %d\n", ij, ji, ij+ji);
                sumLists.add(new ij(i, j, ij+ji));
            }
        }
    }

}

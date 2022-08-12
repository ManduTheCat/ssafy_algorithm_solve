package week5.day5.BJ1541;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;


/**
 * @author 김명진
 * 조건
 * + - 만 있다
 * 풀이
 * 처음에 + 기준으로 잘라서 자른것끼리의 합을 구한다
 * 구한것을 sums arrayList에 넣고 항이 1개인경우 처리하고
 * sums의 차를 구한다.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream( "resources/week5/day5/BJ1541/input1.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(),"-");
        ArrayList<Integer> sums = new ArrayList<>();
        while (st.hasMoreTokens()){
            int sum = 0;
            StringTokenizer stIn = new StringTokenizer(st.nextToken(), "+");
            while (stIn.hasMoreTokens()){
                sum += Integer.parseInt(stIn.nextToken());
            }
            sums.add(sum);
        }
        int min = sums.get(0);
        if(sums.size() > 1){
            for(int i  = 1; i < sums.size(); i++){
                min-=sums.get(i);
            }
        }

        System.out.println(min);
    }
}

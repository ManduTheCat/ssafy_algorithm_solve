package week6.day3.swea1247;


import java.awt.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;


/**
 * 5sec
 * @author 김명진
 * 고객들 순서를 비트마스킹 순열로 구하기 -> 구한 순열로 거리 계산 -> 최소값 찾기
 */
public class Solution {
    static int Tc;
    static Point[] serviceRoute;
    static int serviceNum;
    static Point co;
    static Point home;
    static Point[] resultPermutation;
    static ArrayList<Integer> distSumList;
    static StringBuilder sb = new StringBuilder();



    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/week6/day3/swea1247/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Tc = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= Tc; tc++) {
            serviceNum = Integer.parseInt(br.readLine());
            serviceRoute = new Point[serviceNum];
            StringTokenizer st = new StringTokenizer(br.readLine());
            co = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            home = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            for (int i = 0; i < serviceNum; i++) {
                serviceRoute[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }
            resultPermutation = new Point[serviceNum];
            distSumList = new ArrayList<>();
            permutation(0, 0);
            sb.append("#").append(tc).append(" ").append(Collections.min(distSumList)).append("\n");
        }
        System.out.println(sb);

    }
    // 비트마스킹 순열을 구하고 거리계산한는 함수로 보냄
    public static void permutation(int depth, int flag) {
        if (depth == serviceNum) {
            getDistList();
            return;
        }
        for (int i = 0; i < serviceNum; i++) {
            if ((flag & 1 << i) == 0) {
                resultPermutation[depth] = serviceRoute[i];
                permutation(depth + 1, flag | 1 << i);
            }
        }
    }
    // 만들어진 루트 로 회사에서 집까지의 거리를 구하고 Array리스트에 넣음 -> 메인에서 최소를 구함
    public static void getDistList() {
        int sum =  getDistance(co,resultPermutation[0]);
        for (int i = 0; i < serviceNum - 1; i++) {
            sum += getDistance(resultPermutation[i], resultPermutation[i + 1]);

        }
        sum += getDistance(resultPermutation[serviceNum - 1],home);
        distSumList.add(sum);
    }
    
    // awt Point distance 함수가 sqrt 로 구하기 때문에 따로 거리 구하는 함수 구현
    public static int getDistance (Point from, Point to){
        return Math.abs(from.x - to.x)+ Math.abs(from.y - to.y);
    }
}

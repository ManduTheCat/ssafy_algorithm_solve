package SSAFY.week5.day4.bk2961;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/***
 * <pre>
 *     뽑을수있는 모든 경우의수를 combination 으로 구해
 *     씬맛과 쓴맛 차이 결과를 ArrayList 에 넣어 최소값을 출력했습니다.
 *
 *     힙으로 정렬한다면 더빨라질까?
 *     	11844KB	80ms
 * </pre>
 * @author 김명진
 *
 */
public class Main {
    static int[][] tasteList;
    static ArrayList<Integer> calArrayList;
    static int[] indexProbtemp;

    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("resources/SSAFY.week5/day4/bk2961/input2.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int foodCount = Integer.parseInt(br.readLine());
        tasteList = new int[foodCount][2];
        for (int idx = 0; idx < foodCount; idx++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 2; j++) {
                tasteList[idx][j] = Integer.parseInt(st.nextToken());
            }
        }

        //
        calArrayList = new ArrayList<>();
        int size = tasteList.length;
        for(int i = 1; i <= size; i++){
            indexProbtemp = new int[i];
            combi(0, i, 0);
        }
        System.out.println(Collections.min(calArrayList));
    }

    // 조합을 만들어 계산하는 함수로 보네는 함수
    public static void combi(int start, int n, int depth) {
        if (depth == n) {
            //정해진 선택갯수까지 도착하면
            //계산을 하는 함수로 보넨다
            calc(indexProbtemp);
            return;
        }
        int size = tasteList.length;
        for (int i = start; i < size; i++) {
            indexProbtemp[depth] = i;
            combi(i + 1, n, depth + 1);
        }
    }

    //맛 계산 하는 함수
    public static void calc(int []probs){
        //0이아닌 양수인 정수니 1로 신맛 시작가능
        //인덱스 조합이 들어오면 순서대로 돌면서 연산한다
        int sour = 1;
        int bitter = 0;
        for (int index : probs) {
            sour *= tasteList[index][0];
            bitter += tasteList[index][1];
        }
        calArrayList.add(Math.abs(sour-bitter));
    }
}

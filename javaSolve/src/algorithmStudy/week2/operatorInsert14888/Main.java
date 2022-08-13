package algorithmStudy.week2.operatorInsert14888;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int[] operatorInputList = new int[4];
    public static int[] propbsIndexList;
    public static int[] ableOperatorList;
    public static int n;
    public static int[] numList;
    public static char[] operator = new char[]{'+', '-', 'x', '%'};
    public static ArrayList<Integer> resultList;
    public static ArrayList<Integer> resList = new ArrayList<>();
    /**
     * + - x % 순서로 인덱스 0 ,1,2,3
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("resources/study/week2/bk14888/input1.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        numList = new int[n];
        StringTokenizer numTk = new StringTokenizer(br.readLine());
        for (int numIdx = 0; numIdx < n; numIdx++) {
            numList[numIdx] = Integer.parseInt(numTk.nextToken());
        }

        StringTokenizer oTk = new StringTokenizer(br.readLine());
        for (int oIdx = 0; oIdx < 4; oIdx++) {
            operatorInputList[oIdx] = Integer.parseInt(oTk.nextToken());
        }
        // 모든 연산자 경우의수 를 찾아야한다
        // 숫자 사이에 들어가기에 순서가 의미가 있다.
        // 사용가능한 갯수와 종류를 나타내는 배열을 만들어서 순열 을 구해야한다
        //+ - x % 순서로 인덱스 0 ,1,2,3 라면
        // 가능한 리스트 만들기 ex

        // 갯수를 반영한 입력에 따른 순열 후보 선정
        // 0 1 0 1 => [1, 3] 2001=>[0,0,3]
        // 2 1 1 1 -> [0, 0, 1, 2, 3]
        candidateOperator();


        // 이걸 이제 순열 경우의수를 모두 구한다 
        //System.out.println(Arrays.toString(ableOperatorList));

        propbsIndexList = new int[n - 1];
        //경우의 수 구함
        resultList = new ArrayList<>();
        perm(0, 0);
        System.out.printf("%d\n", Collections.max(resList));
        System.out.printf("%d",  Collections.min(resList));

    }

    public static void calculate() {

        int res = numList[0];
        int index = 1;
        for (int operator : propbsIndexList) {
            switch (operator) {
                case 0:
                    res+= numList[index++];
                    break;
                case 1:
                    res-=numList[index++];
                    break;
                case 2:
                    res*=numList[index++];
                    break;
                case 3:
                    res/=numList[index++];
                    break;
            }
        }
        resList.add(res);

    }

    /**
     * 입력을 갯수와 연산자 인덱스를 반영한 ableOperatorList 만드는 함수
     */
    private static void candidateOperator() {
        int countOper = 0;
        for (int op : operatorInputList) {
            countOper += op;
        }
        ableOperatorList = new int[countOper];
        int idx = 0;
        for (int i = 0; i < operatorInputList.length; i++) {
            int value = operatorInputList[i];
            for (int re = 0; re < value; re++) {
                ableOperatorList[idx++] = i;
            }
        }
    }

    // 인덱스 순열의 경우의수를 구한다
    public static void perm(int depth, int flag) {
        if (depth == n - 1) {
            //System.out.println(Arrays.toString(propbsIndexList));
            calculate();
            return;
        }
        for (int i = 0; i < n - 1; i++) {
            // 비트 마스킹 0이 아니면 이건쓰고 있다
            // 비트 마스킹 0이면 사용가능하다
            if ((flag & 1 << i) == 0) {
                propbsIndexList[depth] = ableOperatorList[i];
                perm(depth + 1, flag | 1 << i);
            }
        }
    }
}

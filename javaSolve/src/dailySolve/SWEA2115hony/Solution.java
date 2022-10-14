package dailySolve.SWEA2115hony;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    static int N;
    static int M;
    static int C;
    static int Tc;
    static boolean[][] check;
    static int[][] map;
    static int[] honeyMan1;
    static int[] honeyMan2;
    static int maxHoney;

    static int powerSum;
    static boolean[] powerSetCheck;
    static int[] resultHoneyHouse;
    static int honeyMax;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/daily/swea2115/input3.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Tc = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < Tc; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            resultHoneyHouse = new int[M];
            map = new int[N][N];
            check = new boolean[N][N];
            maxHoney = 0;
            for (int row = 0; row < N; row++) {
                st = new StringTokenizer(br.readLine());
                for (int col = 0; col < N; col++) {
                    int val = Integer.parseInt(st.nextToken());
                    map[row][col] = val;
                }
            }
            //printArr(map);
            choseLine(0);
            System.out.printf("#%d %d\n", tc + 1, maxHoney);


        }

    }

    private static void choseLine(int depth) {
        if (depth == 2) {
            drainHoney();
            printArr(check);
            return;

        }
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N - M + 1; col++) {
                // 한번 선택 될때 col +M 까지 체크가 된다
                if (!isChecked(row, col)) {
                    for (int chose = col; chose < col + M; chose++) {
                        check[row][chose] = true;
                    }
                    choseLine(depth + 1);
                    for (int chose = col; chose < col + M; chose++) {
                        check[row][chose] = false;
                    }
                }

            }

        }

    }

    private static boolean isChecked(int row, int col) {
        for (int nextCol = col; nextCol < col + M; nextCol++) {
            if (check[row][nextCol]) return true;
        }
        return false;
    }

    private static void drainHoney() {
        // 선택한거 라인별로 구분하기
        honeyMan1 = new int[M];
        honeyMan2 = new int[M];
        int count = 0;

        // 무조건M*2 개만 선택됬다는 가정하에  양봉업자들을 구분한다.
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if (check[row][col]) {
                    if (count < M) {
                        honeyMan1[count] = map[row][col];

                    } else {
                        honeyMan2[count - M] = map[row][col];
                    }
                    count++;
                }
            }
        }
        int sum = 0;
        ArrayList<Integer> honey1 = mesermentHoney(honeyMan1);
        ArrayList<Integer> honey2 = mesermentHoney(honeyMan2);
        System.out.println(honey1);
        System.out.println(honey2);
        for (int el : honey1) {
            sum += el * el;
        }
        for (int el : honey2) {
            sum += el * el;
        }
        maxHoney = Math.max(maxHoney, sum);
        // 만약 라인별로 총합이 C 이상 넘어가면 부분집합을 돌려라 합이 C 이하가 되게

    }

    private static ArrayList<Integer> mesermentHoney(int[] honeyMan) {
        // c 보다 작거나 같으면 그대로 맥스
        // c 보다 크면 부분집합으로 맥스를 찾은다
        powerSum = 0;
        powerSetCheck = new boolean[M];
        honeyMax = 0;


        ArrayList<Integer> temp = new ArrayList<>();

        int sum = 0;
        for (int el : honeyMan) {
            sum += el;
        }
        if (sum > C) {
            powerSet(honeyMan, 0);
            System.out.println("함수밖에서 :" + Arrays.toString(resultHoneyHouse));
        }
        for (int el : honeyMan) {
            temp.add(el);
        }
        return temp;

    }

    private static void powerSet(int[] honeyMan, int depth) {

        if (depth == M) {


            int sum = 0;
            int choseCount = 0;
            for (int i = 0; i < M; i++) {
                if (powerSetCheck[i]) {
                    choseCount++;
                    sum += honeyMan[i];
                }
                // 여기서 결과 보네야함
                // 값이 작다면 그냥 보네야하고
                // 값이 크다면 그중에서 골라야한다.

            } // 일단 값이 작으면 그대로 보내려 했다.
            // 마지막이 0 이여서 그렇다
            // 합이 크다면 바꾸자 temp 에다가만 진행 일단



            int [] temp = new int[choseCount];
            int idx = 0;
            for (int i = 0; i < M; i++) {
                if(powerSetCheck[i]){
                    temp[idx++] = honeyMan[i];
                }
            }
            if(sum <= C){
                resultHoneyHouse = new int[choseCount];
                int compareSum = 0;
                for(int el : temp){
                    compareSum+=sum;
                }
                if(compareSum > honeyMax){
                    honeyMax = compareSum;
                    System.out.println("함수 안에서"+ Arrays.toString(temp) );
                    resultHoneyHouse = temp.clone();
                }
            }


            return;
        }
        powerSetCheck[depth] = true;
        powerSet(honeyMan, depth + 1);
        powerSetCheck[depth] = false;
        powerSet(honeyMan, depth + 1);
    }

    public static void printArr(int[][] input) {
        for (int[] row : input) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println();
    }

    public static void printArr(boolean[][] input) {
        for (boolean[] row : input) {
            for (boolean el : row) {
                System.out.print(el ? "1 " : "0 ");
            }
            System.out.println();
        }
    }
}

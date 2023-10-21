package SSAFY.week6.day2.swea5644;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Bc {
    private int power;
    public int i;
    public int j;
    public int people;
    public int cover;


    public Bc(int row, int col, int cover, int power) {
        this.power = power;
        this.i = row;
        this.j = col;
        this.cover = cover;
        this.people = 0;
    }

    public int getPower() {
        if (people > 1) {
            return power / this.people;
        } else return power;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Bc{");
        sb.append("power=").append(getPower());
        sb.append(", i=").append(i);
        sb.append(", j=").append(j);
        sb.append(", people=").append(people);
        sb.append(", cover=").append(cover);
        sb.append('}');
        return sb.toString();
    }
}

class Person {
    int currI;
    int currJ;
    ArrayList<Integer> inBcList = new ArrayList<>();

    public Person(int currI, int currJ) {
        this.currI = currI;
        this.currJ = currJ;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Person{");
        sb.append("currI=").append(currI);
        sb.append(", currJ=").append(currJ);
        sb.append('}');
        return sb.toString();
    }
}

public class Main {
    static int Tc;
    static int time;
    static int bcCount;
    static int[] AtimeArray;
    static int[] BtimeArray;
    static int[][] totaltime;
    static ArrayList<Bc> bcArrayList;
    static Person A = new Person(1, 1);
    static Person B = new Person(10, 10);

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/SSAFY.week6/day2/swea5644/input1.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Tc = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < Tc; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            time = Integer.parseInt(st.nextToken());
            bcCount = Integer.parseInt(st.nextToken());
            totaltime = new int[2][time];
            AtimeArray = new int[time];
            BtimeArray = new int[time];
            totaltime[0] = AtimeArray;
            totaltime[1] = BtimeArray;

            st = new StringTokenizer(br.readLine());
            for (int t = 0; t < time; t++) {
                AtimeArray[t] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for (int t = 0; t < time; t++) {
                BtimeArray[t] = Integer.parseInt(st.nextToken());
            }
            bcArrayList = new ArrayList<>();
            for (int bc = 0; bc < bcCount; bc++) {
                st = new StringTokenizer(br.readLine());
                int row = Integer.parseInt(st.nextToken());
                int col = Integer.parseInt(st.nextToken());
                int cover = Integer.parseInt(st.nextToken());
                int power = Integer.parseInt(st.nextToken());
                bcArrayList.add(new Bc(row, col, cover, power));
            }
            System.out.println(bcArrayList);
            move();
        }

    }

    // 움직이면서 거리에 들어오면 객체에 피플 업 안들어오면 피플다운
    // ab 동시에 같은시간 움직인다.
    static void move() {
        int[][] deCode = {{0, 0}, {0, -1}, {1, 0}, {0, 1}, {-1, 0}};
        // 시간동안 움직이면서 거리에 들어오는지 않들어오는지 판단
        // 경쟁은 두명이서 한다 이기거나 지거나 Max 로 비교해 큰놈 찾아간다..
        System.out.println(totaltime[0].length);// 체크할지점
        for (int t = 0, size = totaltime[0].length; t < size; t++) {
            A.currI += deCode[totaltime[0][t]][1];
            A.currJ += deCode[totaltime[0][t]][0];
            B.currI += deCode[totaltime[1][t]][1];
            B.currJ += deCode[totaltime[1][t]][0];
            //bc 를 돌면선 체크해라
            //합을 구하는거다

        }
    }

    // 무선충전기에 들어왔는지 확인하는 함수
    public static boolean isIn(Bc bc, Person p) {
        int dist = Math.abs(p.currI - bc.i) + Math.abs(p.currJ - bc.j);
        if(bc.cover >= dist ){
            return true;
        }
        return false;
    }

}












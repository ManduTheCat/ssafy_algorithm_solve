package week5.day5.BJ15686;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;


/**
 * 집별 좌표와 출력 클래스
 */
class Home {
    public int i;
    public int j;
    public int dist;

    Home(int i, int j) {
        this.i = i;
        this.j = j;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Home{");
        sb.append("i=").append(i);
        sb.append(", j=").append(j);
        sb.append(", dist=").append(dist);
        sb.append('}');
        return sb.toString();
    }
}

/**
 * 치킨집 좌표와 출력
 */
class Chicken {
    public int i;
    public int j;

    Chicken(int i, int j) {
        this.i = i;
        this.j = j;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Chicken{");
        sb.append("i=").append(i);
        sb.append(", j=").append(j);
        sb.append('}');
        return sb.toString();
    }
}

/**
 * @author 김명진
 * 가능한 치킨집 조합을 만들고 -> 거리를 계산-> 집에다 할당->  전채합을 ArrayList 에 기록 -> 기록중 최소값 출력
 * 35700KB	336ms
 */
public class Main {
    static int N;
    static int M;
    static ArrayList<Home> homes;// 집 들 정보를 담은리스트
    static int[] chickenIndexList; // 치킨 집의 인덱스를 담아놓은 리스트 역기서 조합을 구합니다.
    static int[] chickenRes; // 치킨집뽑는 경우의 수를 담은 리스트
    static ArrayList<Integer> distSumList; // 거리의 합들을 기록하는 리스트
    static ArrayList<Chicken> chickens; // 치킨정보를 담은 리스트

    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("resources/week5/day5/BJ15686/input4.txt"));
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        homes = new ArrayList<>();
        chickens = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer rowSt = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) {
                int kind = Integer.parseInt(rowSt.nextToken());

                if (kind == 1) {
                    homes.add(new Home(i, j));
                } else if (kind == 2) {
                    chickens.add(new Chicken(i, j));
                }
            }
        }

        chickenIndexList = new int[chickens.size()];
        chickenRes = new int[M];
        distSumList = new ArrayList<>();
        // 순서 조합 구하기 -> dist 계산
        combi(0, 0);
        System.out.println(Collections.min(distSumList));

    }

    static void allocateDist(ArrayList<Chicken> inputChickens) {
        int sum = 0;
        for (Home h : homes) {
            ArrayList<Integer> min = new ArrayList<>();
            for (Chicken c : inputChickens) {
                min.add(Math.abs(h.i - c.i) + Math.abs(h.j - c.j));
            }
            h.dist = Collections.min(min);
            sum += h.dist;
        }
        distSumList.add(sum);

    }

    // 치킨 리스트에서 뽑는다
    static void combi(int start, int depth) {
        if (depth == M) {
            //System.out.println(Arrays.toString(chickenRes));
            ArrayList<Chicken> combiRes = new ArrayList<>();
            for (int index : chickenRes) {
                combiRes.add(chickens.get(index));
            }
            allocateDist(combiRes);
            return;
        }

        int size = chickenIndexList.length;
        for (int i = start; i < size; i++) {
            chickenRes[depth] = i;
            combi(i + 1, depth + 1);
        }
    }
}

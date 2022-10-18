package algorithmStudy.week10.mineSweaper9082;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class IndexAndDouble implements Comparable<IndexAndDouble> {
    int idx;
    double prop;

    public IndexAndDouble(int idx, double prop) {
        this.idx = idx;
        this.prop = prop;
    }

    @Override
    public String toString() {
        return "IndexAndDouble{" +
                "idx=" + idx +
                ", prop=" + prop +
                '}';
    }

    @Override
    public int compareTo(IndexAndDouble o) {
        return Double.compare(o.prop, this.prop);
    }
}

public class Main {
    static int Tc;
    static int N;
    static double[] warrnings;
    static String hides;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/study/week10/bj9082/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Tc = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < Tc; tc++) {
            N = Integer.parseInt(br.readLine());
            warrnings = new double[N];
            String rowLine = br.readLine();
            for (int col = 0; col < N; col++) {
                warrnings[col] = rowLine.charAt(col) - '0';
            }
            hides = br.readLine();
            double[][] fractions = new double[N][N];
            // 양쪽 끝 처리
            for (int i = 0; i < 2; i++) {
                if (warrnings[0] != 0) fractions[0][i] = warrnings[0] / 2;
                else fractions[0][i] = 0;
            }
            for (int i = N - 2; i < N; i++) {
                if (warrnings[N - 1] != 0) fractions[N - 1][i] = warrnings[N - 1] / 2;
                else fractions[N - 1][i] = 0;
            }
            for (int i = 1; i < N - 1; i++) {
                if (warrnings[i] != 0) {
                    fractions[i][i - 1] = warrnings[i] / 3;
                    fractions[i][i] = warrnings[i] / 3;
                    fractions[i][i + 1] = warrnings[i] / 3;
                }
            }
            printFraction(fractions);
            double[] props = new double[N];
            for (int col = 0; col < N; col++) {
                double sum = 0;
                for (int row = 0; row < N; row++) {
                    sum += fractions[row][col];
                }
                props[col] = sum;
            }
            System.out.println(Arrays.toString(props));
            PriorityQueue<IndexAndDouble> pq = new PriorityQueue<>();
            boolean[] resMin = new boolean[N];
            for (int idx = 0; idx < N; idx++) {
                pq.add(new IndexAndDouble(idx, props[idx]));
            }
            while (!pq.isEmpty()){
                IndexAndDouble curr= pq.poll();
                resMin[curr.idx] = true;
                System.out.println(isValid(resMin));
            }
        }
    }

    public static boolean isValid(boolean[] resMin){
        //0 번 확인
        int firstCount = 0;
        int middleCount = 0;
        int endCount = 0;
        for (int idx = 0; idx < 2; idx++) {
            if(resMin[idx])firstCount++;
        }
        if(firstCount != warrnings[0]){
            return false;
        }
        for (int idx = 1; idx < N-1; idx++) {
            // 3개씩 확인
            middleCount = 0;
            if(resMin[idx -1])middleCount++;
            if(resMin[idx])middleCount++;
            if(resMin[idx + 1])middleCount++;
            if(middleCount != warrnings[idx]) return false;
        }
        for (int idx = N-1; idx < N-3; idx++) {
            if(resMin[idx])endCount++;
        }
        if(endCount != warrnings[0]){
            return false;
        }

        return true;
    }
    public static void printFraction(double[][] fraction) {
        for (double[] row : fraction) {
            for (double col : row) {
                System.out.printf("%.2f ", col);
            }
            System.out.println();
        }
        System.out.println();
    }

}

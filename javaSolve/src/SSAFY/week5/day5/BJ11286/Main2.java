package SSAFY.week5.day5.BJ11286;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

class Num implements Comparable<Num> {
    int idx;
    int val;

    public Num(int idx, int val) {
        this.idx = idx;
        this.val = val;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    @Override
    public int compareTo(Num o) {
        if (Math.abs(this.val) == Math.abs(o.getVal())) {
            return this.val - o.getVal();
        }
        return Math.abs(this.val) - Math.abs(o.getVal());
    }

}
public class Main2 {


    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("resources/SSAFY.week5/day5/BJ11286/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Num> pq = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        for (int i = 0; i < n; i++) {
            if (arr[i] == 0) {
                Num item = pq.poll();
                if (item == null) {
                    System.out.println(0);
                    continue;
                }
                sb.append(item.getVal()).append("\n");
            } else {
                pq.add(new Num(i, arr[i]));
            }

        }
    }

}
package week6.day1.JOL1828;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Stuff implements Comparable<Stuff> {
    int minTemp;
    int maxTemp;

    public Stuff(int minTemp, int maxTemp) {
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(minTemp).append(" ").append(maxTemp).append(" ");
        return sb.toString();
    }

    @Override
    public int compareTo(Stuff o) {
        return Integer.valueOf(this.maxTemp).compareTo(Integer.valueOf(o.maxTemp));
    }
}

public class Main {
    static int n;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/week6/JOL1828/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        List<Stuff> stuffs = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            stuffs.add(new Stuff(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        int count = 1;
        int size = stuffs.size();
        Collections.sort(stuffs);
        //System.out.println(stuffs);
        int standardTemp = stuffs.get(0).maxTemp;
        for (int i = 1; i < size; i++) {
            if (standardTemp < stuffs.get(i).minTemp) {
                count++;
                standardTemp = stuffs.get(i).maxTemp;
            }
        }
        System.out.println(count);

    }

}

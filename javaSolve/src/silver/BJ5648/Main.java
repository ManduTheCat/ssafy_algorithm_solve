package silver.BJ5648;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        List<String> input = new ArrayList<>();
        int count = 0;
        while (st.hasMoreTokens()) {
            input.add(st.nextToken());
            count++;

        }
        while (count < N) {
            st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()) {
                String val = st.nextToken();

                input.add(val);
                count++;


            }
        }
        PriorityQueue<Long> sort = new PriorityQueue<>();
        for (String val : input) {
            StringBuilder sb = new StringBuilder(val);
            sort.offer(Long.parseLong(sb.reverse().toString()));
        }
        while (!sort.isEmpty()) {
            System.out.println(sort.poll());
        }
    }
}

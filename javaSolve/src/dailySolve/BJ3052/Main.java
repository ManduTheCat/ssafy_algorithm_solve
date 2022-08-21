package dailySolve.BJ3052;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/daily/Bj3052/input2.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Set<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < 10 ; i++) {
            int num = Integer.parseInt(br.readLine());
            set.add(num % 42);
        }
        System.out.println(set.size());


    }
}

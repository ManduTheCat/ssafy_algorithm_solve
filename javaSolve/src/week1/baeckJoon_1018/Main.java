package week1.baeckJoon_1018;

import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int len =Integer.parseInt(bf.readLine());
        int [] inputArr = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(inputArr);
        System.out.printf("%d %d", inputArr[0], inputArr[len-1]);
    }
}

package week6.day1.BJ2839;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        int fiveCount = 0;
        int threeCount = 0;
        while (n > 0){

            if((n % 5 != 0)) {
                threeCount++;
                n-=3;
            }
            else {
                fiveCount = n/5;
                n %=5;
                break;
            }

        }
        if (n < 0){
            System.out.println(-1);
        }
        else {
            System.out.println(fiveCount+ threeCount);
        }

    }
}

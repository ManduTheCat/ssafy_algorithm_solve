package silver.BJ1439Flip;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        String [] zeroGroup = input.split("1");
        String [] oneGroup = input.split("0");
        int zeroCount = 0;
        int oneCount = 0;
        for(String el : zeroGroup){
            if(!el.equals("")){

                zeroCount++;
            }
        }

        for(String el : oneGroup){
            if(!el.equals("")){

                oneCount++;
            }
        }
        System.out.println(Math.min(zeroCount,oneCount));
    }
}

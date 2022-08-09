package week5.day1.BJ1158;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    //링크드 리스트가 더빠르다.
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        long sTime = System.nanoTime();//jvm 기반
        ArrayDeque<Integer> dq = new ArrayDeque<>();
        for(int i = 1; i<= N; i++){
            dq.addLast(i);
        }
        ArrayList<Integer> res = new ArrayList<>();
        while(!dq.isEmpty()){
            for(int i = 0; i< M-1; i++){

                dq.addLast(dq.pop());
            }
            res.add(dq.pop());
        }
        System.out.print('<');
        for(int i = 0 ;  i< res.size()-1; i++){
            System.out.printf("%d, ", res.get(i));
        }
        System.out.printf("%d>", res.get(res.size()-1));
        long eTime = System.nanoTime();
        long resTime = (eTime - sTime)/1000000000;
        System.out.println(resTime);
    }
}

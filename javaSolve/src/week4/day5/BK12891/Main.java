package week4.day5.BK12891;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    public static String []  pickList;
    public static int Acount, Ccount, Gcount, Tcount, P, S ;
    public static void main(String[] args) throws IOException {
        // 중복 순열로 경우의 수를 구하면서
        // 입력된 규칙을 만족할때
        // 카운트 증가
        System.setIn(new FileInputStream("resources/BJ12891/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        String [] dnaList = br.readLine().split("");
        StringTokenizer count = new StringTokenizer(br.readLine());
        Acount = Integer.parseInt(count.nextToken());
        Ccount = Integer.parseInt(count.nextToken());
        Gcount = Integer.parseInt(count.nextToken());
        Tcount = Integer.parseInt(count.nextToken());
        pickList = new String[P];
        System.out.println(P);

        int countVaild = 0;
        for(int i = 0; i < dnaList.length-P; i++){
            countVaild+=slide(i, dnaList);
        }
    // 결과에 있는 숫자만 검사하기로 하자
    }
    static int slide (int start, String [] dnaList){
        HashMap<String ,Integer> map = new HashMap<String, Integer>();
        for(int i = start; i < start + P; i++ ){
            System.out.printf("%s", dnaList[i]);
            map.put(dnaList[i],map.get(dnaList[i])+1);
        }

        return 0;
    }
}

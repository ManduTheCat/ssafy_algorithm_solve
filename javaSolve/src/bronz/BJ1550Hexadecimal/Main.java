package bronz.BJ1550Hexadecimal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String hex = br.readLine();
        HashMap<Character, Integer> hexMap = new HashMap<>();
        hexMap.put('0',0);
        hexMap.put('1',1);
        hexMap.put('2',2);
        hexMap.put('3',3);
        hexMap.put('4',4);
        hexMap.put('5',5);
        hexMap.put('6',6);
        hexMap.put('7',7);
        hexMap.put('8',8);
        hexMap.put('9',9);
        hexMap.put('A',10);
        hexMap.put('B',11);
        hexMap.put('C',12);
        hexMap.put('D',13);
        hexMap.put('E',14);
        hexMap.put('F',15);
        int res = 0;
        int cur = 0;
        for(int idx = hex.length()-1; idx >=0; idx--){
            char num = hex.charAt(idx);
            int temp = (int) (hexMap.get(num) * Math.pow(16, cur++));
            res +=temp;
        }
        System.out.println(res);

    }
}

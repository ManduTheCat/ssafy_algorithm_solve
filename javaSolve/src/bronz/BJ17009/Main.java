package bronz.BJ17009;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int a = 0;
        int point = 3;
        for(int i  = 0; i < 3; i++){
            a += point * Integer.parseInt(br.readLine());
            point--;
        }
        int b = 0;
        point = 3;
        for(int i  = 0; i < 3; i++){
            b += point * Integer.parseInt(br.readLine());
            point--;
        }
        if(a == b){
            System.out.println("T");
        } else if (a > b) {
            System.out.println("A");
        }else {
            System.out.println("B");
        }
    }
}

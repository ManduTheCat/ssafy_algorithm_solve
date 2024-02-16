package bronz.BJ2789;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        Set<Character> van  = new HashSet<>();
        van.add('C');
        van.add('A');
        van.add('M');
        van.add('B');
        van.add('R');
        van.add('I');
        van.add('D');
        van.add('G');
        van.add('E');
        StringBuilder sb = new StringBuilder();
        for(int i = 0;  i < input.length(); i++){
           if(!van.contains(input.charAt(i))){
               sb.append(input.charAt(i));
           }
        }
        System.out.println(sb);
    }
}

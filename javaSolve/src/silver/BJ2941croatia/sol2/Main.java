package silver.BJ2941croatia.sol2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws IOException {
        String[] croatias = {"c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z="};

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        for(String val :croatias){
            input = input.replace(val, "0");
        }
        System.out.println(input.length());

    }
}

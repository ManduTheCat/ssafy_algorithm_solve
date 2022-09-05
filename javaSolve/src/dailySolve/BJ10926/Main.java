package dailySolve.BJ10926;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        StringBuilder sb = new StringBuilder();
        sb.append(input).append("?").append("?").append("!").append("\n");
        System.out.println(sb);
    }
}

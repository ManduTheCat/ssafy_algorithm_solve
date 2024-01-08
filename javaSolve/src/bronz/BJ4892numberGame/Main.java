package bronz.BJ4892numberGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
    public static void main(String[] args) throws IOException {
        int input;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int count = 1;
        StringBuilder sb = new StringBuilder();
        while (true) {
            input = Integer.parseInt(br.readLine());
            if (input == 0) {
                break;
            }
            sb.append(count++);
            sb.append(". ");
            sb.append(getRes(input));
            sb.append("\n");

        }
        System.out.println(sb);


    }

    public static String getRes(int input) {
        int n1 = 3 * input;
        int n2 = 0;
        if (n1 % 2 == 0) {
            n2 = n1 / 2;
        } else {
            n2 = (n1 + 1) / 2;
        }
        int n3 = n2 * 3;
        int n4 = n3 / 9;
        StringBuilder sb = new StringBuilder();
        if (n4 * 2 == input) {
            sb.append("even");
            sb.append(" ");
            sb.append(n4);
            return sb.toString();
        } else if (2 * n4 + 1 == input) {
            sb.append("odd");
            sb.append(" ");
            sb.append(n4);
            return sb.toString();
        }

        return null;
    }
}

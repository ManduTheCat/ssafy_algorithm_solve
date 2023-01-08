package dailySolve.greedy.BJ2932TableTurn.secSol;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int K;
    static int startNum;
    static int targetRow;
    static int targetCol;
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/daily/greedy/Bj2932/input2.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        int [] rowArr;
        int [] colArr;
    }
}

package week6.day1.BJ1074;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int r;
    static int c;
    static int count;

    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("resources/week6/BJ1074/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        //전체 길이는 2^n
        int size = (int) Math.pow(2, n);
        searchZ(0, 0, size);

    }

    static void searchZ(int i, int j, int size) {
        if (i == r && j == c) {
            System.out.println(count);
            return;
        }
        // 타겟 사분면에 존재하면 분할 재귀 시작
        if (check(i, j, size)) {
            //왼위
            searchZ(i, j, size / 2);
            // 오른위
            searchZ(i, j + size / 2, size / 2);
            // 왼아래
            searchZ(i + size / 2, j, size / 2);
            // 오른 아래
            searchZ(i + size / 2, j + size / 2, size / 2);

        } else count+= size * size; // 도만큼 인덱스 증가 한다.

    }

    // 현제 진입한 사분면 에 타겟이 존재하는지 체크
    static boolean check(int i, int j, int size) {
        if (r < i + size && i <= r && j <= c && c < j + size) {
            return true;
        }
        return false;
    }
}

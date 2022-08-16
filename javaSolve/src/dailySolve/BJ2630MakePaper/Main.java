package dailySolve.BJ2630MakePaper;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] map;
    static int endDepth;
    static int zeroCount;
    static int oneCount;

    // 구역안에 1로 가득 차있지 않으면 분할정복시도
    // 가득차있다면 네모갯수+1
    // log2(n) 만큼 이동했으면 끝에 닿은걸로 1의 갯수를 샌다.
    // 2^n = 8
    //N은 2, 4, 8, 16, 32, 64, 128 중하나
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/daily/BJ_2630/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        // 최대 깊이 계산
        endDepth = (int) (Math.log(n) / Math.log(2));
        // map 할당
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        countingSquire(0, 0, n, 0);
        System.out.println(zeroCount);
        System.out.println(oneCount);
    }

    static void countingSquire(int i, int j, int size, int depth) {
        // 끝에 닿았다면
        if (depth == endDepth) {
            if (checkFull(i, j, size)) {
                for (int row = i; row < i + size; row++) {
                    for (int col = j; col < j + size; col++) {
                        if (map[row][col] == 1) {
                            oneCount++;
                        } else {
                            zeroCount++;
                        }
                    }

                }
            }
            return;
        }
        // 완전 차있는 네모가 아니라면 쪼개라
        if (!checkFull(i, j, size)) {
            // 위왼
            countingSquire(i, j, size / 2, depth + 1);
            // 위 오른
            countingSquire(i, j + size / 2, size / 2, depth + 1);
            // 아래 왼쪽
            countingSquire(i + size / 2, j, size / 2, depth + 1);
            // 아래 오른쪽
            countingSquire(i + size / 2, j + size / 2, size / 2, depth + 1);

        }
        // 완전 차있는 네모라면 else 수행
        // 0으로 차있는 네모와 1로 차 있는 네모로 구분해야한다.
        // 꽉차 있다면 하나만 봐도 뭔지 안다
        else {
            if (map[i][j] == 1) {
                oneCount++;
            } else {
                zeroCount++;
            }
        }

    }

    // 0 또는 1의 갯수를 새서 size**2  과 일치 하지 않으면 꽉찬게 아니다
    static boolean checkFull(int i, int j, int size) {
        int countOne = 0;
        int countZero = 0;
        for (int row = i; row < i + size; row++) {
            for (int col = j; col < j + size; col++) {
                if (map[row][col] == 1) {
                    countOne++;
                } else {
                    countZero++;
                }
            }
        }
        return countZero == size * size || countOne == size * size;
    }
}

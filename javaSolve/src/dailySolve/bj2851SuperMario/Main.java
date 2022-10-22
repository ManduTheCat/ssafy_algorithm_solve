package dailySolve.bj2851SuperMario;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int sum = 0;
        int res = 0;
        for (int i = 0; i < 10; i++) {
            int num = Integer.parseInt(br.readLine());

            // 100과
            // 차가 작으면 갱신하자
            if (sum < 100) {
                sum += num;
                if (Math.abs(res - 100) > Math.abs(sum - 100)) {
                    res = sum;
                } else if (Math.abs(res - 100) == Math.abs(sum - 100)) {
                    res = res > sum ? res : sum;
                }
            }
        }
        System.out.println(res);
    }


}

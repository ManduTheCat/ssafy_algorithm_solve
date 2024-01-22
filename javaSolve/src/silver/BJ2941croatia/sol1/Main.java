package silver.BJ2941croatia.sol1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        Set<String> croatia = new HashSet<>();
        croatia.add("c=");
        croatia.add("c-");
        croatia.add("dz=");
        croatia.add("d-");
        croatia.add("lj");
        croatia.add("nj");
        croatia.add("s=");
        croatia.add("z=");

        int count = input.length(); // 전체 갯수에서 뺴면 조합 알파벳또한 알수 있다
        int idx = 0;
        while (idx < input.length()-1) { // 3개짜리가 z 와 겹치는 경우가 생긴다.
            if (idx < input.length() - 2) { // 덩어리 3개짜리 부터 체크 한다
                String longChunk = input.substring(idx, idx + 3);
                if (croatia.contains(longChunk)) {
                    count -= 2;
                    idx += 3;
                    continue;
                }
            }
            String chunk = input.substring(idx, idx + 2);
            if (croatia.contains(chunk)) { // 2개짜리 확인
                count -= 1;
                idx += 2;
            } else {
                idx++;
            }


        }
        System.out.println(count);
    }
}

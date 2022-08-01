package baeckjoon_1244;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ_1244_스위치켜고끄기_김명진 {
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/1244input/input.txt"));
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int switchLen = Integer.parseInt(bf.readLine());
        int[] switchs = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int studentCount = Integer.parseInt(bf.readLine());
        for (int st = 0; st < studentCount; st++) {
            int[] students = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            /**
             * 성별과 시작 index 추출
             */
            int switchIndex = students[1];
            int gender = students[0];
            /**
             * 성별로 처리
             */
            if (gender == 1) {
                processBoy(switchs, switchIndex);
            } else if (gender == 2) {
                processGirl(switchs, switchIndex);
            }
        }
        for (int i = 0; i < switchLen; i++) {
            System.out.printf("%d ", switchs[i]);
            if ((i + 1) % 20 == 0) {
                System.out.println();
            }
        }

    }

    /**
     * 남자일때 베수 별로 반대 숫자로 처리
     * @param input 전체 스위치 배열
     * @param index 시작 인덱스
     */
    public static void processBoy(int[] input, int index) {
        int len = input.length;
        for (int i = 0; i < len; i++) {
            if ((i + 1) % index == 0) {
                input[i] = input[i] == 1 ? 0 : 1;
            }
        }
    }

    /**
     * 여자일때 처리
     * @param input 전체 스위치 리스트
     * @param index 출발 인덱스
     */

    public static void processGirl(int[] input, int index) {
        int len = input.length;
        index--;
        /**
         * 1. 출발 자리 반대 숫자로 처리
         * 2. for 문을 돌며 양쪽으로 이동하면서 갑변화 만약 범위를 넘거나  같이 않으면 종료한다.
         */
        input[index] = input[index] == 0 ? 1 : 0;
        for (int j = 1; j < len; j++) {
            if (index + j >= len || index- j < 0)
                break;
            if (input[index - j] == input[index + j]) {
                input[index - j] = input[index - j] == 0 ? 1 : 0;
                input[index + j] = input[index + j] == 0 ? 1 : 0;
            } else break;
        }
    }
}

package SSAFY.week4.day5.BK2493;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

/**
 * 풀이 조건 1.5 초 128 MB => 풀이 결과 121.88Mb	976ms
 * 가장 최근의 값과 비교를 해야하는 문제라 생각이 들어
 * stack 을 활용해 풀었습니다.
 * 입력을 거꾸로 진행하면서
 * 값을 스텍에 넣고 다음입력과 비교하며 크면은 통신이 가능한 빌딩이라판단
 * 작거나 같으면 통신이 불가능하니 스텍에 쌓아 대기시키고 그다음값을 비교하는 방식입니다.
 * @author  김명진
 *
 */
public class Main {
    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("resources/SSAFY.week4/day5/BK2493/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int inputLen = Integer.parseInt(br.readLine());
        // 입력 전체를 저장한 array
        int[] buildingHeight = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] result = new int[inputLen]; //결과 array 인덱스별로 바라보는 탑의 index 를 기록
        Stack<Integer> ready = new Stack<>(); // 다음 탑을 비교하기위해 담아놓는 스택 만약 다음탑이 작거나 같으면 add() 그다음 탑을 본다.
        for (int index = buildingHeight.length - 1; index >= 0; index--) {
            if (ready.empty()) {
                // 비어 있다면 현제 index 를 ready 스텍에 넣어라
                ready.add(index);
            } else {
                // 비어 있지 않는 경우
                // 크거나 같을때까지 반복해라
                while (!ready.empty() && buildingHeight[ready.peek()] <= buildingHeight[index]) {
                    // 현재 빌딩 값이 ready 스텍에 있는 값보다 클경우 ready peek 이 바라보는 빌딩을 찾았다는 의미
                    // pop 을하여 처리를 완료한다.
                    if (buildingHeight[index] > buildingHeight[ready.peek()]) {
                        result[ready.pop()] = index + 1;
                    }
                }
                // 빌딩값이 작거나 같은경우 스텍에 담아 다음 값을 비교할 준비한다
                ready.add(index);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int el : result) {
            sb.append(el).append(" ");
        }
        System.out.println(sb);
    }
}

package SSAFY.week1.beackjoon_17478;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(bf.readLine());

        System.out.println("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.");
        recursive(count, 0);
        System.out.println("라고 답변하였지.");
    }

    /**
     * 1. 재귀를 통해 같은 내용의 질문 과 대답 출력
     * 2. 재귀가 해재 되면서 "라고 답변 했지" 출력
     * 3. 재귀 깊이에 따라 출력앞에 밑줄이 출력된다
     * 
     * @param count 재귀의 탈출을 위한 카운트 입력 횟수만큼 재귀를 반복하게 하는역활 재귀반복할수록 감소
     *              count 가 0 이되면 재귀를 탈출한다
     * @param time 재귀의 회수에 따라 밑줄 갯수를 늘려주는 파라미터 재귀 반복할수록 증가
     */
    public static void recursive(int count, int time){
        uderline(time);
        System.out.println("\"재귀함수가 뭔가요?\"");
        if(count ==0){
            uderline(time);
            System.out.println("\"재귀함수는 자기 자신을 호출하는 함수라네\"");
            return;
        }
        uderline(time);
        System.out.println("\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.");
        uderline(time);
        System.out.println("마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.");
        uderline(time);
        System.out.println("그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"");
        recursive(--count, ++time);
        uderline(time);
        System.out.println("라고 답변하였지.");
    }

    /**
     * 재귀의 깊이만큼 출력앞에 밑줄을 출력하기 위한 함수
     * @param time 입력인자는 재귀 횟수만큼 증가한수이다.
     */
    private static void uderline(int time) {
        for(int i = 0; i< time; i++){
            System.out.print("____");
        }
    }
}

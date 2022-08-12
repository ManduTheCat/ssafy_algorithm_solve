package week5.day5.BJ11286;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author 김명진
 * PriorityQueue 에 Comparator 를 재정의 하여 문제의 요구사항 반영
 * 절대값을 우선검사하여 비교하고
 * 만약절대값 이 같으면 숫자비교
 * 53408KB 328ms
 */
public class Main {
    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("resources/week5/day5/BJ11286/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> {
            if(Integer.valueOf(Math.abs(o1)).equals(Math.abs(o2))){
                return o1.compareTo(o2);
            }
            return Integer.compare(Math.abs(o1), Math.abs(o2));
        });
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++){
            int command = Integer.parseInt(br.readLine());
            if(command == 0){
                if(pq.size() == 0) sb.append("0").append("\n");
                else sb.append(pq.poll()).append("\n");
            }
            else pq.offer(command);
        }
        System.out.println(sb);
    }
}

package algorithmStudy.week10.schoolBusBJ2513;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int  N,K,S;
    static int[] map = new int[100_000];
    static int sum = 0;
    static boolean[] check;
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/study/week10/BJ2513/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 지점수
        K = Integer.parseInt(st.nextToken()); // 인원수
        S = Integer.parseInt(st.nextToken()); // 학교위치
        map[S] = -1;// 학교 위치
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            map[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
        }
        printMap();
        // 학교기준 왼쪽 최적 거리구한다.


    }
    public static void findLeft(){
        // 나눠떨어지면 몪만큼 왔다 갔다 해야한다.
        // 학교에서 왼쪽 보기
        for (int i = 0; i < S ; i++) {
            if (map[i] % K ==  0){
                int repeatCount = map[i] / K;
                int dist = Math.abs(map[i] - S);
                sum += dist;
                check[i] = true;
            }
        }
        for (int i = 0; i < S; i++) {
            if(!check[i]){
                // 아까 검사해서 걸러네는게 아니라면
                // 나눠떨어지는게아니다는 소리다
                // 몪이 2 이상이면 두번 이상가는곳이다 

            }
            
        }

    }
    public static void printMap(){
        for (int i = 0; i < map.length; i++) {
            System.out.print(map[i] > 0? map[i] +" " :"");
        }
        System.out.println();
    }
}

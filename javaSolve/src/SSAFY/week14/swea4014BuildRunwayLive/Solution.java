package SSAFY.week14.swea4014BuildRunwayLive;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Pattern {
    int type, freq;

    public Pattern(int type, int freq) {
        this.type = type;
        this.freq = freq;
    }
}

public class Solution {
    static int Tc, N, M;
    static int[][] map;
    static int[][] map2;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/SSAFY.week14/swea4014/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Tc = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < Tc; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken()); // 활주로 길이
            map = new int[N][N];
            map2 = new int[N][N];
            for (int row = 0; row < N; row++) {
                st = new StringTokenizer(br.readLine());
                for (int col = 0; col < N; col++) {
                    int val  = Integer.parseInt(st.nextToken());
                    map[row][col] = val;
                    map2[col][row] = val;
                }
            }
            // map2수평 으로 돌리면 실제  수직 검사
            System.out.printf("#%d %d\n",tc+1,process());

        }
    }

    private static int process() {
        int count  = 0;

        for (int i = 0; i < N; i++) {
            if(makeRoad(map[i]))count++;
            if(makeRoad(map2[i]))count++;
        }
        return count;
    }
    // 해당 지형 정보로 활주로 건설이 가능하면 true 불가능 하면 false;
    private static boolean makeRoad(int [] road){
        int beforeHeight = road[0];
        int size = 0;
        int j = 0;
        while (j < N){
            if(beforeHeight == road[j]){ // 동일높이의 경우
                size++;
                j++;
            }else if(beforeHeight+1 == road[j]){//이전높이보다 1높음 : 오르막 경사로 설치체크
                if(size < M)return false; // x 미만이면 활주로 건설 불가
                beforeHeight++;
                size = 1;
                j++;
            }else if (beforeHeight -1 == road[j]){
                // 이전 높이보다 1작음
                int count = 0;
                for (int k = j; k < N; k++) {
                    if(road[k] != beforeHeight-1){
                        // 현재 높이가 이전보다 정확하게 1씩 차이나야한다.
                        // 안그럼 경사로 불가
                        return false;
                        
                    }
                    if(++count == M)break;// 최소길이 만족 반복문 빠져나온다
                }
                // 연속길이 만족해서 내려오거나 // 끝까지 만족해서 내려옴
                if(count < M){
                    // 끝까지 갔지만 연속실이는 보장하지 못한고옴
                    return  false;
                }
                beforeHeight--;
                size= 0;
                j+=M;
                
            }else {
                // 높이가 2이상차이
                return false;
            }
        }
        return true;

    }

}

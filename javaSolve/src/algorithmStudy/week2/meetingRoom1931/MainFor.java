package algorithmStudy.week2.meetingRoom1931;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MainFor {
    //회의의 시작시간과 끝나는 시간이 같을 수도 있다. 이 경우에는 시작하자마자 끝나는 것으로 생각하면 된다.
    //입력중에 같은 시간의회의가 존재할까?
    //
    static int n;

    static int max = 0;
    static int[][] roomList;


    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("resources/study/week2/bk1931/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        roomList = new int[n][2];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] time = new int[2];
            time[0] = Integer.parseInt(st.nextToken());
            time[1] = Integer.parseInt(st.nextToken());
            roomList[i] = time;
        }
        Arrays.sort(roomList, (o1, o2) -> {
            if (o1[1] == o2[1]) {
                return Integer.valueOf(o1[0]).compareTo(o2[0]);
            }
            return Integer.valueOf(o1[1]).compareTo(o2[1]);
        });
        //System.out.println(Arrays.deepToString(roomList));


        //그리디 ? - 활동 선택 문제
        // 시간이 가장 빨리 끝나는것 기준으로 탐색하면 ?
        int depth = 0;
        int currTime = 0;
        for(int i = 0 ; i < roomList.length; i++){
            if(currTime<= roomList[i][0] ){
                currTime = roomList[i][1];
                depth++;
            }
        }
        System.out.println(depth);
    }

}

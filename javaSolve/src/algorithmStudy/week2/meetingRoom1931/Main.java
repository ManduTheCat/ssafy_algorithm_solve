package algorithmStudy.week2.meetingRoom1931;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    //회의의 시작시간과 끝나는 시간이 같을 수도 있다. 이 경우에는 시작하자마자 끝나는 것으로 생각하면 된다.
    //입력중에 같은 시간의회의가 존재할까?
    //
    static int n;
    static ArrayList<Integer> [] adjList;
    static int max = 0;
    static int [][]  roomList;
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/study/week2/bk1931/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        roomList = new int[n][2];
        adjList = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int [] time = new int[2];
            time[0] = Integer.parseInt(st.nextToken());
            time[1] = Integer.parseInt(st.nextToken());
            roomList[i] = time;
            adjList[i] = new ArrayList<Integer>();
        }
        Arrays.sort(roomList, (o1, o2) -> Integer.valueOf(o1[0]).compareTo(o2[0]));
        makeAdjList();
        //System.out.println(Arrays.toString(adjList));
        for(ArrayList<Integer> nextList :adjList){
            dfs(nextList, 0);
        }
        System.out.println(max);
    }

    static void makeAdjList(){
        int size = roomList.length;
        for(int i = 0; i < size; i++){
            for(int j = i+1; j < size; j++){
                if(roomList[i][1] < roomList[j][0]){
                    adjList[i].add(j);
                }
            }
        }
    }
    // 노드별로 갈수 있는 경우의 수는 고정되어 있다 이걸 메모이제이션 처리 해야한다
    static void dfs(ArrayList<Integer> currNodeList, int depth){
        depth++;
        if(currNodeList.size() == 0){
            max = Math.max(max, depth);
            return;
        }
        // 현재 노드가갈수 있는 리스틀 돈다
        for(int nextIndex: currNodeList){
            dfs(adjList[nextIndex], depth);
        }
    }
}

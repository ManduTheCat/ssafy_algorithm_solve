package SSAFY.week5.day3.bk_17406;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int M;
    static int R;
    static int[][] map;
    static ArrayList<Integer[]> commandList;
    static boolean[] isVisited;
    static int [] permutationCommandArray;
    static ArrayList<Integer> resList;
    static  int[][] originMap;

    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("resources/SSAFY.week5/day3/bk17406/input.txt"));
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        originMap = new int[N][M];
        for (int row = 0; row < N; row++) {
            StringTokenizer rowTk = new StringTokenizer(bf.readLine());
            for (int col = 0; col < M; col++) {
                int el = Integer.parseInt(rowTk.nextToken());
                map[row][col] = el;
                originMap[row][col] = el;
            }
        }

        // map 인풋
        commandList = new ArrayList<>();
        for (int command = 0; command < R; command++) {
            // 3개 길이를 가지는 명령어 하나 할당
            StringTokenizer commandTk = new StringTokenizer(bf.readLine());
            commandList.add(new Integer[3]);
            for (int index = 0; index < 3; index++) {
                commandList.get(command)[index] = Integer.parseInt(commandTk.nextToken());
            }
        }

        //commandList 의 순열 구현
        isVisited = new boolean[commandList.size()];
        //조합
        resList = new ArrayList<>();
        //순열 돌면서 커맨드 실행
        permutationCommandArray = new int[commandList.size()];
        permutation(commandList.size(), 0);
        System.out.println(Collections.min(resList));


    }
    //최소값을 찾고 보관하는 결과용 배열리스트에 보관하는 메서드
    private static void calMin() {
        ArrayList<Integer> sumList = new ArrayList<>();
        for (int[] m : map) {
            int sum = 0;
            for (int el : m) {
                sum += el;
            }
            sumList.add(sum);
        }

        resList.add(Collections.min(sumList));
        //System.out.println(resList);
    }

    //순열 돌리며 명령실행
    //새로운 명령조합을 실행히려면 map 을 초기화시켜야한다.
    private static void permutation(int depth, int inputIdx) {
        if (depth == 0) {
            //System.out.println(Arrays.toString(permutationCommandArray));
            for(int commandIndex : permutationCommandArray){
                //System.out.println(Arrays.toString(commandList.get(commandIndex)));
                runCommand(commandList.get(commandIndex));
            }
            calMin();
            //map 다시 초기화 시켜줘야한다
            map = deepCopy();
            // 한경우의 수 나오면 재귀 종료
            return;
        }
        int size = commandList.size();
        for (int i = 0; i < size; i++) {
            if (!isVisited[i]) {
                isVisited[i] = true;
                permutationCommandArray[inputIdx] = i;
                permutation(depth-1, inputIdx+1);
                isVisited[i] = false;
            }
        }
    }
    //명령 파싱 해서
    private static void runCommand(Integer[] command) {
        int r = command[0] - 1;
        int c = command[1] - 1;
        int s = command[2];
        startArray(r, c, s);
    }

    // 시작지점과 끝점을 구해 돌리는 함수로 넘김 s 갯수만큼 시작점 이동 하여실행
    private static void startArray(int r, int c, int s) {
        for (int ds = 1; ds <= s; ds++) {
            int startR = r - ds;
            int startC = c - ds;
            int endR = r + ds;
            int endC = c + ds;
            //System.out.printf("startR: %d startC: %d ds: %d endR: %d endC: %d\n", startR, startC, ds, endR, endC);
            turn(startR, startC, endR, endC);
        }
    }

    // 시작점과 끝점을 활용해 배열을 돌림
    private static void turn(int startR, int startC, int endR, int endC) {
        // 위쪽 오른쪽 방향으로 이동
        // temp 로 맨 오른쪽이 짤리는걸 미리 저장 = 맨 오른쪽이 짤린다.
        // 땡겨오는 느낌으로 교환
        int upTemp = map[startR][endC];
        for (int c = endC; c > startC; c--) {
            // column 이 오른쪽으로 이동
            map[startR][c] = map[startR][c - 1];
        }

        // 오른쪽 의 아래방향으로 이동
        int leftTemp = map[endR][endC];
        // row 방향으로 땡겨오는 느낌으로 교환
        for (int r = endR; r > startR + 1; r--) {
            map[r][endC] = map[r - 1][endC];
        }
        // 이전에 씹현던것은 여기서 넣어줘야한다.
        map[startR + 1][endC] = upTemp;

        //아래 의 왼쪽 방향으로 이동
        int downTemp = map[endR][startC];
        for (int c = startC; c < endC - 1; c++) {
            //System.out.print(map[endR][c]);
            map[endR][c] = map[endR][c + 1];
        }
        map[endR][endC - 1] = leftTemp;

        for (int r = startR; r < endR - 1; r++) {
            map[r][startC] = map[r + 1][startC];
        }
        map[endR - 1][startC] = downTemp;

    }

    // map을 초기화 해주기 위한 이중리스트 카피
    public static int [][] deepCopy(){
        int [][] copy = new int[N][M];
        for(int i = 0;  i < N; i++){
            if (M >= 0) System.arraycopy(originMap[i], 0, copy[i], 0, M);
        }
        return copy;
    }
    
}

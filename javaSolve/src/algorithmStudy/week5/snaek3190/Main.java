package algorithmStudy.week5.snaek3190;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Command {
    int time;
    char direction;

    public Command(int time, char direction) {
        this.time = time;
        this.direction = direction;
    }

}

public class Main {
    static int N;
    static int K;
    static int L;
    static int count;
    static Set<Point> apples = new HashSet();
    static ArrayList<Command> commands;
    static Point curDirection;
    static Deque<Point> snake = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        //시작지점으로 부터 명령어를 수행
        // 명령어 변환필요
        // 사과담아놓을 set 필요
        // 머리가 이동하고 꼬리가 지워진다
        // 또는  사과를 먹으면 머리가 이동하고 꼬리가 남는다
        // 시간은 흐른다
        // 사과는 먹으면 없어져야한다.
        System.setIn(new FileInputStream("resources/study/week5/BJ3190/input3.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        for (int a = 0; a < K; a++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken()) - 1;
            int col = Integer.parseInt(st.nextToken()) - 1;
            apples.add(new Point(row, col));
        }
        // 1개이상 명령 무조건 있다.
        L = Integer.parseInt(br.readLine());
        commands = new ArrayList<>();
        for (int l = 0; l < L; l++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            char direction = st.nextToken().charAt(0);
            commands.add(new Command(time, direction));
        }
        // 명령어 처리
        if (commands.size() > 1) {
            for (int i = commands.size() - 1; i >= 1; i--) {
                commands.get(i).time -= commands.get(i - 1).time;
            }
        }

        snake.add(new Point(0, 0));
        curDirection = new Point(0, 1);
        for (Command command : commands) {
            runCommand(command);
        }
        // 마지막 명령을 수행하고 더 갈수 있으면 더가야한다.
        while (true){
            Point currHead = snake.peekLast();
            Point nextHead = new Point(currHead.x + curDirection.x, currHead.y+ curDirection.y);
            if(isIn(nextHead.x, nextHead.y) && !isTouch(nextHead.x, nextHead.y)){
                count++;
                snake.addLast(nextHead);
                if(!apples.contains(nextHead)){
                    snake.pollFirst();
                }
            }
            else {
                count++;
                break;
            }
        }
        System.out.println(count);
    }

    /**
     * 머리를 왼쪽 또는 오른쪽 명령에 따라 회전하는 함수
     * @param command 입력 받은 명령에 따라 회전한다.
     */
    public static void changeDirection(char command) {
        //사방중 하나 단지 머리를 돌리는 방향이 다르다
        // 오른쪽 일경우 돌리는 부분
        //System.out.println("in "+ curDirection.x +" , "+ curDirection.y);
        if (command == 'D') {
            int[][] alpha = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
            int curIndex = 0;
            for (int i = 0; i < 4; i++) {
                if (alpha[i][0] == curDirection.x && alpha[i][1] == curDirection.y) {
                    curIndex = i;
                }
            }
            int nextIndex = (curIndex + 1) % 4;
            //할당은 제대로 됬는데
            curDirection = new Point(alpha[nextIndex][0], alpha[nextIndex][1]);
            // 왼쪽일때 돌리는 부분
        } else if (command == 'L') {
            int[][] alpha = new int[][]{{0, 1}, {-1, 0}, {0, -1}, {1, 0}};
            int curIndex = 0;
            for (int i = 0; i < 4; i++) {
                if (alpha[i][0] == curDirection.x && alpha[i][1] == curDirection.y) {
                    curIndex = i;
                }
            }
            int nextIndex = (curIndex + 1) % 4;
            curDirection = new Point(alpha[nextIndex][0], alpha[nextIndex][1]);
        }
    }

    // 명령을 실행한다 시간동안 이동하고 방향 바꾸기
    // 1초 에 머리이동 -> 벽에 닿거나 내몸에 닿았다.->사과 판정 -> 사과없으면 꼬리 pop
    public static void runCommand(Command command) {
        // 다음 이동 가능하면 // isTouch 이상하다 추가를 하고 검사를 하니 무조건 걸린다.
        // 내의도 다음위치가 몸이라면 걸러라 // 지금 상황 다음머리를 큐에 넣고 검사해서 무조건 닿은걸로 판정
        // 검사를 하고 넣자
        int time = command.time;
        while (time-- > 0) {
            Point curHead = snake.peekLast();
            Point nextHead = new Point(curHead.x + curDirection.x, curHead.y + curDirection.y);
            if (isIn(nextHead.x, nextHead.y) && !isTouch(nextHead.x, nextHead.y)) {
                // 자기몸에 닿지 않고 맵안에 있다면 계속 진행
                snake.addLast(nextHead);
                count++;
                if (!apples.contains(nextHead)) {
                    snake.pollFirst();
                    //꼬리 제거
                }
                // 사과를 먹는경우
                else {
                    apples.remove(nextHead);
                }
            }
            else {
                count++;
                // 자기몸에 닿고 밖으로 나가면 시스탬 종료
                System.out.println(count);
                System.exit(0);
            }
        }
        // 시간이 되면 명령의 방향으로 바꾼다.
        changeDirection(command.direction);
    }


    public static boolean isIn(int row, int col) {
        return row >= 0 && row < N && col >= 0 && col < N;
    }

    // 닿음을 판단한 함수
    public static boolean isTouch(int row, int col) {
        for (Point body : snake) {
            if (body.x == row && body.y == col) {
                return true;
            }
        }
        return false;
    }
}

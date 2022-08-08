package algorithmStudy.week1.keylogger5397;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;;

interface MyStack {
    String pop();

    void push(String input);

    int size();

    String toRevers();
}

class MiniArrayStack implements MyStack {
    public String[] stack;
    private int currIndex;

    public MiniArrayStack(int N) {
        this.stack = new String[N];
        this.currIndex = 0;
    }

    @Override
    public String pop() {
        if (currIndex != 0) {
            return stack[--currIndex];
        }
        return "fail";
    }

    @Override
    public void push(String input) {
        stack[currIndex++] = input;
    }

    @Override
    public int size() {
        return currIndex;
    }

    @Override
    public String toString() {
        int len = this.stack.length;
        StringBuilder sb = new StringBuilder();
        for(int i = currIndex-1; i >=0; i-- ){
            sb.append(stack[i]);
        }
        return sb.reverse().toString();
    }
    @Override
    public String toRevers(){
        int len = this.stack.length;
        StringBuilder sb = new StringBuilder();
        for(int i = currIndex-1; i >=0; i-- ){
            sb.append(stack[i]);
        }
        return sb.toString();
    }
}

public class Main2 {
    // 1. 두개의 스택으로 커서의 이동을 표현
    // 2. 두개의 스택이 있고
    // 3. 첫번째 스텍은 커서의 왼쪽을 표현
    // 4. 첫번째 스택의 peek 은 현제 커서
    // 5. 첫번째 스텍의 입구로 새로들어온 입력
    // 6. 두번째 스텍은 커서의 오른쪽을 표현


    // > 연산자는 커서가 오른쪽으로 가니  두번째 스택의 값을pop해 첫번째 스텍에 넣음
    // < 연사자는 커서가 왼쪽으로 가니 첫번째 스텍의 pop 을 두번째 스택에 넣음
    // 연사자들은 모두 스택이 비어있으면 작동하면 안된다.
    // - 연산자는 첫번째 스택을 팝하여 처리한다.


    public static void main(String[] args) throws IOException {
        //System.setIn(Files.newInputStream(Paths.get("resources/study/week1/5397/input.txt")));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < TC; tc++) {
            String[] inputArray = br.readLine().split("");
            processKeyLogger(inputArray);
        }

    }

    public static void processKeyLogger(String[] input) {
        int N = input.length * 3;
        MyStack leftStack = new MiniArrayStack(N);
        MyStack rightStack = new MiniArrayStack(N);
        for (String command : input) {
            switch (command) {
                case "<":
                    if (!(leftStack.size() == 0)) {
                        rightStack.push(leftStack.pop());
                    }
                    break;
                case ">":
                    if (!(rightStack.size() == 0)) {
                        leftStack.push(rightStack.pop());
                    }
                    break;
                case "-":
                    if (!(leftStack.size() == 0)) {
                        leftStack.pop();
                    }
                    break;
                default:
                    leftStack.push(command);
                    break;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(leftStack.toString());
        sb.append(rightStack.toRevers());
        System.out.println(sb);
    }
}

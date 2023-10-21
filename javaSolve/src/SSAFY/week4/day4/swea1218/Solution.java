package SSAFY.week4.day4.swea1218;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class Solution {
    static  final int TC = 10;
    public static void main(String[] args)throws IOException {
        System.setIn(new FileInputStream("resources/1218Input/input.txt"));
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        for(int tc = 0; tc < TC; tc++){
            int len = Integer.parseInt(bf.readLine());
            StringBuffer buffer = new StringBuffer(bf.readLine());
            Stack<String> stack = new Stack<>();
            //정답을 구하는데는 상관없지만 스텍에 넣을때 순서를 유지하게 넣기위해 revers 처리하고 넣었습니다.
            //이러면 스택을 왜쓴거지?

            Arrays.stream(buffer.reverse().toString().split("")).forEach(stack::push);
            
            // 결과에따라 1(유효), 0(실패)
            if (!isRight(stack)) {
                System.out.printf("#%d %d\n", tc + 1, 1);
            } else {
                System.out.printf("#%d %d\n", tc + 1, 0);
            }

        }
    }
    /*
    * 테케가 짝수라면 처리 홀수면 failchek 하면 시간 축 가능
    * 테케의 시작이 닫힌괄호로 시작한다면 fail 가능
    * 스텍에 열리기호가 들어가게 하고 일치하는 닫힌기호 만나pop 하고 스텍에 남느거 있으면 fail
    *
    * */
    //스텍에 있는걸꺼내보며 괄호 종류 별로 체크
    // 0 { 1 [ 2 ( 3 <
    // 닫는 괄호는 -- 여는 괄호는 ++ 처리 해서 최종적으로 짝이 안맞으면 0이아닌 숫자가 count에 들어가게 됩니다.
    public static boolean isRight(Stack<String>stack){
        int [] count = new int[4];
        while (!stack.empty()){
            switch (stack.pop()){
                case("{") :
                    count[0]++;
                break;
                case("[") :
                    count[1]++;
                break;
                case("(") :
                    count[2]++;
                break;
                case("<") :
                    count[3]++;
                break;
                case("}") :
                    count[0]--;
                break;
                case("]") :
                    count[1]--;
                break;
                case(")") :
                    count[2]--;
                break;
                case(">") :
                    count[3]--;
                break;
            }

        }
        for(int c : count){
            if(c != 0) return false;
        }
        return true;
    }
}

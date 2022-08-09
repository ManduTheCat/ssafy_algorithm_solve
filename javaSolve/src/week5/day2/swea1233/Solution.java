package week5.day2.swea1233;

import java.io.*;


/**
 * @author 김명진
 * <pre>
 * 길이가 4개인 경우 노드의 내용이 연산자가 아니면 틀린식
 * 길이가 2개인 경우 노드의 내용이 숫가자 아니며 틀힌식
 * Charactro.isDigit 을 활용해 숫자 검사를 했습니다.
 * </pre>
 */
public class Solution {
    static int Tc = 10;

    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("resources/week5/day2/swea1223/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int tc = 0; tc < Tc; tc++) {
            int nodeCount = Integer.parseInt(br.readLine());
            boolean flag = false; // 주어진 식이 틀리면 flag true
            for (int nodeNum = 1; nodeNum <= nodeCount; nodeNum++) {
                String[] node = br.readLine().split(" ");
                //만약 길이가 4인데  indxe 1 값이 연산자가 아니면 sb 에 0 하고 flag true
                if (node.length == 4 && Character.isDigit(node[1].charAt(0))) {
                    //System.out.println(Arrays.toString(node));
                    flag = true;
                    //System.out.printf("#%d 0", tc+1);
                }
                //만약 길이가 2인데 index 1 의 값이 isDigit 아니면 sb 에 0 하고 flag true
                if (node.length == 2 && !Character.isDigit(node[1].charAt(0))) {
                    //System.out.println(Arrays.toString(node));
                    //System.out.printf("#%d 0", tc+1);
                    flag = true;
                }
            }
            if(flag){
                System.out.printf("#%d 0\n", tc+1);
            }
            else {
                System.out.printf("#%d 1\n", tc+1);
            }
        }
    }
}

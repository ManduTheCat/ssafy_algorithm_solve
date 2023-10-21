package SSAFY.week5.day1.swea1228;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    public static int TC = 1;
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/SSAFY.week5/day1/swea1228/input1.txt"));
        BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
        for(int tc = 0; tc < TC; tc++){
            int originLen = Integer.parseInt(br.readLine());
            ArrayList<Integer> inputList = new ArrayList<>();
            StringTokenizer stfirst = new StringTokenizer(br.readLine());
                for(int i = 0; i < originLen; i++){
                    inputList.add(Integer.parseInt(stfirst.nextToken()));
            }
            int resSize = inputList.size();
            int commandLen = Integer.parseInt(br.readLine());
            //I 1 5 400905 139831 966064 336948 119288
            StringTokenizer st = new StringTokenizer(br.readLine(), "I");

            for(int i = 0; i < commandLen; i++){
                String [] command = st.nextToken().trim().split(" ");

                encoding(inputList, command);
            }
            System.out.printf("#%d ",tc+1);
            inputList.subList(0,9).forEach(e-> System.out.printf("%d ", e));
            System.out.println();
        }
    }
    public static void encoding(ArrayList<Integer>originList, String [] command){
        System.out.println("command : "+Arrays.toString(command));
        int start = Integer.parseInt(command[0]);
        int to = Integer.parseInt(command[1]);
        int [] data = new int[command.length-2];
        int idx = 0;
        for(int i = 2; i < to+2; i++){
            data[idx++] =Integer.parseInt(command[i]);
        }
        System.out.println("data:" + Arrays.toString(data));

        int dataIndex = 0;
        System.out.println("start , to : "+ start+ " "+ to);
        for(int i = 0; i < to; i++){
//            if(i > originList.size()-1){
//                //System.out.println("i: " +i);
//                originList.add(data[dataIndex++]);
//            }
////            else if(start == 0){
////                originList.set(1, data[dataIndex++]);
////            }
//            else {
//                originList.set(i, data[dataIndex++]);
//            }
            originList.add(i+to,data[i]);
        }
        System.out.println("!!!!!!!!!!origin:: " + originList);
    }
}

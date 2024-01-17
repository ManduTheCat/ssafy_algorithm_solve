package silver.BJ2659;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int [] input = new int[4];
        for(int n = 0; n < 4; n++){
            input[n] = Integer.parseInt(st.nextToken());
        }
        int target = findMinClock(input);
        //System.out.println(target);
        int res = 0;
        Set<Integer> set= new HashSet<>();
        for(int comp = 1111; comp <=9999; comp++){
            int [] compArr = makeArr(comp);
            int compClock = findMinClock(compArr);
            //System.out.println(Arrays.toString(compArr));
            if(isInZero(compArr) || set.contains(compClock)){ // 0 이 있으면 넘어가고
                //System.out.println(Arrays.toString(compArr));// 카운트 하지 않는다.

                continue;
            }
            if (comp == target) {
                break;
            } else {
                set.add(comp);
                res++;
            }
            //System.out.println(Arrays.toString(compArr)+ " :"+ res);
        }
        System.out.println(res + 1);
    }

    static public boolean isInZero(int [] in){
        for(int val : in){
           if(val == 0){
               return true;
           }
        }
        return false;
    }
    static public int[] makeArr(int num){
        int ten = 1000;
        int[] res = new int[4];
        for(int i = 0;  i < 4; i++){
            res[i] = num/ten;
            num %=ten;
            ten /=10;

        }
        return res;
    }
    static public int findMinClock(int[] input){
        Deque<Integer> q = new ArrayDeque<>();
        for(int val : input){
            q.add(val);
        }
        int min = Integer.MAX_VALUE;
        for(int t = 0; t < 4; t++){
            int poll = q.poll();
            q.addLast(poll);
            int ten = 1000;
            int resNum = 0;
            for(int val :q){
                resNum += val * ten;
                ten/=10;
            }
           // System.out.println(resNum);
            min = Math.min(resNum, min);
        }

        return min;
    }
}

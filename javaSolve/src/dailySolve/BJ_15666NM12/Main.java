package dailySolve.BJ_15666NM12;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;
import java.util.StringTokenizer;


// 중복을 허용하는 permutation
public class Main {
    static int n;
    static int m;
    static int[] beatArray;
    static int [] inputArray;
    static int [] res;
    static Map <Integer, Boolean> isValueVisited;
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/daily/BJ_15666/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        inputArray = new int[n];
        st = new StringTokenizer(br.readLine(), " ");
        beatArray = new int[m];
        res = new int[m];
        for(int i = 0; i <  n; i++){
            inputArray[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(inputArray);
        System.out.println(Arrays.toString(inputArray));
        permutation(0,0);


    }
    static void permutation(int depth, int flag){
        if(depth == m){
            System.out.println(Arrays.toString(res));
            return;
        }
        for(int i = 0;  i< inputArray.length; i++){
            if((flag & 1 << i) == 0){
                res[depth] = inputArray[i];
                // 지금 나자신을 못가는 곳으로 마킹 한다
                // 하지만 내가아닌 나랑 같은 값을 못가게 해야한다
                // map.put(inputArray[i], true)
                permutation(depth+1, flag | 1 <<i);
            }
        }
    }
}

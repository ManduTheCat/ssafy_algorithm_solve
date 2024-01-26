package silver.BJ2784;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static char[][] dfsRes;
    private static String[] input;
    private static boolean[] check;
    private static Map<String, Integer> countMap;
    private static  List<char[][]> res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = new String[6];
        dfsRes = new char[3][3];
        check = new boolean[6];
        countMap = new HashMap<>();
        res = new ArrayList<>();
        for (int n = 0; n < 6; n++) {
            String val = br.readLine();
            input[n] = val;
            if(countMap.containsKey(val)){
                countMap.put(val, countMap.get(val) + 1);
            }else {
                countMap.put(val, 1);
            }
        }
        Arrays.sort(input);

        // 가로랑 세로만 검사하면된다.
        dfs(0);
        if(res.size() == 0){
            System.out.println(0);
        }else {
            print(res.get(0));
        }

    }
    private static boolean validation(){
        Map<String, Integer> copyMap = new HashMap<>(countMap);
        for(int row = 0; row < 3; row++){
            String rowVal = "";
            String colVal = "";
            for (int col = 0; col < 3; col++){
                rowVal += dfsRes[row][col];
                colVal += dfsRes[col][row];
            }
            //System.out.println(val);
            if(copyMap.containsKey(rowVal)){
                copyMap.put(rowVal, copyMap.get(rowVal) - 1);
            }
            if(copyMap.containsKey(colVal)){
                copyMap.put(colVal, copyMap.get(colVal) - 1);
            }

        }
        for(String key:copyMap.keySet()){
            if(copyMap.get(key) > 0){

                return false;
            }
        }
        return true;
    }

    private static void dfs(int depth) {
        if (depth == 3) {
            //System.out.println();
            if(validation()){
                res.add(getRes());
            }
            return;
        }
        for (int i= 0; i < 6; i++){
            String pick = input[i];
            if(!check[i]){
                check[i] = true;
                for(int idx = 0; idx < 3; idx++){
                    dfsRes[depth][idx] = pick.charAt(idx);
                }
                dfs(depth +1);
                check[i] = false;

            }
        }
    }
    private static void print(char[][] in){
        for(int row = 0; row < 3; row++){
            for (int col = 0; col < 3; col++){
                System.out.print(in[row][col]);
            }
            System.out.println();
        }

    }

    private static char[][] getRes(){
        char[][] res = new char[3][3];
        for(int row = 0; row < 3; row++){
            for (int col = 0; col < 3; col++){
                res[row][col] = dfsRes[row][col];
            }

        }
        return res;
    }
}

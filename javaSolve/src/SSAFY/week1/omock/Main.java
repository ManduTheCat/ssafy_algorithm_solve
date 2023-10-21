package SSAFY.week1.omock;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./resources/2615input3.txt"));
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int inputCount = 19;
        int [] res = new int [3];
        int [][] map = new int [inputCount][inputCount];
        for (int i = 0; i < inputCount; i++){
            map [i]= Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        res = ansList(inputCount, res, map);
        if(res[2]== 0){
            System.out.println(0);
        }
        else {
            System.out.println(res[2]);
            System.out.printf("%d %d", res[0]+1, res[1]+1);
        }
    }

    private static int[] ansList(int inputCount, int[] res, int[][] map) {
        for (int i = 0; i < inputCount; i++){
            for (int j = 0; j < inputCount; j++){
                if(map[i][j] == 1 || map[i][j] == 2) {
                    int [] temp = move(i, j, map);
                    if(temp != null){
                        return temp;
                    }
                }
            }
        }
        return new int []{0,0,0};
    }

    public static int[] move(int i, int j , int[][]map){
        //System.out.println("start " + i +" "+ j);
        //int [] dy = new int[]{-1,-1,0, 1, 1, 1, 0, -1 };
        //int [] dx = new int[]{0, 1, 1, 1, 0,-1,-1, -1 };
        int [] dy = new int[]{-1, 0, 1, 1 };
        int [] dx = new int[]{ 1, 1, 1, 0 };
        int startValue = map[i][j];
        int count = 0;
        //System.out.printf("[%d %d]%d ",i, j, map[i][j]);
        for (int di = 0; di < dx.length; di++){
            // 방향 고정
            if(checkOut(i, j , dy[di], dx[di])){
                int [] sixPick = new int[]{startValue,-1,-1,-1,-1,-1};
                for(int d = 1; d < 6; d++){
                    if(checkOut(i, j, dy[di]*d,dx[di]*d )){
                        sixPick[d] = map[i+ dy[di]*d][j+ dx[di]*d];
                    }
                }
                if(checkFiveSix(sixPick, map , di)){
                    System.out.println(Arrays.toString(sixPick));
                    System.out.printf("true : %d %d, %d\n",i,j, startValue);
                }
            }
        }
        //System.out.println();
        return null;
    }
    public static boolean checkOut(int i, int j, int dy, int dx){
        return i + dy >= 0 && i + dy < 19 && j + dx >= 0 && j + dx < 19;
    }

    public static boolean checkFiveSix(int [] nums,int [][]map, int di){
        //int [] dy = new int[]{-1, 0, 1, 1 };
        //int [] dx = new int[]{ 1, 1, 1, 0 };
        //방향확인
        int [] dy = new int[]{1, 0, -1, -1 };
        int [] dx = new int[]{ -1, -1, -1, 0 };
        int startNum = nums[0];
        for (int idx = 0; idx < nums.length-1; idx++){
            if(nums[idx] != startNum){
                //System.out.println(i);
                return false;
            }
        }
        if(nums[nums.length-1] == startNum) return false;

        //거꾸로 갔을때 같은 게 있으면 false
        //다른게 있거나 벽에 막히면 시각점일라는뜻으로 true
        return true;
    }
}

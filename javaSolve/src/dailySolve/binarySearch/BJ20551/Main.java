package dailySolve.binarySearch.BJ20551;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static int[] arr;
    static int[] commandArr;
    static int target;
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/daily/binarySearch/BJ_20551/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        commandArr = new int[M];
        for (int n = 0; n < N; n++) {
            arr[n] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr); // 이분탐색은 정렬이 필수다.
        for (int m = 0; m < M; m++) {
            int commandNum = Integer.parseInt(br.readLine());
            target = commandNum;
            int findIdx = binarySearch(0, arr.length - 1);
            if(findIdx >= 0 && findIdx < arr.length && arr[findIdx] == target){
                System.out.println(findIdx);
            }else {
                System.out.println(-1);
            }

        }
    }

    // 같은값이 연속해서 있는경우?
    // 문제에서 정렬한 배열에서 가장먼저 등장한 위치를 보여달라 했다.
    /*
    *
    * 2 3 3 4 9 2 5 3 4
        3 10 4 2
        1 -1 4 0 // 빠른게 안나온다....
    * */
    private static int binarySearch(int s, int e) {
        int mid = (s + e) / 2;
        if(s >= e) return s;

        if (target > arr[mid]) { // 타겟이 작다면 왼쪽 에 있을거다
            return binarySearch(mid + 1 , e);
        } else{ // 타겟이 대상보다 클때 오른쪽에 있을거다
            return binarySearch(s, mid);
        }



    }
}

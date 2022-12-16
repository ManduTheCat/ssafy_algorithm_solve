package dailySolve.binarySearch.BJ20551while;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static int[] arr;
    static int[] commandArr;
    static int target;
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/daily/binarySearch/BJ_20551/input2.txt"));
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
            int findIdx = Arrays.binarySearch(arr,target );
            System.out.println(findIdx < 0 ? -1 : findIdx);

        }
    }
}

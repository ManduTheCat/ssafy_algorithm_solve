package week13.day3.LisBinary;

import java.util.Arrays;
import java.util.Scanner;

public class LISTest1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] arr = new int[N];
        int[] C = new int[N]; // 동적 테이블 C[K] 해당(K) 길이를 만족하는 자리 (K 자리) 에 오는 수의 최소값
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();

        }
        int size = 0;
        for (int i = 0; i < N; i++) {
            //c전체 찾으면안되고 데이터가 들어가있는곳까지만 찾아야한다 size까지만
            //from index 는 incursive to exclusive는 입력 직전까지
            //뒤에 붙붙일수 있으면 붙이는 위치 없으면 삽입 위치를 준다.
            // 1, 4 에 5 서치 하면  4다음이다 abs(resturn)-1 -3-1
            // 1, 4 에 4 서치 하면  4위치다 abs(resturn)-1 -2-1
            // 처음에 size 0이면 무조건 0으로 간다. size0 이여도 탐색예상 위치 넘겨준다.
            int pos = Arrays.binarySearch(C, 0, size, arr[i]);
            if (pos >= 0) {//음수가 아니면 대상을 찾았다. 그러면 같은숫자가 이미 있다. ?!
                // 붙이지도 못하고 꾀차도 값이 같다 변화가 없다
                continue;
            }
            // 못찾으면
            int insertPos = Math.abs(pos) - 1; //  insertPos 맨뒤일수도, 기존원소 대체자리일수도 있다.
            C[insertPos] = arr[i]; // 그자리에 값을 넣어라!
            // 만약 5를 찾는데 1, 4, 다음인 2인덱스자리를준다면 길이는 size=2 에서 size=3 으로 바꿔야한다
            if(insertPos == size)size++; // 그러니 싸이즈 자리에 넣은다면 싸이즈를 올려줘야한다
            //이상태에서 k 찍으면된다

        }
        System.out.println(size);

    }
}

package towpoint.jumong;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import javax.swing.text.Caret;

public class Main {
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./resources/daily/towpoint/1940/input.txt"));
		BufferedReader sb = new BufferedReader(new InputStreamReader(System.in));
		// 정렬을 하고
		// 합이 크면 오른쪽을 내리고
		// 합이 크면 왼쪽으 올린다
		// 중간에 체크를 하여 한번 고른건 못고르개한다...
		int n = Integer.parseInt(sb.readLine());
		int m = Integer.parseInt(sb.readLine());
		int [] metalArr = new int[n];
		StringTokenizer st =  new StringTokenizer(sb.readLine());
		for (int metalIdx = 0; metalIdx < n; metalIdx++) {
			metalArr[metalIdx] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(metalArr);
		// System.out.println(Arrays.toString(metalArr));
		//언제 종료해야 하는지 찾을수 있는게 없을때 까지?
		System.out.println(findPlateMail(metalArr,  m));



	}

	private static int findPlateMail(int[] metalArr, int m) {
		// 합이 작으면 각 포인터 이동
		int count = 0;
		int lIdx = 0;
		int rIdx = metalArr.length-1;
		while (lIdx < rIdx){
			// System.out.println(metalArr[lIdx] + metalArr[rIdx]);
			int sum = metalArr[lIdx] + metalArr[rIdx];
			if (sum == m){
				count++;
				rIdx--;
				lIdx++;
			}
			else if(sum > m){
				rIdx--;
			}else {
				lIdx++;
			}


		}
		return count;
	}
}

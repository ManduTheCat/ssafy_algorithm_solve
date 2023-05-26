package towpoint.sumnum2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./resources/daily/towpoint/2003/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int [] inputs = new int[n];
		for (int idx = 0; idx < n; idx++) {
			inputs[idx] = Integer.parseInt(st.nextToken());
		}
		// 시작지점을 0, 0 으로 잡고 하나씩 움직여야한다.
		System.out.println(Arrays.toString(inputs));
		int startIdx = 0;
		int endIdx = inputs.length-1;
		int count = 0;

		System.out.println(count);
	}
}

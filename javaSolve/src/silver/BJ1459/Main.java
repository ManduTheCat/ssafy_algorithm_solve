package silver.BJ1459;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long x = Long.parseLong(st.nextToken());
		long y = Long.parseLong(st.nextToken());
		long w = Long.parseLong(st.nextToken());
		long s = Long.parseLong(st.nextToken());
		long[] way = new long[3];
		if ((x + y) % 2 == 0) { // 짝수인경우
			long fullCross = Math.max(x, y) * s;
			way[0] = fullCross;
		} else { // 홀수인 경우  한번만 평행이동
			long maxLen = Math.max(x, y);
			long fullCross = (maxLen - 1) * s + w;
			way[0] = fullCross;
		}
		long fullStep = (x + y) * w;
		way[1] = fullStep;

		// 갈만큼 대각이동
		long minLen = Math.min(x, y);
		long maxLen = Math.max(x, y);
		way[2] = (minLen * s) + (maxLen - minLen) * w;
		Arrays.sort(way);
		System.out.println(way[0]);

	}
}

package silver.BJ18870;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int[] inputs = new int[N];
		int[] printOrderKey = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int n = 0; n < N; n++) {
			int value = Integer.parseInt(st.nextToken());
			inputs[n] = value;
			printOrderKey[n] = value;
		}
		// System.out.println(Arrays.toString(inputs));
		Arrays.sort(inputs);
		Map<Integer, Integer> countMap = new HashMap<>();
		countMap.put(inputs[0], 0);
		for (int idx = 1; idx < N; idx++) {
			if (!countMap.containsKey(inputs[idx])) {
				int beforeValue = countMap.get(inputs[idx - 1]);
				countMap.put(inputs[idx], beforeValue + 1);
			}
		}
		StringBuilder sb = new StringBuilder();
		for(int pKey : printOrderKey){
			sb.append(countMap.get(pKey));
			sb.append(" ");
		}
		System.out.println(sb);
	}
}

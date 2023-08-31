package beak.simul.bigNBJ2693;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	static int T;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int [] input = new int[10];
			for (int n = 0; n < 10; n++) {
				input[n] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(input);
			System.out.println(input[input.length-3]);

		}
	}
}

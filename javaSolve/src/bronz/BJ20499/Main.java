package bronz.BJ20499;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		String[] split = input.split("/");
		int K = Integer.parseInt(split[0]);
		int D = Integer.parseInt(split[1]);
		int A =Integer.parseInt(split[2]);
		if (K + A < D || D == 0){
			System.out.println("hasu");
		}else {
			System.out.println("gosu");
		}

	}
}

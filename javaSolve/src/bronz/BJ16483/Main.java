package bronz.BJ16483;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static float T;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Float.parseFloat(br.readLine());
		T /=2;
		System.out.println(Math.round(Math.pow(T,2)));
	}
}

package beak.simul.oddNumber;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		List<Integer> res = new ArrayList<>();
		int sum = 0;
		for (int n = 0; n < 7; n++){
			int val = Integer.parseInt(br.readLine());
			if (val %2 != 0) {
				res.add(val);
				sum += val;
			}

		}
		Collections.sort(res);

		if(res.size() > 0 ){
			System.out.println(sum);
			System.out.println(res.get(0));
		}else {
			System.out.println(-1);
		}
	}
}

package bronz.BJ5585;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int [] coinList = {500, 100, 50, 10, 5, 1};
		Map<Integer, Integer> count = new HashMap();
		for(int coin: coinList){
			count.put(coin, 0);
		}
		int input = Integer.parseInt(br.readLine());
		input = 1000 - input;

		for(int coin : coinList){
			count.put(coin, count.get(coin) + input/coin);
			input %=coin;
			if(input < 1){
				break;
			}

		}
		int res = 0;
		for(int key:count.keySet()){
			res += count.get(key);
		}
		System.out.println(res + input);
	}

}

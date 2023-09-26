package string.orderginFile20291;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static int N;
	public static void main(String[] args) throws IOException {
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		Map<String, Integer> countMap = new HashMap<>();
		for(int n = 0; n < N ; n++){
			StringTokenizer st = new StringTokenizer(br.readLine(), ".");
			String name = st.nextToken();
			String extend = st.nextToken();
			if(!countMap.containsKey(extend)){
				countMap.put(extend, 1);
			}else {
				countMap.put(extend , countMap.get(extend) + 1);
			}

		}
		// System.out.println(countMap);
		List<String> sort = new ArrayList<>();
		for(String key :countMap.keySet()){
			sort.add(key);
		}
		Collections.sort(sort);
		for(String key :sort){
			System.out.println(key + " " + countMap.get(key));
		}
	}
}

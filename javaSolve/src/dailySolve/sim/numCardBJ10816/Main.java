package dailySolve.sim.numCardBJ10816;
import java.util.*;
import java.io.*;
public class Main {
	static int N;
	static int M;
	static Map<Integer, Integer> input;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./resources/daily/sim/numberCard2BJ10816/input.txt"));
		//resources/daily/sim/numberCard2BJ10816/input.txt
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		input = new HashMap<>();
		StringTokenizer st  = new StringTokenizer(br.readLine());
		for(int n = 0; n < N; n++){
			int inputNum = Integer.parseInt(st.nextToken());
			if(input.containsKey(inputNum)){
				input.put(inputNum, input.get(inputNum)+1);
			}else {
				input.put(inputNum, 1);
			}
		}
		M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		StringBuilder res = new StringBuilder();
		for(int m = 0; m < M; m++){
			int find = Integer.parseInt(st.nextToken());
			Integer value = input.get(find);
			if(value== null){

				res.append(0);
			}else {
				res.append(value);
			}
			res.append(" ");
		}
		System.out.println(res);

	}
}

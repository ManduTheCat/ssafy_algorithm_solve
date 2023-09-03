package greedy.airPortBJ10775;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int [] parents;
	static int P;
	static int G;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./resources/daily/greedy/BJ10775/input2.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		G = Integer.parseInt(br.readLine());
		P = Integer.parseInt(br.readLine());
		parents = new int[G + 1];
		for(int i = 0; i <= G; i++){
			parents[i] = i;
		}
		int count = 0;
		for (int p = 0; p < P; p++) {
			int plain = Integer.parseInt(br.readLine());
			if(find(plain) == 0) break;
			count++;
			union(find(plain), find(plain)-1);
		}
		System.out.println(count);


	}
	public static int find(int a){
		if(parents[a] == a){
			return a;
		}
		return parents[a] = find(parents[a]);
	}
	public static boolean union(int a, int b){
		// 작은값이 부모가 되게 하는 유니온
		a = find(a);
		b = find(b);
		if(a == b) return false;
		if(a > b){
			parents[a] = b;
		}
		else parents[b] = a;
		return true;

	}
}
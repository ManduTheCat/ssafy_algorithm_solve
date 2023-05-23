package unionfind.represent;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Main {
	public static int N;
	public static int M;
	public static int [] parent;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./resources/daily/unionfind/1717/input1.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		parent = new int[N + 1];
		for (int p = 0; p < N + 1; p++) {
			parent[p] = p;
		}
		for (int inputLine = 0; inputLine < M; inputLine++) {
			st = new StringTokenizer(br.readLine());
			int command = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			switch (command){
				case 0:
					union(a, b);
					break;
				case 1:
					System.out.println(find(b) == find(a) ? "yes" : "no");
					break;

			}
		}
	}
	public static int find(int a){
		if(parent[a] == a){
			return a;
		}
		return parent[a] = find(parent[a]);
	}
	public static boolean union(int a, int b){
		if(find(a) == find(b)){
			return false;
		}
		// parent[b] = find(a);
		parent[find(b)] = find(a);
		return true;
	}
}

package unionfind.friendsnetwork;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static Map<String, String> parents;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./resources/daily/unionfind/4195/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Integer testCount = Integer.parseInt(br.readLine());
		for (int t = 0; t < testCount; t++) {
			int f = Integer.parseInt(br.readLine());
			for (int relastion = 0; relastion < f; relastion++) {
				parents = new HashMap();

				StringTokenizer st = new StringTokenizer(br.readLine());
				String friendsA = st.nextToken();
				String friendsB = st.nextToken();
				System.out.println(friendsA + " "+ friendsB);
			}
		}

	}
	public static String find(String name){
		if(parents.get(name).equals(name)){
			return name;
		}
		return parents.put(parents.get(name), find(name));
	}
	public static boolean union(String a, String b){
		if(find(a).equals(find(b))){
			return false;
		}
		parents.put(find(b), parents.get(a));
		return true;
	}
}

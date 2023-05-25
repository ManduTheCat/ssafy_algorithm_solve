package unionfind.friendsnetwork;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

class Relation{
	String a;
	String b;

	public Relation(String a, String b) {
		this.a = a;
		this.b = b;
	}

	@Override
	public String toString() {
		return "Relation{" +
			"a='" + a + '\'' +
			", b='" + b + '\'' +
			'}';
	}
}
public class Main {
	static Map<String, Integer> parents;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./resources/daily/unionfind/4195/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Integer testCount = Integer.parseInt(br.readLine());
		List<Integer> result = new ArrayList<>();
		for (int t = 0; t < testCount; t++) {
			System.out.println();
			int f = Integer.parseInt(br.readLine());
			List<Relation> inputList = new ArrayList<>();
			parents = new HashMap();
			for (int relastion = 0; relastion < f; relastion++) {

				StringTokenizer st = new StringTokenizer(br.readLine());
				String friendsA = st.nextToken();
				String friendsB = st.nextToken();
				inputList.add(new Relation(friendsA, friendsB));
			}
			for (Relation relation : inputList) {

			}
		}
	}
	public static String find(String name){
		// 부모와 찾는 값이 같으면 최상위 에 도달했으니 부모를 리턴한다.
		if(parents.get(name).equals(name)){
			return name;
		}
		// 그게 아니라면 찾는거 의 재귀타고 바꾼다...
		// System.out.println("put " + name + " : " + find(parents.get(name)));
		// String findP = find(parents.get(name));
		// parents.put(name, findP);
		// return parents.get(findP);
		return  null;
	}
	public static boolean union(String a, String b){
		String ap = find(a);
		String ab = find(b);
		if(find(a).equals(find(b))){
			return false;
		}
		parents.put(find(b), parents.get(a));
		return true;
	}
}

package silver.BJ10814;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Member implements Comparable<Member> {
	private String name;
	private int idx;
	private int age;

	Member(int idx, int age, String name) {
		this.name = name;
		this.age = age;
		this.idx = idx;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public int getIdx() {
		return idx;
	}

	@Override
	public int compareTo(Member o) {
		if (o.age == this.age) {
			return Integer.compare(this.idx, o.idx);
		}
		return Integer.compare(this.age, o.age);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(age);
		sb.append(" ");
		sb.append(name);
		return sb.toString();
	}
}

public class Main {

	public static void main(String[] args) throws IOException {
		// 나이순으로 정렬 나이가 같으면 가입한 순서로
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Member> pq = new PriorityQueue<>();
		int N = Integer.parseInt(br.readLine());
		for (int n = 0; n < N; n++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int age = Integer.parseInt(st.nextToken());
			String name = st.nextToken();
			pq.offer(new Member(n,age, name));
		}
		StringBuilder sb = new StringBuilder();
		while (!pq.isEmpty()){
			sb.append(pq.poll());
			sb.append("\n");
		}
		System.out.println(sb);
	}
}

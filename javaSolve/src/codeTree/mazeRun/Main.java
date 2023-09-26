package codeTree.mazeRun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Person {
	int row;
	int col;
	int count;
	boolean isEnd;

	Person(int row, int col, int count, boolean isEnd) {
		super();
		this.row = row;
		this.col = col;
		this.count = count;
		this.isEnd = isEnd;
	}

	@Override
	public String toString() {
		return "Person{" + "row=" + row + ", col=" + col + '}';
	}
}

class Dist implements Comparable<Dist> {
	private Person start;
	public int dir;
	public int dist;

	public Dist(Person start, int dir, int dist) {
		this.start = start;
		this.dir = dir;
		this.dist = dist;
	}

	public Person getStart() {
		return start;
	}

	@Override
	public int compareTo(Dist o) {
		if (dist != o.dist) {
			return this.dist - o.dist;
		} else {
			return this.dir - o.dir;

		}
	}

	@Override
	public String toString() {
		return "Dist{" +
			"start=" + start +
			", dir=" + dir +
			", dist=" + dist +
			'}';
	}
}

class Seq implements Comparable<Seq> {
	Cordi ru;
	Cordi rd;
	Cordi ld;
	Cordi lu;

	public Seq(Cordi ru, Cordi rd, Cordi ld, Cordi lu) {
		this.ru = ru;
		this.rd = rd;
		this.ld = ld;
		this.lu = lu;
	}

	@Override
	public int compareTo(Seq o) {
		if (this.lu.row - o.lu.row != 0) {
			return this.lu.row - o.lu.row;
		} else {
			return this.lu.col - o.lu.col;
		}
	}
}

class Cordi {
	int row;
	int col;

	public Cordi(int row, int col) {
		this.row = row;
		this.col = col;
	}

	@Override
	public String toString() {
		return "Cordi{" +
			"row=" + row +
			", col=" + col +
			'}';
	}
}

public class Main {
	static int N;
	static int M;
	static int K;
	static List<Person> people;
	static Person exit; // 해당칸에 도달하면 즉시 탈출
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		people = new ArrayList<>();
		map = new int[N][N];
		for (int row = 0; row < N; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < N; col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		// 사람은 동시에 움직인다.
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int row = Integer.parseInt(st.nextToken()) - 1;
			int col = Integer.parseInt(st.nextToken()) - 1;
			people.add(new Person(row, col, 0, false));
		}
		st = new StringTokenizer(br.readLine());
		exit = new Person(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, 0, false);
		// 동시에 이동하고
		for (Person p : people) {
			move(p);
		}
		for (Person p : people) {
			if (p.row == exit.row && p.col == exit.col) {
				p.isEnd = true;
			}
		}

		// 네모를 잡고
		// 네모를 돌린다.
		// 벽을 깍는다.

	}

	public static Seq findSeq() {
		// 대각 한방향씩 이동 시키고
		// 마지막에 전체 이동해서 pq 로 뽑는다.
		PriorityQueue<Seq> pq = new PriorityQueue<>();
		int[][] dir = {{-1, -1}, {1, 1}, {-1, 1}, {-1, -1}};
		// Seq 값이 넘어가는지 확인하는 함수 필요
		// 각 방향으로 증가하면서 사람도 포함하고 있어야한다.

		return pq.poll();

	}

	public static Seq totalSeq(int count) {
		// 각자가 가르키는 방향응로
		Cordi nextRd = new Cordi(exit.row + count, exit.col + count);
		Cordi nextRu = new Cordi(exit.row - count, exit.col + count);
		Cordi nextLd = new Cordi(exit.row + count, exit.col - count); //고정
		Cordi nextLu = new Cordi(exit.row - count, exit.col - count);
		return new Seq(nextRu, nextRd, nextLd, nextLu);

	}

	public static Seq nextRu(int count) {
		// 오른쪽 위 (1,1)
		Cordi nextRd = new Cordi(exit.row, exit.col + count);
		Cordi nextRu = new Cordi(exit.row - count, exit.col + count);
		Cordi nextLd = new Cordi(exit.row, exit.col); //고정
		Cordi nextLu = new Cordi(exit.row + count, exit.col);
		return new Seq(nextRu, nextRd, nextLd, nextLu);

	}

	public static Seq nextRd(int count) { // -1, 1

		// 오른쪽 아래 -1, 1
		// 값이 인덱스 넘어가면?
		Cordi nextRd = new Cordi(exit.row - count, exit.col + count);
		Cordi nextRu = new Cordi(exit.row, exit.col + count);
		Cordi nextLd = new Cordi(exit.row - count, exit.col);
		Cordi nextLu = new Cordi(exit.row, exit.col); // 공정
		return new Seq(nextRu, nextRd, nextLd, nextLu);
	}

	public static Seq nextLu(int count) {
		// -1, -1
		Cordi nextRd = new Cordi(exit.row, exit.col); // 고정
		Cordi nextRu = new Cordi(exit.row - count, exit.col);
		Cordi nextLd = new Cordi(exit.row, exit.col - count);
		Cordi nextLu = new Cordi(exit.row - count, exit.col - count);
		return new Seq(nextRu, nextRd, nextLd, nextLu);

	}

	public static Seq nextLd(int count) {
		// +1-1
		Cordi nextRd = new Cordi(exit.row + count, exit.col);
		Cordi nextRu = new Cordi(exit.row, exit.col); // 고정
		Cordi nextLd = new Cordi(exit.row + count, exit.col - count);
		Cordi nextLu = new Cordi(exit.row, exit.col - count);
		return new Seq(nextRu, nextRd, nextLd, nextLu);

	}

	// 네모안에 사람있는거 확인하는건 공통 로직이다.
	public static boolean pInRac(Seq curr) {// 네모 안에 사람이 있는지?

		for (Person p : people) {
			if (p.row <= curr.lu.row && p.row >= curr.ld.row && p.col <= curr.lu.col && p.col >= curr.ru.col) {
				return true;
			}
		}
		return false;
	}

	public static void move(Person person) {
		// 상하 좌우 로이동하되 조건을 만족
		int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
		PriorityQueue<Dist> distPq = new PriorityQueue<>();
		for (int d = 0; d < 4; d++) {
			int nextRow = person.row + dir[d][0];
			int nextCol = person.col + dir[d][1];
			Person next = new Person(nextRow, nextCol, 0, false);
			// 벽이아니고 , 거리가 가까워야한다.
			//System.out.println( !person.isEnd && isIn(next) && distance(person, exit) > distance(next, exit) && map[next.row][next.col] == 0);
			if (!person.isEnd && isIn(next) && distance(person, exit) > distance(next, exit)
				&& map[next.row][next.col] == 0) {
				// 다음게 더 가깝다면? 이거 논리적 에러 있다.
				// min 에 넣고 최소 값 처리
				// System.out.println("in");
				distPq.offer(new Dist(next, d, distance(next, exit)));
			}
		}
		System.out.println(distPq.poll());
	}

	public static int distance(Person start, Person end) {
		return Math.abs(start.row - end.row) + Math.abs(start.col - end.col);
	}

	public static boolean isIn(Person curr) {
		return curr.row >= 0 && curr.row < N && curr.col >= 0 && curr.col < N;
	}
}

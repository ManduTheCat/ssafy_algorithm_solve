package softeer.asableLine;

import java.util.*;
import java.io.*;

// 다익스트라 어케 했더라?
// dist + bfs
// dist[i] 는 i 에 올수 있는 작은 비용
// 같은 라인가는건 비용 작업시간만
// 다른 라인가는건 이동시간 + 작업시간
class LineDto implements Comparable<LineDto> {
	int lineName; // 0 = A, 1 = B //
	int nodeNum;
	int cost;

	// 같은 라인가는건 비용 작업시간만
	// 다른 라인가는건 이동시간(ab, ba) + 작업시간
	LineDto(int lineName, int nodeNum, int cost) {
		this.lineName = lineName;
		this.nodeNum = nodeNum;
		this.cost = cost;

	}

	@Override
	public int compareTo(LineDto o) {
		return Integer.compare(this.cost, o.cost);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		sb.append(lineName == 0 ? "A" : "B");
		sb.append(" ");
		sb.append(nodeNum);
		sb.append(" ");
		sb.append(cost);
		sb.append("]");
		return sb.toString();
	}

}

class LineCost {
	int a;
	int b;
	int ab;
	int ba;

	LineCost(int a, int b, int ab, int ba) {
		this.a = a;
		this.b = b;
		this.ab = ab;
		this.ba = ba;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		sb.append(this.a);
		sb.append(" ");
		sb.append(this.b);
		sb.append(" ");
		sb.append(this.ab);
		sb.append(" ");
		sb.append(this.ba);
		sb.append("[");
		return sb.toString();
	}
}

public class Main {
	static int N;
	static int START;
	static int END;
	static LineCost[] lineCosts;
	static int[][] dist;

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		START = 0;
		END = N - 1;
		lineCosts = new LineCost[N];
		dist = new int[2][N];
		Arrays.fill(dist[0], Integer.MAX_VALUE);
		Arrays.fill(dist[1], Integer.MAX_VALUE);
		for (int n = 0; n < N - 1; n++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			// 노드 두게의 정보가 들어온다
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int ab = Integer.parseInt(st.nextToken());
			int ba = Integer.parseInt(st.nextToken());
			lineCosts[n] = new LineCost(a, b, ab, ba);
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		int an = Integer.parseInt(st.nextToken());
		int bn = Integer.parseInt(st.nextToken());
		lineCosts[N - 1] = new LineCost(an, bn, 0, 0);
		dijkstra();
		int min = Integer.MAX_VALUE;

		for (int[] d : dist) {
			min = Math.min(d[d.length - 1], min);
		}
		System.out.println(min);
	}

	public static void dijkstra() {
		PriorityQueue<LineDto> pq = new PriorityQueue<>();
		pq.offer(new LineDto(0, 0, lineCosts[0].a)); // a 라인 0번 부터시작
		pq.offer(new LineDto(1, 0, lineCosts[0].b)); // b 라인 0번 부터시작
		dist[0][0] = lineCosts[0].a;
		dist[1][0] = lineCosts[0].b;
		boolean[] lastCheck = new boolean[2];
		while (!pq.isEmpty()) {
			System.out.println(Arrays.deepToString(dist));
			LineDto curr = pq.poll();
			int nextNode = curr.nodeNum + 1;
			if (nextNode == END && curr.lineName == 0) {
				System.out.println("0 -> 0 : " + dist[0][curr.nodeNum] + " + " + lineCosts[END].a);
				System.out.println("0 -> 1 : " +  dist[0][curr.nodeNum] + " + " +lineCosts[END].b + " + " + lineCosts[curr.nodeNum].ab);
				lastCheck[0] = true; //현제는 a 라인 다음이 a 의 n 에 도달한경우
				// 더해줘야하나
				// aN로 가는경우 // bN 으로 가는경우
				if (dist[0][END] > dist[0][curr.nodeNum] + lineCosts[END].a) {// a->an 로 가는경우
					dist[0][END] = dist[0][curr.nodeNum] + lineCosts[END].a;
				}
				if (dist[1][END] > dist[0][curr.nodeNum] + lineCosts[END].b + lineCosts[curr.nodeNum].ab) {// a->bn 로 가는경우
					dist[1][END] = dist[0][curr.nodeNum] + lineCosts[END].b + lineCosts[curr.nodeNum].ab;
				}
				continue;
			}
			if (nextNode == END && curr.lineName == 1) {
				lastCheck[1] = true;
				// aN로 가는경우 // bN 으로 가는경우
				System.out.println("1 -> 0 : " +  dist[1][curr.nodeNum] + " + " +lineCosts[END].a + " + " + lineCosts[curr.nodeNum].ba);
				System.out.println("1 -> 1 : " + dist[1][curr.nodeNum] + " + " +  lineCosts[END].b);
				if (dist[1][END] > dist[1][curr.nodeNum] + lineCosts[END].b) {// b-> bn
					dist[1][END] = dist[1][curr.nodeNum] + lineCosts[END].b;
				}
				if (dist[0][END] > dist[1][curr.nodeNum] + lineCosts[END].a + lineCosts[curr.nodeNum].ba) {// b -> an
					dist[0][END] = dist[1][curr.nodeNum] + lineCosts[END].a + lineCosts[curr.nodeNum].ba;
				}
				continue;
			}
			if (lastCheck[0] && lastCheck[1]) {
				break;
			}
			//if로 분기처리
			// 0일때 (A0)0 -> (A0)0 : time 만 (A0)0->(B1)1 : cost + moveCost
			// 1일땐 (B0)1 -> (B0)1 (B0)1->(A1)0
			// 경로 탐색
			if (curr.lineName == 0) {// 현제 라인이 a 라면
				int toAcost = lineCosts[nextNode].a;

				int toBcost = curr.cost + lineCosts[nextNode].ab;

				if (dist[0][nextNode] > toAcost + dist[0][curr.nodeNum]) {// a->a 로 가는경우
					dist[0][nextNode] = toAcost + dist[0][curr.nodeNum];
					pq.offer(new LineDto(0, nextNode, toAcost + dist[0][curr.nodeNum]));
				}
				if (dist[1][nextNode] > toBcost + dist[0][curr.nodeNum]) {// b 로 가는경우
					dist[1][nextNode] = toBcost + dist[0][curr.nodeNum];
					pq.offer(new LineDto(1, nextNode, toBcost + dist[0][curr.nodeNum]));
				}

			} else { // 현재 라인이 b 라면
				int toAcost = curr.cost + lineCosts[nextNode].ba; // b ->a cost = b + ba
				int toBcost = lineCosts[nextNode].b; //  b -> b  cost = b
				if (dist[0][nextNode] > toAcost + dist[1][curr.nodeNum]) { // b -> a 로 가는경우 비교
					dist[0][nextNode] = toAcost + dist[1][curr.nodeNum];
					pq.offer(new LineDto(0, nextNode, toAcost + dist[1][curr.nodeNum]));
				}
				if (dist[1][nextNode] > toBcost + dist[1][curr.nodeNum]) {// b-> b 로 가능경우
					dist[1][nextNode] = toBcost + dist[1][curr.nodeNum];
					pq.offer(new LineDto(1, nextNode, toBcost + dist[1][curr.nodeNum]));
				}
			}
		}

	}
}
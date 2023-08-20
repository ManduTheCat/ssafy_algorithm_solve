package sweaping.BJ2170;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 선긋기 BJ 2170
 * 보고푼부분 x 가 같은 경우 에는 y 가 빠른게 오게 정렬 처리를 해야한다.
 * 완전히 포함하고 있는 경우 처리가 되는지?
 * 정보를 갱신을 for 로 무조건 다음걸로 하게 되면 비교 하면안되는 친구도 같이 비교하게된다.
 * 280484MB	1484ms
 */
class Cordi implements Comparable<Cordi>{
	int start, end;
	Cordi(int start, int end){
		this.start = start;
		this.end = end;
	}
	@Override
	public String toString(){
		StringBuilder sb  = new StringBuilder();
		sb.append("[");
		sb.append(this.start);
		sb.append(",");
		sb.append(this.end);
		sb.append("]");
		return sb.toString();
	}
	@Override
	public int compareTo(Cordi o){
		if(this.start != o.start){
			return this.start- o.start;
		}
		return this.end - o.end;
	}
}
public class Main {
	static int N;
	static List<Cordi> inputs;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./resources/daily/sweping/BJ2170/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		inputs = new ArrayList<>();
		for(int n = 0; n < N ; n++){
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			inputs.add(new Cordi(start, end));

		}
		Collections.sort(inputs);
		Cordi curr = new Cordi(inputs.get(0).start, inputs.get(0).end);
		int resLen = 0;
		resLen += Math.abs(curr.end - curr.start);
		for(int idx = 1; idx < N; idx++){
			Cordi next = inputs.get(idx);
			// 현재 선분과 겹치는 부분이 있는지 판단
			if(curr.start <= next.start && curr.end >= next.end){
				continue;
			}
			if(curr.end > next.start){//겹친다:  현재 끝부분보다 작으면 겹치는 부분이 있다.
				// 더해지는 부분을 구해 더한다.
				resLen += Math.abs(next.end - curr.end);
			}
			else{// 겹치치 않는다 : 현재 끝부분보다 크거나 같으면 겹치지 않는다
				// 걍더한다.
				resLen += Math.abs(next.end - next.start);
			}
			curr = new Cordi(next.start, next.end);
		}
		System.out.println(resLen);
	}
}

package silver.BJ10655.trySol;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


class Cordi {
	public int x , y;
	Cordi(int x , int y){
		this.x = x;
		this.y = y;
	}
	public String toString(){
		StringBuilder sb  = new StringBuilder();
		sb.append("[");
		sb.append(this.x);
		sb.append(",");
		sb.append(this.y);
		sb.append("]");
		return sb.toString();
	}
}
public class Main {
	static int N;
	static List<Cordi> inputs = new ArrayList<>();
	static int min = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		for(int n = 0; n < N; n++){
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			inputs.add(new Cordi(x, y));
		}
		int sum = 0;
		for(int n = 0; n < N - 1; n++){
			Cordi curr = inputs.get(n);
			Cordi next = inputs.get(n+1);
			int val = Math.abs(curr.x - next.x) + Math.abs(curr.y - next.y);
			sum += val;
		}

		for(int delIdx  = 1; delIdx < N - 1; delIdx++){
			int val = deleteLength(delIdx, sum);
			min  = Math.min(min, val);
		}


		System.out.println(min);
	}
	// 점을 지우면 이전 점과 거리 이후 과의 거리를 전체 길이에서 빼고, 지우고 나서 이어진 길이를 더해보면된다.
	public static int deleteLength(int delIdx, int totalLen){
		Cordi curr = inputs.get(delIdx);
		Cordi next = inputs.get(delIdx + 1);
		Cordi before = inputs.get(delIdx - 1);
		int currBefore = Math.abs(curr.x - before.x) + Math.abs(curr.y - before.y);
		int currNext = Math.abs(curr.x - next.x) + Math.abs(curr.y - next.y);
		int beforeNext = Math.abs(before.x - next.x) + Math.abs(before.y - next.y);
		return (totalLen - currBefore - currNext) + beforeNext;

	}

}

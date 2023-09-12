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

		System.out.println(min);
	}

}

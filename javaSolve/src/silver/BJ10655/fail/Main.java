package silver.BJ10655.fail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


/// 여기서 설명할건데 보이지?
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
		find(false, 0, 0);
		System.out.println(min);
	}
	public  static void find(boolean isJump, int idx, int sum){
		// 완탐으로 하려 했습니다.
		// 왜냐
		// (10 ^ 5 )- 2 * 10 ^5 = 대략 10 ^ 10? 좀만 줄이면 1초내로 되지않을까 ..새줘.. ㅋㅋㅋㅋㅋㅋㅋ
		// 조졋네 이거.. 나무르
		//나무를 잘라야 했네 이거..
		//ㅇㅇㅇㅇㅇ
		// 그래서 좀마 줄여보자 .. 내머리론 생각이 안난다..
		// 그래서 재귀를 돌려
		// 재귀는 현제 인덱스, 지금까지 거리합, 점프 여부
		if(idx == inputs.size()-1){
			// System.out.println(sum);
			if (min  > sum ){
				min = sum;
			}
			return;
		}
		// 그냥 하고 넘기기
		Cordi curr = inputs.get(idx);
		Cordi next = inputs.get(idx + 1);
		// 진행 하면서 두가지 경우 확인하는데
		int nextSum = sum +  Math.abs(curr.x - next.x) + Math.abs(curr.y - next.y);
		if(nextSum < min){ // 글지 마지막까지 가면 조지지.. 이거 // 아무튼 설명하면 다음것과 길이 구하고
			// 길이가 지금까지 도착한것보다 작으면 다음 가지로 넘어가고 아니면 진행안함
			// 이부분이 그냥 다음것 가는부분, 점푸 안함
			// 이거 말하면 쫒겨냐..
			find(isJump, idx + 1, nextSum);
		}
		// 스킵하고 넘기기
		if(!isJump && idx + 2 < inputs.size()){
			// 점프 하고 넘어가는거
			Cordi nextNext = inputs.get(idx + 2);
			int jumpSum = sum + Math.abs(curr.x - nextNext.x) + Math.abs(curr.y - nextNext.y);
			if(jumpSum < min){// 나름 가지치기..
				find(true, idx + 2, jumpSum);
			}
		}

		//어렵다 .. 어우
		// 그냥 벽부스고 이동하기 아니야? zzzzzzzzz
		// 499 500 이면 ..

	}
}

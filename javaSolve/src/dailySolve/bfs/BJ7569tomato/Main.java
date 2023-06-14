package dailySolve.bfs.BJ7569tomato;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static int N;
	private static int M;
	private static int H;
	private static int[][][] box;
	private static boolean[][][] check;
	// clock wise , up and down
	private static int[][] dir = {
		{-1,0,0},
		{0,-1,0,},
		{1,0,0},
		{0,1,0},
		{0,0,-1},
		{0,0,1}};
	private static int count;

	public static void main(String[] args) throws IOException {
		// 처음 한번 bfs 돌고 마지막에 검사한다.
		// 다 완료 되면 카운트 출력
		// 안되면 0 출력
		System.setIn(new FileInputStream("./resources/daily/bfs/BJ7569/input3.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		box = new int[N][M][H];
		check = new boolean[N][M][H];
		count = -1; // -1 이면 시작점이 없어도 -1 보네준다;
		// 입력 첫부분은 맨아래층이다 나는 0번이 꼭대기로 잡고 싶다 => 거꾸로 인덱스 할당
		for (int hg = H-1; hg >= 0; hg--) {
			for(int row = 0; row < N; row++){
				st = new StringTokenizer(br.readLine());
				for (int col = 0; col < M; col++) {
					box[row][col][hg] = Integer.parseInt(st.nextToken());
				}
			}
		}
		// 시작지점은 1인 토마토들의 좌표
		List<Cordi> startList = findStart();
		bfs(startList);
		if(checkIsVisitAll()){
			System.out.println(count);
		}
		else {

			System.out.println(-1);
		}
	}
	private static boolean checkIsVisitAll(){
		for (int hg = H-1; hg >= 0; hg--) {
			for(int row = 0; row < N; row++){
				for (int col = 0; col < M; col++) {
					if(!check[row][col][hg]){
						return false;
					}
				}
			}
		}
		return true;
	}
	private static List<Cordi> findStart() {
		List<Cordi> startPoints = new ArrayList<>();
		for (int hg = H-1; hg >= 0; hg--) {
			for(int row = 0; row < N; row++){
				for (int col = 0; col < M; col++) {
					if(box[row][col][hg] == 1){
						startPoints.add(new Cordi(row, col, hg));
						check[row][col][hg] = true;
					} else if (box[row][col][hg] == -1) {
						check[row][col][hg] = true;
					}
				}
			}
		}
		return startPoints;
	}

	private static void bfs(List<Cordi> startList){
		Queue<Cordi> q = new LinkedList<>();

		for (Cordi cordi : startList) {
			q.offer(cordi);
		}
		while (!q.isEmpty()){
			int size = q.size();
			count++;
			while (size --> 0){
				Cordi cur = q.poll();
				// 6방향 확인
				for(int d = 0; d < 6; d++){
					int nextRow = cur.row + dir[d][0];
					int nextCol = cur.col + dir[d][1];
					int nextHigh = cur.high + dir[d][2];
					if(isAble(nextRow, nextCol, nextHigh)){ // 박스밖인지 && 방문 여부 && 다음은 1 인지
						check[nextRow][nextCol][nextHigh] = true;
						q.add(new Cordi(nextRow, nextCol, nextHigh));
					}
				}
			}

		}
	}

	private static boolean isAble(int row, int col, int h){
		return isIn(row, col, h) && box[row][col][h] == 0 && !check[row][col][h];
	}
	private static boolean isIn(int row, int col, int h){
		return row >= 0 && row < N && col >= 0 && col < M && h >=0 && h < H;
	}
}
class Cordi {
	public int row;
	public int col;
	public int high;
	Cordi(int row, int col, int high){
		this.row = row;
		this.col = col;
		this.high = high;
	}

}

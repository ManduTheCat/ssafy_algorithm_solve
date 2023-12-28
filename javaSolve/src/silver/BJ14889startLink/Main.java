package silver.BJ14889startLink;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] synergy;
	static int[] teamPool1;
	static int[] teamPool2;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		synergy = new int[N][N];
		teamPool1 = new int[N/2];
		teamPool2 = new int[N/2];
		for(int row = 0; row < N; row++){
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int col  = 0; col < N; col++){
				synergy[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		// 팀 나누고 그중에서 경우수를 구한다.
		divideTeam(0,0);


	}
	public static void divideTeam(int start, int depth){// 조합 구하기 N 개중에서 N/2 선택하기
		if(depth == N/2){

			teamPool2 = makePool2(teamPool1);
			System.out.println(Arrays.toString(teamPool1) + " vs " + Arrays.toString(teamPool2));
			System.out.println(cal());
			return;
		}
		for(int i = start;  i < N; i++){
			teamPool1[depth] = i;
			divideTeam(i+1, depth+1);
		}
		//2 3 1 4
	}
	public static int[] makePool2(int[] poo1){
		Set<Integer> team2 = new HashSet<>();
		int [] res = new int[N/2];
		for(int i = 0; i < N; i++){
			team2.add(i);
		}
		int idx = 0;
		for(int mamber : poo1){
			team2.remove(mamber);

		}
		for(int mamber:team2){
			res[idx] = mamber;
			idx++;
		}
		return res;
	}
	public static int  cal  (){
		int team1sum = 0;
		for(int i = 0; i < teamPool1.length; i++){
			for(int j = i + 1; j <teamPool1.length; j++){
				team1sum +=synergy[teamPool1[i]][teamPool1[j]];
			}
		}
		int team2sum = 0;
		for(int i = 0; i < teamPool2.length; i++){
			for(int j = i + 1; j <teamPool2.length; j++){
				team2sum +=synergy[teamPool2[i]][teamPool2[j]];
			}
		}

		System.out.println(team1sum  + " vs " + team2sum);
		return Math.abs(team1sum - team2sum);
	}
}

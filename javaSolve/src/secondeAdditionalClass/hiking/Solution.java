package secondeAdditionalClass.hiking;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class PointDto {
	int r;
	int c;

	public PointDto(int r, int c) {
		super();
		this.r = r;
		this.c = c;
	}

}

public class Solution {
	// 단순하게 생각하는 연습
	static int Tc;
	static int N;
	static int K;
	static int[][] originMap;
	static int maxRoad;
	static boolean[][] check;
	static int[][] dArr = { { 0, -1 }, { -1, 0 }, { 0, 1 }, { 1, 0 } };

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("resources/secondClass/sweaHiking/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Tc = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < Tc; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			originMap = new int[N][N];
			check = new boolean[N][N];
			for (int row = 0; row < N; row++) {
				st = new StringTokenizer(br.readLine());
				for (int col = 0; col < N; col++) {
					originMap[row][col] = Integer.parseInt(st.nextToken());
				}
			}
			// 바꿀거하나선택하는 부분
			maxRoad = 0;
			for (int row = 0; row < N; row++) {
				for (int col = 0; col < N; col++) {
					for (int k = 0; k <= K; k++) {
						List<PointDto> maxList = findMax(originMap);
						originMap[row][col] -= k;
						for(PointDto point : maxList) {
							check = new boolean[N][N];
							check[point.r][point.c] = true;
							dfs(point.r, point.c, 1);
						}
						originMap[row][col] += k;
					}
				}
			}
			System.out.printf("#%d %d\n", tc + 1, maxRoad);
		}

	}

	private static void dfs(int targetRow, int targetCol, int depth) {
		maxRoad = Math.max(maxRoad, depth);
		for (int d = 0; d < 4; d++) {
			int nextRow = targetRow + dArr[d][0];
			int nextCol = targetCol + dArr[d][1];
			if (isOut(nextRow, nextCol) && !check[nextRow][nextCol]) {
				if (originMap[targetRow][targetCol] > originMap[nextRow][nextCol]) {
					check[nextRow][nextCol] = true;
					dfs(nextRow, nextCol, depth + 1);
					check[nextRow][nextCol] = false;
				}
			}
		}
	}

	private static boolean isOut(int nextRow, int nextCol) {
		return nextRow < N && nextRow >= 0 && nextCol < N && nextCol >= 0;
	}

	private static List<PointDto> findMax(int[][] inputMap) {
		int maxNum = 0;
		List<PointDto> res = new ArrayList<>();
		for (int[] row : inputMap) {
			for (int el : row) {
				maxNum = Math.max(el, maxNum);
			}
		}
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				if (originMap[row][col] == maxNum) {
					res.add(new PointDto(row, col));
				}
			}
		}
		return res;
	}

}

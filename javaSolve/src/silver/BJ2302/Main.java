package silver.BJ2302;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class Main {
	static int N;
	static int M;
	static long count;
	static Set<Integer> vip = new HashSet<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		int[] set = new int[N + 1];
		set[0] = -1;
		boolean[] check = new boolean[N + 1];
		check[0] = true;
		for (int m = 0; m < M; m++) {
			int val = Integer.parseInt(br.readLine());
			set[val] = val;
			vip.add(val);
			check[val] = true;
		}
		//System.out.println(Arrays.toString(set));
		dfs(set, 1, check);
		System.out.println(count);
	}

	public static boolean isInZero(int[] set) {
		for (int idx = 1; idx <= N; idx++) {
			if (set[idx] == 0) {
				return true;
			}
		}
		return false;
	}

	public static int[] getClone(int[] src) {
		int[] res = new int[src.length];
		for (int idx = 0; idx < res.length; idx++) {
			res[idx] = src[idx];
		}
		return res;
	}

	public static void dfs(int[] inputSet, int start, boolean[] check) {
		if (!isInZero(inputSet)) {
			//System.out.println(Arrays.toString(inputSet));
			//System.out.println(Arrays.toString(check));
			count++;
			return;
		}
		for (int man = start; man <= N; man++) {
			if (!vip.contains(man)) { // vip 가 아니면 진행해라

				if (man == 1) { // 1이면 두가지 경우만 본다.
					if (inputSet[man] == 0 && !check[man]) { // 자리가 있다면 재귀를 타라
						//int[] nextSet = getClone(inputSet);
						//boolean[] nextCheck = check.clone();
						inputSet[man] = man;
						check[man] = true;
						dfs(inputSet, man + 1, check);
						check[man] = false;
						inputSet[man] = 0;

					}
					if (inputSet[man + 1] == 0 && !check[man]) {
						//int[] nextSet = getClone(inputSet);
						//boolean[] nextCheck = check.clone();
						//nextSet[man + 1] = man;
						inputSet[man + 1] = man;
						//System.out.println(Arrays.toString(nextSet));
						check[man] = true;
						dfs(inputSet, man + 1, check);
						check[man] = false;
						inputSet[man + 1] = 0;
					}

				} else if (man == N) {
					if (inputSet[man] == 0 && !check[man]) {
						//int[] nextSet = getClone(inputSet);
						//boolean[] nextCheck = check.clone();
						//nextSet[man] = man;
						inputSet[man] = man;
						check[man] = true;
						dfs(inputSet, man + 1, check);
						check[man] = false;
						inputSet[man] = 0;

					}
					if (inputSet[man - 1] == 0 && !check[man]) {
						//int[] nextSet = getClone(inputSet);
						//boolean[] nextCheck = check.clone();
						//nextSet[man - 1] = man;
						inputSet[man - 1] = man;
						check[man] = true;
						dfs(inputSet, man + 1, check);
						check[man] = false;
						inputSet[man - 1] = 0;
					}
				} else {
					if (inputSet[man] == 0 && !check[man]) {
						//int[] nextSet = getClone(inputSet);
						//boolean[] nextCheck = check.clone();
						//nextSet[man] = man;
						inputSet[man] = man;
						check[man] = true;
						dfs(inputSet, man + 1, check);
						check[man] = false;
						inputSet[man] = 0;

					}
					if (inputSet[man - 1] == 0 && !check[man]) {
						//int[] nextSet = getClone(inputSet);
						//boolean[] nextCheck = check.clone();
						//nextSet[man - 1] = man;
						inputSet[man - 1] = man;
						check[man] = true;
						dfs(inputSet, man + 1, check);
						check[man] = false;
						inputSet[man - 1] = 0;
					}
					if (inputSet[man + 1] == 0 && !check[man]) {
						//int[] nextSet = getClone(inputSet);
						//boolean[] nextCheck = check.clone();
						//nextSet[man + 1] = man;
						inputSet[man + 1] = man;
						check[man] = true; // 해당번호는 방문 했음을 적어 놓는다.
						//System.out.println(Arrays.toString(nextSet));
						dfs(inputSet, man + 1, check);
						check[man] = false;
						inputSet[man + 1] = 0;
					}
				}

			}

		}
	}
}

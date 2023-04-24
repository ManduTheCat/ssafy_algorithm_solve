package programers.runner;

import java.util.Arrays;

public class Main {
	public static void main(String[] args) {
		String [] player = {"mumu", "soe", "poe", "kai", "mine"};
		String [] caller = {"kai", "kai", "mine", "mine"};
		Solution solution  = new Solution();
		System.out.println(Arrays.toString(solution.solution(player, caller)));

	}
}

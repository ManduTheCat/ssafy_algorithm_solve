package codingTest.gabia.rank;

import codingTest.gabia.rank.Solution;

public class Main {
	public static void main(String[] args) {
		int K = 3;
		String [] user_scores = {"alex111 100", "cheries2 200", "coco 150", "luna 100", "alex111 120", "coco 300", "cheries2 110"};
		Solution solution = new Solution();
		solution.solution(K, user_scores);
	}
}

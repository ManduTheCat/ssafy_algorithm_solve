package programers.divideWires;

public class Main {
	public static void main(String[] args) {

		Solution s = new Solution();
		int n1 = 3;
		int[][] wires = {{1, 3}, {2, 3}, {3, 4}, {4, 5}, {4, 6}, {4, 7}, {7, 8}, {7, 9}};
		s.solution(9,wires);
	}
}

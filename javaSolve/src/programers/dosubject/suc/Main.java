package programers.dosubject.suc;

public class Main {
	public static void main(String[] args) {
		String[][] plan1 = {{"korean", "11:40", "30"}, {"english", "12:10", "20"}, {"math", "12:30", "40"}};
		String[][] plan2 = {{"a", "12:00", "10"}, {"b", "12:20", "100"},
			{"c", "12:30", "10"}, {"d", "12:35", "5"}, {"e", "12:38", "20"}, {"f", "12:39", "10"}};
		String[][] plan3 = {{"A", "12:00", "30"}, {"B", "12:10", "20"}, {"C", "15:00", "40"}, {"D", "15:10", "30"}};
		String[][] plan4 = {{"aaa", "12:00", "30"}, {"bbb", "12:10", "30"}, {"ccc", "14:00", "30"}};
		String[][] plan5 = {{"1", "00:00", "30"}, {"2", "00:10", "10"}, {"3", "00:30", "10"}, {"4", "00:50", "10"}};
		String[][] plan6 = {{"science", "12:40", "50"}, {"music", "12:20", "40"}, {"history", "14:00", "30"},
			{"computer", "12:30", "100"}};
		Solution s = new Solution();
		String[] solution = s.solution(plan6);
		System.out.println(solution);
	}
}

package codingTest.gabia.rank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
	public int solution(int K, String[] user_scores) {
		int answer = 0;
		Map<String, RankInfo> userMap = new HashMap<>();
		List<String> topKList = new ArrayList<>();
		int idx = 0;
		for (int i = 0; i < user_scores.length; ++i) {
			String name = user_scores[i].split(" ")[0];
			int score = Integer.parseInt(user_scores[i].split(" ")[1]);

			HashMap<String, RankInfo> tempUserRank = new HashMap<>();
			List<String> keySet = new ArrayList<>(userMap.keySet());
			for (int j = 0; j < keySet.size(); ++j) {
				RankInfo cloneUserRank = userMap.get(keySet.get(j));
				tempUserRank.put(cloneUserRank.name,
					new RankInfo(cloneUserRank.name, cloneUserRank.score, cloneUserRank.order));
			}
			System.out.println(userMap);
			return 0;
		}
		return 0;
	}
		static class RankInfo {
			String name;
			int score;
			int order;

			public RankInfo(String name, int score, int order) {
				this.name = name;
				this.score = score;
				this.order = order;
			}

			@Override
			public String toString() {
				return "RankInfo{" +
					"name='" + name + '\'' +
					", score=" + score +
					", order=" + order +
					'}';
			}
		}
	}

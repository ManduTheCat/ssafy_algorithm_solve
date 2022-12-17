package codingTest.kTest.p1Duration;
import java.util.*;

class Day_Terms {
    public int year;
    public int month;
    public int day;
    public String kind;

    public Day_Terms(int year, int month, int day, String kind) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.kind = kind;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Day_Terms{");
        sb.append("year=").append(year);
        sb.append(", month=").append(month);
        sb.append(", day=").append(day);
        sb.append(", kind='").append(kind).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

public class Solution {

    static ArrayList<Day_Terms> days = new ArrayList<>();
    static Map<String, Integer> mapTerms = new HashMap();
    static ArrayList<Integer> res = new ArrayList<>();

    //제출시 static 제거
    public static int[] solution(String today, String[] terms, String[] privacies) {
        parseDay(terms, privacies);
        cal();
        compareDay(today);

        int[] answer = new int[res.size()];
        for (int i = 0; i < res.size() ; i++) {
            answer[i] = res.get(i);
        }
        return answer;
    }

    private static void compareDay(String today) {
        StringTokenizer st = new StringTokenizer(today, ".");
        int year = Integer.parseInt(st.nextToken());
        int month = Integer.parseInt(st.nextToken());
        int day = Integer.parseInt(st.nextToken());
        Day_Terms todayClass = new Day_Terms(year, month, day, null);
        for (int idx = 0; idx < days.size(); idx++) {
            Day_Terms p = days.get(idx);
            if (p.year > todayClass.year) {
                continue;
            } else if (p.year == todayClass.year) {// 년도가 같으면 달을 비교 달이 같으면 일을 비교
                if (p.month > todayClass.month) {
                    continue;
                } else if (p.month == todayClass.month) {
                    if (p.day > todayClass.day) {
                        continue;
                    }
                }
            }
            res.add(idx+1);
        }
    }

    private static void cal() {
        for (Day_Terms d : days) {
            d.month = d.month + mapTerms.get(d.kind);
            if (d.month > 12) {
                d.year += d.month / 12;
                d.month = d.month % 12;
            }
        }
    }

    public static void parseDay(String[] terms, String[] privacies) {
        for (String p : privacies) {
            String[] splitP = p.split(" ");
            String YMD = splitP[0];
            String termKind = splitP[1];
            StringTokenizer st = new StringTokenizer(YMD, ".");
            int year = Integer.parseInt(st.nextToken());
            int month = Integer.parseInt(st.nextToken());
            int day = Integer.parseInt(st.nextToken());
            days.add(new Day_Terms(year, month, day, termKind));
        }
        for (String t : terms) {
            StringTokenizer st = new StringTokenizer(t, " ");
            String kind = st.nextToken();
            int duration = Integer.parseInt(st.nextToken());
            mapTerms.put(kind, duration);
        }
    }

    public static void main(String[] args) {
        String today = "2020.01.01";
        String today2 = "2022.05.19";
        String[] terms = {"Z 3", "D 5" };
        String[] terms2 = {"A 6", "B 12", "C 3" };
        String[] privacies = {"2019.01.01 D", "2019.11.15 Z", "2019.08.02 D", "2019.07.01 D", "2018.12.28 Z" };
        String[] privacies2 = {"2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C" };
        solution(today2, terms2, privacies2);

    }

}

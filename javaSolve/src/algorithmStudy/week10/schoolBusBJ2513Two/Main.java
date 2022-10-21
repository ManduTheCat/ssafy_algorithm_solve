package algorithmStudy.week10.schoolBusBJ2513Two;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


class Bus {
    int maxStudent;
    int currStudent;

    public Bus(int maxStudent, int currStudent) {
        this.maxStudent = maxStudent;
        this.currStudent = currStudent;
    }

    @Override
    public String toString() {
        return "Bus{" +
                "maxStudent=" + maxStudent +
                ", currStudent=" + currStudent +
                '}';
    }
}
class AptPoint implements Comparable<AptPoint>{
    int studentNum;
    int dist;
    int point;

    public AptPoint(int studentNum, int dist, int point) {
        this.studentNum = studentNum;
        this.dist = dist;
        this.point = point;
    }

    @Override
    public String toString() {
        return "AptPoint{" +
                "studentNum=" + studentNum +
                ", dist=" + dist +
                ", point=" + point +
                '}';
    }

    @Override
    public int compareTo(AptPoint o) {
        return this.dist - o.dist;
    }
}

public class Main {
    static int N, K, S;
    static int sum = 0;
    static PriorityQueue<AptPoint> left;
    static PriorityQueue<AptPoint> right;
    static Bus bus;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/study/week10/BJ2513/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 지점수
        K = Integer.parseInt(st.nextToken()); // 인원수
        S = Integer.parseInt(st.nextToken()); // 학교위치
        bus = new Bus(K, 0);
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            int point = Integer.parseInt(st.nextToken());
            int student = Integer.parseInt(st.nextToken());
            int dist = Math.abs(S - point);
            if (point < S){
                // 왼쪽에 있따
                left.add(new AptPoint(student,dist, point));
            }else{
                // 오른쪽에 있다
                right.add(new AptPoint(student, dist, point));
            }
        }


    }

    public static void findLeft() {

    }


}

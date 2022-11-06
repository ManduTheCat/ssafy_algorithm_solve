package additionalClass.daiilyAlgo.week3.arrowCompetition;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] info1 = {2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0};
        int n1 = 5;
        //[0,2,2,0,1,0,0,0,0,0,0]
        int[] info2 = {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int n2 = 1;
        //[-1]
        int [] info3 ={0,0,1,2,0,1,1,1,1,1,1};
        int n3= 9;
        //[1,1,2,0,1,2,2,0,0,0,0]
        int [] info4 ={0,0,0,0,0,0,0,0,3,4,3};
        int n4 = 10;
        //1,1,1,1,1,1,1,1,0,0,2
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.solution(n1, info1)));
        System.out.println(Arrays.toString(solution.solution(n2, info2)));
        System.out.println(Arrays.toString(solution.solution(n3, info3)));
        System.out.println(Arrays.toString(solution.solution(n4, info4)));

    }
}

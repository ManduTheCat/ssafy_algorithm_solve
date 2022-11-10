package additionalClass.daiilyAlgo.week4.parkingFee;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] fee = {180, 5000, 10, 600};
        String[] records = {"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN",
                "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"};
        int [] fee2 ={120, 0, 60, 591};
        String [] records2= {"16:00 3961 IN","16:00 0202 IN","18:00 3961 OUT","18:00 0202 OUT","23:58 3961 IN"};
        int [] fee3 ={1, 461, 1, 10};
        String [] records3= {"00:00 1234 IN"};
        Solution solution = new Solution();
        System.out.println("res" + Arrays.toString(solution.solution(fee, records)));
        System.out.println("res" + Arrays.toString(solution.solution(fee2, records2)));
        //System.out.println(Arrays.toString(solution.solution(fee3, records3)));
    }
}

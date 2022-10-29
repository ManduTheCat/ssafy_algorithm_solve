package additionalClass.daiilyAlgo.week1.pgStringCompression2;

public class Main {
    public static void main(String[] args) {
        Solution solution  = new Solution();
        String input1 = "aabbaccc";
        String input2 = "ababcdcdababcdcd";
        String input3 = "abcabcdede";
        String input4 = "abcabcabcabcdededededede";
        String input5 = "xababcdcdababcdcd";
        String input6 = "x";
        System.out.println("1 " + solution.solution(input1));
        System.out.println("2 " + solution.solution(input2));
        System.out.println("3 " + solution.solution(input3));
        System.out.println("4 " + solution.solution(input4));
        System.out.println("5 "+  solution.solution(input5));
        System.out.println("6 "+  solution.solution(input6));

    }
}

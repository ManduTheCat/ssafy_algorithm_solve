package polalise.test1;

import java.util.*;

class Solution {
    static class Ball {
        int row;
        int col;
        int countingStar;

        Ball(int row, int col) {
            this.row = row;
            this.col = col;
            countingStar = 0;
        }

        @Override
        public String toString() {
            return "Ball{" +
                    "row=" + row +
                    ", col=" + col +
                    ", countingStar=" + countingStar +
                    '}';
        }
    }

    static int N;
    static int M;


    public int solution(String[] drum) {
        int answer = 0;
        N = drum.length;
        M = drum[0].length();
        String[][] map = new String[N][M];
        for (int row = 0; row < N; row++) {
            String rowString = drum[row];
            for (int col = 0; col < M; col++) {
                String val = String.valueOf(rowString.charAt(col));
                //System.out.println(val);
                map[row][col] = val;
            }
        }
        //printMap(map);
        for (int starRow = 0; starRow < M; starRow++) {
            Ball res = go(starRow,map);
            if (goal(res.row, res.col )){
                //System.out.println(starRow);
                //System.out.println(res);
                answer++;
            }
        }
        return answer;
    }
    public  static void printMap(String[][] map){
        for(int row = 0; row < N; row++){
            for(int col = 0; col < M; col++){
                System.out.print(map[row][col] + " ");
            }
            System.out.println();
        }
    }
    public  static void printMap(String[][] map , Ball ball){
        for(int row = 0; row < N; row++){
            for(int col = 0; col < M; col++){
                if(row == ball.row &&ball.col == col){
                    System.out.print("%" + " ");
                }else {

                    System.out.print(map[row][col] + " ");
                }
            }
            System.out.println();
        }

    }

    public static Ball go(int startCol, String map[][]) {
        Ball ball = new Ball(0, startCol);
        while (isIn(ball.row, ball.col)) {
            String curr = map[ball.row][ball.col];
            //System.out.println(curr);
            //System.out.println();
            //printMap(map, ball);
            if (curr.equals("#")) {
                ball.row = ball.row + 1;
            } else if (curr.equals(">")) {
                ball.col = ball.col + 1;
            } else if (curr.equals("<")) {
                ball.col = ball.col - 1;
            }else if (curr.equals("*")) {
                if (ball.countingStar == 0) {
                    // 아래로 한칸이동
                    ball.row = ball.row + 1;
                    ball.countingStar = ball.countingStar + 1;

                } else if (ball.countingStar >= 1) {
                    break;
                }

            }
        }

        return ball;

    }

    public static boolean isIn(int row, int col) {
        return row >= 0 && row < N && col >= 0 && col < M;
    }

    public static boolean goal(int row, int col) {
        if(row >= N){
            if(col >=0 && col < M){
                return true;
            }
        }
        return false;
    }

}
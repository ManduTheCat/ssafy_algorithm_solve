package bronz.BJ2511CardPlay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] game = new int[10][3];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int a = 0; a < 10; a++){
            game[a][0] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for(int a = 0; a < 10; a++){
            game[a][1] = Integer.parseInt(st.nextToken());
        }
        for(int a = 0; a < 10; a++){
            if(game[a][0] > game[a][1] ){
                game[a][2] = 1;
            } else if (game[a][0] < game[a][1]) {
                game[a][2] = 2;
            }else {
                game[a][2] = 0;
            }
        }
        int aCount = 0;
        int bCount = 0;
        for(int a = 0; a < 10; a++){
            if(game[a][2] == 1){
                aCount+=3;
            } else if(game[a][2] == 2){
                bCount+=3;
            }else {
                aCount++;
                bCount++;
            }
        }
        String winner ="D";
        if(aCount > bCount){
            winner = "A";
        } else if (aCount < bCount) {
            winner = "B";
        }else {
            for(int a = 9; a >= 0; a--){
                if(game[a][2] == 1){
                    winner = "A";
                    break;
                }else if(game[a][2] == 2){
                    winner = "B";
                    break;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(aCount);
        sb.append(" ");
        sb.append(bCount);
        sb.append("\n");
        sb.append(winner);
        System.out.println(sb);
    }

}

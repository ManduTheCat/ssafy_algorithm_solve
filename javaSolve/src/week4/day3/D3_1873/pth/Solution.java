package week4.day3.D3_1873.pth;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author 박태환
 */
public class Solution {
    static char[][] gameArr;

    static int dir = 0;
    // 탱크 위치
    static int startPositionX = 0;
    static int startPositionY = 0;

    public static void main(String[] args) throws NumberFormatException, IOException {
        System.setIn(new FileInputStream("resources/D3_1873/input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // tc갯수
        int T = Integer.parseInt(in.readLine());

        // 게임판 크기 (높이 ,넓이)
        int H = 0;
        int W = 0;

        // 게임판에 입력될 정보들이 저장될 배열

        // 게임 명령 input 갯수
        int N = 0;
        char[] inputArr;

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(in.readLine());

            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());


            gameArr = new char[H][W];

            for (int i = 0; i < H; i++) {
                String inputStr = in.readLine();
                for (int j = 0; j < W; j++) {
                    //여기서 전차위치 찾아도됌
                    gameArr[i][j] = inputStr.charAt(j);
                }
            }

            //전차위치찾기
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    if (gameArr[i][j] == '^') {
                        dir = 1;
                        startPositionX = i;
                        startPositionY = j;
                        break;
                    } else if (gameArr[i][j] == 'v') {
                        dir = 2;
                        startPositionX = i;
                        startPositionY = j;
                        break;
                    } else if (gameArr[i][j] == '<') {
                        dir = 3;
                        startPositionX = i;
                        startPositionY = j;
                        break;
                    } else if (gameArr[i][j] == '>') {
                        dir = 4;
                        startPositionX = i;
                        startPositionY = j;
                        break;
                    }
                }
            }

            N = Integer.parseInt(in.readLine());
            inputArr = new char[N];
            String inputStr = in.readLine();

            for (int i = 0; i < N; i++) {
                inputArr[i] = inputStr.charAt(i);
                checkInput(inputArr[i]);
            }

            System.out.print("#"+ tc +" ");
            for(char[] temp : gameArr) {

                System.out.println(String.valueOf(temp));
            }
        }
    }

    private static void checkInput(char c) {
        switch (c) {
            case 'U':
                // 배열 범위 안이고, 평지일때
                gameArr[startPositionX][startPositionY] = '^';
                if (startPositionX - 1 >= 0 && gameArr[startPositionX - 1][startPositionY] == '.') {
                    gameArr[startPositionX][startPositionY] = '.';
                    gameArr[startPositionX - 1][startPositionY] = '^';
                    startPositionX -= 1;
                    dir = 1;

                }

                // 평지 아닐 때
                else if (startPositionX - 1 >= 0 && gameArr[startPositionX - 1][startPositionY] != '.') {
                    gameArr[startPositionX][startPositionY] = '^';
                    dir = 1;
                }
                System.out.println("U");
                for(char [] ga : gameArr){
                    System.out.println(Arrays.toString(ga));
                }
                break;

            case 'D':
                // 배열 범위 안이고, 평지일 때
                gameArr[startPositionX][startPositionY] = 'v';
                if (startPositionX + 1 < gameArr.length && gameArr[startPositionX + 1][startPositionY] == '.') {
                    gameArr[startPositionX][startPositionY] = '.';
                    gameArr[startPositionX+1][startPositionY] = 'v';
                    startPositionX += 1;
                    dir = 2;

                }
                // 평지 아닐 때
                else if (startPositionX + 1 < gameArr.length && gameArr[startPositionX + 1][startPositionY] != '.') {
                    gameArr[startPositionX][startPositionY] = 'v';
                    dir = 2;
                }
                break;

            case 'L':
                // 배열 범위 안이고, 평지일 때
                gameArr[startPositionX][startPositionY] = '<';
                if (startPositionY - 1 >= 0 && gameArr[startPositionX][startPositionY-1] == '.') {
                    gameArr[startPositionX][startPositionY] = '.';
                    gameArr[startPositionX][startPositionY-1] = '<';
                    startPositionY -= 1;
                    dir = 3;

                }
                // 평지 아닐 때
                else if (startPositionY - 1 >= 0 && gameArr[startPositionX][startPositionY-1] != '.') {
                    gameArr[startPositionX][startPositionY] = '<';
                    dir = 3;
                }

                break;

            case 'R':
                // 배열 범위 안이고, 평지일 때
                gameArr[startPositionX][startPositionY] = '>';
                if (startPositionY + 1 < gameArr[0].length && gameArr[startPositionX][startPositionY+1] == '.') {
                    gameArr[startPositionX][startPositionY] = '.';
                    gameArr[startPositionX][startPositionY+1] = '>';
                    startPositionY += 1;
                    dir = 4;

                }
                // 평지 아닐 때
                else if (startPositionY + 1 < gameArr[0].length && gameArr[startPositionX][startPositionY+1] != '.') {
                    gameArr[startPositionX][startPositionY] = '>';
                    dir = 4;
                }
                break;
            case 'S':
                shoot();
                break;
        }
    }

    private static void shoot() {
        switch (dir) {
            case 1:
                for(int i = 1; i <= startPositionX; i++) {
                    //범위 밖으로 나가면 break;
                    if(startPositionX - i < 0) {
                        break;
                    }

                    if(gameArr[startPositionX - i][startPositionY] == '*') {
                        gameArr[startPositionX - i][startPositionY] = '.';
                        break;
                    }
                    else if(gameArr[startPositionX - i][startPositionY] == '#') {
                        break;
                    }

                }
                break;
            case 2:
                for(int i = 1; i < gameArr.length-startPositionX; i++) {
                    if(startPositionX + i > gameArr.length) {
                        break;
                    }

                    if(gameArr[startPositionX + i][startPositionY] == '*') {
                        gameArr[startPositionX + i][startPositionY] = '.';
                        break;
                    }
                    else if(gameArr[startPositionX + i][startPositionY] == '#') {
                        break;
                    }

                }
                break;
            case 3:
                for(int i = 1; i <= startPositionY; i++) {
                    if(startPositionY - i < 0) {
                        break;
                    }


                    if( gameArr[startPositionX][startPositionY- i] == '*') {
                        gameArr[startPositionX][startPositionY-i] = '.';
                        break;
                    }
                    else if(gameArr[startPositionX][startPositionY- i] == '#') {
                        break;
                    }

                }
                break;
            case 4:
                for(int i = 1; i < gameArr[0].length - startPositionY; i++) {
                    if(startPositionY + i > gameArr[0].length) {
                        break;
                    }


                    if(gameArr[startPositionX][startPositionY + i] == '*') {
                        gameArr[startPositionX][startPositionY + i] = '.';
                        break;
                    }
                    else if(gameArr[startPositionX][startPositionY + i] == '#') {
                        break;
                    }

                }
                break;
        }
    }
}
package additionalClass.day4.ohmyGoddess;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author 은서파
 * @since 2022. 10. 6.
 * @see 
 * @git 
 * @youtube  
 * @performance 26,304 kb, 119 ms
 * @category #
 * @note 
 */

public class SWEA_D5_07793_오나의여신님 {

    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder output = new StringBuilder();
    static StringTokenizer tokens;

    static int T;
    static int R, C;
    static char [][] map;
    static Queue<Point> dQ, sQ;
    
    static int [][] deltas = {{-1,0},{1,0},{0,1},{0,-1}};
    public static void main(String[] args) throws IOException {
        input = new BufferedReader(new StringReader(instr));
        T = Integer.parseInt(input.readLine());
        for(int t=1; t<=T; t++) {
            tokens = new StringTokenizer(input.readLine());
            R = Integer.parseInt(tokens.nextToken());
            C = Integer.parseInt(tokens.nextToken());
            
            map = new char [R][];
            dQ = new LinkedList<>();
            sQ = new LinkedList<>();
            for(int r=0; r<R; r++) {
                map[r] = input.readLine().toCharArray();
                for(int c=0; c<C; c++) {
                    if(map[r][c]=='*') {
                        dQ.offer(new Point(r, c));
                    }else if(map[r][c]=='S') {
                        sQ.offer(new Point(r, c));
                    }
                }
            }
            // System.out.println(dQ+" : "+sQ);
            // 입력 완료
            int result = bfs();
            output.append(String.format("#%d %s%n", t, result==-1?"GAME OVER":result));
        }
        System.out.println(output);
    }
    
    static int bfs() {
        // 준비물
        // Queue
        // boolean [][] visited
        
        // 초기화 -> 출발지 설정 완료, 방문 처리완료
        
        int depth = 1;
        while(true) {
            // 악마 -->수연 --> 악마 --> 수연
            int size = dQ.size();
            while(size-->0) {
                Point head = dQ.poll();
                
                for(int d=0; d<deltas.length; d++) {
                    int nr = head.r + deltas[d][0];
                    int nc = head.c + deltas[d][1];
                    
                    if(isIn(nr, nc) && (map[nr][nc]=='.' || map[nr][nc]=='S')) {
                        map[nr][nc] = '*';  // 방문 처리
                        dQ.offer(new Point(nr, nc));
                    }                    
                }
            }
            // 악마의 1초 동작 완료!
            size = sQ.size();
            while(size-->0) {
                Point head = sQ.poll();
                
                for(int d=0; d<deltas.length; d++) {
                    int nr = head.r + deltas[d][0];
                    int nc = head.c + deltas[d][1];
                    
                    if(isIn(nr, nc)) {
                        if(map[nr][nc]=='.') {
                            map[nr][nc]='S';
                            sQ.offer(new Point(nr, nc));
                        }else if(map[nr][nc]=='D') {
                            return depth;
                        }
                    }
                }
                
            }
            //수연이의 동작 --> 끝났거나,sQ에 다음 1초에 동작할 Point가 담겨있겠다.!
            // 아직 동작이 안끝났는데.. 움직일 수연이 없다면?? 
            if(sQ.size()==0) {
                break;
            }
            // 아니면 1초 더!
            depth++;
        }
        // 더이상 악마의 활동도 없고, 수연도 없고.
        return -1;
    }
    
    static boolean isIn(int r, int c) {
        return 0<=r && r<R && 0<=c && c<C;
    }
    
    
    static class Point{
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public String toString() {
            return "[r=" + r + ", c=" + c + "]";
        }
        
    }

    // REMOVE_START
    public static String instr = "2\n"
            + "5 3\n"
            + "D*S\n"
            + ".X.\n"
            + ".X.\n"
            + ".X.\n"
            + "...\n"
            + "5 3\n"
            + "D*S\n"
            + "...\n"
            + ".X.\n"
            + ".X.\n"
            + "...";
    // REMOVE_END

}

package softeer.workProess;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Worker {
    Queue<Integer> bottomJob = new ArrayDeque<>();
    Queue<Integer> leftJob = new ArrayDeque<>();
    Queue<Integer> rightJob = new ArrayDeque<>();
    boolean isEmpty = true;


}
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        Worker [] heap = new Worker [2*H + 2];
        for(int h = 0; h < 2*H + 2; h++){
            heap[h] = new Worker();
        }

    }

}

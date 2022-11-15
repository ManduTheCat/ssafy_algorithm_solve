package dailySolve.sweaMechnicStore;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Customers {
    int index;
    int time;
    int reception;
    int mechanic;

    public Customers(int index, int time, int reception, int mechanic) {
        this.index = index;
        this.time = time;
        this.reception = reception;
        this.mechanic = mechanic;
    }


    @Override
    public String toString() {
        return "Customers{" +
                "index=" + index +
                ", time=" + time +
                ", reception=" + reception +
                ", mechanic=" + mechanic +
                '}';
    }
}
class Mechanic{
    int processTime;
    int currTime;
    int customIdx;

    public Mechanic(int processTime, int currTime, int customIdx) {
        this.processTime = processTime;
        this.currTime = currTime;
        this.customIdx = customIdx;
    }

    @Override
    public String toString() {
        return "Mechanic{" +
                "processTime=" + processTime +
                ", currTime=" + currTime +
                ", customIdx=" + customIdx +
                '}';
    }
}
class reception {
    int processTime;
    int currTime;
    int customIdx;

    public reception(int processTime, int currTime, int customIdx) {
        this.processTime = processTime;
        this.currTime = currTime;
        this.customIdx = customIdx;
    }

    @Override
    public String toString() {
        return "reception{" +
                "processTime=" + processTime +
                ", currTime=" + currTime +
                ", customIdx=" + customIdx +
                '}';
    }
}

public class Solution {
    static int Tc;
    static int N;
    static int M;
    static int K;
    static int A;
    static int B;
    static reception[] arrA;
    static Mechanic[] arrB;
    static Queue<Customers> waitingQueue;

    static Map<Integer, ArrayList<Customers>> timeKeyCustomerMap;
    static int maxTime;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/daily/swea2477/test1.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Tc = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < Tc; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            arrA = new reception[N];
            arrB = new Mechanic[M];
            timeKeyCustomerMap  = new HashMap<>();
            waitingQueue = new ArrayDeque<>();
            st = new StringTokenizer(br.readLine());
            for (int n = 0; n < N; n++) {
                arrA[n] = new reception(Integer.parseInt(st.nextToken()), 1, -1);

            }
            st = new StringTokenizer(br.readLine());
            for (int m = 0; m < M; m++) {
                arrB[m] = new Mechanic(Integer.parseInt(st.nextToken()), 1, -1);
            }
            st = new StringTokenizer(br.readLine());

            for (int k = 0; k < K; k++) {
                int time = Integer.parseInt(st.nextToken());
                maxTime = Math.max(time, maxTime);
                if(!timeKeyCustomerMap.containsKey(time)){
                    timeKeyCustomerMap.put(time , new ArrayList<>());
                    timeKeyCustomerMap.get(time).add(new Customers(k+1, time, -1, -1 ));
                }
                else {
                    timeKeyCustomerMap.get(time).add(new Customers(k+1, time, -1, -1));
                }
            }
            System.out.println(timeKeyCustomerMap);
            System.out.println(maxTime);

        }


    }
}

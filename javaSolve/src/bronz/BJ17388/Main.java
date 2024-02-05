package bronz.BJ17388;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Circle implements Comparable<Circle>{
    int idx;
    int val;
    String name;

    public Circle(int idx, int val, String name) {
        this.idx = idx;
        this.val = val;
        this.name = name;
    }
    @Override
    public int compareTo(Circle o){
        return this.val - o.val;
    }
    @Override
    public String toString(){
        return this.name;
    }


}
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        PriorityQueue<Circle> pq = new PriorityQueue<>();
        int sum = 0;
        for(int i = 0; i < 3; i++){
            int val = Integer.parseInt(st.nextToken());
            sum +=val;
            String name = "";
            if(i == 0){
                name = "Soongsil";
            }else if (i == 1){
                name = "Korea";
            } else if (i == 2) {
                name= "Hanyang";
            }
            pq.add(new Circle(i, val, name ));
        }
        if(sum < 100){
            System.out.println(pq.poll());
        }else {
            System.out.println("OK");
        }
    }
}

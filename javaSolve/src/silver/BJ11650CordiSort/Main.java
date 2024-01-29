package silver.BJ11650CordiSort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Cordi implements Comparable<Cordi>{
    int x, y;

    public Cordi(int x, int y){
        this.x = x;
        this.y = y;
    }


    @Override
    public int compareTo(Cordi o) {
        if(this.x == o.x){
            return Integer.compare(this.y, o.y);
        }
        return Integer.compare(this.x, o.x);
    }
    @Override
    public String toString(){
        return this.x + " " + this.y;
    }
}
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<Cordi> cordinates = new ArrayList<>();

        for(int n = 0; n < N; n++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            cordinates.add(new Cordi(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        Collections.sort(cordinates);
        StringBuilder sb = new StringBuilder();
        for(Cordi cordi : cordinates){
            sb.append(cordi);
            sb.append("\n");
        }
        System.out.println(sb);

    }
}

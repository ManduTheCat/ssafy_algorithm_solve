package silver.BJ10815NumberCard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N;
    private static int M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        Set<Integer> pool = new HashSet<>();
        StringTokenizer st= new StringTokenizer(br.readLine());
        for(int n = 0; n < N; n++){
            pool.add(Integer.parseInt(st.nextToken()));
        }
        M = Integer.parseInt(br.readLine());
        List<Integer> search = new ArrayList<>();
        st= new StringTokenizer(br.readLine());
        for(int m = 0; m < M; m++){
            search.add(Integer.parseInt(st.nextToken()));
        }
        List<Integer> isIn = new ArrayList<>();
        for(int val :search){
            if(pool.contains(val)){
                isIn.add(1);
            }else {
                isIn.add(0);
            }
        }
        isIn.forEach(e-> System.out.print(e + " "));
    }
}

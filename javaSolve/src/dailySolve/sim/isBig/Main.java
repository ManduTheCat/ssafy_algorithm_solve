package dailySolve.sim.isBig;

import java.util.*;
import java.io.*;

public class Main{

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int a = -1;
		int b = -1;
		while(true){
			StringTokenizer st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			if(a == 0 && b == 0 )break;
			if(a > b){
				System.out.println("Yes");
			}else {
				System.out.println("No");
			}
		}
	}
}
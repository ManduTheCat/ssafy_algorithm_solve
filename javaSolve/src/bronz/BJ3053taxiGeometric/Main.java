package bronz.BJ3053taxiGeometric;
import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		double input = Double.parseDouble(st.nextToken());
		double euclid = Math.pow(input, 2) * Math.PI;
		double texi = Math.pow(input, 2) * 2;
		System.out.printf("%.6f \n",euclid);
		System.out.printf("%.6f \n",texi );

	}
}

package silver.BJ1181;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Word implements Comparable<Word>{
	String value;
	int size;
	public Word (String value){
		this.value = value;
		this.size = value.length();
	}
	@Override
	public int compareTo(Word o){
		if(o.size == this.size){
			return  value.compareTo(o.value);
		}return this.size - o.size;

	}
}
public class Main {
	static int N;
	public static void main(String[] args) throws IOException {
		BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		Set <String> inputSet = new HashSet<>();
		for(int i = 0; i < N; i++){
			String input = br.readLine();
			inputSet.add(input);
		}
		List<Word> inputs = new ArrayList<>();
		for(String input:inputSet){
			inputs.add(new Word(input));
		}
		Collections.sort(inputs);
		for(Word input:inputs){
			System.out.println(input.value);
		}
	}
}

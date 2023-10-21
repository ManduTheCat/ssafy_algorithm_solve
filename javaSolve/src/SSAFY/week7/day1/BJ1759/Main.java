package SSAFY.week7.day1.BJ1759;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


/**
 * 12532KB	92ms
 * @author 김명진
 * 전체 뽑을 수 있는 갯수 L 에서 모음 을 뽑으면 자음을 그만큼 뽑을수 없습니다.
 * 자음을 뽑는 갯수 모음을 뽑는 갯수 별로 조합을 구해 두개의 조합을 합치는 로직을 사용했습니다
 * 1. 모음 과 자음을 뽑는 수를 구한다
 * 2. 모음 조합구한다 , 자음 조합구한다
 * 3. 두개의 조합을 합치고 오름찾순 정렬해 res 배열리스트에 넣어준다
 * 4. 최종적으로 가능한 모든뽑는 갯수를 구하고 res 배열리스트를 오름차순 정렬해주고 출력한다.
 */
public class Main {
    static int L;
    static int C;
    static ArrayList<Character> consonant = new ArrayList<>();
    static ArrayList<Character> inputVowel = new ArrayList<>();
    static Set<Character> vowel = new HashSet<>();
    static ArrayList<Character[]> volCombi;
    static ArrayList<Character[]> conCombi;
    static ArrayList<String> res = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/SSAFY.week7/day1/BJ1759/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        vowel.add('a');
        vowel.add('e');
        vowel.add('i');
        vowel.add('o');
        vowel.add('u');
        for (int i = 0; i < C; i++) {
            // 자음 모음 구분해야한다
            char cur = st.nextToken().charAt(0);
            if (vowel.contains(cur)) inputVowel.add(cur);
            else consonant.add(cur);
        }
        // 1. 모음 과 자음을 뽑는 수를 구한다

        for (int v = 1; L - v >= 2; v++) {
            //v 는 모음을뽑는 갯수
            // cr 은 consonantR 자음을뽑는갯수
            int cr = L - v;
            volCombi = new ArrayList<>();
            conCombi = new ArrayList<>();
            Character[] vowelCombiRes = new Character[v];
            Character[] consCombiRes = new Character[cr];
            //2. 모음 조합구한다 , 자음 조합구한다
            vowelCombination(vowelCombiRes, v, 0, 0);
            conCombination(consCombiRes, cr, 0, 0);

            // 나온 경우의 수를 합쳐본다
            for (Character[] characters : volCombi) {
                for (Character[] value : conCombi) {
                    //3.두개의 조합을 합치고 오름찾순 정렬해 res 배열리스트에 넣어준다
                    concat(characters, value);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        // 4. 최종적으로 가능한 모든뽑는 갯수를 구하고 res 배열리스트를 오름차순 정렬해주고 출력한다.
        Collections.sort(res);
        for (String r : res) {
            sb.append(r).append("\n");
        }
        System.out.println(sb);
    }
    
    // 모음 조합 결과를 volCombi 에  넣는다 = 전체 경우의 수를 찾는다
    public static void vowelCombination(Character[] vowelCombiRes, int v, int depth, int start) {
        if (depth == v) {
            //복사를 하지 않으면 vowelCombiRes 를 계속 바뀌기 지만
            // volCombi가
            // 참조하기 때문에 값이 이상하게 들어간다.
            volCombi.add(Arrays.copyOf(vowelCombiRes, vowelCombiRes.length));
            return;
        }
        for (int i = start, size = inputVowel.size(); i < size; i++) {
            vowelCombiRes[depth] = inputVowel.get(i);
            vowelCombination(vowelCombiRes, v, depth + 1, i + 1);
        }
    }
    // 자음 조합 결과를 conCombi 에넣는다
    public static void conCombination(Character[] consCombiRes, int cr, int depth, int start) {
        if (depth == cr) {
            conCombi.add(Arrays.copyOf(consCombiRes, consCombiRes.length));
            return;
        }
        for (int i = start, size = consonant.size(); i < size; i++) {
            consCombiRes[depth] = consonant.get(i);
            conCombination(consCombiRes, cr, depth + 1, i + 1);
        }
    }

    // 정해진 갯수만큼의 조합두개를 입력으로 받고 합치고 정렬하는 함수
    public static void concat(Character[] from, Character[] to) {
        StringBuilder sb = new StringBuilder();
        for (Character c : from) {
            sb.append(c);
        }
        for (Character c : to) {
            sb.append(c);
        }
        String resConcat = sb.toString();
        char[] resChar = resConcat.toCharArray();
        Arrays.sort(resChar);
        res.add(String.valueOf(resChar));
    }

}
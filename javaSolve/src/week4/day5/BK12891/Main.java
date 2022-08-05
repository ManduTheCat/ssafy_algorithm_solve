package week4.day5.BK12891;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    public static String[] pickList;
    public static int Acount, Ccount, Gcount, Tcount, P, S;

    /**
     * @author 김명진
     * @문제 12891 DNA비밀번호
     * @복잡도	111964KB	652ms
     * @로직
     * 슬라이드 윈도우 구현
     * 단순히  for 를 활용해 슬라이드가 움직이게 구현하면 n^2 시간복잡도가 나옵니다
     * 윈도우가 움직이는게 아닌 윈도우의 양끝 값을 바꾸는 형태로 구현해야합니다
     * 예를 들어 dns {0,1,2,3,4} 일때 P 가 3라면 
     * 첫 슬라이드느는 0,1,2 을 가르킵니다 
     * 다음 슬라이드로 이동할때 첫번째 0 은 빠지고 2의 다음값인 3이 추가되기만 하면 
     * 움직이는 모습을 구현할수 있습니다.
     * 이러한 방법을 통해 슬라이드 윈도우를 구현했습니다.
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("resources/BJ12891/input1.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        String[] dnaList = br.readLine().split("");
        StringTokenizer count = new StringTokenizer(br.readLine());
        Acount = Integer.parseInt(count.nextToken());
        Ccount = Integer.parseInt(count.nextToken());
        Gcount = Integer.parseInt(count.nextToken());
        Tcount = Integer.parseInt(count.nextToken());
        pickList = new String[P];

        int countVaild = 0;
        HashMap<String, Integer> currWindowMap = new HashMap<>();
        currWindowMap.put("A", 0);
        currWindowMap.put("C", 0);
        currWindowMap.put("G", 0);
        currWindowMap.put("T", 0);
        // 현재 슬라이드의 알파뱃 겟수를 기록한는  currWindowMap 처음 0인덱스를 먼저 수행
        // 0 ~ P까지 를 먼저기록한다
        for (int i = 0; i < P; i++) {
            currWindowMap.put(dnaList[i], currWindowMap.get(dnaList[i]) + 1);
        }
        //요구된 비밀번호 갯수와 일치하는지 검사
        if (currWindowMap.get("A") >= Acount && currWindowMap.get("C") >= Ccount && currWindowMap.get("T") >= Tcount && currWindowMap.get("G") >= Gcount) {
            countVaild++;
        }

        // 이후 한칸씩 이동하는데 앞 뒤 한칸씩만 고려해 갯수를 새면된다 = 슬라이딩 윈도우는 윈도우 양쪽만 움직이면된다.
        // 일단 한칸 이동 한거 확인
        // 시작하는 인덱스의 범위는 처음 다음이 1부터  ~ dnsList.length - P 까지 범위를 넘어가지 않기 위해
        for (int i = 1; i <= dnaList.length - P; i++) {
            countVaild += slide(i, dnaList, currWindowMap);
        }
        System.out.println(countVaild);
    }

    /**
     * 슬라이드 윈도우가 한칸 움직이는걸 구현한 메소드입니다
     * @param start 시작할 index 값입니다
     * @param dnaList 입력된 dna 리스트입니다
     * @param currWindowMap 이전 슬라이드의 값이 해쉬맵형태로 기록 되어 있습니다.
     *                      만약 슬라이드가 이동한다면 이변수의 양끝값이 변하는 형태로
     *                      구현되어있습니다.
     * @return
     */
    static int slide(int start, String[] dnaList, HashMap<String, Integer> currWindowMap) {
        // 한칸 이동한다면 횟수 기록하는 map 에서  dna[start -1] 해당되는 dna--
        // 마지막값은 현재 윈도우의 마지막 다음값을 map 에 추가한다 [start+P +1]++
        // map 에서 이전 start 해당되는 거뺴고 end 의 범위가 늘어나면서 추가된걸 더한다
        currWindowMap.put(dnaList[start - 1], currWindowMap.get(dnaList[start - 1]) - 1);
        // 끝이아니라면 마지막 다음값을 가져와 추가
        if (!(start + P == dnaList.length)) {
            currWindowMap.put(dnaList[start + P - 1], currWindowMap.get(dnaList[start + P - 1]) + 1);
        }
        //끝에 닿는다면 dna 마지막 값을 가져온다
        else {
            currWindowMap.put(dnaList[dnaList.length - 1], currWindowMap.get(dnaList[dnaList.length - 1]) + 1);
        }
        //요구된 비밀번호 갯수와 일치하는지 검사
        if (currWindowMap.get("A") >= Acount && currWindowMap.get("C") >= Ccount && currWindowMap.get("T") >= Tcount && currWindowMap.get("G") >= Gcount) {
            return 1;
        }
        return 0;
    }
}

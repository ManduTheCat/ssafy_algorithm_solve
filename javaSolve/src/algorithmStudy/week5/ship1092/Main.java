package algorithmStudy.week5.ship1092;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 	17828kb	4964ms
 * 화물과 크레인을 내림차순으로 정렬해 큰거 먼저 처리하는 형태로 접근했습니다
 * 아마 그리디? 인거 같습니다.
 * 크래인은 stack으로 O(1)의연산을 가지지만
 * 화물의 경우 내림차순으로 정렬해도 바로 stack 에서 크레인 무게 한계보다
 * 화물이 더큰 경우가 있기에 for 를 쓸수 밖에 없었습니다
 * ex) 크래인 20 20 18 화물 20 20 20 20 20 20
 *
 * 처음에 삭제 비용이 좋은 링크드 리스트를 화물에 적용 했지만 결국
 * 시간복잡도 는 화물 리스트를 링크드 리스트에서 arrayList로 바꿈으로 서 해결했습니다
 * 기존의 링크드리스트는 순회할때 링크드리스트는 O(N) 비용이 기떄문에
 * 삭제 보다 순회를 자주 하는 제 코드에서 시간이 터졌었습니다.
 */
public class Main {
    static int N;
    static int M;
    static Stack<Integer> originCraneStack;
    static int time = 0;
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/study/week5/BJ1092/input5.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        originCraneStack = new Stack<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        ArrayList<Integer> tempCranes = new ArrayList<>();
        for (int n = 0; n < N; n++) {
            tempCranes.add(Integer.parseInt(st.nextToken()));
        }
        ArrayList<Integer> tempCargos = new ArrayList<>();
        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int m = 0; m < M; m++) {
            tempCargos.add(Integer.parseInt(st.nextToken()));
        }
        Collections.sort(tempCranes);
        Collections.sort(tempCargos, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
        for(Integer el :tempCranes){
            originCraneStack.push(el);
        }

        if(Collections.max(originCraneStack) < Collections.max(tempCargos)){
            System.out.println(-1);
            System.exit(0);
        }
        while (!tempCargos.isEmpty()){
            Stack<Integer> roundStack = (Stack<Integer>) originCraneStack.clone();
            time++;
            while (!roundStack.isEmpty()){
                int currCrane = roundStack.pop();
                for(int i = 0, size = tempCargos.size(); i < size; i++){
                    if(currCrane >= tempCargos.get(i)){
                        tempCargos.remove(i);
                        break;
                    }
                }
            }
        }
        System.out.println(time);

    }
}

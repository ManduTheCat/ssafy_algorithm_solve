package algorithmStudy.week5.ship1092;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 17896kb	352ms
 * 화물과 크레인을 내림차순으로 정렬해 큰거 먼저 처리하는 형태로 접근했습니다
 * 아마 그리디? 인거 같습니다.
 * 크래인은 stack으로 O(1)의연산을 가지지만
 * 화물의 경우 내림차순으로 정렬해도 바로 stack 에서 크레인 무게 한계보다
 * 화물이 더큰 경우가 있기에 for 를 쓸수 밖에 없었습니다
 * ex) 크래인 20 20 18 화물 20 20 20 20 20 20
 * <p>
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
        System.setIn(new FileInputStream("resources/study/SSAFY.week5/BJ1092/input5.txt"));
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
        for (Integer el : tempCranes) {
            originCraneStack.push(el);
        }
        
        if (Collections.max(originCraneStack) < Collections.max(tempCargos)) {
            System.out.println(-1);
            System.exit(0);
        }
        
        while (!tempCargos.isEmpty()) {
            // 돌때마다 크래인 순히하기위해 스택을 새로 만들어준다
            Stack<Integer> roundStack = (Stack<Integer>) originCraneStack.clone();
            time++;
            int cargoIndex = 0;
            while (!roundStack.isEmpty()) {
                // 크래인 하나 뽑고
                int currCrane = roundStack.pop();
                // 처음부터 다시 비교하는게 아니라 마지막으로 제거한 값부터 이어서 비교한다.
                // 왜냐면 정렬되어 있기 떄문에 값이 작아진다.
                //끝에 도달하지 않았다면
                while (cargoIndex != tempCargos.size()){
                    if(currCrane >= tempCargos.get(cargoIndex)){
                        tempCargos.remove(cargoIndex);
                        break;
                    }
                    // 크래인보다 크다면 넘어간다.
                    else cargoIndex++;
                }
                // 기존코드 지우고 나서 처음부터 다시 비교한다
//                for (int i = 0, size = tempCargos.size(); i < size; i++) {
//                    if (currCrane >= tempCargos.get(i)) {
//                        tempCargos.remove(i);
//                        break;
//                    }
//                }

            }
        }
        System.out.println(time);

    }
}

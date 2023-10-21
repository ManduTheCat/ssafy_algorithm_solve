package SSAFY.week7.day2.BJ_13023;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class hs {


    // 친구 관계 HashMap에 저장
    // A B C D E index가 될 수 있는 조합 구한 후
    // 해당 친구 관계과 성립되는 지 HashMap을 통해 확인
    // 시간초과 방지를 위해 부분적으로 검사 필요

    static boolean checkFlag = false;

    private static void permutation(int N, int bit, int count, LinkedList<Integer> selected, HashMap<Integer, LinkedList<Integer>> friend) {
        if (count > 1) {
            boolean check = false;

            if (friend.containsKey(selected.get(count - 2))) {
                LinkedList<Integer> fList = friend.get(selected.get(count - 2));
                for (int f = 0; f < fList.size(); ++f) {
                    if (selected.get(count - 1) == fList.get(f)) {
                        check = true;
                    }
                }
            }
            if (!check) {
                return;
            }
        }

        System.out.println(selected);

        if (count == 5) {
            checkFlag = true;
            //System.out.println(selected);
            return;
        }


        for (int i = 0; i < N; ++i) {
            if ((bit & 1 << i) == 0 && !checkFlag) {
                selected.addLast(i);
                permutation(N, bit | 1 << i, count + 1, selected, friend);
                selected.pollLast();
            }
        }

    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("resources/SSAFY.week7/day2/input1.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken()); // 사람의 수, 친구 관계의 수

        HashMap<Integer, LinkedList<Integer>> friend = new HashMap<>();
        for (int m = 0; m < M; ++m) {    // 친구 관계의 수
            st = new StringTokenizer(br.readLine());
            int f1 = Integer.parseInt(st.nextToken()), f2 = Integer.parseInt(st.nextToken());
            LinkedList<Integer> temp = new LinkedList<>();
            if (friend.containsKey(f1)) {
                temp = friend.get(f1);
            }
            temp.addLast(f2);
            friend.put(f1, temp);

            temp = new LinkedList<>();
            if (friend.containsKey(f2)) {
                temp = friend.get(f2);
            }
            temp.addLast(f1);
            friend.put(f2, temp);

        }

        //System.out.println(friend);
        LinkedList<Integer> selected = new LinkedList<>();
        permutation(N, 0, 0, selected, friend);
        if (checkFlag)
            System.out.println(1);
        else System.out.println(0);
    }
}



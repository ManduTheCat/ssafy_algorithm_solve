package gold.BJ16719ZOAC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class CharIndex implements Comparable<CharIndex>{
    char ch;
    int idx;

    public CharIndex(char ch, int idx) {
        this.ch = ch;
        this.idx = idx;
    }

    @Override
    public String toString() {
        return String.valueOf(this.ch);
    }

    @Override
    public int compareTo(CharIndex o) {
        return Character.compare(this.ch, o.ch);
    }
}
public class Main {
    static boolean [] check ;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        check = new boolean[input.length()];
        PriorityQueue<CharIndex> minQ = new PriorityQueue<>();
        for(int idx =0; idx < input.length(); idx++){
            minQ.add(new CharIndex(input.charAt(idx), idx));
        }
        CharIndex first = minQ.poll();
        check[first.idx] = true;
        CharIndex curr = first;

        List<CharIndex> selected = new ArrayList<>();
        selected.add(curr);
        System.out.println(curr.ch);
        while (!isComplete()){
            // 왼쪽 우선 탐색 없으면 넘어간다
            //System.out.println(curr);
            boolean isLeftSelect = false;
            List<CharIndex> left = new ArrayList<>();
            List<CharIndex> right = new ArrayList<>();
            for(int rIdx = curr.idx + 1; rIdx < input.length(); rIdx++){
                if(check[rIdx]) continue;
                right.add(new CharIndex(input.charAt(rIdx), rIdx));
                isLeftSelect = true;
            }
            //System.out.println(right);
            if(isLeftSelect){
                CharIndex min = findMin(right);
                selected.add(min);
                check[min.idx] = true;
                curr = min;
            } else {
                // 오른쪽 없다면 왼쪽 탐색
                //System.out.println("in");
                for(int lIdx = curr.idx - 1; lIdx>=0; lIdx--){
                    if(check[lIdx]) continue;
                    left.add(new CharIndex(input.charAt(lIdx), lIdx));
                }
                CharIndex min = findMin(left);
                check[min.idx] = true;
                curr = min;
                selected.add(min);
            }
            Collections.sort(selected, Comparator.comparingInt(o -> o.idx));
            StringBuilder sb = new StringBuilder();
            for(CharIndex val :selected){
                sb.append(val.ch);
            }
            System.out.println(sb);//ALIK ->  AINK STARTLINK
        }
    }

    private static boolean isComplete (){
        for(boolean b : check){
            if(!b){
                return false;
            }
        }
        return true;
    }

    private static CharIndex findMin(List<CharIndex> chunk){ // 체크가 된
        PriorityQueue<CharIndex> temp = new PriorityQueue<>();
        if(chunk.isEmpty()) return null;
        for(CharIndex alp:chunk){
            temp.offer(alp);
        }
        return temp.poll();
    }
}

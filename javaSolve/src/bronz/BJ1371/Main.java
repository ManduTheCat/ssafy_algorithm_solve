package bronz.BJ1371;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Map<String, Integer> countMap = new HashMap<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;
        String str ="";
        while ((input = br.readLine())!= null  ){
            str+=input;
        }
        for(int idx = 0; idx < str.length(); idx++){
            String alpha = String.valueOf(str.charAt(idx));
            if((str.charAt(idx) >= 'a' && str.charAt(idx) <='z')){
                if(!countMap.containsKey(alpha)){
                    countMap.put(alpha, 1);
                }else {
                    countMap.put(alpha,countMap.get(alpha) + 1);
                }
            }
        }



        //System.out.println(countMap);
        int max = -1;
        String maxKey ="";
        for(String key:countMap.keySet()){
            if(max < countMap.get(key)){
                max = countMap.get(key);
                maxKey = key;
            }
        }
        List<String> sb = new ArrayList<>();
        sb.add(maxKey);
        //sb.append( " ");
        for(String key:countMap.keySet()){
            if(countMap.get(key) == max && !key.equals(maxKey)){
                sb.add(key);
            }
        }

        //System.out.println(countMap);
        Collections.sort(sb);
        for(String val:sb){
            System.out.print(val);
        }

    }
}

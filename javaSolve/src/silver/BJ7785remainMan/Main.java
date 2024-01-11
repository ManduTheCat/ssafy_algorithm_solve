package silver.BJ7785remainMan;

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Map<String, Boolean> company = new HashMap<>();
        for (int n = 0; n < N; n++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            String act = st.nextToken();
            if (act.equals("enter")) { // enter
                company.put(name, true);
            } else { // leave
                    company.put(name, false);
            }
        }
        List<String> res = new ArrayList<>();
        for (String name : company.keySet()) {
            if (company.get(name)) {
                res.add(name);
            }
        }
        res.sort(Collections.reverseOrder());
        StringBuilder sb = new StringBuilder();
        for (String name : res) {
            sb.append(name);
            sb.append("\n");
        }
        System.out.println(sb);
    }
}

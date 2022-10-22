package algorithmStudy.week11.ElectroFee;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        // 0받으면 종료한다.
        System.setIn(new FileInputStream("resources/study/week11/bj5710/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            if (A == 0 && B == 0) {
                break;
            }
            // B 가 일치 할떄까지 이분 탐색 무조건 상근이가 작다
            int total = convertWatt(A);
            int left = 0;
            int right = total/2;
            while (left <= right){
                int mid = (left+ right)/2;
                int SangPrice = convertBill(mid);// 항상 상근이가 작다
                int NPrice = convertBill(total - mid); // 나머지는 이웃이다
                int diff = Math.abs(NPrice - SangPrice); // 돌면서 B 후보를 구한다
                if(diff < B){
                    right = mid - 1; // 기준보다 크면 기준에서 왼쪽으로
                }else if(diff > B)left = mid+ 1; // 기준보다 작으면 오른쪽으로  
                else {
                    System.out.println(convertBill(mid));// 차이가 일치하는 순간 멈춰라
                    break;
                }

            }


        }
        //System.out.println(convertWatt(35515));
        //System.out.println(convertBill(10123));
    }

    public static int convertWatt(int fee) {
        int[] rangeList = {200, 200 + 9900 * 3, 200 + 9900 * 3 + 990000 * 5};
        if (fee <= rangeList[0]) {
            return fee / 2;
        }
        if (fee <= rangeList[1]) { // 100단위 는 넘겼으니 1000단위 +  100 더해준다.
            // 그리고 100 wat 를 더했기 때문에 100 와트만큼 가격 빼줘야한다.
            return (fee - rangeList[0]) / 3 + 100;
        }
        if (fee <= rangeList[2]) {
            return (fee - rangeList[1]) / 5 + 10000;
        }
        return (fee - rangeList[2]) / 7 + 1000000;
    }

    public static int convertBill(int watt) {
        if (watt <= 100) return watt * 2;
        if (watt <= 10000) return (watt - 100) * 3 + 100 * 2;
        // 1000 의 최대의 요금값 + 100 의 최대 요금값 + 나머지 와트 값
        if (watt <= 1000000) return (watt - 10000) * 5 + 200 + 9900 * 3;
        return (watt - 1000000) * 7 + 200 + 9900 * 3 + 990000 * 5;
    }

}

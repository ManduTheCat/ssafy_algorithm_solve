package tack;

/**
 * 식인종 성직자 문제 풀이
 */
public class Solution4 {

    public static void main(String[] args) {
//        System.out.println(solution(2, 2, 2));
//        System.out.println(solution(2, 2, 1));
        System.out.println(solution(100, 10, 9));
    }

    public static class Place{
        int good, bad;

        public Place(int good, int bad) {
            this.good = good;
            this.bad = bad;
        }
    }

    public static Place A, B, boot;
    public static int N, M, ans = Integer.MAX_VALUE;
    public static boolean[][] visitedA, visitedB;

    public static int solution(int n, int m, int p) {
        ans = Integer.MAX_VALUE;
        A = new Place(n, m);
        B = new Place(0, 0);
        boot = new Place(0, 0);
        N = n;
        M = m;

        visitedA = new boolean[n+1][m+1];
        visitedB = new boolean[n+1][m+1];
        visitedA[n][m] = true;

        AToB(p, 0);

        if(ans == Integer.MAX_VALUE) ans = -1;
        return ans;
    }

    public static void AToB(int p, int cnt){
        if(cnt > ans) return;

        boot = new Place(0, 0);

        for(int i = p; i >= 1; i--){
            for(int moveBad = 0; moveBad <= i; moveBad++){
                if(A.bad >= moveBad && A.good >= (i-moveBad)){
                    int moveGood = i - moveBad;
                    move(moveGood, moveBad, true);

                    if(isPossible() && !visitedB[B.good][B.bad]){
                        visitedB[B.good][B.bad] = true;
                        BToA(p, cnt+1);
                    }

                    move(moveGood, moveBad, false);
                }
            }
        }
    }

    public static void BToA(int p, int cnt){
        if(cnt > ans) return;

        if(B.good == N && B.bad == M){
            ans = Math.min(cnt, ans);
            return;
        }

        boot = new Place(0, 0);

        for(int i = p; i >= 1; i--){
            for(int moveBad = 0; moveBad <= i; moveBad++){
                if(B.bad >= moveBad && B.good >= (i-moveBad)){
                    int moveGood = i - moveBad;
                    move(moveGood, moveBad, false);

                    if(isPossible() && !visitedA[A.good][A.bad]){
                        visitedA[A.good][A.bad] = true;
                        AToB(p, cnt+1);
                    }

                    move(moveGood, moveBad, true);
                }
            }
        }
    }

    public static void move(int moveGood, int moveBad, boolean flag){
        int tmp1 = 1;
        int tmp2 = -1;

        if(flag) {
            tmp1 = -1;
            tmp2 = 1;
        }

        A.good += (tmp1 * moveGood);
        A.bad += (tmp1 * moveBad);

        boot.good += moveGood;
        boot.bad += moveBad;

        B.good += (tmp2 * moveGood);
        B.bad += (tmp2 * moveBad);
    }

    public static boolean isPossible(){
        return isPossible(A) && isPossible(B) && isPossible(boot);
    }

    public static boolean isPossible(Place place){
        if(place.good == 0) return true;
        return place.good >= place.bad;
    }
}

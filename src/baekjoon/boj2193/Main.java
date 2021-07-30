package baekjoon.boj2193;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
    }

    // 이 부분을 int로 풀이 했음...
    long[][] T = new long[100+1][2+1];
    public void solve(){
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();

        T[1][1] = 1;
        T[1][0] = 0;
        T[2][1] = 0;
        T[2][0] = 1;

        for(int n = 3; n <= N; n++){
            T[n][0] = T[n-1][0] +T[n-1][1];
            T[n][1] = T[n-1][0];
        }

        System.out.println(T[N][0] + T[N][1]);
    }
}

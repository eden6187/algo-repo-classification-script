package baekjoon.boj2579;

import java.util.Scanner;

/*
 * 올바른 풀이 방법.
 */
public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
    }

    public void solve(){
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        int[][] table = new int[300+1][3];
        // table[i][1] = i번째 계단을 연속해서 1개의 계단을 밟고 올라온 경우, 단 현재 계단을 포함해서
        // table[i][2] = i번쩨 계단을 연속해서 2개의 게단을 밟고 올라온 경우, 단 현재 계단을 포함해서

        int[] stair = new int[300+1];
        for(int i = 1 ; i <= t; i++){
            stair[i] = scanner.nextInt();
        }

        table[1][1] = stair[1];
        table[1][2] = -1;

        table[2][1] = stair[2];
        table[2][2] = stair[1] + stair[2];

        for(int i = 3; i <= t; i++){
            table[i][1] = Math.max(table[i-2][2], table[i-2][1]) + stair[i];
            table[i][2] = table[i-1][1] + stair[i];
        }

        System.out.println(Math.max(table[t][1], table[t][2]));
    }
}

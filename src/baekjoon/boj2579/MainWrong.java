package baekjoon.boj2579;

import java.util.Scanner;

/*
 * 잘못된 풀이 방법입니다.
 */
public class MainWrong {
    public static void main(String[] args) {
        MainWrong main = new MainWrong();
        main.solve();
    }

    public void solve(){
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        int[][] table = new int[300+1][2];
        int[] stair = new int[300+1];

        //[i][0] -> i 번째 계단 까지 올라갔을 때의 최댓값.
        //[i][1] -> i 번째 계단에 올라올때 직전에 밟았던 계단.

        for(int i = 1 ; i <= t; i++){
            stair[i] = scanner.nextInt();
        }

        table[1][0] = stair[1];
        table[1][1] = -1;

        table[2][0] =  Math.max(stair[1] + stair[2] , stair[2]);
        table[2][1] = stair[1] + stair[2] > stair[2] ? 0 : 1;


        for(int i = 3; i <= t; i++){

            int max = table[i-2][0];
            int stairNum = i-2;

            if(table[i-1][1] != i-2){
                max = Math.max(table[i-1][0], max);
                stairNum = i-1;
            }

            table[i][0] = max + stair[i];
            table[i][1] = stairNum;
        }

        System.out.println(table[t][0]);
    }
}

package baekjoon.boj1003;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }

    int[][] table;
    int[][] ans;

    void init(int testCnt){
        table = new int[40+1][2];
        ans = new int[testCnt][2];
        for (int[] ints : table) {
            Arrays.fill(ints,-1);
        }
        for (int[] ints : ans) {
            Arrays.fill(ints,-1);
        }
    }

    void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCnt = Integer.parseInt(br.readLine());
        init(testCnt);

        for(int i = 0 ; i < testCnt; i++){
            int input = Integer.parseInt(br.readLine());
            int[] ans = fib(input);
            this.ans[i] = ans;
        }

        for(int i = 0 ; i < testCnt; i++){
            System.out.println(ans[i][0] + " " + ans[i][1]);
        }
    }

    int[] fib(int n){
        if (table[n][0] != -1 && table[n][1] != -1) return table[n];

        if(n == 0){
            table[0][0] = 1;
            table[0][1] = 0;
            return table[0];
        }else if (n == 1){
            table[1][0] = 0;
            table[1][1] = 1;
            return table[1];
        }else{
            int[] minus2 = fib(n-2);
            int[] minus1 = fib(n-1);
            table[n][0] = minus1[0] + minus2[0];
            table[n][1] = minus1[1] + minus2[1];
            return table[n];
        }
    }
}

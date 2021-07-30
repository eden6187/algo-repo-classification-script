package baekjoon.boj1932;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException{
        Main main = new Main();
        main.solve();
    }

    public void printTriangle(){
        for (int[] ints : triangle) {
            for (int anInt : ints) {
                System.out.print(anInt);
            }
            System.out.println();
        }
    }

    int[][] triangle;
    int[][] table;
    int tSize;
    public void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        tSize = Integer.parseInt(br.readLine());
        triangle = new int[500+1][500+1];
        table = new int[500+1][500+1];

        for(int i = 1; i <= tSize; i++){
            String[] ints = br.readLine().split(" ");
            for(int j = 0 ; j < ints.length; j++){
                triangle[i][j+1] = Integer.parseInt(ints[j]);
            }
        }

        table[1][1] = triangle[1][1];
        for(int N = 2; N <= tSize; N++){ // 2층부터 N층 까지
            for(int K = 1 ; K <= N; K++){ // N번째 층의 K번째 요소에 대하여
                int max = Integer.MIN_VALUE;

                int left = K -1; // 왼쪽 위
                int right = K; // 오른쪽 위

                if(left >= 1) max = Math.max(table[N-1][left], max); // 왼쪽 위가 있다면, 최댓값 갱신
                if(right <= N-1) max = Math.max(table[N-1][right], max); // 오른쪽 위가 있다면, 최댓값 갱신.

                table[N][K] = max + triangle[N][K];
                // table 갱신
            }
        }

        int ans = Integer.MIN_VALUE;
        for(int i = 1; i <= tSize; i++){
            ans = Math.max(table[tSize][i], ans);
        }
        // 테이블의 N번째 층에서 최댓값 추출

        System.out.println(ans);
    }
}

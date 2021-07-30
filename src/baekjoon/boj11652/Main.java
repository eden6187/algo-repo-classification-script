package baekjoon.boj11652;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException{
        Main main = new Main();
        main.solve();
    }

    public void solve()throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();

        int cnt = Integer.parseInt(line);
        long[] cards = new long[100_002];

        for(int i = 0; i < cnt; i++){
            String buff = br.readLine();
            long card = Long.parseLong(buff);
            cards[i] = card;
        }

        Arrays.sort(cards,0,cnt);
        // 정렬

        long current = cards[0];
        int size = 1;
        int maxSize = 1;
        long maxNum = cards[0];

        for (int i = 1 ; i < cnt; i++){
            long next = cards[i];
            if(current == next){
                size++;
            }else{
                if(size > maxSize){
                    maxSize = size;
                    maxNum = current;
                }
                current = next;
                size = 1;
            }
        }

        if(size > maxSize) maxNum = current;

        System.out.println(maxNum);
    }
}

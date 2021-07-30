package baekjoon.boj2910;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.zip.ZipInputStream;

public class Main {
    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }

    int C, N;
    HashMap<Integer, ZippedNum> zippedNumbers = new HashMap<>();

    void solve() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nAndC = br.readLine().split(" ");

        N = Integer.parseInt(nAndC[0]);
        C = Integer.parseInt(nAndC[1]);

        String[] inputArr = br.readLine().split(" ");
        int[] arr = new int[inputArr.length];
        int idx = 0;

        for (String s : inputArr) {
            arr[idx] = Integer.parseInt(inputArr[idx]);
            idx++;
        }

        for(int i = 0 ; i < arr.length; i++){
            zippedNumbers.putIfAbsent(arr[i], new ZippedNum(0,arr[i],i));
            ZippedNum zippedNum = zippedNumbers.get(arr[i]);
            zippedNum.cnt++;
            zippedNumbers.put(arr[i], zippedNum);
        }

        List<ZippedNum> zippedNumbersList = new ArrayList<>(zippedNumbers.values());

         zippedNumbersList.sort(new Comparator<ZippedNum>() {
             @Override
             public int compare(ZippedNum o1, ZippedNum o2) {
                 if (o1.cnt == o2.cnt) {
                     return Integer.compare(o1.firstCome, o2.firstCome);
                 } else {
                     return -Integer.compare(o1.cnt, o2.cnt);
                 }
             }
         });

        for (ZippedNum zippedNum : zippedNumbersList) {
            for(int i = 0 ; i < zippedNum.cnt; i++){
                System.out.print(zippedNum.num + " ");
            }
        }
    }

    class ZippedNum{
        int cnt;
        int num;
        int firstCome;

        public ZippedNum(int cnt, int num, int firstCome) {
            this.cnt = cnt;
            this.num = num;
            this.firstCome = firstCome;
        }
    }
}

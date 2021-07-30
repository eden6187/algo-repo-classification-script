package baekjoon.boj7795;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    static int[] A = new int[20_002];
    static int[] B = new int[20_002];

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        int[] ans = main.solve();
        for (int an : ans) { System.out.println(an); }
    }

    int[] solve() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int caseCnt = Integer.parseInt(br.readLine());

        int[] ans = new int[caseCnt];

        for(int i = 0 ; i < caseCnt; i++) {
            String[] sizes = br.readLine().split(" ");

            int sizeA = Integer.parseInt(sizes[0]);
            int sizeB = Integer.parseInt(sizes[1]);

            int[] A = sToI(br.readLine().split(" "));
            int[] B = sToI(br.readLine().split(" "));

            ans[i] = getCouplesByJustLowerBound(A,B);
        }
        return ans;
    }

    int[] sToI(String[] sArr){
        int[] iArr = new int[sArr.length];
        int i = 0;
        for (String s : sArr) { iArr[i++] = Integer.parseInt(s); }
        return iArr;
    }

    // 내가 푼 방식 : mergeSort와 유사한 알고리즘
    int getCouplesByMerge(int[] A, int[] B){
        int couples = 0;

        Arrays.sort(A);
        Arrays.sort(B);

        int aPtr = 0;
        int bPtr = 0;

        for(aPtr = 0 ; aPtr < A.length; aPtr++){
            int curAVal = A[aPtr];
            while (bPtr < B.length && B[bPtr] < curAVal) bPtr++;
            couples += bPtr;
        }

        return couples;
    }

    // 바킹 독 님 방식 : 그냥 지려버리는 방식

    // 내가 푼 방식 : mergeSort와 유사한 알고리즘
    int getCouplesByJustSorting(int[] A, int[] B){

        Element[] elements = new Element[A.length + B.length];
        int couples = 0;
        int idx = 0;

        for (int i : A) {
            elements[idx] = new Element('a',A[idx]);
            idx++;
        }

        for (int i : B) {
            elements[idx] = new Element('b', B[idx - A.length]);
            idx++;
        }

        Arrays.sort(elements, new Comparator<Element>() {
            @Override
            public int compare(Element o1, Element o2) {
                return Integer.compare(o1.num, o2.num);
            }
        });

        int cnt = 0;
        for (int i = 0 ; i < idx; i++){
            if(elements[i].aOrB == 'b') cnt++;
            else {
                couples += cnt;
            }
        }

        return couples;
    }

    class Element{
        char aOrB;
        int num;

        public Element(char aOrB, int num) {
            this.aOrB = aOrB;
            this.num = num;
        }
    }

    int getCouplesByJustLowerBound(int[] A, int[] B){
        int couples = 0;

        Arrays.sort(A);
        Arrays.sort(B);

        for (int i : A) {
            couples += lowerBound(B, i);
        }

        return couples;
    }

    // lower bound : 삽입 되었을 때 모순이 발생하지 않는 위치의 "lower bound"
    // target 보다 작거나 같은 값중 가장 오른쪽에 있는 값.
    int lowerBound(int[] arr, int target){
        int st = 0;
        int en = arr.length;

        while(st < en){
            int mid = (st + en) / 2;
            if(target > arr[mid]){
                st = mid + 1;
            }else{
                en = mid;
            }
        }

        return st;
    }
}

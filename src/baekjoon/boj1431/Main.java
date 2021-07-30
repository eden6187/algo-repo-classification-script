package baekjoon.boj1431;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = new String[1002];

        int cnt = Integer.parseInt(br.readLine());

        for(int i = 0 ; i < cnt; i++){
            str[i] = br.readLine();
        }

        Arrays.sort(str,0,cnt, new CustomComparator());

        for (int i = 0 ; i < cnt; i++){
            System.out.println(str[i]);
        }
    }

    static class CustomComparator implements Comparator<String>{
        @Override
        public int compare(String o1, String o2) {
            if(o1.length() == o2.length()){
                int numSum1 = getNumSum(o1);
                int numSum2 = getNumSum(o2);
                if(numSum1 == numSum2){
                    return o1.compareTo(o2);
                }else{
                    return Integer.compare(numSum1,numSum2);
                }
            }else{
                return Integer.compare(o1.length(), o2.length());
            }
        }

        public int getNumSum(String str){
            int sum = 0;
            for(int i = 0; i < str.length(); i++){
                char c = str.charAt(i);
                if(Character.isDigit(c)) sum += Character.getNumericValue(c);
            }
            return sum;
        }
    }
}

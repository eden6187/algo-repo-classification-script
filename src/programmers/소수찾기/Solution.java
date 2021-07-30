package programmers.소수찾기;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution("011"));
    }

    String numbers;
    List<Character> list = new ArrayList<>();
    Set<Integer> primeSet = new HashSet<>();

    public int solution(String numbers) {
        this.numbers = numbers;
        dfs(0);
        return primeSet.size();
    }

    boolean[] isUsed = new boolean[10];

    public void dfs(int curDepth){
        if(curDepth > numbers.length()){
            return;
        }

        // 소수 판별 부분.
        String str = makeString(this.list);
        if(!str.equals("")){
            int n = Integer.parseInt(str);
            if(isPrime(n)) primeSet.add(n);
        }
//        for (Character character : list) {
//            System.out.print(character);
//        }
//        System.out.println();

        for(int i = 0 ; i < this.numbers.length(); i++){
            if(isUsed[i]) continue;;
            isUsed[i] = true;
            list.add(this.numbers.charAt(i));
            dfs(curDepth + 1);
            list.remove(list.size()-1);
            isUsed[i] = false;
        }
        // 조합을 이용해서 계산

    }

     boolean isPrime(int n){
        if(n == 1 || n == 0) return false;
        for(int i = 2; i <= n-1; i++){
            if(n % i == 0) return false;
        }
        return true;
     }

     String makeString(List<Character> chars){
        StringBuilder stringBuilder = new StringBuilder("");
         for (Character aChar : chars) {
             stringBuilder.append(aChar);
         }
         return stringBuilder.toString();
     }
}

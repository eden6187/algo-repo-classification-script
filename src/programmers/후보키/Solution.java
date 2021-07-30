package programmers.후보키;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        String[][] relation = {
                {"100","ryan","music","2"},
                {"200","apeach","math","2"},
                {"300","tube","computer","3"},
                {"400","con","computer","4"},
                {"500","muzi","music","3"},
                {"600","apeach","music","2"}
        };
        Solution solution = new Solution();
        System.out.println(solution.solution(relation));
    }

    public int solution(String[][] relation) {
        int answer = 0;
        List<Integer> uniqueKeySet = findUniqueness(relation);
        List<Integer> answerList = findMinimality(uniqueKeySet);
        return answerList.size();

    }

    /*
    Point4 - Iterator를 이용하여 최소성을 만족하지 않는 key들 제거하기
     */
   public List<Integer> findMinimality(List<Integer> uniquenessOk){
        List<Integer> minimalityOk = new ArrayList<>();

        while(!uniquenessOk.isEmpty()){
            int cur = uniquenessOk.remove(0);
            minimalityOk.add(cur);
            Iterator<Integer> iterator = uniquenessOk.iterator();

            while (iterator.hasNext()){
                if((iterator.next() & cur) == cur) iterator.remove();
            }
        }

        return minimalityOk;
    }

    /*
    Point2 - 선택한 Col들이 Uniqueness를 만족하는지 판단.
     */
    boolean isUnique(int keySet, String[][] relation){
        int col = relation[0].length;

        List<Integer> list = new ArrayList<>();
        Set<String> set = new HashSet<>();

        for(int i  = 0 ; i < col; i++){
            if(((keySet >> i) & 1) == 1) list.add(i);
        }

        for (String[] tuple : relation) {
            StringBuilder str = new StringBuilder("");
            for (Integer idx : list) {
                str.append(tuple[idx]);
            }
            if(!set.add(str.toString())) return false;
        }

        return true;
    }

    List<Integer> findUniqueness(String[][] relation){
        ArrayList<Integer> uniquenessOks = new ArrayList<>();
        int col = relation[0].length; // col의 갯수
        int possibleCnt = 1 << col;

        /*
        Point 1 - col 갯수에 따른 가능한 조합의 갯수
        각각의 col을 key의 attribute로 포함할지, 포함하지 않을지를 고려 해야 하기 때문에

        Ex)
            col : 0 ------> 2^0 == 1 << 0 == 1(2진수)
            col : 1 ------> 2^1 == 1 << 1 == 10(2진수)
            col : 2 ------> 2^2 == 1 << 2 == 100(2진수)
            col : 3 ------> 2^3 == 1 << 3 == 1000(2진수)
         */
        for(int i = 1 ; i < possibleCnt ; i++){
            int keySet = i;
            if(isUnique(keySet, relation)) {
                uniquenessOks.add(keySet);
            }
        } // 유일성을 만족하는 것들을 찾아내고

        /*
        Point3 - 선택한 것들을 내가 원하는 기준으로 정렬 할 수 있는가?
        이 문제에서는 2진수로 표현했을때 1의 갯수가 적은 순으로 integer 값을 정렬 할 수 있는 능력이 필요하다.
        즉, Comparator를 평소에 원하는 입맛대로 사용 할 수 있는 능력이 있어야 한다.
         */
        // 해당 keySet들을 1의 갯수를 적게 가진 순으로 정렬한다.
        Collections.sort(uniquenessOks, new Comparator<Integer>() {

            int getCntOf1(int n){
                int cnt = 0;
                while (n != 0){
                    if((n & 1) == 1)  cnt++;
                    n = n >> 1;
                }
                return cnt;
            }

            @Override
            public int compare(Integer o1, Integer o2) {
                 int cnt1 = getCntOf1(o1);
                 int cnt2 =  getCntOf1(o2);
                 return Integer.compare(cnt1, cnt2);
            }
        });

        return uniquenessOks;
    }
}

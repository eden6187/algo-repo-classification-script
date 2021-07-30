package programmers.후보키;

import java.util.ArrayList;
import java.util.HashSet;

// 잘못된 접근 방식
// 백트래킹을 사용했음
// 만약, [a,b,c,d]중에서
// [b,c,d]가 유일성을 만족했더라도
// [b,d]도 유일성을 만족하지 않을 수 있기 때문에 이 풀이는 잘못된 풀이이다.

public class WrongBacktracking {
    String[][] relation;
    ArrayList<Integer> chosenCol = new ArrayList<>();
    int answer = 0;

    public static void main(String[] args) {
        WrongBacktracking solution = new WrongBacktracking();
        String[][] relation = {
                {"100","ryan","music","2"},
                {"200","apeach","math","2"},
                {"300","tube","computer","3"},
                {"400","con","computer","4"},
                {"500","muzi","music","3"},
                {"600","apeach","music","2"}
        };
        System.out.println(solution.solution(relation));
    }

    public int solution(String[][] relation) {
        this.relation = relation;
        dfs(0,relation[0].length,0);
        return this.answer;
    }

    public void dfs(int curDepth, int maxDepth, int curIdx){

        if(curDepth == maxDepth) return;

        // for branching
        if(canBeCandidate()) {
            System.out.println(chosenCol);
            answer++;
            return;
        }

        // for dfs
        for(int i = curIdx; i < maxDepth; i++){
            chosenCol.add(i);
            dfs(curDepth+1, maxDepth, i + 1);
            chosenCol.remove(chosenCol.size() - 1);
        }
    }

    boolean canBeCandidate(){
        if(chosenCol.size() == 0) return false;

        int rowCnt = this.relation.length;
        HashSet<String> relations = new HashSet<>();

        for (String[] strings : relation) {
            StringBuilder str = new StringBuilder();
            for (Integer idx : chosenCol) {
                str.append(strings[idx]);
            }
            relations.add(str.toString());
        }

        return relations.size() == rowCnt;
    }

}


package programmers.모의고사;
import java.util.*;

class Solution {
    final int[][] SP = {
            {1, 2, 3, 4, 5},
            {2, 1, 2, 3, 2, 4, 2, 5},
            {3, 3, 1, 1, 2, 2, 4, 4, 5, 5}
    };

    public int[] solution(int[] answers) {
        int[] spAnswerCnts = new int[3];

        int problemCnt = answers.length;

        for(int i = 0 ; i < problemCnt; i++){
            int ans = answers[i];
            for(int n = 0; n < 3; n++){
                // n번 수포자가 찍은 답
                int size = SP[n].length;
                if(SP[n][i % size] == ans) spAnswerCnts[n]++;
            }
        }

        int maxCnt = Integer.MIN_VALUE;

        for(int c : spAnswerCnts){
            if(c >= maxCnt) maxCnt = c;
        }

        ArrayList<Integer> ansList = new ArrayList<>();

        for(int i = 0 ; i < 3; i++){
            if(spAnswerCnts[i] == maxCnt) ansList.add(i+1);
        }


        int[] answer = new int[ansList.size()];
        for(int i = 0 ; i < ansList.size(); i++){
            answer[i] = ansList.get(i);
        }

        return answer;
    }
}
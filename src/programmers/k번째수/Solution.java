package programmers.k번째수;

import java.util.Arrays;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        int[] arrForProcess = new int[101];

        int ansCnt = 0;
        for(int[] command : commands){
            // 0-index로 변경
            int s = command[0] - 1;
            int e = command[1] - 1;
            int k = command[2] - 1;

            int size = e - s + 1;

            for(int i = 0; i < size; i++){
                arrForProcess[i] = array[s + i];
            }

            Arrays.sort(arrForProcess,0,size);
            answer[ansCnt] = arrForProcess[k];
        }
        return answer;
    }
}

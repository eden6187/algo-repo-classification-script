package programmers.단어변환;

class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.solution("hit", "cog", new String[]{"hot", "dot", "dog", "lot", "log", "cog"});
        solution.solution("hit", "cog", new String[]{"hot", "dot", "dog", "lot", "log"});
    }

    String[] words;
    boolean[] isUsed;
    int minDepth = Integer.MAX_VALUE;

    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        this.words = words;
        isUsed = new boolean[words.length];
        minDepth = Integer.MAX_VALUE;

        dfs(0, begin, target);

//        System.out.println(minDepth == Integer.MAX_VALUE ? 0 : minDepth);

        return minDepth == Integer.MAX_VALUE ? 0 : minDepth;

    }

    boolean isPossible = true;

    public void dfs(int curDepth ,String curString, String target){

        if(!isPossible) return;

        if(curDepth == words.length && !curString.equals(target)) {
            isPossible = false;
            return;
        }

        if(curString.equals(target)) {
            minDepth = Math.min(minDepth, curDepth);
            return;
        }

        for(int i = 0 ; i < words.length; i++){
            if(isUsed[i]) continue;
            if(!canChange(curString, words[i])) continue;
            isUsed[i] = true;
            dfs(curDepth + 1, words[i], target);
            isUsed[i] = false;
        }
    }

    public boolean canChange(String a, String b){
        int diff = 0;

        for(int i = 0 ; i < a.length(); i++){
            if(a.charAt(i) != b.charAt(i)) diff++;
            if(diff > 1) return false;
        }

        return true;
    }
}

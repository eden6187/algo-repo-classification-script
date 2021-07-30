package programmers.괄호회전;

import java.util.Stack;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.isRight(solution.rotate("[](){}" , 1)));
        System.out.println(solution.isRight(solution.rotate("[](){}" , 2)));
        System.out.println(solution.isRight(solution.rotate("[](){}" , 3)));
        System.out.println(solution.isRight(solution.rotate("[](){}" , 4)));
        System.out.println(solution.isRight(solution.rotate("[](){}" , 5)));
        System.out.println(solution.isRight(solution.rotate("[](){}" , 6)));

    }
    public int solution(String s) {
        int answer = 0;

        for(int i = 0 ; i < s.length(); i++){
            if(isRight(rotate(s,i))) answer++;
        }

        if(answer != 0) return answer;
        else return -1;
    }

    public String rotate(String s, int dist){
        dist = dist % s.length();
        StringBuilder stringBuilder = new StringBuilder("");
        char[] chars = s.toCharArray();
        for(int i = 0 ; i < chars.length; i++){
            int cur = (i + dist) % chars.length;
            stringBuilder.append(chars[cur]);
        }
        return stringBuilder.toString();
    }

    boolean isRight(String s){
        Stack<Character> stack = new Stack<>();

        for(int i = 0 ; i < s.length(); i++){
            char c = s.charAt(i);

            if(c == '(' || c == '[' || c == '{') {
                stack.push(c);
                continue;
            } // 여는 괄호

            //닫는 괄호 나왔는데 스택 비어 있으면 얼리 리턴
            if(stack.isEmpty()) return false;

            char curTop = stack.peek();

            switch (curTop){
                case '{' :
                    if(c != '}') return false;
                    break;
                case '(' :
                    if(c != ')') return false;
                    break;
                case '[' :
                    if(c != ']') return false;
                    break;
            }

            stack.pop();
        }

        return stack.isEmpty();
    }
}

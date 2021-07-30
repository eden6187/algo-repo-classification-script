package programmers.완주목록;
import java.util.*;
class Solution {
    HashMap<String, Integer> map = new HashMap<>();

    public String solution(String[] participant, String[] completion) {
//        for (String s : participant) {
//            if(map.containsKey(s)){
//                int cnt = map.get(s);
//                map.put(s,++cnt);
//            }else{
//                map.put(s, 1);
//            }
//        }
//
//        for (String s : completion) {
//            int cnt  = map.get(s);
//            map.put(s,--cnt);
//        }
//
//        for (Map.Entry<String, Integer> entry : map.entrySet()) {
//            if(entry.getValue() > 0) return entry.getKey();
//        }

        Arrays.sort(participant);
        Arrays.sort(completion);

        for(int i = 0 ; i < completion.length; i++){
            if(!participant[i].equals(completion[i])) return participant[i];
        }

        return "";
    }
}
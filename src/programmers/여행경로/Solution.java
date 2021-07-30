package programmers.여행경로;

import java.util.*;

// package programmers.여행경로;


public class Solution {

    public static void main(String[] args) {
        Solution sol = new Solution();
//        sol.solution(new String[][]{{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL","SFO"}});
//        sol.solution(new String[][]{{"ICN", "JFK"}, {"JFK", "HND"}, {"HND", "ICN"}});
          sol.solution(new String[][]{{"ICN", "SFO"}, {"SFO", "ICN"}, {"ICN", "SFO"}, {"SFO", "QRE"}});

    }

    int routeLen;
    String[][] tickets;
    HashMap<String, ArrayList<Ticket>> map = new HashMap<>();
    ArrayList<String> ans = new ArrayList<>();
    String[] ansArr = {};

    public String[] solution(String[][] tickets) {
        String[] answer = {};
        this.routeLen = tickets.length + 1;
        this.tickets = tickets;

        for (String[] t : tickets) {
            String dept = t[0];
            String dest = t[1];
            Ticket ticket = new Ticket(false, dest);
            map.putIfAbsent(dept, new ArrayList<>());
            ArrayList<Ticket> list = map.get(dept);
            list.add(ticket);
        }

        for (Map.Entry<String, ArrayList<Ticket>> entry : map.entrySet()){
            Collections.sort(entry.getValue());
             List<Ticket> list = entry.getValue();

//             for (Ticket ticket : list) {
//                 System.out.println(ticket);
//             }
        }

        ans.add("ICN");
        dfs(0,"ICN");

        return this.ansArr;

    }

    boolean isFind = false;

    void dfs(int curDepth, String dept){

        if(curDepth == routeLen-1) {
            if(isFind) return;
            makeArr();
            isFind = true;
            return;
        }

//        System.out.println(dept);

        List<Ticket> dest = map.get(dept);

        if(dest == null) return;

        for (Ticket ticket : dest) {
            if(ticket.isUsed) continue;
            this.ans.add(ticket.dest);
            ticket.isUsed = true;
            dfs(curDepth + 1, ticket.dest);
            ticket.isUsed = false;
            this.ans.remove(this.ans.size()-1);
        }
    }

    void makeArr(){
        this.ansArr = new String[ans.size()];
        int idx = 0;
        for (String s : ans) {
            this.ansArr[idx++] = s;
        }
    }

    class Ticket implements Comparable<Ticket>{
        boolean isUsed;
        String dest;

        public Ticket(boolean isUsed, String dest) {
            this.isUsed = isUsed;
            this.dest = dest;
        }

        @Override
        public int compareTo(Ticket o) {
            return this.dest.compareTo(o.dest);
        }

        @Override
        public String toString() {
            return "Ticket{" +
                    "isUsed=" + isUsed +
                    ", dest='" + dest + '\'' +
                    '}';
        }
    }
}
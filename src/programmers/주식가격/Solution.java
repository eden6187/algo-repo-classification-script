package programmers.주식가격;

import java.util.Stack;

public class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        int size = prices.length;
        Stack<StockInfo> stk = new Stack<>();

        for(int curIdx = 0 ; curIdx < size ; curIdx++){
            int currentPrice = prices[curIdx];

            while(!stk.isEmpty() && stk.peek().price > currentPrice){
                int poppedStockIdx = stk.pop().idx;
                answer[poppedStockIdx] = curIdx - poppedStockIdx;
            }

            stk.push(new StockInfo(curIdx, currentPrice));
        }

        while(!stk.isEmpty()){
            StockInfo stockInfo = stk.pop();
            int poppedStockIdx = stockInfo.idx;
            answer[poppedStockIdx] = size - poppedStockIdx - 1;
        }

        return answer;
    }

    public class StockInfo{
        int idx;
        int price;

        public StockInfo(int idx, int price) {
            this.idx = idx;
            this.price = price;
        }
    }
}

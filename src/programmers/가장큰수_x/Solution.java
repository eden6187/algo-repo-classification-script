package programmers.가장큰수_x;

class Solution {

    public static void main(String[] args) {
        int[][] arr = {{1, 4}, {3, 4}, {3, 10}};
        Solution sol = new Solution();
        int[] ans = sol.solution(arr);
        for (int an : ans) {
            System.out.println(an);
        }
    }


    public int[] solution(int[][] v) {
        int[] answer = {};

        int[] x = {Integer.MAX_VALUE, Integer.MIN_VALUE};
        int[] y = {Integer.MIN_VALUE, Integer.MAX_VALUE};

        for(int i = 0 ; i < 3; i++){
            int curX = v[i][0];
            int curY = v[i][1];

            if(curX <= x[0]) x[0] = curX;
            if(curX >= x[1]) x[1] = curX;
            if(curY <= y[1]) y[1] = curY;
            if(curY >= y[0]) y[0] = curY;
        }

        for(int xx = 0 ; xx < 2; xx++){
            for(int yy = 0; yy < 2; yy++){

                int[] cor = new int[]{x[xx],y[yy]};

                boolean isExist = false;

                for(int l = 0 ; l < 3; l++){
                    if(isSame(cor, v[l])) {
                        isExist = true;
                        break;
                    }
                }

                if(!isExist) return cor;
            }
        }

        return answer;
    }

    boolean isSame(int[] a, int[] b){
        for(int i = 0 ; i < 2; i++){
            if(a[i] != b[i]) return false;
        }
        return true;
    }

}




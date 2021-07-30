package programmers.프렌즈블록;

import java.util.*;
class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int ans = solution.solution(6,6, new String[]{
                "TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"
        });
        System.out.println(ans);
    }

    class Coordinate{
        int x;
        int y;
        Coordinate(int x, int y){
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Coordinate{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    final int[] dx = {0,1,1};
    final int[] dy = {1,1,0};

    public int solution(int m, int n, String[] b) {
        int answer = 0;
        ArrayList<Coordinate> cors = new ArrayList<>();

        char[][] board = makeBoard(b);
        int blkCount = 0;

        while(true){
            cors = new ArrayList<>();

            for(int i = 0 ; i < m; i++){
                for(int j = 0 ; j < n; j++){
                    if(check(i,j,board)) cors.add(new Coordinate(i,j));
                }
            }

            int blk = clearBoard(board, cors);


//            for(int i = 0 ; i < m; i++){
//                for(int j = 0 ; j < n; j++){
//                    System.out.print(board[i][j]);
//                }
//                System.out.println();
//            }
//            System.out.println("After Clear");

            blkCount += blk;
            downBoard(board,m,n);

//             for(int i = 0 ; i < m; i++){
//                 for(int j = 0 ; j < n; j++){
//                     System.out.print(board[i][j]);
//                 }
//                 System.out.println();
//             }
//            System.out.println("After Move");

            if(blk == 0) return blkCount;
        }

    }

    boolean oob(int x, int y, int xSize, int ySize){
        if(x < 0 || x >= xSize || y < 0 || y >= ySize) return true;
        else return false;
    }

    boolean check(int x, int y, char[][] board){
        if(board[x][y] == '-') return false;
        int xSize = board.length;
        int ySize = board[0].length;

        char cur = board[x][y];
        for(int i = 0 ; i < 3; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            char nChar = board[nx][ny];

            if(oob(x,y,xSize,ySize)) return false;
            if(nChar != cur) return false;
        }

        return true;
    }

    char[][] makeBoard(String[] board){
        int xSize = board.length;
        int ySize = board[0].length();

        char[][] newBoard = new char[xSize + 1][ySize + 1];

        for(int i = 0 ; i < xSize; i++){
            for(int j = 0 ; j < ySize; j++){
                newBoard[i][j] = board[i].charAt(j);
            }
        }

        return newBoard;
    }

    int clearBoard(char[][] board, ArrayList<Coordinate> cors){
        int cnt = 0;
        for(Coordinate cor : cors){
            int ltx = cor.x;
            int lty = cor.y;

            if(board[ltx][lty] != '-'){
                board[ltx][lty] = '-';
                cnt++;
            }

            for(int i = 0; i < 3; i++){
                if(board[ltx + dx[i]][lty + dy[i]] != '-'){
                    board[ltx + dx[i]][lty + dy[i]] = '-';
                    cnt++;
                }
            }
        }
        return cnt;
    }

    void downBoard(char[][] board, int m, int n){
        for(int i = 0 ; i < n; i++){
            // 각각의 컬럼에 대하여
            Queue<Character> q = new LinkedList<>();

            for(int j = m-1; j >= 0; j--){
                char cur = board[j][i];
                if(cur != '-') q.offer(cur);
            }

            int x = m-1;
            int y = i;

            while(!q.isEmpty()){
                board[x--][y] = q.poll();
            }

            if(x < 0) continue;

            for(int k = x ; k >= 0; k--){
                board[k][y] = '-';
            }
        }
    }
}
package programmers.implementation.p160585;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        String[][] boards = {
                {"O.X", ".O.", "..X"},
                {"OOO", "...", "XXX"},
                {"...", ".X.", "..."},
                {"...", "...", "..."}
        };
        int[] answers = {1, 0, 0, 1};
        for (int i = 0; i < boards.length; i++) {
            System.out.println(s.solution(boards[i]) == answers[i]);
        }
    }

    String[] board;
    int cntO, cntX;

    public int solution(String[] board) {
        this.board = board;
        cnt();
        if (cntO != cntX && cntO != cntX + 1) return 0;
        if (isWin('O') && cntO != cntX + 1) return 0;
        if (isWin('X') && cntO != cntX) return 0;
        return 1;
    }

    public void cnt() {
        this.cntO = 0;
        this.cntX = 0;
        for (String r : board) {
            for (int i = 0; i < r.length(); i++) {
                if (r.charAt(i) == 'O') cntO++;
                else if (r.charAt(i) == 'X') cntX++;
            }
        }
    }

    public boolean isWin(char c) {
        boolean dtlbr = true, dtrbl = true;
        for (int i = 0; i < board.length; i++) {
            if (board[i].charAt(i) != c) dtlbr =  false;
            if (board[i].charAt(2 - i) != c) dtrbl = false;
            boolean ri = true, cj = true;
            for (int j = 0; j < board[i].length(); j++) {
                if (board[i].charAt(j) != c) ri = false;
                if (board[j].charAt(i) != c) cj = false;
            }
            if (ri || cj) return true;
        }
        return dtlbr || dtrbl;
    }
}
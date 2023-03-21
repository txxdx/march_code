class Solution {
    boolean[][] row = new boolean[9][9];
    boolean[][] col = new boolean[9][9];
    boolean[][][] cell = new boolean[3][3][9];

    public boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < 9; i ++) {
            for (int j = 0; j < 9; j ++) {
                if (board[i][j] != '.') {
                    int index = board[i][j] - '1';
                    if (row[i][index] || col[j][index] || cell[i/3][j/3][index]) {
                        return false;
                    }

                    row[i][index] = col[j][index] = cell[i/3][j/3][index] = true;
                }
            }
        }

        return true;
    }
}
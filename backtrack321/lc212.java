//给定一个 m x n 二维字符网格 board 和一个单词（字符串）列表 words， 返回所有二维网格上的单词 。 
//
// 单词必须按照字母顺序，通过 相邻的单元格 内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使
//用。 
//
// 
//
// 示例 1： 
// 
// 
//输入：board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f",
//"l","v"]], words = ["oath","pea","eat","rain"]
//输出：["eat","oath"]
// 
//
// 示例 2： 
// 
// 
//输入：board = [["a","b"],["c","d"]], words = ["abcb"]
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// m == board.length 
// n == board[i].length 
// 1 <= m, n <= 12 
// board[i][j] 是一个小写英文字母 
// 1 <= words.length <= 3 * 10⁴ 
// 1 <= words[i].length <= 10 
// words[i] 由小写英文字母组成 
// words 中的所有字符串互不相同 
// 
//
// Related Topics 字典树 数组 字符串 回溯 矩阵 👍 753 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    Set<String> set = new HashSet<>();
    List<String> ans = new ArrayList<>();
    int m, n;
    char[][] board;
    boolean[][] visited;
    int[][] directions = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    int maxLength = Integer.MIN_VALUE;
    public List<String> findWords(char[][] board, String[] words) {
        if (board == null || board.length == 0 || words == null || words.length == 0) {
            return ans;
        }

        this.board = board;
        m = board.length;
        n = board[0].length;
        visited = new boolean[m][n];
        for(String word: words) {
            set.add(word);
            maxLength = Math.max(maxLength, word.length());
        }

        StringBuilder path = new StringBuilder();
        for (int i = 0; i < m; i ++) {
            for (int j = 0; j < n; j ++) {
                visited[i][j] = true;
                path.append(board[i][j]);
                dfs(i, j, path);
                visited[i][j] = false;
                path.deleteCharAt(path.length() - 1);
            }
        }

        return ans;
    }

    private void dfs(int x, int y, StringBuilder path) {
        if (path.length() > maxLength) return;
        if (set.contains(path.toString())) {
            ans.add(path.toString());
            set.remove(path.toString());
        }

        for (int i = 0; i < 4; i ++) {
            int nextX = directions[i][0] + x;
            int nextY = directions[i][1] + y;

            if (nextX < 0 || nextX >= m || nextY < 0 || nextY >= n || visited[nextX][nextY]) {
                continue;
            }

            visited[nextX][nextY] = true;
            path.append(board[nextX][nextY]);
            dfs(nextX, nextY, path);
            visited[nextX][nextY] = false;
            path.deleteCharAt(path.length() - 1);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

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
    char[][] board;
    int m, n;
    Set<String> set = new HashSet<>();
    List<String> ans = new LinkedList<>();
    boolean[][] visited;
    int maxLength = Integer.MIN_VALUE;

    public List<String> findWords(char[][] board, String[] words) {
        if (isBoardEmpty(board) || isWordsEmpty(words)) {
            return ans;
        }

        this.board = board;
        m = board.length;
        n = board[0].length;
        visited = new boolean[m][n];
        for (String w: words) {
            set.add(w);
            maxLength = Math.max(maxLength, w.length());
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

    private boolean isBoardEmpty(char[][] board) {
        return board == null || board.length == 0 || board[0].length == 0;
    }

    private boolean isWordsEmpty(String[] words) {
        return words == null || words.length == 0;
    }

    private void dfs(int x, int y, StringBuilder path) {
        if (path.length() > maxLength) {
            return;
        }

        if (set.contains(path.toString())) {
            ans.add(path.toString());
            set.remove(path.toString());
        }

        int[][] dirs = new int[][]{{ -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 }};
        for (int i = 0; i < 4; i ++) {
            int nextX = x + dirs[i][0];
            int nextY = y + dirs[i][1];

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
    char[][] board;
    int m, n;
    Set<String> set = new HashSet<>();
    List<String> ans = new LinkedList<>();
    boolean[][] visted;

    class TrieNode {
        String word;
        TrieNode[] next = new TrieNode[26];
    }

    TrieNode root = new TrieNode();

    void insert(String w) {
        TrieNode cur = root;
        for (int i = 0; i < w.length(); i ++) {
            int t = w.charAt(i) - 'a';
            if (cur.next[t] == null) {
                cur.next[t] = new TrieNode();
            }
            cur = cur.next[t];
        }
        cur.word = w;
    }

    public List<String> findWords(char[][] board, String[] words) {
        this.board = board;
        m = board.length;
        n = board[0].length;
        visted = new boolean[m][n];

        for (String w: words) {
            insert(w);
        }

        for (int i = 0; i < m; i ++) {
            for (int j = 0; j < n; j ++) {
                int t = board[i][j] - 'a';
                if (root.next[t] != null) {
                    visted[i][j] = true;
                    dfs(i, j, root.next[t]);
                    visted[i][j] = false;
                }
            }
        }
        for (String w: set) {
            ans.add(w);
        }
        return ans;
    }

    private void dfs(int x, int y, TrieNode node) {
        if (node.word != null) {
            set.add(node.word);
        }

        int[][] dirs = new int[][]{{ -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 }};
        for (int i = 0; i < 4; i ++) {
            int nextX = x + dirs[i][0];
            int nextY = y + dirs[i][1];

            if (nextX < 0 || nextX >= m || nextY < 0 || nextY >= n || visted[nextX][nextY]) {
                continue;
            }

            int t = board[nextX][nextY] - 'a';
            if (node.next[t] != null) {
                visted[nextX][nextY] = true;
                dfs(nextX, nextY, node.next[t]);
                visted[nextX][nextY] = false;
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

class Solution {

    class TrieNode {
        String word;
        TrieNode[] next = new TrieNode[26];
    }

    class Trie {
        private TrieNode root = new TrieNode();

        public void insert(String s) {
            TrieNode node = root;
            for (int i = 0; i < s.length(); i ++) {
                int t = s.charAt(i) - 'a';
                if (node.next[t] == null) {
                    node.next[t] = new TrieNode();
                }
                node = node.next[t];
            }
            node.word = s;
        }

        public List<String> findWords(char[][] board, String[] words) {
            for (String w: words) {
                insert(w);
            }

            Set<String> set = new HashSet<>();
            int m = board.length, n = board[0].length;
            for (int i = 0; i < m; i ++) {
                for (int j = 0; j < n; j ++) {
                    dfs(i, j, root, board, set);
                }
            }

            List<String> ans = new ArrayList<>();
            for (String w: set) {
                ans.add(w);
            }

            return ans;
        }

        private void dfs(int x, int y, TrieNode node, char[][] board, Set<String> set) {
            if (x < 0 || x >= board.length || y < 0 || y >= board[0].length || board[x][y] == '#' || node == null) {
                return;
            }

            char c = board[x][y];
            int t = c - 'a';
            node = node.next[t];
            if (node == null) return;

            if (node.word != null) {
                set.add(node.word);
            }

            board[x][y] = '#';

            dfs(x + 1, y, node, board, set);
            dfs(x - 1, y, node, board, set);
            dfs(x, y + 1, node, board, set);
            dfs(x, y - 1, node, board, set);

            board[x][y] = c;
        }
    }

    public List<String> findWords(char[][] board, String[] words) {
        Trie trie = new Trie();
        return trie.findWords(board, words);
    }
}

//runtime:208 ms
//memory:41.6 MB
